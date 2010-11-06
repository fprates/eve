package org.eve.mm.material.model;

import java.sql.Time;
import java.util.Calendar;

import org.eve.mm.material.Material;
import org.eve.model.AbstractModel;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class MaterialModel extends AbstractModel {
	
	/*
	 * (non-Javadoc)
	 * @see org.eve.model.AbstractModel#save(java.lang.Object)
	 */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public final void save(Object object) {
        Calendar calendar;
        Material material = (Material)object;
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        
        if (material.getRegUser() == null || material.getRegUser().equals("")) {
        	material.setRegUser("SYSTEM");
            calendar = Calendar.getInstance();
            material.setRegDate(calendar.getTime());
            material.setRegTime(new Time(calendar.getTimeInMillis()));
            session.save(material);
        } else {
            session.update(material);
        }
        
        session.getTransaction().commit();
    }

}
