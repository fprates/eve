package org.eve.mm.material.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;

import org.eve.mm.material.Material;
import org.eve.model.AbstractModel;
import org.eve.model.EveException;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class MaterialModel extends AbstractModel {

    private void copyMaterial(Material material_, Material material) {
        material.setId(material_.getId());
        material.setGrossWeight(material_.getGrossWeight());
        material.setNetWeight(material_.getNetWeight());
        material.setWeightUnit(material_.getWeightUnit());
        material.setMaterialType(material_.getMaterialType());
        material.setNetPrice(material_.getNetPrice());
        material.setPriceUnit(material_.getPriceUnit());
        material.setQuantity(material_.getQuantity());
        material.setQuantityUnit(material_.getQuantityUnit());
        material.setReference(material_.getReference());
        material.setRegDate(material_.getRegDate());
        material.setRegTime(material_.getRegTime());
        material.setRegUser(material_.getRegUser());
    }
	
    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public final void load(Class<?> class_, Serializable object_, Object object) {
        Material material;
        Material material_;
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        
        material = (Material)object;
        material_ = (Material)session.get(class_, object_);
        copyMaterial(material_, material);
        
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

}
