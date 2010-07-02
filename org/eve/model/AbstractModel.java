package org.eve.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractModel implements Model {
    private SessionFactory sessionFactory;
    
    /**
     * 
     * @param session
     * @param rngname
     * @return
     */
    protected final int getNextIdent(Session session, String rngname) {
        NumericRange range = (NumericRange)session.
            createQuery("from NumericRange where range = ?").
            setString(0, rngname).uniqueResult();
        
        range.setCurrent(range.getCurrent() + 1);
        
        session.update(range);
        
        return range.getCurrent();
    }
    
    /**
     * 
     * @return
     */
    protected final SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public final void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
