package org.eve.sd.customer;

import org.eve.model.AbstractModel;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class CustomerModel extends AbstractModel {
    private Customer customer;

    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public final void select(Customer customer) throws Exception {
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        this.customer = (Customer)session.createQuery("from Customer where ident = ?").
            setInteger(0, customer.getId()).uniqueResult();
        
        if (this.customer == null)
            throw new Exception();
        
        session.getTransaction().commit();
    }
    
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
