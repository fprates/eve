/**
 * 
 */
package org.eve.view;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eve.main.EVE;

/**
 * @author francisco.prates
 *
 */
public class FormComponent {
    private String name;
    private int length;
    private boolean enabled;
    private Control control;
    private int type;
    private String[] options;
    
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
        type = EVE.text;
        
    }
    
    /*
     * 
     * Getters
     * 
     */
    
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
    public final Control getControl() {
    	return control;
    }
    
    /**
     * 
     * @return
     */
    public final String[] getOptions() {
        return options;
    }
    
    /**
     * 
     * @return
     */
    public final String getText() {
        switch (type) {
        case EVE.text:
            return ((Text)control).getText();
            
        case EVE.combo:
            return ((CCombo)control).getText();
        }
        
        return null;
    }
    
    /**
     * 
     * @return
     */
    public final int getType() {
        return type;
    }
    
    /**
     * 
     * @param index
     * @return
     */
    public final String getOption(int index) {
        return options[index];
    }
    
    /*
     * 
     * Setters
     * 
     */    
    
    /**
     * 
     * @param text
     */
    public final void setControl(Control control) {
    	this.control = control;
    }
    
    /**
     * 
     * @param type
     */
    public final void setType(int type) {
        this.type = type;
    }
    
    /**
     * 
     * @param options
     */
    public final void setOptions(String[] options) {
        this.options = options;
    }
    
    /**
     * 
     * @param text
     */
    public final void setText(String text) {
        switch (type) {
        case EVE.text:
            ((Text)control).setText(text);
            break;
            
        case EVE.combo:
            ((CCombo)control).setText(text);
            break;
            
        }
    }
    
    /**
     * 
     * @param enabled
     */
    public final void setEnabled(boolean enabled) {
        switch (type) {
        case EVE.text:
            ((Text)control).setEnabled(enabled);
            break;
            
        case EVE.combo:
            ((CCombo)control).setEnabled(enabled);
            break;
        }
    }
}
