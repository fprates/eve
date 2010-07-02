package org.eve.model;

import org.hibernate.SessionFactory;

public abstract class AbstractModel implements Model {
    private SessionFactory sessionFactory;
    
    protected final SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public final void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
