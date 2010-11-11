package org.eve.sd.supplier.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;

import org.eve.model.AbstractModel;
//import org.eve.sd.common.Country;
import org.eve.sd.supplier.Supplier;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class SupplierModel extends AbstractModel {

    private final void copySupplier(Supplier supplier_, Supplier supplier) {
        for (Object id : supplier_.getIds())
            supplier.setFieldValue((String)id, supplier_.getFieldValue((String)id));
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public final void load(Class<?> class_, Serializable object_, Object object) {
        Supplier supplier_;        
        Supplier supplier;
        Session session = getSessionFactory().getCurrentSession();

        session.beginTransaction();
        
        if (class_.equals(Supplier.class)) {
            supplier = (Supplier)object;
            
            supplier_ = (Supplier)session.get(class_, object_);
            copySupplier(supplier_, supplier);
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
        Supplier supplier = (Supplier)object;
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        
        if (supplier.getId() == 0) {
            supplier.setId(getNextIdent(session, "SUPPLR"));
            session.save(supplier);
            calendar = Calendar.getInstance();
            supplier.setRegDate(calendar.getTime());
            supplier.setRegTime(new Time(calendar.getTimeInMillis()));
        } else {
            session.update(supplier);
        }
        
        session.getTransaction().commit();
    }
}
