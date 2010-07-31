package org.eve.view;

import java.util.Map;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eve.main.EVE;

public class ComboListener implements Listener {
    private Object object;
    private Controller controller;
    private String id;
    private String reference;
    private int index;
    private int type;
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
    
    /**
     * 
     * @param type
     */
    public final void setType(int type) {
        this.type = type;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    @Override
    public final void handleEvent(Event ev) {
        Control control;
        Component component;
        Object object_ = null;
        CCombo combo = (CCombo)ev.widget;
        
        if (combo.getListVisible())
            return;
        
        if (reference != null) {
            
            component = table.get(reference);
            switch (type) {
            case EVE.multi:
                control = component.getControl(index);
                break;
                
            case EVE.single:
                control = component.getControl();
                break;
                
            default:
                return;
            }
            
            switch (component.getType()) {
            case EVE.ccombo:
                object_ = ((CCombo)control).getText();
                break;
                
            case EVE.combo:
                object_ = ((Combo)control).getText();
                break;
                
            case EVE.text:
                object_ = ((Text)control).getText();
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