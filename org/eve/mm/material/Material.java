package org.eve.mm.material;

import org.eve.model.AbstractDocument;
import org.eve.model.DataType;

/**
 * Material
 * @author francisco.prates
 *
 */
public class Material extends AbstractDocument implements Comparable<Material> {
    private static final long serialVersionUID = 4251254484945797800L;

    public Material() {
        put("material.ident", true, DataType.CHAR, 12);        
        put("material.refer", false, DataType.CHAR, 60);
        put("material.undps", false, DataType.CHAR, 3);
        put("material.psliq", false, DataType.FLOAT,13);
        put("material.undqt", false, DataType.CHAR, 3);
        put("material.quant", false, DataType.FLOAT, 13);
        put("material.moeda", false, DataType.CHAR, 3);
        put("material.vlliq", false, DataType.FLOAT, 12);
        put("material.lngth", false, DataType.FLOAT, 12);
        put("material.width", false, DataType.FLOAT, 12);
        put("material.thick", false, DataType.FLOAT, 12);
    }

    /**
     * 
     * @return
     */
    public String getCurrency() {
        return (String)getValue("material.moeda");
    }
    
    /**
     * 
     * @return
     */
	public float getGrossWeight() {
		return (Float)getValue("material.psbrt");
	}
    
    /**
     * @return the id
     */
    public String getId() {
        return (String)getValue("material.ident");
    }
    
    /**
     * 
     * @return
     */
    public float getLength() {
        return (Float)getValue("material.lngth");
    }

    /**
     * @return the materialType
     */
    public int getMaterialType() {
        return (Integer)getValue("material.type");
    }

    /**
     * @return the netPrice
     */
    public float getNetPrice() {
        return (Float)getValue("material.vlliq");
    }

    /**
     * 
     * @return
     */
	public float getNetWeight() {
		return (Float)getValue("material.psliq");
	}

	/**
	 * 
	 * @return
	 */
	public float getQuantity() {
		return (Float)getValue("material.quant");
	}

	/**
	 * 
	 * @return
	 */
	public String getQuantityUnit() {
		return (String)getValue("material.unqtd");
	}
    
    /**
     * 
     * @return
     */
    public String getReference() {
    	return (String)getValue("material.refer");
    }
    
    /**
     * 
     * @return
     */
    public float getThickness() {
        return (Float)getValue("material.thick");
    }

    /**
     * 
     * @return
     */
	public String getWeightUnit() {
		return (String)getValue("material.undps");
	}

	/**
	 * 
	 * @return
	 */
	public float getWidth() {
	    return (Float)getValue("material.width");
	}
    
    /*
     * 
     * Setters
     * 
     */

	/**
	 * 
	 * @param currency
	 */
    public void setCurrency(String currency) {
        setValue("material.moeda", currency);
    }

    /**
     * 
     * @param grossWeight
     */
	public void setGrossWeight(float grossWeight) {
	    setValue("material.psbrt", grossWeight);
	}
    
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        setValue("material.ident", id);
    }
    
    /**
     * 
     * @param length
     */
    public void setLength(float length) {
        setValue("material.lngth", length);
    }

    /**
     * @param materialType the materialType to set
     */
    public void setMaterialType(int materialType) {
        setValue("material.type", materialType);
    }

    /**
     * @param netPrice the netPrice to set
     */
    public void setNetPrice(float netPrice) {
        setValue("material.vlliq", netPrice);
    }

    /**
     * 
     * @param netWeight
     */
	public void setNetWeight(float netWeight) {
	    setValue("material.psliq", netWeight);
	}
    
    /**
     * 
     * @param reference
     */
    public void setReference(String reference) {
        setValue("material.refer", reference);
    }

    /**
     * 
     * @param thickness
     */
    public void setThickness(float thickness) {
        setValue("material.thick", thickness);
    }
    
    /**
     * 
     * @param quantity
     */
	public void setQuantity(float quantity) {
	    setValue("material.quant", quantity);
	}

	/**
	 * 
	 * @param quantityUnit
	 */
	public void setQuantityUnit(String quantityUnit) {
	    setValue("material.undqt", quantityUnit);
	}

	/**
	 * 
	 * @param weightUnit
	 */
	public void setWeightUnit(String weightUnit) {
	    setValue("material.undps", weightUnit);
	}

	/**
	 * 
	 * @param width
	 */
	public void setWidth(float width) {
	    setValue("material.width", width);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
    @Override
    public int compareTo(Material material) {
        return getId().compareTo(material.getId());
    }
}
