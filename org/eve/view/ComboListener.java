package org.eve.view;

import java.util.Map;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eve.main.EVE;

public class ComboListener implements Listener {
    private Object object;
    private Controller controller;
    private String id;
    private String reference;
    private int index;
    private Map<String, Component> table;
    private Map<Object, String> results;
    
    public ComboListener(String id, Controller controller) {
        object = null;
        this.id = id;
        this.controller = controller;
    }

    /**
     * 
     * @param table
     */
    public final void setTableReference(Map<String, Component> table) {
        this.table = table;
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
     * @param index
     */
    public final void setIndex(int index) {
        this.index = index;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    @Override
    public final void handleEvent(Event ev) {
        Object object_;
        Component component;
        CCombo combo = (CCombo)ev.widget;
        
        if (combo.getListVisible())
            return;
        
        if (reference != null) {
            component = table.get(reference);
            switch (component.getType()) {
            case EVE.table:
                object_ = ((CCombo)component.getControl(index)).getText();
                break;
                
            case EVE.form:
                object_ = ((Combo)component.getControl()).getText();
                break;
                
            default:
                return;
            }
            
            if (object_ == null || object_.equals(object))
                return;
            
            object = object_;
        }
        
        results = controller.getResults(id, object);
        
        if (results == null)
            return;
        
        combo.removeAll();
        for (Object value : results.keySet())
            combo.add(results.get(value));
    }    
}