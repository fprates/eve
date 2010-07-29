package org.eve.sd.supplier.model;

import java.io.Serializable;

import org.eve.model.AbstractModel;
//import org.eve.sd.common.Country;
import org.eve.sd.supplier.Supplier;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class SupplierModel extends AbstractModel {

    private final void copySupplier(Supplier supplier_, Supplier supplier) {
        supplier.setAlternateName(supplier_.getAlternateName());
//        supplier.setCodCadFiscal(supplier_.getCodCadFiscal());
        supplier.setRegDate(supplier_.getRegDate());
        supplier.setRegTime(supplier_.getRegTime());
        supplier.setRegUser(supplier_.getRegUser());
        supplier.setId(supplier_.getId());
        supplier.setName(supplier_.getName());
//        supplier.setHomePage(supplier_.getHomePage());
//        supplier.setEmail(supplier_.getEmail());
//        supplier.setStatus(supplier_.getStatus());
//        supplier.setIVF(supplier_.getIVF());
//        supplier.setIncentive(supplier_.getIncentive());
//        supplier.setProductIncentiveValue(supplier_.getProductIncentiveValue());
//        supplier.setBillingIncentiveValue(supplier_.getBillingIncentiveValue());
//        supplier.setSupplierIncentiveValue(supplier_.getSupplierIncentiveValue());
//        supplier.setPartnerIncentiveValue(supplier_.getPartnerIncentiveValue());
//        
//        supplier.getContacts().clear();
//        supplier.getContacts().addAll(supplier_.getContacts());
//        
//        supplier.getAddresses().clear();
//        supplier.getAddresses().addAll(supplier_.getAddresses());
//        
//        supplier.getSchedule().clear();
//        supplier.getSchedule().addAll(supplier_.getSchedule());
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public final void load(Class<?> class_, Serializable object_, Object object) {
        Supplier supplier_;        
        Supplier supplier;
//        Country country_;
//        Country country;
        Session session = getSessionFactory().getCurrentSession();

        session.beginTransaction();
        
        if (class_.equals(Supplier.class)) {
            supplier = (Supplier)object;
            
            supplier_ = (Supplier)session.get(class_, object_);
            copySupplier(supplier_, supplier);
        }
        
//        if (class_.equals(Country.class)) {
//            country = (Country)object;
//            
//            country_ = (Country)session.get(class_, object_);
//            copyCountry(country_, country);
//        }
        
        session.getTransaction().commit();
    }
}
