package org.eve.mm.material;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Material
 * @author francisco.prates
 *
 */
public class Material implements Serializable {
    private static final long serialVersionUID = 4251254484945797800L;
    private int materialType;
    private float netWeight;
    private float grossWeight;
    private float netPrice;
    private float quantity;
    private Date regDate;
    private String id;
    private String priceUnit;
	private String quantityUnit;
    private String regUser;
    private String reference;
    private String weightUnit;
    private Time regTime;

	public float getGrossWeight() {
		return grossWeight;
	}
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the materialType
     */
    public int getMaterialType() {
        return materialType;
    }

    /**
     * @return the netPrice
     */
    public float getNetPrice() {
        return netPrice;
    }

    /**
     * 
     * @return
     */
	public float getNetWeight() {
		return netWeight;
	}

    /**
     * 
     * @return
     */
	public String getPriceUnit() {
		return priceUnit;
	}

	public float getQuantity() {
		return quantity;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}
    
    /**
     * 
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
	public String getWeightUnit() {
		return weightUnit;
	}

    /*
     * 
     * Setters
     * 
     */

	public void setGrossWeight(float grossWeight) {
		this.grossWeight = grossWeight;
	}
    
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param materialType the materialType to set
     */
    public void setMaterialType(int materialType) {
        this.materialType = materialType;
    }

    /**
     * @param netPrice the netPrice to set
     */
    public void setNetPrice(float netPrice) {
        this.netPrice = netPrice;
    }

	public void setNetWeight(float netWeight) {
		this.netWeight = netWeight;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
    
    /**
     * 
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
     * @param user
     */
    public void setRegUser(String user) {
    	regUser = user;
    }

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
}
