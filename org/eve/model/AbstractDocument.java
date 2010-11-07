package org.eve.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDocument implements Serializable {
    public enum datatype {CHAR, INT, LONG, FLOAT, DATE, TIME};
    private static final long serialVersionUID = 6622123475315846780L;
    private Map<String, Field> fields;
    
    public AbstractDocument() {
        fields = new HashMap<String, Field>();
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    public final Object getFieldValue(String id) {
        Method method;
        String name = new StringBuffer("get").append(
                id.substring(0, 1).toUpperCase()).append(
                        id.substring(1)).toString();

        try {
            method = getClass().getMethod(name, new Class<?>[0]);
            
            return method.invoke(this, new Object[0]);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * 
     * @return
     */
    public final Object[] getIds() {
        return fields.keySet().toArray();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public final int getLength(String id) {
        return fields.get(id).getLength();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public final String getName(String id) {
        return fields.get(id).getName();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public final String[] getValues(String id) {
        return fields.get(id).getValues();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public final datatype getType(String id) {
        return fields.get(id).getType();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public final boolean isKey(String id) {
        return fields.get(id).isKey();
    }
    
    public final void setFieldValue(String id, Object object) {
        Method method;
        Class<?>[] class_;
        Field field = fields.get(id);
        String name = new StringBuffer("set").append(
                id.substring(0, 1).toUpperCase()).append(
                        id.substring(1)).toString();
        
        switch (field.getType()) {
        case CHAR:
            class_ = new Class[] {String.class};
            break;
            
        case INT:
            class_ = new Class[] {Integer.TYPE};
            break;
        
        case LONG:
            class_ = new Class[] {Long.TYPE};
            break;
            
        case FLOAT:
            class_ = new Class[] {Float.TYPE};
            break;
            
        case DATE:
            class_ = new Class[] {Date.class};
            break;
        
        case TIME:
            class_ = new Class[] {Time.class};
            break;
            
        default:
            return;
        }
        
        try {
            method = getClass().getMethod(name, class_);
            
            method.invoke(this, new Object[] {object});
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * 
     * @param id
     * @param name
     */
    public final void put(String id, String name, boolean key, datatype type, int length) {
        Field field =  new Field(name);
        
        field.setKey(key);
        field.setLength(length);
        field.setType(type);
        
        fields.put(id, field);
    }
    
    /**
     * 
     * @param id
     * @param values
     */
    public final void putValues(String id, String[] values) {
        fields.get(id).setValues(values);
    }
}

class Field {
    private boolean key;
    private int length;
    private AbstractDocument.datatype type;
    private String name;
    private String[] values;
    
    public Field(String name) {
        this.name = name;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * 
     * @return
     */
    public final int getLength() {
        return length;
    }
    
    public final String getName() {
        return name;
    }
    
    public final AbstractDocument.datatype getType() {
        return type;
    }
    
    public final String[] getValues() {
        return values;
    }
    
    public final boolean isKey() {
        return key;
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    /**
     * 
     * @param key
     */
    public final void setKey(boolean key) {
        this.key = key;
    }
    
    public final void setLength(int length) {
        this.length = length;
    }
    
    public final void setType(AbstractDocument.datatype type) {
        this.type = type;
    }
    
    public final void setValues(String[] values) {
        this.values = values;
    }
}