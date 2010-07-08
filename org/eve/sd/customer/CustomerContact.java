package org.eve.sd.customer;

import java.io.Serializable;

public class CustomerContact implements Serializable {
    private static final long serialVersionUID = -6501815384505430816L;
    private Customer customer;
    private int item;
    
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
