package org.eve.sd.common;

import java.io.Serializable;

public class State implements Serializable, Comparable<State> {
    private static final long serialVersionUID = 6713857938932579688L;
    private Country country;
    private String ident;
    private String name;
    
    public State() { }

    
    
    /**
     * @return the country
     */
    public Country getCountry() {
        return country;
    }



    /**
     * @return the ident
     */
    public String getIdent() {
        return ident;
    }
    
    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }



    /**
     * @param country the country to set
     */
    public void setCountry(Country country) {
        this.country = country;
    }



    /**
     * @param ident the ident to set
     */
    public void setIdent(String ident) {
        this.ident = ident;
    }
    
    /**
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        State state;
        
        if (this == object)
            return true;
        
        if (!(object instanceof State))
            return false;
        
        state = (State)object;
        if (country.equals(state.getCountry()) && ident.equals(ident))
            return true;
        
        return false;
    }

    @Override
    public int compareTo(State state) {
        int compare;
        
        if (this == state)
            return 0;
        
        compare = country.getIdent().compareTo(state.getCountry().getIdent());
        if (compare != 0)
            return compare;
        
        return ident.compareTo(state.getIdent());
    }
}
