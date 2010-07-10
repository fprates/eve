package org.eve.sd.customer;

import java.io.Serializable;

public class CustomerAddress implements Serializable, Comparable<CustomerAddress> {
    private static final long serialVersionUID = -6527551878586866451L;
    private Customer customer;
    private int item;
    private String address;
    private int number;
    
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
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(CustomerAddress address) {
        int result;
        
        if (address != this) {
            result = customer.getId() - address.getCustomer().getId(); 
            if (result != 0)
                return result;
            
            result = item - address.getItem();
            
            return result;
        }
        
        return 0;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        CustomerAddress address;
        
        if (this == object)
            return true;
        
        if (!(object instanceof CustomerAddress))
            return false;
        
        address = (CustomerAddress)object;
        if (!customer.equals(address.getCustomer()))
            return false;
        
        if (item != address.getItem())
            return false;
        
        return true;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = customer.hashCode();
        
        result = (100 * result) + Integer.toString(item).hashCode();
        
        return result;
    }

}
