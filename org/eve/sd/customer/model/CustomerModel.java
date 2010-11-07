package org.eve.sd.customer.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;

import org.eve.model.AbstractModel;
import org.eve.sd.common.Country;
import org.eve.sd.customer.Customer;
import org.eve.sd.customer.CustomerAddress;
import org.eve.sd.customer.CustomerContact;
import org.eve.sd.customer.CustomerSchedule;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class CustomerModel extends AbstractModel {

    private final void copyCustomer(Customer customer_, Customer customer) {
        for (Object id : customer_.getIds())
            customer.setFieldValue((String)id, customer_.getFieldValue((String)id));
        
        customer.setStandardSupplier(customer_.getStandardSupplier());
        
        customer.getContacts().clear();
        customer.getContacts().addAll(customer_.getContacts());
        
        customer.getAddresses().clear();
        customer.getAddresses().addAll(customer_.getAddresses());
        
        customer.getSchedule().clear();
        customer.getSchedule().addAll(customer_.getSchedule());
    }

    private final void copyCountry(Country country_, Country country) {
        country.setIdent(country_.getIdent());
        
        country.getStates().clear();
        country.getStates().addAll(country_.getStates());
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public final void load(Class<?> class_, Serializable object_, Object object) {
        Customer customer_;        
        Customer customer;
        Country country_;
        Country country;
        Session session = getSessionFactory().getCurrentSession();

        session.beginTransaction();
        
        if (class_.equals(Customer.class)) {
            customer = (Customer)object;
            
            customer_ = (Customer)session.get(class_, object_);
            copyCustomer(customer_, customer);
        }
        
        if (class_.equals(Country.class)) {
            country = (Country)object;
            
            country_ = (Country)session.get(class_, object_);
            copyCountry(country_, country);
        }
        
        session.getTransaction().commit();
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.model.Model#save(java.lang.Object)
     */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public final void save(Object object) {
        Calendar calendar;
        int k = 0;
        Customer customer = (Customer)object;
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        
        if (customer.getId() == 0) {
            customer.setId(getNextIdent(session, "CUSTMR"));
            session.save(customer);
            calendar = Calendar.getInstance();
            customer.setRegDate(calendar.getTime());
            customer.setRegTime(new Time(calendar.getTimeInMillis()));
            
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
