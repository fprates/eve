package org.eve.sd.common;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class Country implements Serializable {
    private static final long serialVersionUID = 5719816046416959597L;
    private String ident;
    private Set<State> states;
    
    public Country() {
        states = new TreeSet<State>();
    }

    /**
     * @return the ident
     */
    public String getIdent() {
        return ident;
    }

    /**
     * @return the states
     */
    public Set<State> getStates() {
        return states;
    }

    /**
     * @param ident the ident to set
     */
    public void setIdent(String ident) {
        this.ident = ident;
    }

    /**
     * @param states the states to set
     */
    public void setStates(Set<State> states) {
        this.states = states;
    }    
    
    @Override
    public boolean equals(Object object) {
        Country country;
        
        if (this == object)
            return true;
        
        if (!(object instanceof Country))
            return false;
        
        country = (Country)object;
        if (ident.equals(country.getIdent()))
            return true;
        
        return false;        
    }

}
