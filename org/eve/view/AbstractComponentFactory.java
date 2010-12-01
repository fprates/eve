package org.eve.view;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eve.main.EVE;
import org.eve.main.EveAPI;
import org.eve.model.AbstractDocument;
import org.springframework.context.MessageSource;

public abstract class AbstractComponentFactory {
    private Locale locale;
    private MessageSource messages;
    private EveAPI system;
    private Map<String, Component> fields;

    public AbstractComponentFactory() {
        fields = new LinkedHashMap<String, Component>();
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
     * @param id
     * @param value
     */
    public final void setFieldValue(String id, Object value) {        
        switch (fields.get(id).getDataType()) {
        case CHAR:
            setString(id, (String)value);
            break;
            
        case DATE:
            setDate(id, (Date)value);
            break;
            
        case TIME:
            setTime(id, (Time)value);
            break;
        
        case INT:
            setInt(id, (Integer)value);
            break;
        
        case LONG:
            setLong(id, (Long)value);
            break;
            
        case FLOAT:
            setFloat(id, (Float)value);
            break;
        }
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
     * 
     * @param field
     */
    public final void setFocus(String field) {
        fields.get(field).getControl().setFocus();
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
     * Define valor inteiro em tabela
     * @param id
     * @param row
     * @param value
     */
    public final void setInt(String field, int index, int value) {
        fields.get(field).setInt(value, index);
    }
    
    /**
     * Define localização
     * @param locale
     */
    public final void setLocale(Locale locale) {
        this.locale = locale;
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
     * 
     * @param status
     * @param id
     */
    public final void setMessage(int status, String id) {
        system.setMessage(status, getMessage(id));
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
    public final void setString(String field, String text) {
        fields.get(field).setString(text);
    }
    
    /**
     * Define string em tabela
     * @param id
     * @param row
     * @param value
     */
    public final void setString(String field, int index, String text) {
        fields.get(field).setString(text, index);
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
    
    /**
     * Define valor do campo hora
     * @param id
     * @param row
     * @param value
     */
    public final void setTime(String field, int index, Time time) {
        fields.get(field).setTime(time, index);
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    protected final Component getComponent(String field) {
        return fields.get(field);
    }
    
    /**
     * Retorna components do formulário
     * @return
     */
    public final Collection<Component> getComponents() {
        return fields.values();
    }
    
    /**
     * 
     * @param field
     * @return
     */
    public final Date getDate(String field) {
        return fields.get(field).getDate();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public final Object getFieldValue(AbstractDocument document, String id) {
        String id_ = document.getName(id);
        
        switch (fields.get(id_).getDataType()) {
        case CHAR:
            return getString(id_);
            
        case DATE:
            return getDate(id_);
            
        case TIME:
            return getTime(id_);
        
        case INT:
            return getInt(id_);
        
        case FLOAT:
            return getFloat(id_);
            
        default:
            return null;
        }
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
     * Retorna valor do campo inteiro
     * @param id coluna
     * @param row linha
     * @return conteúdo inteiro
     */
    public final int getInt(String field, int row)
        throws NumberFormatException {
        String value;
        int value_;
        
        switch (fields.get(field).getType()) {
        case EVE.text:
            value = getString(field, row);            
            return (value.equals(""))? 0 : Integer.parseInt(value);
            
        case EVE.ccombo:
            value_ = ((CCombo)fields.get(field).getControl(row)).getSelectionIndex();            
            return (value_ == -1)? 0 : value_;
            
        default:            
            return 0;
        }
    }
    
    /**
     * 
     * @return
     */
    protected final Locale getLocale() {
        return locale;
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
     * 
     * @param message
     * @return
     */
    protected final String getMessage(String message) {
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
     * Define valor do campo string
     * @param id
     * @param row
     * @return
     */
    public final String getString(String field, int row) {
        for (String id_ : fields.keySet())
            if (id_.equals(field))
                return fields.get(fields).getString(row);
        
        return "";
        
    }
    
    /**
     * 
     * @return
     */
    protected final EveAPI getSystem() {
        return system;
    }
    
    /**
     * 
     * @param field
     * @return
     */
    public final Time getTime(String field) {
        return fields.get(field).getTime();
    }
    
    /**
     * Retorna valor do campo hora
     * @param id
     * @param row
     * @return
     */
    public final Time getTime(String field, int row) {
        String value = getString(field, row);
        int len;
            
        if (value.equals(""))
            return Time.valueOf("00:00:00");
        
        value = value.replace(":", "");
        len = value.length(); 
        if ((len % 2) != 0) {
            setMessage(EVE.error, getMessage("invalid.time.format"));
            fields.get(field).getControl(row).setFocus();
            throw new IllegalArgumentException();
        }
        
        if ((len != 4) && (len != 6)) {
            setMessage(EVE.error, getMessage("invalid.time.format"));
            fields.get(field).getControl(row).setFocus();
            throw new IllegalArgumentException();
        }
        
        if (len == 4)
            value = value.concat("00");
        
        value = new StringBuffer(value.substring(0, 2))
            .append(":").append(value.substring(2, 4))
            .append(":").append(value.substring(4, 6)).toString();
        
        setString(field, row, value);
        
        return Time.valueOf(value);
    }
    
    /**
     * Retorna valor do campo de marcação de linha
     * @param row
     * @return
     */
    public final boolean getMarkValue(int row) {
        Component component;
        
        for (String id : fields.keySet()) {
            component = fields.get(id);
            switch (component.getType()) {
            case EVE.single:
            case EVE.multi:
                return ((Button)component.getControl(row)).getSelection();
            }
        }
        
        return false;
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * Limpa formulário
     */
    public void clear() {
        for (Component component : fields.values())
            component.clear();
    }
    
    /**
     * 
     * @param name
     * @param component
     */
    public final void putComponent(String name, Component component) {
        component.setMessages(messages);
        
        fields.put(name, component);
    }
    
    /**
     * Seleciona foco do campo
     * @param col
     * @param row
     */
    public final void sel(int col, int row) {
        Object[] objects = fields.values().toArray();
        Control control = ((Component)objects[col]).getControl();
        
        if (control instanceof Text)
            ((Text)control).selectAll();
        
        control.setFocus();        
    }
    
}
