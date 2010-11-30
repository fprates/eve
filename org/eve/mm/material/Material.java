package org.eve.mm.material;

import org.eve.model.AbstractDocument;

/**
 * Material
 * @author francisco.prates
 *
 */
public class Material extends AbstractDocument implements Comparable<Material> {
    public static final String IDENT = "id";
    public static final String REFER = "reference";
    public static final String UNDPS = "weightUnit";
    public static final String PSLIQ = "netWeight";
    public static final String UNDQT = "quantityUnit";
    public static final String QUANT = "quantity";
    public static final String MOEDA = "currency";
    public static final String VLCST = "netPrice";
    public static final String LNGTH = "length";
    public static final String WIDTH = "width";
    public static final String THICK = "thickness";
    private static final long serialVersionUID = 4251254484945797800L;
    private int materialType;
    private float netWeight;
    private float grossWeight;
    private float netPrice;
    private float quantity;
    private float length;
    private float width;
    private float thickness;
    private String id;
    private String currency;
	private String quantityUnit;
    private String reference;
    private String weightUnit;

    public Material() {
        put(IDENT, "material.ident", true, datatype.CHAR, 12);        
        put(REFER, "material.refer", false, datatype.CHAR, 60);
        put(UNDPS, "material.undps", false, datatype.CHAR, 3);
        put(PSLIQ, "material.psliq", false, datatype.FLOAT,13);
        put(UNDQT, "material.undqt", false, datatype.CHAR,3);
        put(QUANT, "material.quant", false, datatype.FLOAT,13);
        put(MOEDA, "material.moeda", false, datatype.CHAR, 3);
        put(VLCST, "material.vlcst", false, datatype.FLOAT, 12);
        put(LNGTH, "material.lngth", false, datatype.FLOAT, 12);
        put(WIDTH, "material.width", false, datatype.FLOAT, 12);
        put(THICK, "material.thick", false, datatype.FLOAT, 12);
    }

    /**
     * 
     * @return
     */
    public String getCurrency() {
        return currency;
    }
    
    /**
     * 
     * @return
     */
	public float getGrossWeight() {
		return grossWeight;
	}
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    public float getLength() {
        return length;
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
	public float getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @return
	 */
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
    
    public float getThickness() {
        return thickness;
    }

    /**
     * 
     * @return
     */
	public String getWeightUnit() {
		return weightUnit;
	}

	public float getWidth() {
	    return width;
	}
	
    /*
     * 
     * Setters
     * 
     */

    public void setCurrency(String currency) {
        this.currency = currency;
    }

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
     * 
     * @param length
     */
    public void setLength(float length) {
        this.length = length;
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
    
    /**
     * 
     * @param reference
     */
    public void setReference(String reference) {
    	this.reference = reference;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
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

	public void setWidth(float width) {
	    this.width = width;
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
