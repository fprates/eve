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
	private String codCadFiscal;
    private int status;
    private String homepage;
    private String email;
    private int incentive;
    private int ivf;
    private float prdctinc;
    private float billinc;
    private float splrinc;
    private float prtninc;
    private String reference;
    private int standardsupplier;
    private String inscricaoestadual;
    private int tipoestab;
    private int tipocomunicacao;
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
     * 
     * @return
     */
    public Set<CustomerAddress> getAddresses() {
        return addresses;
    }
    
    /**
     * @return the alternateName
     */
    public String getAlternateName() {
        return alternateName;
    }
    
    /**
     * 
     * @return
     */
    public float getBillingIncentiveValue() {
        return billinc;
    }
    
    /**
     * @return the codCadFiscal
     */
    public String getCodCadFiscal() {
        return codCadFiscal;
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
    public String getEmail() {
        return email;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
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
    public String getInscricaoEstadual() {
        return inscricaoestadual;
    }
    
    /**
     * 
     * @return
     */
    public int getIVF() {
        return ivf;
    }
    
    /**
     * 
     * @return
     */
    public String getHomePage() {
        return homepage;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return
     */
    public float getPartnerIncentiveValue() {
        return prtninc;
    }
    
    /**
     * 
     * @return
     */
    public float getProductIncentiveValue() {
        return prdctinc;
    }
    
    /**
     * Retorna código de referência
     * @return
     */
    public String getReference() {
        return reference;
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
     * 
     * @return
     */
    public Set<CustomerSchedule> getSchedule() {
        return schedule;
    }
    
    /**
     * Retorna fornecedor padrão
     * @return
     */
    public int getStandardSupplier() {
        return standardsupplier;
    }
    
    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }
    
    /**
     * 
     * @return
     */
    public float getSupplierIncentiveValue() {
        return splrinc;
    }
    
    /**
     * 
     * @return
     */
    public int getTipoComunicacao() {
        return tipocomunicacao;
    }
    
    /**
     * 
     * @return
     */
    public int getTipoEstabelecimento() {
        return tipoestab;
    }
    
    /*
     * Setters
     */
    
    /**
     * 
     * @param addresses
     */
    public void setAddresses(Set<CustomerAddress> addresses) {
        this.addresses = addresses;
    }
    
    /**
     * @param alternateName the alternateName to set
     */
    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName;
    }
    
    /**
     * 
     * @param billinc
     */
    public void setBillingIncentiveValue(float billinc) {
        this.billinc = billinc;
    }

    /**
     * @param codCadNac the codCadFiscal to set
     */
    public void setCodCadFiscal(String codCadFiscal) {
        this.codCadFiscal = codCadFiscal;
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
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * 
     * @param homepage
     */
    public void setHomePage(String homepage) {
        this.homepage = homepage;
    }
    
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
     * @param inscricaoestadual
     */
    public void setInscricaoEstadual(String inscricaoestadual) {
        this.inscricaoestadual = inscricaoestadual;
    }
    
    /**
     * 
     * @param ivf
     */
    public void setIVF(int ivf) {
        this.ivf = ivf;
    }
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * 
	 * @param prtninc
	 */
    public void setPartnerIncentiveValue(float prtninc) {
        this.prtninc = prtninc;
    }
    
    /**
     * 
     * @param prdctinc
     */
    public void setProductIncentiveValue(float prdctinc) {
        this.prdctinc = prdctinc;
    }
    
    /**
     * Ajusta código de referência
     * @param reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
	
	/**
	 * @param creation the creation to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	/**
	 * 
	 * @param regTime
	 */
	public void setRegTime(Time regTime) {
	    this.regTime = regTime;
	}
	
	/**
	 * 
	 * @param regUser
	 */
	public void setRegUser(String regUser) {
	    this.regUser = regUser;
	}
    
    /**
     * 
     * @param schedule
     */
    public void setSchedule(Set<CustomerSchedule> schedule) {
        this.schedule = schedule;
    }
    
    /**
     * Ajusta fornecedor padrão
     * @param standardsupplier
     */
    public void setStandardSupplier(int standardsupplier) {
        this.standardsupplier = standardsupplier;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
    /**
     * 
     * @param splrinc
     */
    public void setSupplierIncentiveValue(float splrinc) {
        this.splrinc = splrinc;
    }
    
    /**
     * 
     * @param tipocomunicacao
     */
    public void setTipoComunicacao(int tipocomunicacao) {
        this.tipocomunicacao = tipocomunicacao;
    }
    
    /**
     * 
     * @param tipoestab
     */
    public void setTipoEstabelecimento(int tipoestab) {
        this.tipoestab = tipoestab;
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
