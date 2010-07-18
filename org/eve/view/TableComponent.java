/**
 * 
 */
package org.eve.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private ComponentListener complistener;
    
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
     * @param table
     * @return
     */
    public final Listener listener(String id, Controller controller, int index) {
        complistener = new ComponentListener(id, controller, index);
        
        return complistener;
    }
    
    /**
     * 
     * @param table
     */
    public final void setTableReference(Map<String, TableComponent> table) {
        complistener.setTableReference(table);
    }
    
    /**
     * 
     * @param id
     */
    public final void setListenerReference(String id) {
        complistener.setReference(id);
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
    private int index;
    private String id;
    private Controller controller;
    private String reference;
    private Map<String, TableComponent> table;
    
    public ComponentListener(String id, Controller controller, int index) {
        this.id = id;
        this.controller = controller;
        this.index = index;
    }
    
    /**
     * 
     * @param table
     */
    public final void setTableReference(Map<String, TableComponent> table) {
        this.table = table;
    }
    
    /**
     * 
     * @param reference
     */
    public final void setReference(String reference) {
        this.reference = reference;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    @Override
    public final void handleEvent(Event ev) {
        Object object;
        Object[] results;
        TableComponent component;
        CCombo combo = (CCombo)ev.widget;
        
        if (combo.getListVisible())
            return;
        
        combo.clearSelection();
        combo.removeAll();
        
        if (reference != null) {
            component = table.get(reference);
            switch (component.getType()) {
            case EVE.combo:
                object = ((CCombo)component.getControl(index)).getText();
                break;
                
            default:
                object = null;
                break;
            } 
        } else {
            object = null;
        }
        
        results = controller.getResults(id, object);
        
        if (results == null)
            return;
        
        for (Object object_ : results)
            combo.add((String)object_);
    }
    
}