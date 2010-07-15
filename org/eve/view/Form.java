package org.eve.view;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eve.main.EVE;
import org.springframework.context.MessageSource;

public class Form {
    private Map<String, FormComponent> fields;
    private MessageSource messages;
    private Locale locale;
    
    public Form() {
        fields = new LinkedHashMap<String, FormComponent>();
    }

    /*
     * 
     * Setters
     * 
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
     * Ajusta valor do campo caractere do formulário
     * @param field
     * @param value
     */
    public final void setString(String field, String value) {
        fields.get(field).setText((value == null)?"":value);
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
            System.out.println(field+Integer.toString(value));
            component.setText(component.getOption(value));
            break;
        }
    }
    
    /**
     * 
     * @param field
     * @param value
     */
    public final void setFloat(String field, float value) {
        setString(field, Float.toString(value));
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * 
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
            return test.equals("")? 0:Integer.parseInt(test);
        
        case EVE.combo:
            test_ = ((CCombo)component.getControl()).getSelectionIndex();
            return (test_ < 0)? 0:test_;
        }
        
        return 0;
    }
    
    /**
     * 
     * @param field
     * @return
     */
    public final float getFloat(String field) {
        String test;
        
        test = getString(field);
        
        return test.equals("")? 0:Float.parseFloat(test);
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
        CCombo combo;
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
                combo = new CCombo(fieldComposite, SWT.BORDER);
                combo.setItems(component.getOptions());
                
                options = component.getOptions();
                if (options.length > 0) {
                    combo.setText(options[0]);
                    combo.setItems(options);
                }
                
                charw = ViewUtils.getCharWidth(combo);
                charh = ViewUtils.getCharHeight(combo) + 2;
                
                combo.setSize(combo.computeSize(
                        (component.getLength() * charw) + 35, charh));
                
                component.setControl(combo);
                
                break;
            }
        }
        
        return composite;
    }
    
    /**
     * 
     * @param id
     * @param length
     * @param visible
     */
    public final void put(String id, int length, boolean visible) {
        fields.put(id, new FormComponent(
                messages.getMessage(id, null, locale), length, visible));        
    }

    /**
     * 
     * @param id
     * @param length
     */
    public final void put(String id, int length) {
        fields.put(id, new FormComponent(
                messages.getMessage(id, null, id, locale), length, true));        
    }
    
    /**
     * 
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
