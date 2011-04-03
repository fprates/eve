package org.eve.sd.customer;

import org.eve.model.DataType;

public class CustomerContact extends AbstractCustomerItem {
    private static final long serialVersionUID = -6501815384505430816L;
    
    public CustomerContact() {
        hash = 11;
        
        put("contact.type", NOT_KEY, DataType.CHAR, 20);
        put("contact.rname", NOT_KEY, DataType.CHAR, 40);
        put("contact.im", NOT_KEY, DataType.CHAR, 60);
        put("contact.funct", NOT_KEY, DataType.CHAR, 20);
        put("contact.teln1", NOT_KEY, DataType.CHAR, 12);
        put("contact.teln2", NOT_KEY, DataType.CHAR, 12);
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
    public String getFunction() {
        return (String)getValue("contact.fnctn");
    }
    
    public String getInstantMessenger() {
        return (String)getValue("contact.im");
    }
    
    public String getName() {
        return (String)getValue("contact.rname");
    }
    
    public int getTelephone1() {
        return (Integer)getValue("contact.telf1");
    }
    
    public int getTelephone2() {
        return (Integer)getValue("contact.telf2");
    }
    
    public String getType() {
        return (String)getValue("contact.type");
    }
    
    /*
     * 
     * Setters
     * 
     */

    /**
     * @param function the function to set
     */
    public void setFunction(String function) {
        setValue("contact.fnctn", function);
    }
    
    /**
     * 
     * @param instantmessenger
     */
    public void setInstantMessenger(String instantmessenger) {
        setValue("contact.im", instantmessenger);
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        setValue("contact.rname", name);
    }

    /**
     * @param telf1 the telf1 to set
     */
    public void setTelephone1(int telf1) {
        setValue("contact.telf1", telf1);
    }

    /**
     * @param telf2 the telf2 to set
     */
    public void setTelephone2(int telf2) {
        setValue("contact.telf2", telf2);
    }
    
    /**
     * 
     * @param type
     */
    public void setType(String type) {
        setValue("contact.type", type);
    }
    
}
