/**
 * 
 */
package org.eve.sd.customer;

import java.sql.Time;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.eve.model.AbstractDocument;

/**
 * @author francisco.prates
 *
 */
public class Customer extends AbstractDocument {
    public static final String IDENT = "id";
    public static final String DTREG = "regDate";
    public static final String TMREG = "regTime";
    public static final String USREG = "regUser";
    public static final String TPINC = "incentive";
    public static final String CDIVF = "IVF";
    public static final String STATUS = "status";
    public static final String STDSP = "standardSupplier";
    public static final String TPEST = "tipoEstabelecimento";
    public static final String TPCOM = "tipoComunicacao";
    public static final String REFER = "reference";
    public static final String NAME = "name";
    public static final String ANAME = "alternateName";
    public static final String CNPJ = "codCadFiscal";
    public static final String IE = "inscricaoEstadual";
    public static final String EMAIL = "email";
    public static final String HOMEP = "homePage";
    public static final String VLIPR = "productIncentiveValue";
    public static final String VLIBL = "billingIncentiveValue";
    public static final String DVCSP = "supplierIncentiveValue";
    public static final String DVCPT = "partnerIncentiveValue";
    
	private static final long serialVersionUID = 3061311103322489445L;
    private float prdctinc;
    private float billinc;
    private float splrinc;
    private float prtninc;
	private int id;
    private int incentive;
    private int ivf;
    private int status;
    private int standardsupplier;
    private int tipoestab;
    private int tipocomunicacao;
    private long inscricaoestadual;
	private Date regDate;
    private Set<CustomerContact> contacts;
    private Set<CustomerAddress> addresses;
    private Set<CustomerSchedule> schedule;
    private String regUser;
    private String codCadFiscal;
    private String homepage;
    private String email;
    private String reference;
    private String name;
    private String alternateName;
    private Time regTime;
	
	public Customer() {
	    contacts = new TreeSet<CustomerContact>();
	    addresses = new TreeSet<CustomerAddress>();
	    schedule = new TreeSet<CustomerSchedule>();
	    
	    put(IDENT, "customer.ident", true, datatype.INT, 10);
        put(DTREG, "customer.dtreg", false, datatype.DATE, 10);
        put(TMREG, "customer.tmreg", false, datatype.TIME, 8);
        put(USREG, "customer.usreg", false, datatype.CHAR, 12);
        put(REFER, "customer.refer", false, datatype.CHAR, 12);
        put(NAME, "customer.name", false, datatype.CHAR, 40);
        put(ANAME, "customer.aname", false, datatype.CHAR, 40);
        put(CNPJ, "customer.cnpj", false, datatype.CHAR, 18);
        put(IE, "customer.ie", false, datatype.LONG, 12);
        put(HOMEP, "customer.homep", false, datatype.CHAR, 128);
        setLowerCase(HOMEP);
        put(EMAIL, "customer.email", false, datatype.CHAR, 128);
        setLowerCase(EMAIL);
        put(VLIPR, "customer.vlipr", false, datatype.FLOAT, 13);
        put(VLIBL, "customer.vlibl", false, datatype.FLOAT, 7);
        put(DVCSP, "customer.dvcsp", false, datatype.FLOAT, 7);
        put(DVCPT, "customer.dvcpt", false, datatype.FLOAT, 7);

        put(TPINC, "customer.tpinc", false, datatype.INT, 1);
        putValues(TPINC, new String[] {
                "incentive.free",
                "incentive.product",
                "incentive.bill"});
        
        put(CDIVF, "customer.cdivf", false, datatype.INT, 1);
        putValues(CDIVF, new String[] {"A", "B", "C"});
        
        put(STATUS, "customer.status", false, datatype.INT, 1);
        putValues(STATUS, new String[] {
                "customer.inactive",
                "customer.active"});
        
        put(STDSP, "customer.stdsp", false, datatype.INT, 10);
        put(TPEST, "customer.tpest", false, datatype.INT, 12);
        putValues(TPEST, new String[] {
                "tpest.super",
                "tpest.indus",
                "tpest.reven"});
        
        put(TPCOM, "customer.tpcom", false, datatype.INT, 1);
        putValues(TPCOM, new String[] {
                "tpcom.blue",
                "tpcom.green",
                "tpcom.red",
                "tpcom.yellow"});
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
    public long getInscricaoEstadual() {
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
    public void setInscricaoEstadual(long inscricaoestadual) {
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
