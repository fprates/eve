package org.eve.view;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eve.main.EVE;
import org.eve.main.EveAPI;
import org.springframework.context.MessageSource;

/**
 * Assistente para formulário de entrada
 * @author francisco.prates
 *
 */
public class Form {
    private Map<String, FormComponent> fields;
    private MessageSource messages;
    private Locale locale;
    private DateFormat dateformat;
    private EveAPI system;
    
    public Form(String id) {
        fields = new LinkedHashMap<String, FormComponent>();
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    /**
     * Define localização
     * @param locale
     */
    public final void setLocale(Locale locale) {
        this.locale = locale;
        dateformat = DateFormat.getDateInstance(DateFormat.SHORT, locale);
    }
    
    /**
     * Defie propriedades do campo
     * @param id
     * @param properties
     */
    public final void setProperties(String id, int properties) {
        FormComponent component = fields.get(id);
        
        if ((properties & EVE.nocase) == EVE.nocase)
            component.setNocase(true);
    }
    
    /**
     * Define mensagens do sistema
     * @param messages
     */
    public final void setMessages(MessageSource messages) {
        this.messages = messages;
    }
    
    /**
     * Ajusta valor do campo caractere do formulário
     * @param field
     * @param value
     */
    public final void setString(String field, String value) {
        FormComponent component = fields.get(field);
        
        if (!component.isNocase() && value != null)
            value = value.toUpperCase(locale);
        
        component.setText((value == null)? "" : value);
    }
    
    /**
     * Ajusta valor do campo inteiro do formulário
     * @param field
     * @param value
     */
    public final void setInt(String field, int value) {
        FormComponent component = fields.get(field);
        
        switch (component.getType()) {
        case EVE.text:
            setString(field, Integer.toString(value));
            break;
            
        case EVE.combo:
            component.setText(component.getOption(value));
            break;
        }
    }
    
    /**
     * Ajusta valor de ponto flutuante para campo
     * @param field
     * @param value
     */
    public final void setFloat(String field, float value) {
        setString(field, Float.toString(value));
    }
    
    /**
     * Ajusta valor data para campo
     * @param field
     * @param date
     */
    public final void setDate(String field, Date date) {        
        if (date == null)
            setString(field, "");
        else 
            setString(field, dateformat.format(date));
    }
    
    /**
     * Ajusta valor hora para campo
     * @param field
     * @param time
     */
    public final void setTime(String field, Time time) {
        if (time == null)
            setString(field, "");
        else
            setString(field, time.toString());
    }
    
    /**
     * 
     * @param system
     */
    public final void setSystem(EveAPI system) {
        this.system = system;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * 
     * @param message
     * @return
     */
    private final String getMessage(String message) {
        return messages.getMessage(message, null, message, locale);        
    }
    
    /**
     * Retorna componente de formulário
     * @param id
     * @return
     */
    public final FormComponent get(String id) {
        return fields.get(id);
    }
    
    /**
     * Retorna valor do campo caractere do formulário
     * @param field
     * @return
     */
    public final String getString(String field) {
        return fields.get(field).getText();
    }
    
    /**
     * Retorna valor do campo caractere do formulário,
     * em formato "like" para seleção
     * @param field
     * @return
     */
    public final String getStringLike(String field) {
        String value = getString(field).replace("*", "%");
        
        return (value.equals(""))?"%":value;
    }
    
    /**
     * Retorna valor do campo inteiro do formulário
     * @param field
     * @return
     */
    public final int getInt(String field) {
        String test;
        int test_;
        FormComponent component = fields.get(field);
        
        switch (component.getType()) {
        case EVE.text:
            test = getString(field);
            try {
                return test.equals("")? 0:Integer.parseInt(test);                
            } catch (NumberFormatException ex) {
                system.setMessage(EVE.error, getMessage("invalid.int.format"));
                component.getControl().setFocus();
                
                throw new NumberFormatException();
            }
        
        case EVE.combo:
            test_ = ((Combo)component.getControl()).getSelectionIndex();
            return (test_ < 0)? 0:test_;
        }
        
        return 0;
    }
    
    /**
     * Retorna valor de ponto flutuante de um campo
     * @param field
     * @return
     */
    public final float getFloat(String field) {
        String test;
        
        test = getString(field);
        
        try {
            return test.equals("")? 0:Float.parseFloat(test);
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.float.format"));
            fields.get(field).getControl().setFocus();
            
            throw new NumberFormatException();
        }
    }
    
    /**
     * Retorna components do formulário
     * @return
     */
    public final Collection<FormComponent> getComponents() {
        return fields.values();
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * Constrói formulário e inicializa o próximo
     * @param form
     * @param composite
     */
    public final Composite define(Composite container) {
        Label label;
        Text text;
        Combo combo;
        int charw;
        int charh;
        Composite fieldComposite;
        FormComponent component;
        String[] options;
        Composite composite = new Composite(container, SWT.NONE);
        
        composite.setLayout(new GridLayout(2, false));
        
        for(String field : fields.keySet()) {
            component = fields.get(field);
            
            label = new Label(composite, SWT.NONE);
            label.setText(component.getName());
            label.setLayoutData(
                    new GridData(SWT.RIGHT, SWT.CENTER, false, false));

            fieldComposite = new Composite(composite, SWT.NONE);
            
            switch (component.getType()) {
            case EVE.text:
                text = new Text(fieldComposite, SWT.BORDER);
                
                charw = ViewUtils.getCharWidth(text);
                charh = ViewUtils.getCharHeight(text);
                
                text.setSize(text.computeSize(
                        component.getLength() * charw, charh));
                
                component.setControl(text);
                
                break;
            
            case EVE.combo:
                combo = new Combo(fieldComposite, SWT.BORDER);
                combo.setItems(component.getOptions());
                
                options = component.getOptions();
                if (options.length > 0) {
                    combo.setText(options[0]);
                    combo.setItems(options);
                }
                
                charw = ViewUtils.getCharWidth(combo);
                charh = ViewUtils.getCharHeight(combo);
                
                combo.setSize(combo.computeSize(
                        (component.getLength() * charw) + 40, charh));
                
                component.setControl(combo);
                
                break;
            }
        }
        
        return composite;
    }
    
    /**
     * Insere campo com definição de comprimento e visibilidade
     * @param id
     * @param length
     * @param visible
     */
    public final void put(String id, int length, boolean visible) {
        fields.put(id, new FormComponent(
                messages.getMessage(id, null, id, locale), length, visible));
    }

    /**
     * Insere campo com definição de comprimento
     * @param id
     * @param length
     */
    public final void put(String id, int length) {
        fields.put(id, new FormComponent(
                messages.getMessage(id, null, id, locale), length, true));        
    }
    
    /**
     * Insere combobox
     * @param id
     * @param options
     * @param length
     */
    public final void putCombo(String id, String[] options, int length) {
        FormComponent component = new FormComponent(
                messages.getMessage(id, null, id, locale), length, true);
        
        component.setType(EVE.combo);
        component.setOptions(options);
        
        fields.put(id, component);
    }
    
    /**
     * Limpa formulário
     */
    public final void clear() {
        for (FormComponent component : fields.values())
            component.setText("");
    }
    
}
