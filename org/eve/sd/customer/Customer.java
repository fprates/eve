/**
 * 
 */
package org.eve.sd.customer;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author francisco.prates
 *
 */
public class Customer implements Serializable {
	private static final long serialVersionUID = 3061311103322489445L;
	private int id;
	private String name;
	private String alternateName;
	private Date regDate;
	private Time regTime;
	private String regUser;
	private String codCadNac;
    private int status;
    private int incentive;
    private float prdctinc;
    private float billinc;
    private float splrinc;
    private float prtninc;
    private Set<CustomerContact> contacts;
    private Set<CustomerAddress> addresses;
    private Set<CustomerSchedule> schedule;
	
	public Customer() {
	    contacts = new TreeSet<CustomerContact>();
	    addresses = new TreeSet<CustomerAddress>();
	    schedule = new TreeSet<CustomerSchedule>();
	}
	
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
	public Date getRegDate() {
		return regDate;
	}
	
	/**
	 * 
	 * @return
	 */
	public Time getRegTime() {
	    return regTime;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getRegUser() {
	    return regUser;
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
    public int getIncentive() {
        return incentive;
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
    
    /**
     * 
     * @return
     */
    public Set<CustomerSchedule> getSchedule() {
        return schedule;
    }
    
    public float getProductIncentiveValue() {
        return prdctinc;
    }
    
    public float getBillingIncentiveValue() {
        return billinc;
    }
    
    public float getSupplierIncentiveValue() {
        return splrinc;
    }
    
    public float getPartnerIncentiveValue() {
        return prtninc;
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
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public void setRegTime(Time regTime) {
	    this.regTime = regTime;
	}
	
	public void setRegUser(String regUser) {
	    this.regUser = regUser;
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
     * @param incentive
     */
    public void setIncentive(int incentive) {
        this.incentive = incentive;
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
    
    /**
     * 
     * @param schedule
     */
    public void setSchedule(Set<CustomerSchedule> schedule) {
        this.schedule = schedule;
    }
    
    public void setProductIncentiveValue(float prdctinc) {
        this.prdctinc = prdctinc;
    }
    
    public void setBillingIncentiveValue(float billinc) {
        this.billinc = billinc;
    }
    
    public void setSupplierIncentiveValue(float splrinc) {
        this.splrinc = splrinc;
    }
    
    public void setPartnerIncentiveValue(float prtninc) {
        this.prtninc = prtninc;
    }
    
    @Override
    public boolean equals(Object object) {
        Customer customer;
        
        if (this == object)
            return true;
        
        if (!(object instanceof Customer))
            return false;
        
        customer = (Customer)object;
        if (id != customer.getId())
            return false;
        
        return true;
    }
	
    @Override
    public int hashCode() {
        return Integer.toString(id).hashCode();
    }
}
