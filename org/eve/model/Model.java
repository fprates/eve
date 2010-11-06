package org.eve.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

public interface Model {
    /*
     * 
     * Setters
     * 
     */
    
    public abstract void setQueries(Map<String, String> queries);
    
    public abstract void setSessionFactory(SessionFactory sessionFactory);
    
    /*
     * 
     * Others
     * 
     */
    
    public abstract void load(Class<?> class_, Serializable object_, Object object);
    
    public abstract Object selectUnique(String query, Object[] criteria);
    
    public abstract List<?> select(String query, Object[] criteria);
    
    public abstract void save(Object object) throws EveException;
}
