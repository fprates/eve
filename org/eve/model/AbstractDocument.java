package org.eve.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractDocument implements Serializable {
    private static final long serialVersionUID = 6622123475315846780L;
    private Map<String, Field> fields;
    private Map<String, Value> values;
    
    public AbstractDocument() {
        fields = new HashMap<String, Field>();
        values = new HashMap<String, Value>();
        
        put("document.dtreg", false, DataType.DATE, 10);
        put("document.tmreg", false, DataType.TIME, 8);
        put("document.usreg", false, DataType.CHAR, 12);
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
    public final Object getFieldValue(String id) {
        return values.get(id).get();
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
     * @return the creation
     */
    public Date getRegDate() {
        return values.get("document.dtreg").getDate();
    }
    
    /**
     * 
     * @return
     */
    public Time getRegTime() {
        return values.get("document.tmreg").getTime();
    }
    
    /**
     * 
     * @return
     */
    public String getRegUser() {
        return values.get("document.usreg").getString();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public final DataType getType(String id) {
        return fields.get(id).getType();
    }
    
    protected final Object getValue(String id) {
        return values.get(id);
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public final Map<String, ?> getValues(String id) {
        return fields.get(id).getValues();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public final boolean isKey(String id) {
        return fields.get(id).isKey();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public final boolean isUpcase(String id) {
        return fields.get(id).isUpcase();
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    /**
     * 
     * @param id
     * @param object
     */
    public final void setFieldValue(String id, Object object) {
        values.get(id).set(object);
    }
    
    /**
     * 
     * @param id
     */
    public final void setLowerCase(String id) {
        fields.get(id).setUpcase(false);
    }
    
    /**
     * @param creation the creation to set
     */
    public void setRegDate(Date regDate) {
        values.get("document.dtreg").set(regDate);
    }
    
    /**
     * 
     * @param regTime
     */
    public void setRegTime(Time regTime) {
        values.get("document.tmreg").set(regTime);
    }
    
    /**
     * 
     * @param regUser
     */
    public void setRegUser(String regUser) {
        values.get("document.usreg").set(regUser);
    }
    
    protected final void setValue(String id, Object object) {
        values.get(id).set(object);
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
    public final void put(String name, boolean key, DataType type, int length) {
        Field field =  new Field();
        Value value = new Value();
        
        field.setKey(key);
        field.setLength(length);
        field.setType(type);
        
        fields.put(name, field);
        values.put(name, value);
        
        switch (type) {
        case CHAR:
            value.set("");
            break;
            
        case DATE:
            value.set(new Date());
            break;
            
        case INT:
            value.set(0);
            break;
            
        case LONG:
            value.set(0L);
            break;
            
        case TIME:
            value.set(Time.valueOf("00:00:00"));
            break;
        }
    }
    
    /**
     * 
     * @param id
     * @param keys
     */
    public final void putAutoValues(String id, String[] keys) {
        int k = 0;
        Map<String, Integer> values = new LinkedHashMap<String, Integer>();
        
        for (String key : keys)
            values.put(key, k++);
        
        putValues(id, values);
    }
    
    /**
     * 
     * @param id
     * @param values
     */
    public final void putValues(String id, Map<String, ?> values) {
        fields.get(id).setValues(values);
    }
}

class Value {
    private Object data;
    
    public final void set(Object data) {
        this.data = data;
    }
    
    public final Object get() {
        return data;
    }
    
    public final Date getDate() {
        return (Date)data;
    }
    
    public final String getString() {
        return (String)data;
    }
    
    public final Time getTime() {
        return (Time)data;
    }
}

class Field {
    private boolean key;
    private boolean upcase;
    private int length;
    private DataType type;
    private Map<String, ?> values;
    
    public Field() {
        upcase = true;
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
    
    public final DataType getType() {
        return type;
    }
    
    public final Map<String, ?> getValues() {
        return values;
    }
    
    public final boolean isKey() {
        return key;
    }
    
    public final boolean isUpcase() {
        return upcase;
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
    
    public final void setType(DataType type) {
        this.type = type;
    }
    
    public final void setUpcase(boolean upcase) {
        this.upcase = upcase;
    }
    
    public final void setValues(Map<String, ?> values) {
        this.values = values;
    }
}