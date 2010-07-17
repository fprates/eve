/**
 * 
 */
package org.eve.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;
import org.eve.main.EVE;

/**
 * @author francisco.prates
 *
 */
public class TableComponent {
    private String name;
    private int type;
    private String[] options;
    private List<Control> controls;
    private boolean enabled;
    
    public TableComponent(String name) {
        this.name = name;
        type = EVE.text;
        controls = new ArrayList<Control>();
        enabled = true;
    }

    /*
     * 
     * Setters
     * 
     */

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
     * @param editable
     */
    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
        
        for (Control control : controls)
            control.setEnabled(enabled);
    }
    
    /*
     * 
     * Getters
     * 
     */
    /**
     * 
     * @return
     */
    public final String getName() {
        return name;
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
     * @return
     */
    public final String[] getOptions() {
        return options;
    }
    
    /**
     * 
     * @param row
     * @return
     */
    public final Widget getControl(int row) {
        return controls.get(row);
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
     * @param id
     * @param controller
     * @return
     */
    public final Listener listener(String id, Controller controller) {
        return new ComponentListener(id, controller);
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * 
     */
    public final void addControl(Control control) {
        controls.add(control);
    }
}

class ComponentListener implements Listener {
    private String id;
    private Controller controller;
    
    public ComponentListener(String id, Controller controller) {
        this.id = id;
        this.controller = controller;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    @Override
    public void handleEvent(Event ev) {
        Object[] results;
        CCombo combo = (CCombo)ev.widget;
        
        if (combo.getListVisible())
            return;
        
        combo.clearSelection();
        combo.removeAll();
        results = controller.getResults(id);
        
        for (Object object : results)
            combo.add((String)object);
    }
    
}