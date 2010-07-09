package org.eve.sd.customer;

import org.eve.model.AbstractModel;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class CustomerModel extends AbstractModel {    
    
    /*
     * (non-Javadoc)
     * @see org.eve.model.Model#save(java.lang.Object)
     */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public final void save(Object object) {
        Customer customer = (Customer)object;
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        
        if (customer.getId() == 0) {
            customer.setId(getNextIdent(session, "CUSTMR"));
            session.save(customer);
            for (CustomerContact contact : customer.getContacts())
                session.save(contact);
        } else {
            session.update(customer);
            for (CustomerContact contact : customer.getContacts())
                session.saveOrUpdate(contact);
        }
        
        session.getTransaction().commit();
    }
}
