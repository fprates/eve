package org.eve.sd.customer;

public class CustomerAddress extends AbstractCustomerItem {
    private static final long serialVersionUID = -6527551878586866451L;
    private String address;
    private int number;
    
    public CustomerAddress() {
        hash = 7;
    }

    /*
     * 
     * Getters
     * 
     */
    
    public String getAddress() {
        return address;
    }
    
    public int getNumber() {
        return number;
    }

    /*
     * 
     * Setters
     * 
     */
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }

}
