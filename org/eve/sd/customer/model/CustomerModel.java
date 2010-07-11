package org.eve.sd.customer.model;

import java.io.Serializable;

import org.eve.model.AbstractModel;
import org.eve.sd.customer.Customer;
import org.eve.sd.customer.CustomerAddress;
import org.eve.sd.customer.CustomerContact;
import org.eve.sd.customer.CustomerSchedule;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class CustomerModel extends AbstractModel {

    private final void copyCustomer(Customer customer_, Customer customer) {
        customer.setAlternateName(customer_.getAlternateName());
        customer.setCodCadNac(customer_.getCodCadNac());
        customer.setRegDate(customer_.getRegDate());
        customer.setRegTime(customer.getRegTime());
        customer.setRegUser(customer.getRegUser());
        customer.setId(customer_.getId());
        customer.setName(customer_.getName());
        customer.setStatus(customer_.getStatus());
        
        customer.getContacts().clear();
        customer.getContacts().addAll(customer_.getContacts());
        
        customer.getAddresses().clear();
        customer.getAddresses().addAll(customer_.getAddresses());
        
        customer.getSchedule().clear();
        customer.getSchedule().addAll(customer_.getSchedule());
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
        int k = 0;
        Customer customer = (Customer)object;
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        
        if (customer.getId() == 0) {
            customer.setId(getNextIdent(session, "CUSTMR"));
            session.save(customer);
            
            for (CustomerContact contact : customer.getContacts()) {
                contact.setItem(++k + (customer.getId() * 100));
                session.save(contact);
            }
            
            k = 0;
            for (CustomerAddress address : customer.getAddresses()) {
                address.setItem(++k + (customer.getId() * 100));
                session.save(address);
            }
            
            k = 0;
            for (CustomerSchedule schedule : customer.getSchedule()) {
                schedule.setItem(++k + (customer.getId() * 100));
                session.save(schedule);
            }
        } else {
            session.update(customer);
            
            for (CustomerContact contact : customer.getContacts()) {
                contact.setItem(++k + (customer.getId() * 100));
                session.saveOrUpdate(contact);
            }
            
            k = 0;
            for (CustomerAddress address : customer.getAddresses()) {
                address.setItem(++k + (customer.getId() * 100));
                session.saveOrUpdate(address);
            }
            
            k = 0;
            for (CustomerSchedule schedule : customer.getSchedule()) {
                schedule.setItem(++k + (customer.getId() * 100));
                session.saveOrUpdate(schedule);
            }
        }
        
        session.getTransaction().commit();
    }
}
