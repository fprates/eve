package org.eve.sd.supplier;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eve.model.AbstractDocument;
import org.eve.model.DataType;
import org.eve.sd.supplier.Supplier;

public class Supplier extends AbstractDocument {
    private static final long serialVersionUID = -4022380613384815458L;

    public Supplier() {
        Map<String, Integer> spstatus = new LinkedHashMap<String, Integer>();
        
        spstatus.put("supplier.status", 0);
        spstatus.put("supplier.active", 1);
        
        put("supplier.ident", true, DataType.INT, 10);
        put("supplier.refer", false, DataType.CHAR, 12);
        put("supplier.name", false, DataType.CHAR, 40);
        put("supplier.aname", false, DataType.CHAR, 40);
        put("supplier.cnpj", false, DataType.CHAR, 18);
        
        put("supplier.status", false, DataType.INT, 1);
        putValues("supplier.status", spstatus);
    }
    
    /*
     * Getters
     */
    /**
     * Retorna identificador
     * @return the id
     */
    public int getId() {
        return (Integer)getValue("supplier.ident");
    }
    
    /**
     * Retorna referência
     * @return
     */
    public String getReference() {
        return (String)getValue("supplier.refer");
    }
    
    /**
     * Retorna razão social
     * @return the name
     */
    public String getName() {
        return (String)getValue("supplier.name");
    }
    
    /**
     * Retorna nome fantasia
     * @return the alternateName
     */
    public String getAlternateName() {
        return (String)getValue("supplier.aname");
    }
    
    /*
     * Setters
     */
    
    /**
     * Define identificador
     * @param id the id to set
     */
    public void setId(int id) {
        setValue("supplier.ident", id);
    }
    
    /**
     * Ajusta referência
     * @param reference
     */
    public void setReference(String reference) {
        setValue("supplier.refer", reference);
    }
    
    /**
     * Ajusta razão social
     * @param name the name to set
     */
    public void setName(String name) {
        setValue("supplier.name", name);
    }
    
    /**
     * Ajusta nome fantasia
     * @param alternateName the alternateName to set
     */
    public void setAlternateName(String alternateName) {
        setValue("supplier.aname", alternateName);
    }
    
    @Override
    public boolean equals(Object object) {
        Supplier supplier;
        
        if (this == object)
            return true;
        
        if (!(object instanceof Supplier))
            return false;
        
        supplier = (Supplier)object;
        if (getId() != supplier.getId())
            return false;
        
        return true;
    }
    
    @Override
    public int hashCode() {
        return Integer.toString(getId()).hashCode();
    }
}
