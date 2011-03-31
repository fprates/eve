package org.eve.view;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

public class ComboAssist {
    private int length;
    private boolean customized;
    private ComponentFactory factory;
    private Map<String, ?> options;
    private Composite container;
    
    public ComboAssist() {
        customized = false;
    }
    
    public final boolean isCustomized() {
        return customized;
    }
    
    /**
     * 
     * @param container
     */
    public final void setContainer(Composite container) {
        this.container = container;
    }
    
    /**
     * 
     * @param customized
     */
    public final void setCustomized(boolean customized) {
        this.customized = customized;
    }
    
    /**
     * 
     * @param length
     */
    public final void setLength(int length) {
        this.length = length;
    }
    
    /**
     * 
     * @param factory
     */
    public final void setFactory(ComponentFactory factory) {
        this.factory = factory;
    }
    
    /**
     * 
     * @param options
     */
    public final void setOptions(Map<String, ?> options) {
        this.options = options;
    }
    
    /**
     * 
     * @param map
     * @return
     */
    private final String[] MapToArray(Map<String, ?> map) {
        String[] values;
        int k = map.size();
        
        if (k == 0)
            return null;
        
        values = new String[k];
        
        k = 0;
        for (String key : options.keySet())
            values[k++] = key;
        
        return values;
    }
    
    /**
     * 
     * @return
     */
    public final Widget newInstance() {
        int charh;
        int charw;
        CCombo ccombo;
        Combo combo;
        String[] values;
        
        if (customized) {
            ccombo = new CCombo(container, SWT.NONE);
            
            charw = ViewUtils.getCharWidth(ccombo);
            charh = ViewUtils.getCharHeight(ccombo);
            
            ccombo.setSize(ccombo.computeSize(
                    (length * charw) + 40, charh));
            
            /*
             * definições para carga dinâmica de valores
             */
            if (options != null) {
                values = MapToArray(options);

                ccombo.setText(factory.getMessage(values[0]));
                for (String value : values)
                    ccombo.add(factory.getMessage(value));
            }
            
            return ccombo;
            
        } else {
            combo = new Combo(container, SWT.BORDER);
            
            charw = ViewUtils.getCharWidth(combo);
            charh = ViewUtils.getCharHeight(combo);

            combo.setSize(combo.computeSize((length * charw) + 35, charh));
            
            /*
             * definições para carga dinâmica de valores
             */
            if (options != null) {
                values = MapToArray(options);

                combo.setText(factory.getMessage(values[0]));
                for (String value : values)
                    combo.add(factory.getMessage(value));
            }
            
            return combo;
        }
    }
}
