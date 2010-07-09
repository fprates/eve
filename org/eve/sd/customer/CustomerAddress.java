package org.eve.sd.customer;

import java.io.Serializable;

public class CustomerAddress implements Serializable {
    private static final long serialVersionUID = -6527551878586866451L;
    private Customer customer;
    private int item;
    
    public CustomerAddress() { }

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
    
    

}
