package org.eve.sd.customer;

public class CustomerAddress extends AbstractCustomerItem {
    private static final long serialVersionUID = -6527551878586866451L;
    private int cep;
    private int municipio;
    private int number;
    private int type;
    private String address;
    private String complemento;
    private String localidade;
    private String pais;
    private String estado;
    
    public CustomerAddress() {
        hash = 7;
    }

    /*
     * 
     * Getters
     * 
     */
    
    public int getType() {
        return type;
    }
    
    public String getAddress() {
        return address;
    }
    
    public int getNumber() {
        return number;
    }

    /*
     * 
     * Setters
     * 
     */
    
    public void setType(int type) {
        this.type = type;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @return the localidade
     */
    public String getLocalidade() {
        return localidade;
    }

    /**
     * @return the cEP
     */
    public int getCEP() {
        return cep;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @return the municipio
     */
    public int getMunicipio() {
        return municipio;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @param localidade the localidade to set
     */
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    /**
     * @param cEP the cEP to set
     */
    public void setCEP(int cep) {
        this.cep = cep;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(int municipio) {
        this.municipio = municipio;
    }

}
