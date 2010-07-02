package org.eve.model;

import org.hibernate.SessionFactory;

public interface Model {

    public abstract void save(Object object);
    
    public abstract void setSessionFactory(SessionFactory sessionFactory);
}
