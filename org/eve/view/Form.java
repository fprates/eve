package org.eve.view;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
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
     * 
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
        fields.get(field).getTextWidget().setText((value == null)?"":value);
    }
    
    /**
     * Ajusta valor do campo inteiro do formulário
     * @param field
     * @param value
     */
    public final void setInt(String field, int value) {
        setString(field, Integer.toString(value));
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
        return fields.get(field).getTextWidget().getText();
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
        String test = getString(field);
        
        return test.equals("")? 0:Integer.parseInt(test);
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
        int charw = 0;
        int charh = 0;
        Composite fieldComposite;
        FormComponent component;
        Composite composite = new Composite(container, SWT.NONE);
        
        composite.setLayout(new GridLayout(2, false));
        
        for(String field : fields.keySet()) {
            component = fields.get(field);
            
            label = new Label(composite, SWT.NONE);
            label.setText(component.getName());
            label.setLayoutData(
                    new GridData(SWT.RIGHT, SWT.CENTER, false, false));

            fieldComposite = new Composite(composite, SWT.NONE);
            text = new Text(fieldComposite, SWT.BORDER);
            
            if (charw == 0) {
                charw = ViewUtils.getCharWidth(text);
                charh = ViewUtils.getCharHeight(text);             
            }
            
            text.setSize(text.computeSize(
                    component.getLength() * charw, charh));
            component.setText(text);            
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
                messages.getMessage(id, null, locale), length, true));        
    }
    
    /**
     * Limpa formulário
     */
    public final void clear() {
        for (FormComponent component : fields.values())
            component.getTextWidget().setText("");
    }
    
}
