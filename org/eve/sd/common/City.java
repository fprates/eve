package org.eve.sd.common;

import java.io.Serializable;

public class City implements Serializable {
    private static final long serialVersionUID = 7115629047283328837L;
    private String ufkey;
    private int ident;
    private String name;
    
    /**
     * @return the ufkey
     */
    public String getUfkey() {
        return ufkey;
    }
    /**
     * @return the ident
     */
    public int getIdent() {
        return ident;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param ufkey the ufkey to set
     */
    public void setUfkey(String ufkey) {
        this.ufkey = ufkey;
    }
    
    /**
     * @param ident the ident to set
     */
    public void setIdent(int ident) {
        this.ident = ident;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        City city;
        
        if (this == object)
            return true;
        
        if (!(object instanceof City))
            return false;
        
        city = (City)object;
        if (ufkey.equals(city.getUfkey()) && ident == city.getIdent())
            return true;
        
        return false;
    }
}
