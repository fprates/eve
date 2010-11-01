package org.eve.mm.material;

import java.io.Serializable;

/**
 * Material
 * @author francisco.prates
 *
 */
public class Material implements Serializable {
    private static final long serialVersionUID = 4251254484945797800L;
    private int materialType;
    private long id;
    private float netPrice;
    private String name;
    
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the materialType
     */
    public int getMaterialType() {
        return materialType;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the netPrice
     */
    public float getNetPrice() {
        return netPrice;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @param materialType the materialType to set
     */
    public void setMaterialType(int materialType) {
        this.materialType = materialType;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param netPrice the netPrice to set
     */
    public void setNetPrice(float netPrice) {
        this.netPrice = netPrice;
    }
    
}
