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
    private float netPrice;
    private Date regDate;
    private String id;
    private String name;
    private Time regTime;
    
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
    
}
