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
    public static final String DTREG = "regDate";
    public static final String TMREG = "regTime";
    public static final String USREG = "regUser";
    
    private static final long serialVersionUID = 6622123475315846780L;
    private Date regDate;
    private String regUser;
    private Time regTime;
    private Map<String, Field> fields;
    
    public AbstractDocument() {
        fields = new HashMap<String, Field>();
        put(DTREG, "document.dtreg", false, datatype.DATE, 10);
        put(TMREG, "document.tmreg", false, datatype.TIME, 8);
        put(USREG, "document.usreg", false, datatype.CHAR, 12);
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
     * @return the creation
     */
    public Date getRegDate() {
        return regDate;
    }
    
    /**
     * 
     * @return
     */
    public Time getRegTime() {
        return regTime;
    }
    
    /**
     * 
     * @return
     */
    public String getRegUser() {
        return regUser;
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
    public final String[] getValues(String id) {
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
        Method method;
        Class<?>[] class_;
        Field field = fields.get(id);
        String name = new StringBuffer("set").append(
                id.substring(0, 1).toUpperCase()).append(
                        id.substring(1)).toString();
        
        switch (field.getType()) {
        case CHAR:
            class_ = new Class[] {String.class};
            if ((object != null) && (field.isUpcase()))
                object = ((String)object).toUpperCase();
            
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
            System.out.println(id);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public final void setLowerCase(String id) {
        fields.get(id).setUpcase(false);
    }
    
    /**
     * @param creation the creation to set
     */
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    
    /**
     * 
     * @param regTime
     */
    public void setRegTime(Time regTime) {
        this.regTime = regTime;
    }
    
    /**
     * 
     * @param regUser
     */
    public void setRegUser(String regUser) {
        this.regUser = regUser;
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
    private boolean upcase;
    private int length;
    private AbstractDocument.datatype type;
    private String name;
    private String[] values;
    
    public Field(String name) {
        this.name = name;
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
    
    public final void setType(AbstractDocument.datatype type) {
        this.type = type;
    }
    
    public final void setUpcase(boolean upcase) {
        this.upcase = upcase;
    }
    
    public final void setValues(String[] values) {
        this.values = values;
    }
}