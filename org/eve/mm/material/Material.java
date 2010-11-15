package org.eve.mm.material;

import java.sql.Time;
import java.util.Date;

import org.eve.model.AbstractDocument;

/**
 * Material
 * @author francisco.prates
 *
 */
public class Material extends AbstractDocument implements Comparable<Material> {
    public static final String IDENT = "id";
    public static final String REFER = "reference";
    public static final String DTREG = "regDate";
    public static final String TMREG = "regTime";
    public static final String USREG = "regUser";
    public static final String UNDPS = "weightUnit";
    public static final String PSLIQ = "netWeight";
    public static final String UNDQT = "quantityUnit";
    public static final String QUANT = "quantity";
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

    public Material() {
        put(IDENT, "material.ident", true, datatype.CHAR, 12);        
        put(REFER, "material.refer", false, datatype.CHAR, 60);
        put(DTREG, "material.dtreg", false, datatype.DATE, 10);
        put(TMREG, "material.tmreg", false, datatype.TIME, 8);
        put(USREG, "material.usreg", false, datatype.CHAR, 12);
        put(UNDPS, "material.undps", false, datatype.CHAR, 3);
        put(PSLIQ, "material.psliq", false, datatype.FLOAT,13);
        put(UNDQT, "material.undqt", false, datatype.CHAR,3);
        put(QUANT, "material.quant", false, datatype.FLOAT,13);
    }
    
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
    @Override
    public int compareTo(Material material) {
        return id.compareTo(material.getId());
    }
}
