package org.eve.view;

import java.util.Map;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * Utilitários para visão
 * @author francisco.prates
 *
 */
public class ViewUtils {
    
    /**
     * Retorna largura do texto
     * @param text
     * @return
     */
    public static final int getCharWidth(Drawable text) {
        GC gc = new GC(text);
        int charw = gc.getFontMetrics().getAverageCharWidth();
        
        gc.dispose();
        
        return charw;
    }
    
    /**
     * Retorna altura do texto
     * @param text
     * @return
     */
    public static final int getCharHeight(Drawable text) {
        GC gc = new GC(text);
        int charh = gc.getFontMetrics().getHeight();
        
        gc.dispose();
        
        return charh;
    }

    /**
     * 
     * @param component
     * @param text
     * @param factory
     * @return
     */
    private static final Object getComboValue(
            Component component, String text, ComponentFactory factory) {
        for (String key : component.getOptions().keySet())
            if (factory.getMessage(key).equals(text))
                return component.getOptions().get(key);
        
        return null;
    }
    
    /**
     * 
     * @param component
     * @param control
     * @return
     */
    public static final Object getControlValue(
            Component component, Control control, ComponentFactory factory) {
        String text;
        
        switch (component.getType()) {
        case COMBO:
            if (!factory.isComboAssistCustomized()) {
                text = ((Combo)control).getText();
            } else {
                text = ((CCombo)control).getText();
            }
            
            return getComboValue(component, text, factory);
            
        case TEXT:
            return ((Text)control).getText();
            
        default:
            return null;
        }
    }
    
    /**
     * 
     * @param component
     * @param control
     */
    public static final void setControlSize(Component component, Control control) {
        int charw = getCharWidth(control);
        int charh = getCharHeight(control);
        
        control.setSize(control.computeSize(component.getLength() * charw, charh));
    }
    
    /**
     * 
     * @param component
     * @param control
     * @param value
     * @param factory
     */
    public static final void setControlText(Component component,
            Control control, Object value, ComponentFactory factory) {
        Map<String, ?> options = component.getOptions();
        
        switch (component.getType()) {
        case COMBO:
            for (String key : options.keySet()) {
                if (!options.get(key).equals(value))
                    continue;
                
                if (factory.isComboAssistCustomized())
                    ((CCombo)control).setText(factory.getMessage(key));
                else
                    ((Combo)control).setText(factory.getMessage(key));
                
                break;
            }
            
            break;
            
        case TEXT:
            ((Text)control).setText((String)value);
            break;
        }
    }
    
    /**
     * 
     * @param control
     * @param enabled
     */
    public static final void setEnabledControl(Control control, boolean enabled) {
        control.setEnabled(enabled);
    }
}
