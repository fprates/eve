package org.eve.view;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.eve.main.EVE;
import org.eve.main.EveAPI;
import org.eve.model.AbstractDocument;
import org.springframework.context.MessageSource;

public abstract class AbstractComponentFactory implements ComponentFactory {
    private Locale locale;
    private MessageSource messages;
    private EveAPI system;
    private Map<String, Component> fields;
    private DateFormat dateformat;
    
    public AbstractComponentFactory() {
        fields = new LinkedHashMap<String, Component>();
        dateformat = DateFormat.getDateInstance(DateFormat.MEDIUM);
    }

    protected abstract void setControlFocus(Component component);
    
    protected abstract void setControlValue(Component component, String value);
    
    protected abstract void setControlValue(Component component, int index, String value);
    
    public abstract void setControlSize(Component component);
    
    protected abstract String getControlValue(Component component);
    
    protected abstract String getControlValue(Component component, int index);
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setDate(java.lang.String, java.util.Date)
     */
    @Override
    public final void setDate(String field, Date date) {
        Component component = fields.get(field);
        
        if (date == null)
            setControlValue(component, "");
        else
            setControlValue(component, date.toString());
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setFieldValue(java.lang.String, java.lang.Object)
     */
    @Override
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
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setFloat(java.lang.String, float)
     */
    @Override
    public final void setFloat(String field, float value) {
        setControlValue(fields.get(field), Float.toString(value));
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setFocus(java.lang.String)
     */
    @Override
    public final void setFocus(String field) {
        setControlFocus(fields.get(field));
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setInt(java.lang.String, int)
     */
    @Override
    public final void setInt(String field, int value) {
        setControlValue(fields.get(field), Integer.toString(value));
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setInt(java.lang.String, int, int)
     */
    @Override
    public final void setInt(String field, int index, int value) {
        setControlValue(fields.get(field), index, Integer.toString(value));
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setLocale(java.util.Locale)
     */
    @Override
    public final void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setLong(java.lang.String, long)
     */
    @Override
    public final void setLong(String field, long value) {
        setControlValue(fields.get(field), Long.toString(value));
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setMessage(int, java.lang.String)
     */
    @Override
    public final void setMessage(int status, String id) {
        system.setMessage(status, getMessage(id));
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setMessages(org.springframework.context.MessageSource)
     */
    @Override
    public final void setMessages(MessageSource messages) {
        this.messages = messages;
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setString(java.lang.String, java.lang.String)
     */
    @Override
    public final void setString(String field, String text) {
        Component component = fields.get(field);
        
        if (text == null)
            setControlValue(component, "");
        else
            setControlValue(component, text);
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setString(java.lang.String, int, java.lang.String)
     */
    @Override
    public final void setString(String field, int index, String text) {
        Component component = fields.get(field);
        
        if (text == null)
            setControlValue(component, index, "");
        else
            setControlValue(component, index, text);
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setSystem(org.eve.main.EveAPI)
     */
    @Override
    public final void setSystem(EveAPI system) {
        this.system = system;
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setTime(java.lang.String, java.sql.Time)
     */
    @Override
    public final void setTime(String field, Time time) {
        Component component = fields.get(field);
        
        if (time == null)
            setControlValue(component, "");
        else
            setControlValue(component, time.toString());
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#setTime(java.lang.String, int, java.sql.Time)
     */
    @Override
    public final void setTime(String field, int index, Time time) {
        Component component = fields.get(field);
        
        if (time == null)
            setControlValue(component, index, "");
        else
            setControlValue(component, index, time.toString());
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    protected final Component getComponent(String field) {
        return fields.get(field);
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getComponents()
     */
    @Override
    public final Collection<Component> getComponents() {
        return fields.values();
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getDate(java.lang.String)
     */
    @Override
    public final Date getDate(String field) {
        try {
            return dateformat.parse(getControlValue(fields.get(field)));
        } catch (ParseException ex) {
            system.setMessage(EVE.error, getMessage("invalid.date.format"));
            setControlFocus(fields.get(field));
            ex.printStackTrace();
        }
        
        return null;
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getFieldValue(org.eve.model.AbstractDocument, java.lang.String)
     */
    @Override
    public final Object getFieldValue(AbstractDocument document, String id) {
        String id_ = document.getName(id);
        Component component = fields.get(id_);
        
        /*
         * significa que o campo do objeto não existe no formulário
         */
        if (component == null)
            return null;
        
        switch (component.getDataType()) {
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
        
        case LONG:
            return getLong(id_);
            
        default:
            return null;
        }
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getFloat(java.lang.String)
     */
    @Override
    public final float getFloat(String field) {
        Component component = fields.get(field);
        
        try {
            return Float.parseFloat(getControlValue(component));
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.float.format"));
            setControlFocus(component);
            
            throw ex;
        }
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getInt(java.lang.String)
     */
    @Override
    public final int getInt(String field) {
        Component component = fields.get(field);
        String text = getControlValue(component);
        
        try {
            return Integer.parseInt((text.length() == 0)?"0":text);
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.int.format"));
            setControlFocus(component);
            
            throw ex;
        }
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getInt(java.lang.String, int)
     */
    @Override
    public final int getInt(String field, int index) {
        Component component = fields.get(field);
        String text = getControlValue(component, index);
        
        try {
            return Integer.parseInt((text.length() == 0)?"0":text);
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.int.format"));
            setControlFocus(component);
            
            throw ex;
        }
    }
    
    /**
     * 
     * @return
     */
    protected final Locale getLocale() {
        return locale;
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getLong(java.lang.String)
     */
    @Override
    public final long getLong(String field) {
        Component component = fields.get(field);
        
        try {
            return Long.parseLong(getControlValue(component));
        } catch (NumberFormatException ex) {
            system.setMessage(EVE.error, getMessage("invalid.int.format"));
            setControlFocus(component);
            
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
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getString(java.lang.String)
     */
    @Override
    public final String getString(String field) {
        return getControlValue(fields.get(field));
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getString(java.lang.String, int)
     */
    @Override
    public final String getString(String field, int index) {
        return getControlValue(fields.get(field), index);
    }
    
    /**
     * 
     * @return
     */
    protected final EveAPI getSystem() {
        return system;
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getTime(java.lang.String)
     */
    @Override
    public final Time getTime(String field) {
//        return fields.get(field).getTime();
        return null;
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#getTime(java.lang.String, int)
     */
    @Override
    public final Time getTime(String field, int row) {
        String value = getString(field, row);
//        int len;
//            
//        if (value.equals(""))
//            return Time.valueOf("00:00:00");
//        
//        value = value.replace(":", "");
//        len = value.length(); 
//        if ((len % 2) != 0) {
//            setMessage(EVE.error, getMessage("invalid.time.format"));
//            fields.get(field).getItem(row).getControl().setFocus();
//            throw new IllegalArgumentException();
//        }
//        
//        if ((len != 4) && (len != 6)) {
//            setMessage(EVE.error, getMessage("invalid.time.format"));
//            fields.get(field).getItem(row).getControl().setFocus();
//            throw new IllegalArgumentException();
//        }
//        
//        if (len == 4)
//            value = value.concat("00");
//        
//        value = new StringBuffer(value.substring(0, 2))
//            .append(":").append(value.substring(2, 4))
//            .append(":").append(value.substring(4, 6)).toString();
//        
//        setString(field, row, value);
        
        return Time.valueOf(value);
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#clear()
     */
    @Override
    public void clear() {
//        for (Component component : fields.values())
//            component.clear();
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#putComponent(java.lang.String, org.eve.view.Component)
     */
    @Override
    public final void putComponent(String name, Component component) {
        fields.put(name, component);
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.ComponentFactory#sel(int, int)
     */
    @Override
    public final void sel(int col, int row) {
//        Object[] objects = fields.values().toArray();
//        Control control = ((Component)objects[col]).getControl();
//        
//        if (control instanceof Text)
//            ((Text)control).selectAll();
//        
//        control.setFocus();
    }
    
}
