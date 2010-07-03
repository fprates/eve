package org.eve.model;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

public interface Model {
    public abstract void setQueries(Map<String, String> queries);
    
    public abstract Object selectUnique(String query, Object[] criteria);
    
    public abstract List<?> select(String query, Object[] criteria);
    
    public abstract void setSessionFactory(SessionFactory sessionFactory);
}
