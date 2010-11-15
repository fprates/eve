package org.eve.mm.material.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.eve.mm.material.Material;
import org.eve.model.AbstractModel;
import org.eve.model.EveException;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class MaterialModel extends AbstractModel {
	
    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public final void load(Class<?> class_, Serializable object_, Object object) {
        Material material;
        Material material_;
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        
        material = (Material)object;
        material_ = (Material)session.get(class_, object_);
        
        for (Object id : material_.getIds())
            material.setFieldValue(
                    (String)id, material_.getFieldValue((String)id));
        
        session.getTransaction().commit();
    }
    
	/*
	 * (non-Javadoc)
	 * @see org.eve.model.AbstractModel#save(java.lang.Object)
	 */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public final void save(Object object) throws EveException {
        Calendar calendar;
        Material material_;
        Material material = (Material)object;
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        
        if (material.getRegUser() == null || material.getRegUser().equals("")) {
            material_ = (Material)session.load(Material.class, material.getId());
            
            if (material_.getId() == null)
                throw new EveException("material.duplicated.ident");
            
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
    
    /*
     * (non-Javadoc)
     * @see org.eve.model.AbstractModel#insert(java.util.Set)
     */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public final void insert(Set<?> objects) {
        Material material;
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        Time time = new Time(calendar.getTimeInMillis());
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        for (Object object: objects) {
            material = (Material)object;
            
            material.setRegDate(date);
            material.setRegTime(time);
            session.save(material);
        }
        session.getTransaction().commit();
        
    }

}
