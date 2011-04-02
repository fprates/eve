/**
 * 
 */
package org.eve.sd.customer;

import java.util.Set;
import java.util.TreeSet;

import org.eve.model.AbstractDocument;
import org.eve.model.DataType;

/**
 * @author francisco.prates
 *
 */
public class Customer extends AbstractDocument
    implements Comparable<Customer> {
	private static final long serialVersionUID = 3061311103322489445L;
    private Set<CustomerContact> contacts;
    private Set<CustomerAddress> addresses;
    private Set<CustomerSchedule> schedule;
	
	public Customer() {
	    contacts = new TreeSet<CustomerContact>();
	    addresses = new TreeSet<CustomerAddress>();
	    schedule = new TreeSet<CustomerSchedule>();
	    
	    put("customer.ident", true, DataType.INT, 10);
        put("customer.refer", false, DataType.CHAR, 12);
        put("customer.name", false, DataType.CHAR, 40);
        put("customer.aname", false, DataType.CHAR, 40);
        put("customer.cnpj", false, DataType.LONG, 18);
        put("customer.ie", false, DataType.CHAR, 12);
        put("customer.homep", false, DataType.CHAR, 128);
        setLowerCase("customer.homep");
        
        put("customer.email", false, DataType.CHAR, 128);
        setLowerCase("customer.email");
        
        put("customer.vlipr", false, DataType.FLOAT, 13);
        put("customer.vlibl", false, DataType.FLOAT, 7);
        put("customer.dvcsp", false, DataType.FLOAT, 7);
        put("customer.dvcpt", false, DataType.FLOAT, 7);

        put("customer.tpinc", false, DataType.INT, 1);
        putAutoValues("customer.tpinc", new String[] {"none",
                "incentive.bill", "incentive.product", "incentive.free"});
        
        put("customer.cdivf", false, DataType.INT, 1);
        putAutoValues("customer.cdivf", new String[] {"none", "A", "B", "C"});
        
        put("customer.status", false, DataType.INT, 1);
        putAutoValues("customer.status", new String[] {
                "customer.inactive", "customer.active"});
        
        put("customer.stdsp", false, DataType.INT, 10);
        put("customer.vlisp", false, DataType.FLOAT, 7);
        put("customer.vlipa", false, DataType.FLOAT, 7);
        put("customer.tpest", false, DataType.INT, 12);
        putAutoValues("customer.tpest", new String[] {
                "none", "tpest.super", "tpest.indus", "tpest.reven"});
        
        put("customer.tpcom", false, DataType.INT, 1);
        putAutoValues("customer.tpcom", new String[] {"none",
                "tpcom.blue", "tpcom.green", "tpcom.red", "tpcom.yellow"});
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
        return (String)getValue("customer.aname");
    }
    
    /**
     * 
     * @return
     */
    public float getBillingIncentiveValue() {
        return (Float)getValue("customer.vlibl");
    }
    
    /**
     * @return the codCadFiscal
     */
    public long getCodCadFiscal() {
        return (Long)getValue("customer.cnpj");
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
        return (String)getValue("customer.email");
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return (Integer)getValue("customer.ident");
    }
    
    /**
     * 
     * @return
     */
    public int getIncentive() {
        return (Integer)getValue("customer.tpinc");
    }
    
    /**
     * 
     * @return
     */
    public String getInscricaoEstadual() {
        return (String)getValue("customer.ie");
    }
    
    /**
     * 
     * @return
     */
    public int getIVF() {
        return (Integer)getValue("customer.cdivf");
    }
    
    /**
     * 
     * @return
     */
    public String getHomePage() {
        return (String)getValue("customer.homep");
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return (String)getValue("customer.name");
    }
    
    /**
     * 
     * @return
     */
    public float getPartnerIncentiveValue() {
        return (Float)getValue("customer.vlipa");
    }
    
    /**
     * 
     * @return
     */
    public float getProductIncentiveValue() {
        return (Float)getValue("customer.vlipr");
    }
    
    /**
     * Retorna código de referência
     * @return
     */
    public String getReference() {
        return (String)getValue("customer.refer");
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
        return (Integer)getValue("customer.stdsp");
    }
    
    /**
     * @return the status
     */
    public int getStatus() {
        return (Integer)getValue("customer.status");
    }
    
    /**
     * 
     * @return
     */
    public float getSupplierIncentiveValue() {
        return (Float)getValue("customer.vlisp");
    }
    
    /**
     * 
     * @return
     */
    public int getTipoComunicacao() {
        return (Integer)getValue("customer.tpcom");
    }
    
    /**
     * 
     * @return
     */
    public int getTipoEstabelecimento() {
        return (Integer)getValue("customer.tpest");
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
        setValue("customer.aname", alternateName);
    }
    
    /**
     * 
     * @param billinc
     */
    public void setBillingIncentiveValue(float billinc) {
        setValue("customer.vlibl", billinc);
    }

    /**
     * @param codCadNac the codCadFiscal to set
     */
    public void setCodCadFiscal(long codCadFiscal) {
        setValue("customer.cnpj", codCadFiscal);
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
        setValue("customer.email", email);
    }
    
    /**
     * 
     * @param homepage
     */
    public void setHomePage(String homepage) {
        setValue("customer.homep", homepage);
    }
    
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
	    setValue("customer.ident", id);
	}
    
    /**
     * 
     * @param incentive
     */
    public void setIncentive(int incentive) {
        setValue("customer.tpinc", incentive);
    }
    
    /**
     * 
     * @param inscricaoestadual
     */
    public void setInscricaoEstadual(String inscricaoestadual) {
        setValue("customer.ie", inscricaoestadual);
    }
    
    /**
     * 
     * @param ivf
     */
    public void setIVF(int ivf) {
        setValue("customer.cdivf", ivf);
    }
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
	    setValue("customer.name", name);
	}
    
	/**
	 * 
	 * @param prtninc
	 */
    public void setPartnerIncentiveValue(float prtninc) {
        setValue("customer.vlipa", prtninc);
    }
    
    /**
     * 
     * @param prdctinc
     */
    public void setProductIncentiveValue(float prdctinc) {
        setValue("customer.vlipr", prdctinc);
    }
    
    /**
     * Ajusta código de referência
     * @param reference
     */
    public void setReference(String reference) {
        setValue("customer.refer", reference);
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
        setValue("customer.stdsp", standardsupplier);
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        setValue("customer.status", status);
    }
    
    /**
     * 
     * @param splrinc
     */
    public void setSupplierIncentiveValue(float splrinc) {
        setValue("customer.vlisp", splrinc);
    }
    
    /**
     * 
     * @param tipocomunicacao
     */
    public void setTipoComunicacao(int tipocomunicacao) {
        setValue("customer.tpcom", tipocomunicacao);
    }
    
    /**
     * 
     * @param tipoestab
     */
    public void setTipoEstabelecimento(int tipoestab) {
        setValue("customer.tpest", tipoestab);
    }
    
    @Override
    public boolean equals(Object object) {
        Customer customer;
        
        if (this == object)
            return true;
        
        if (!(object instanceof Customer))
            return false;
        
        customer = (Customer)object;
        if (getId() != customer.getId())
            return false;
        
        return true;
    }
	
    @Override
    public int hashCode() {
        return Integer.toString(getId()).hashCode();
    }

    @Override
    public int compareTo(Customer customer) {
        int r = getId() - customer.getId();
        
        if (r > 0)
            return 1;
        
        if (r < 0)
            return -1;
        
        return 0;
    }
}
