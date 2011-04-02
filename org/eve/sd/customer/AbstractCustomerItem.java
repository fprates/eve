package org.eve.sd.customer;

import org.eve.model.AbstractDocument;
import org.eve.model.DataType;

public abstract class AbstractCustomerItem extends AbstractDocument
    implements CustomerItem {
    
    private static final long serialVersionUID = -4059519796299991979L;
    private Customer customer;
    protected int hash;

    public AbstractCustomerItem() {
        put("customer.item", IS_KEY, DataType.INT, 5);
    }
    
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
        return (Integer)getValue("customer.item");
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
        setValue("customer.item", item);
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
        if (!getCustomer().equals(address.getCustomer()))
            return false;
        
        if (getItem() != address.getItem())
            return false;
        
        return true;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = getCustomer().hashCode();
        
        result = (hash * result) + getItem();
        
        return result;
    }


    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public final int compareTo(CustomerItem customeritem) {
        int result;
        
        if (customeritem!= this) {
            result = getCustomer().getId() - customeritem.getCustomer().getId(); 
            if (result != 0)
                return result;
            
            result = getItem() - customeritem.getItem();
            
            return result;
        }
        
        return 0;        
    }

}
