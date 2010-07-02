package org.eve.sd.customer;

import org.eve.model.AbstractModel;
import org.hibernate.Session;

public class CustomerModel extends AbstractModel {

    /*
     * (non-Javadoc)
     * @see org.eve.model.Model#save(java.lang.Object)
     */
    @Override
    public final void save(Object object) {
        Customer customer = (Customer)object;
        
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        
        if (customer.getId() == 0)
            customer.setId(getNextIdent(session, "CUSTMR"));
        
        session.save(customer);
        session.getTransaction().commit();        
    }
}
