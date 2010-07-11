package org.eve.sd.customer;

import java.io.Serializable;

public interface CustomerItem extends Serializable, Comparable<CustomerItem> {
    public abstract void setCustomer(Customer customer);
    
    public abstract void setItem(int item);
    
    public abstract Customer getCustomer();
    
    public abstract int getItem();
}
