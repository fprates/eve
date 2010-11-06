package org.eve.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractModel implements Model {
    private SessionFactory sessionFactory;
    private Map<String, String> queries;

    /*
     * 
     * Setters
     * 
     */
    
    /*
     * (non-Javadoc)
     * @see org.eve.model.Model#setQueries(java.util.Map)
     */
    @Override
    public final void setQueries(Map<String, String> queries) {
        this.queries = queries;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.model.Model#setSessionFactory(org.hibernate.SessionFactory)
     */
    @Override
    public final void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * Retorna o próximo identificador numérico
     * @param session
     * @param rngname
     * @return
     */
    @Transactional(propagation=Propagation.SUPPORTS)
    protected final int getNextIdent(Session session, String rngname) {
        NumericRange range = (NumericRange)session.
            createQuery("from NumericRange where range = ?").
            setString(0, rngname).uniqueResult();
        
        range.setCurrent(range.getCurrent() + 1);
        
        session.update(range);
        
        return range.getCurrent();
    }
    
    /**
     * Retorna session factory
     * @return
     */
    protected final SessionFactory getSessionFactory() {
        return sessionFactory;
    }    
    
    /*
     * 
     * Others
     * 
     */

    @Override
    public void load(Class<?> class_, Serializable object_, Object object) { }
    
    /*
     * (non-Javadoc)
     * @see org.eve.model.Model#selectUnique(java.lang.String, java.lang.Object[])
     */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public final Object selectUnique(String queryid, Object[] criteria) {
        Query query;
        Object result;
        int id = 0;
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        query = session.createQuery(queries.get(queryid));
        
        for (Object object : criteria)
            query.setParameter(id++, object);
        
        result = query.uniqueResult();
        session.getTransaction().commit();
        
        return result;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.model.Model#select(java.lang.String, java.lang.Object[])
     */
    @Override
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public final List<?> select(String queryid, Object[] criteria) {
        Query query;
        List<?> results;
        int id = 0;        
        Session session = getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        query = session.createQuery(queries.get(queryid));
        
        if (criteria != null)
            for (Object object : criteria)
                query.setParameter(id++, object);
        
        results = query.list();
        session.getTransaction().commit();
        
        return results;
    }

    /*
     * (non-Javadoc)
     * @see org.eve.model.Model#save(java.lang.Object)
     */
    @Override
    public void save(Object object) throws EveException { }
}
