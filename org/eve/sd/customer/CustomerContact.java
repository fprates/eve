package org.eve.sd.customer;

public class CustomerContact extends AbstractCustomerItem {
    private static final long serialVersionUID = -6501815384505430816L;
    private String type;
    private String name;
    private String function;
    private String instantmessenger;
    private int telf1;
    private int telf2;
    
    public CustomerContact() {
        hash = 11;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    public String getFunction() {
        return function;
    }
    
    public String getInstantMessenger() {
        return instantmessenger;
    }
    
    public String getName() {
        return name;
    }
    
    public int getTelephone1() {
        return telf1;
    }
    
    public int getTelephone2() {
        return telf2;
    }
    
    public String getType() {
        return type;
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
        this.function = function;
    }
    
    /**
     * 
     * @param instantmessenger
     */
    public void setInstantMessenger(String instantmessenger) {
        this.instantmessenger = instantmessenger;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param telf1 the telf1 to set
     */
    public void setTelephone1(int telf1) {
        this.telf1 = telf1;
    }

    /**
     * @param telf2 the telf2 to set
     */
    public void setTelephone2(int telf2) {
        this.telf2 = telf2;
    }
    
    /**
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
    
}
