package org.eve.sd.customer;

import java.io.Serializable;

public class CustomerContact implements Serializable {
    private static final long serialVersionUID = -6501815384505430816L;
    private Customer customer;
    private int item;
    private String name;
    private String function;
    private int telf1;
    private int telf2;
    
    public CustomerContact() { }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }
    
    /**
     * @return the item
     */
    public int getItem() {
        return item;
    }
    
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
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    /**
     * @param item the item to set
     */
    public void setItem(int item) {
        this.item = item;
    }

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
