package org.eve.sd.customer;

import org.eve.model.AbstractModel;
import org.hibernate.Session;

public class CustomerModel extends AbstractModel {    
    /**
     * 
     * @param customer
     */
    public final void save(Customer customer) {        
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        
        if (customer.getId() == 0)
            customer.setId(getNextIdent(session, "CUSTMR"));
        
        session.save(customer);
        session.getTransaction().commit();        
    }
}
