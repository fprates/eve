package org.eve.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDocument implements Serializable {
    public enum datatype {CHAR, INT, FLOAT, DATE, TIME};
    private static final long serialVersionUID = 6622123475315846780L;
    private Map<Integer, Field> fields;
    
    public AbstractDocument() {
        fields = new HashMap<Integer, Field>();
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
    public final int getLength(int id) {
        return fields.get(id).getLength();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public final String getName(int id) {
        return fields.get(id).getName();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public final datatype getType(int id) {
        return fields.get(id).getType();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public final boolean isKey(int id) {
        return fields.get(id).isKey();
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
    public final void put(int id, String name, boolean key, datatype type, int length) {
        Field field =  new Field(name);
        
        field.setKey(key);
        field.setLength(length);
        field.setType(type);
        
        fields.put(id, field);
    }
}

class Field {
    private boolean key;
    private int length;
    private AbstractDocument.datatype type;
    private String name;
    
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
}