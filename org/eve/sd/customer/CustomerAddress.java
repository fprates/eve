package org.eve.sd.customer;

import org.eve.model.DataType;

public class CustomerAddress extends AbstractCustomerItem {
    private static final long serialVersionUID = -6527551878586866451L;
    
    public CustomerAddress() {
        hash = 7;
        
        put("address.logra", NOT_KEY, DataType.CHAR, 60);
        put("address.numer", NOT_KEY, DataType.INT, 5);
        put("address.compl", NOT_KEY, DataType.CHAR, 8);
        put("address.cdend", NOT_KEY, DataType.INT, 8);
        put("address.coduf", NOT_KEY, DataType.CHAR, 2);
        put("address.munic", NOT_KEY, DataType.INT, 40);
        put("address.cntry", NOT_KEY, DataType.CHAR, 3);
        put("address.type", NOT_KEY, DataType.INT, 1);
        put("address.local", NOT_KEY, DataType.CHAR, 20);
    }

    /*
     * 
     * Getters
     * 
     */
    
    public int getType() {
        return (Integer)getValue("address.type");
    }
    
    /**
     * 
     * @return
     */
    public String getAddress() {
        return (String)getValue("address.logra");
    }
    
    /**
     * 
     * @return
     */
    public int getNumber() {
        return (Integer)getValue("address.numer");
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return (String)getValue("address.compl");
    }

    /**
     * @return the localidade
     */
    public String getLocalidade() {
        return (String)getValue("address.local");
    }

    /**
     * @return the cEP
     */
    public int getCEP() {
        return (Integer)getValue("address.cdend");
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return (String)getValue("address.cntry");
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return (String)getValue("address.coduf");
    }

    /**
     * @return the municipio
     */
    public int getMunicipio() {
        return (Integer)getValue("address.munic");
    }

    /*
     * 
     * Setters
     * 
     */
    
    /**
     * 
     * @param type
     */
    public void setType(int type) {
        setValue("address.type", type);
    }
    
    /**
     * 
     * @param address
     */
    public void setAddress(String address) {
        setValue("address.logra", address);
    }
    
    /**
     * 
     * @param number
     */
    public void setNumber(int number) {
        setValue("address.numer", number);
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        setValue("address.compl", complemento);
    }

    /**
     * @param localidade the localidade to set
     */
    public void setLocalidade(String localidade) {
        setValue("address.local", localidade);
    }

    /**
     * @param cEP the cEP to set
     */
    public void setCEP(int cep) {
        setValue("address.cdend", cep);
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        setValue("address.cntry", pais);
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        setValue("address.coduf", estado);
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(int municipio) {
        setValue("address.munic", municipio);
    }

}
