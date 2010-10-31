package org.eve.view;

import java.sql.Time;
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
    private boolean editable;
    private ComboAssist comboassist;
    private Controller controller;
    private EveAPI system;
    private Locale locale;
    private Map<String, Component> fields;
    private MessageSource messages;
    
    public Form(String id) {
        fields = new LinkedHashMap<String, Component>();
        comboassist = new ComboAssist();
        comboassist.setType(EVE.combo);
        comboassist.setControlType(EVE.single);
        editable = true;
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    /**
     * 
     * @param controller
     */
    public final void setController(Controller controller) {
        this.controller = controller;
    }
    
    /**
     * Ajusta valor data para campo
     * @param field
     * @param date
     */
    public final void setDate(String field, Date date) {
        fields.get(field).setDate(date);
    }
    
    /**
     * 
     * @param editable
     */
    public final void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    /**
     * Ajusta valor de ponto flutuante para campo
     * @param field
     * @param value
     */
    public final void setFloat(String field, float value) {
        fields.get(field).setFloat(value);
    }
    
    /**
     * Ajusta valor do campo inteiro do formulário
     * @param field
     * @param value
     */
    public final void setInt(String field, int value) {
        fields.get(field).setInt(value);
    }
    
    /**
     * Ajusta valor do campo inteiro longo
     * @param field
     * @param value
     */
    public final void setLong(String field, long value) {
        fields.get(field).setLong(value);
    }
    
    /**
     * Define localização
     * @param locale
     */
    public final void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    /**
     * Define mensagens do sistema
     * @param messages
     */
    public final void setMessages(MessageSource messages) {
        this.messages = messages;
    }
    
    /**
     * Defie propriedades do campo
     * @param id
     * @param properties
     */
    public final void setProperties(String id, int properties) {
        Component component = fields.get(id);
        
        if ((properties & EVE.nocase) == EVE.nocase)
            component.setNocase(true);
    }
    
    /**
     * Ajusta valor do campo caractere do formulário
     * @param field
     * @param value
     */
    public final void setString(String field, String text) {
        fields.get(field).setString(text);
    }
    
    /**
     * 
     * @param system
     */
    public final void setSystem(EveAPI system) {
        this.system = system;
    }
    
    /**
     * Ajusta valor hora para campo
     * @param field
     * @param time
     */
    public final void setTime(String field, Time time) {
        fields.get(field).setTime(time);
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
     * Retorna valor do campo caractere do formulário
     * @param field
     * @return
     */
    public final String getString(String field) {
        return fields.get(field).getString();
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
     * Retorna valor de ponto flutuante de um campo
     * @param field
     * @return
     */
    public final float getFloat(String field) {
        try {
            return fields.get(field).getFloat();
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.float.format"));
            fields.get(field).getControl().setFocus();            
            throw ex;
        }
    }
    
    /**
     * Retorna valor do campo inteiro do formulário
     * @param field
     * @return
     */
    public final int getInt(String field) {
        Component component = fields.get(field);
        
        try {
            return component.getInt();
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.int.format"));
            component.getControl().setFocus();                
            throw ex;
        }
    }
    
    /**
     * Retorna valor do campo inteiro do formulário
     * @param field
     * @return
     */
    public final long getLong(String field) {
        Component component = fields.get(field);
        
        try {
            return component.getLong();
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.int.format"));
            component.getControl().setFocus();                
            throw ex;
        }
    }
    
    /**
     * Retorna components do formulário
     * @return
     */
    public final Collection<Component> getComponents() {
        return fields.values();
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * Limpa formulário
     */
    public final void clear() {
        for (Component component : fields.values())
            component.clear();
    }
    
    /**
     * 
     */
    public final void commit() {
        Component component;
        
        for (String id: fields.keySet()) {
            component = fields.get(id);
            
            if (!component.isEnabled()) {
                component.getControl().setEnabled(false);
                continue;
            }
            
            component.getControl().setEnabled(editable);
            component.commit();
        }
    }
    
    /**
     * Constrói formulário e inicializa o próximo
     * @param form
     * @param composite
     */
    public final Composite define(Composite container) {
        int charw;
        int charh;
        Combo combo;
        Component component;
        Composite fieldComposite;
        Label label;
        Text text;
        Search search;
        Composite composite = new Composite(container, SWT.NONE);
        
        composite.setLayout(new GridLayout(2, false));
        comboassist.setController(controller);
        comboassist.setTableReference(fields);
        
        for(String field : fields.keySet()) {
            component = fields.get(field);            
            component.setLocale(locale);
            
            label = new Label(composite, SWT.NONE);
            label.setText(component.getTitle());
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
                
                if (component.hasSearch()) {
                    search = new Search(component, fieldComposite);
                    search.setLocale(locale);
                    search.setMessages(messages);
                    search.setController(controller);
                }
                
                break;
                
            case EVE.combo:
                comboassist.setContainer(fieldComposite);
                comboassist.setOptions(component.getOptions());
                comboassist.setLength(component.getLength());
                comboassist.setId(component.getName());
                
                combo = (Combo)comboassist.newInstance();
                
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
        FormComponent component = new FormComponent(id, length, visible);        

        component.setTitle(messages.getMessage(id, null, id, locale));
        fields.put(id, component);
    }

    /**
     * Insere campo com definição de comprimento
     * @param id
     * @param length
     */
    public final void put(String id, int length) {
        put(id, length, true);
    }
    
    /**
     * Insere combobox
     * @param id
     * @param options
     * @param length
     */
    public final void putCombo(String id, String[] options, int length) {
        FormComponent component = new FormComponent(id, length, true);
        
        component.setTitle(messages.getMessage(id, null, id, locale));
        component.setType(EVE.combo);
        if (options == null) {
            options = new String[1];
            options[0] = "";
        }
        
        component.setOptions(options);
        
        fields.put(id, component);
    }
    
    /**
     * Insere campo com ajuda de pesquisa
     * @param id
     * @param length
     */
    public final void putSearch(String id, int length) {
        Component component;
        
        put(id, length);
        component = fields.get(id);
        component.setSearch(true);
    }
}
