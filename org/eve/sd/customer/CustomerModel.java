package org.eve.sd.customer;

import java.io.Serializable;

import org.eve.model.AbstractModel;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class CustomerModel extends AbstractModel {

    private final void copyCustomer(Customer customer_, Customer customer) {
        String aname = customer_.getAlternateName();
        customer.setAlternateName(aname);
        customer.setCodCadNac(customer_.getCodCadNac());
        customer.setRegDate(customer_.getRegDate());
        customer.setRegTime(customer.getRegTime());
        customer.setRegUser(customer.getRegUser());
        customer.setId(customer_.getId());
        customer.setName(customer_.getName());
        customer.setStatus(customer_.getStatus());
        customer.getContacts().clear();
        customer.getContacts().addAll(customer_.getContacts());        
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public final void load(Class<?> class_, Serializable object_, Object object) {
        Customer result_;
        Session session = getSessionFactory().getCurrentSession();
        Customer result = (Customer)object;
        
        session.beginTransaction();
        result_ = (Customer)session.get(class_, object_);
        copyCustomer(result_, result);
        
        session.getTransaction().commit();
    }
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
        } else {
            session.update(customer);
        }
        
        for (CustomerContact contact : customer.getContacts())
            session.save(contact);
        
        session.getTransaction().commit();
    }
}
