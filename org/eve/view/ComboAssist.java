package org.eve.view;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

public class ComboAssist {
    private int length;
    private int item;
    private ComponentType type;
    private String id;
    private String reference;
    private Map<String, ?> options;
    private Composite container;
    private Controller controller;
    private Map<String, Component> table;
    
    public ComboAssist() { }
    
    /**
     * 
     * @param container
     */
    public final void setContainer(Composite container) {
        this.container = container;
    }
    
    /**
     * 
     * @param controller
     */
    public final void setController(Controller controller) {
        this.controller = controller;
    }
    
    /**
     * 
     * @param id
     */
    public final void setId(String id) {
        this.id = id;
    }
    
    /**
     * 
     * @param item
     */
    public final void setItem(int item) {
        this.item = item;
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
     * @param options
     */
    public final void setOptions(Map<String, ?> options) {
        this.options = options;
    }
    
    /**
     * 
     * @param reference
     */
    public final void setReference(String reference) {
        this.reference = reference;
    }
    
    /**
     * 
     * @param type
     */
    public final void setType(ComponentType type) {
        this.type = type;
    }
    
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
        ComboListener listener;
        String[] values;
        
        switch(type) {
        case CCOMBO:
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

                ccombo.setText(values[0]);
                ccombo.setItems(values);
            }
            
            if (options == null) {
                listener = new ComboListener(id, controller);
                listener.setIndex(item);
                listener.setTableReference(table);
                listener.setReference(reference);
                
                ccombo.addListener(SWT.MouseDown, listener);
            }
            
            return ccombo;
            
        case COMBO:
            combo = new Combo(container, SWT.BORDER);
            
            charw = ViewUtils.getCharWidth(combo);
            charh = ViewUtils.getCharHeight(combo);

            combo.setSize(combo.computeSize((length * charw) + 35, charh));
            
            /*
             * definições para carga dinâmica de valores
             */
            if (options != null) {
                values = MapToArray(options);

                combo.setText(values[0]);
                combo.setItems(values);
            }
            
            if (options == null) {
                listener = new ComboListener(id, controller);
                listener.setTableReference(table);
                listener.setReference(reference);
                
                combo.addListener(SWT.MouseDown, listener);
            }
            
            return combo;
            
        default:
            return null;
        }
    }
}
