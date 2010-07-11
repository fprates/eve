package org.eve.sd.customer;

public class CustomerContact extends AbstractCustomerItem {
    private static final long serialVersionUID = -6501815384505430816L;
    private String name;
    private String function;
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
    
    public String getName() {
        return name;
    }
    
    public String getFunction() {
        return function;
    }
    
    public int getTelephone1() {
        return telf1;
    }
    
    public int getTelephone2() {
        return telf2;
    }
    /*
     * 
     * Setters
     * 
     */

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param function the function to set
     */
    public void setFunction(String function) {
        this.function = function;
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
    
}
