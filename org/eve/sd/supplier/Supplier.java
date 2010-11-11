package org.eve.sd.supplier;

import java.sql.Time;
import java.util.Date;

import org.eve.model.AbstractDocument;
import org.eve.sd.supplier.Supplier;

public class Supplier extends AbstractDocument {
    public static final String IDENT = "id";
    public static final String DTREG = "regDate";
    public static final String TMREG = "regTime";
    public static final String USREG = "regUser";
    public static final String REFER = "reference";
    public static final String NAME = "name";
    public static final String ANAME = "alternateName";
//    public static final String CNPJ = "codCadFiscal";
//    public static final String STATUS = "status";
    private static final long serialVersionUID = -4022380613384815458L;
    private int id;
    private String reference;
    private String name;
    private String alternateName;
    private Date regDate;
    private Time regTime;
    private String regUser;

    public Supplier() {
        put(IDENT, "supplier.ident", true, datatype.INT, 10);
        put(DTREG, "supplier.dtreg", false, datatype.DATE, 10);
        put(TMREG, "supplier.tmreg", false, datatype.TIME, 8);
        put(USREG, "supplier.usreg", false, datatype.CHAR, 12);
        put(REFER, "supplier.refer", false, datatype.CHAR, 12);
        put(NAME, "supplier.name", false, datatype.CHAR, 40);
        put(ANAME, "supplier.aname", false, datatype.CHAR, 40);
//        put(CNPJ, "supplier.cnpj", false, datatype.CHAR, 18);
//        
//        put(STATUS, "supplier.status", false, datatype.INT, 1);
//        putValues(STATUS, new String[] {
//                "supplier.inactive",
//                "supplier.active"});
    }
    
    /*
     * Getters
     */
    /**
     * Retorna identificador
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * Retorna referência
     * @return
     */
    public String getReference() {
        return reference;
    }
    
    /**
     * Retorna razão social
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Retorna nome fantasia
     * @return the alternateName
     */
    public String getAlternateName() {
        return alternateName;
    }
    
    /**
     * Retorna data de registro
     * @return the creation
     */
    public Date getRegDate() {
        return regDate;
    }
    
    /**
     * Retorna hora de registro
     * @return
     */
    public Time getRegTime() {
        return regTime;
    }
    
    /**
     * Retorna usuário de registro
     * @return
     */
    public String getRegUser() {
        return regUser;
    }
    
    /*
     * Setters
     */
    /**
     * Define identificador
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Ajusta referência
     * @param reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    /**
     * Ajusta razão social
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Ajusta nome fantasia
     * @param alternateName the alternateName to set
     */
    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName;
    }
    
    /**
     * Define data de registro
     * @param creation the creation to set
     */
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    
    /**
     * Define hora de registro
     * @param regTime
     */
    public void setRegTime(Time regTime) {
        this.regTime = regTime;
    }
    
    /**
     * Define usuário de registro
     * @param regUser
     */
    public void setRegUser(String regUser) {
        this.regUser = regUser;
    }
    
    @Override
    public boolean equals(Object object) {
        Supplier supplier;
        
        if (this == object)
            return true;
        
        if (!(object instanceof Supplier))
            return false;
        
        supplier = (Supplier)object;
        if (id != supplier.getId())
            return false;
        
        return true;
    }
    
    @Override
    public int hashCode() {
        return Integer.toString(id).hashCode();
    }
}
