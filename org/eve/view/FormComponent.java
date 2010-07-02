/**
 * 
 */
package org.eve.view;

import org.eclipse.swt.widgets.Text;

/**
 * @author francisco.prates
 *
 */
public class FormComponent {
    private String name;
    private int length;
    private boolean enabled;
    private Text text;
    
    /**
     * 
     * @param name
     * @param length
     * @param enabled
     */
    public FormComponent(String name, int length, boolean enabled) {
        this.name = name;
        this.length = length;
        this.enabled = enabled;
    }
    
    /**
     * 
     * @return the name
     */
    public final String getName() {
        return name;
    }
    /**
     * 
     * @return the length
     */
    public final int getLength() {
        return length;
    }
    
    /**
     * 
     * @return
     */
    public final boolean isEnabled() {
        return enabled;
    }
    
    /**
     * 
     * @return
     */
    public final Text getTextWidget() {
    	return text;
    }
    
    /**
     * 
     * @param text
     */
    public final void setText(Text text) {
    	this.text = text;
    }

}
