package org.eve.sd.order.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Set;

import org.eve.model.AbstractModel;
import org.eve.model.EveException;
import org.eve.sd.order.Order;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class OrderModel extends AbstractModel {

    /*
     * (non-Javadoc)
     * @see org.eve.model.AbstractModel#insert(java.util.Set)
     */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public void insert(Set<?> objects) {
        
    }

    /*
     * (non-Javadoc)
     * @see org.eve.model.AbstractModel#load(java.lang.Class, java.io.Serializable, java.lang.Object)
     */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public void load(Class<?> class_, Serializable object_, Object object) {
        
    }

    /*
     * (non-Javadoc)
     * @see org.eve.model.AbstractModel#save(java.lang.Object)
     */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public void save(Object object) throws EveException {
        Calendar calendar;
        Order order = (Order)object;
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();

        if (order.getId() == 0) {
            order.setId(getNextIdent(session, "ORDR01"));
            session.save(order);
            calendar = Calendar.getInstance();
            order.setRegDate(calendar.getTime());
            order.setRegTime(new Time(calendar.getTimeInMillis()));
        } else {
            session.update(order);
        }
        
        session.getTransaction().commit();
    }

}
