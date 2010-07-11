package org.eve.sd.customer;

public abstract class AbstractCustomerItem implements CustomerItem {
    private static final long serialVersionUID = -4059519796299991979L;
    private Customer customer;
    private int item;
    protected int hash;

    public AbstractCustomerItem() {
        // TODO Auto-generated constructor stub
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
        
        result = (hash * result) + item;
        
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
            result = customer.getId() - customeritem.getCustomer().getId(); 
            if (result != 0)
                return result;
            
            result = item - customeritem.getItem();
            
            return result;
        }
        
        return 0;        
    }

}
