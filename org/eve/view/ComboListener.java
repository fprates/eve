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
    
    /**
     * 
     * @param component
     * @param type
     * @return
     */
    private final Control getControl(Component component, int index) {
        switch (type) {
        case EVE.single:
            return component.getControl();
        
        case EVE.multi:
            return component.getControl(index);
            
        default:
            return null;
        }
        
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    @Override
    public final void handleEvent(Event ev) {
        Component component;
        Component component_ = table.get(id);
        Control control_ = getControl(component_, index);
        Object object_ = null;
        
        switch (component_.getType()) {
        case EVE.combo:
            if (((Combo)control_).getListVisible())
                return;
            break;
            
        case EVE.ccombo:
            if (((CCombo)control_).getListVisible())
                return;
            break;
            
        default:
            return;
        }
            
        if (reference != null) {
            component = table.get(reference);
            
            switch (component.getType()) {
            case EVE.ccombo:
                object_ = ((CCombo)getControl(component, index)).getText();
                break;
                
            case EVE.combo:
                object_ = ((Combo)getControl(component, index)).getText();
                break;
                
            case EVE.text:
                object_ = ((Text)getControl(component, index)).getText();
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
        
        switch (component_.getType()) {
        case EVE.combo:
            ((Combo)control_).removeAll();
            for (Object value : results.keySet())
                ((Combo)control_).add(results.get(value));
            
            break;
            
        case EVE.ccombo:
            ((CCombo)control_).removeAll();
            for (Object value : results.keySet())
                ((CCombo)control_).add(results.get(value));
            
            break;
        }
    }    
}
