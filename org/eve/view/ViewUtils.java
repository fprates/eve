package org.eve.view;

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

    public static final Object getControlValue(
            Component component, Control control) {
        switch (component.getType()) {
        case COMBO:
            return component.getOptions().get(((Combo)control).getText());
            
        case TEXT:
            return ((Text)control).getText();
            
        default:
            return null;
        }
    }
    
    public static final void setControlSize(Component component, Control control) {
        int charw = getCharWidth(control);
        int charh = getCharHeight(control);
        
        control.setSize(control.computeSize(component.getLength() * charw, charh));
    }
    
    public static final void setControlText(Component component, Control control, String value) {
        switch (component.getType()) {
        case COMBO:
            ((Combo)control).setText(value);
            break;
        case TEXT:
            ((Text)control).setText(value);
            break;
        }
    }
    
    public static final void setEnabledControl(Control control, boolean enabled) {
        control.setEnabled(enabled);
    }
}
