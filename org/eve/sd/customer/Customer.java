/**
 * 
 */
package org.eve.sd.customer;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author francisco.prates
 *
 */
public class Customer implements Serializable {
	private static final long serialVersionUID = 3061311103322489445L;
	private int id;
	private String name;
	private String alternateName;
	private Date creation;
	private String codCadNac;
    private int status;
    private Set<CustomerContact> contacts;
    private Set<CustomerAddress> addresses;
	
	public Customer() { }
	
	/*
	 * Getters
	 */
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the alternateName
	 */
	public String getAlternateName() {
		return alternateName;
	}
	
	/**
	 * @return the creation
	 */
	public Date getCreation() {
		return creation;
	}
	
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	
    /**
     * @return the codCadNac
     */
    public String getCodCadNac() {
        return codCadNac;
    }
    
    /**
     * 
     * @return
     */
    public Set<CustomerContact> getContacts() {
        return contacts;
    }
    
    /**
     * 
     * @return
     */
    public Set<CustomerAddress> getAddresses() {
        return addresses;
    }
    
    /*
     * Setters
     */
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param alternateName the alternateName to set
	 */
	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}
	
	/**
	 * @param creation the creation to set
	 */
	public void setCreation(Date creation) {
		this.creation = creation;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

    /**
     * @param codCadNac the codCadNac to set
     */
    public void setCodCadNac(String codCadNac) {
        this.codCadNac = codCadNac;
    }
    
    /**
     * 
     * @param contacts
     */
    public void setContacts(Set<CustomerContact> contacts) {
        this.contacts = contacts;
    }
    
    /**
     * 
     * @param addresses
     */
    public void setAddresses(Set<CustomerAddress> addresses) {
        this.addresses = addresses;
    }
	
}
