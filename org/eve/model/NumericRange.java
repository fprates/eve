/**
 * 
 */
package org.eve.model;

import java.io.Serializable;

/**
 * @author francisco.prates
 *
 */
public class NumericRange implements Serializable {
    private static final long serialVersionUID = -4216318209033157342L;
    private String range;
    private int current;
    
    /**
     * 
     */
    public NumericRange() { }

    /**
     * @return the range
     */
    public String getRange() {
        return range;
    }

    /**
     * @return the current
     */
    public int getCurrent() {
        return current;
    }

    /**
     * @param range the range to set
     */
    public void setRange(String range) {
        this.range = range;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(int current) {
        this.current = current;
    }
}
