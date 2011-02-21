package org.eve.view;

import java.util.Map;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class ComboListener implements Listener {
    private int index;
    private Controller controller;
    private Map<String, Component> table;
    private Map<Object, String> results;
    private Object object;
    private String id;
    private String reference;
    private ComponentFactory factory;
    
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
     * @param component
     * @param type
     * @return
     */
    private final Control getControl(Component component, int index) {
//        switch (type) {
//        case EVE.single:
//            return component.getControl();
//        
//        case EVE.multi:
//            return component.getItem(index).getControl();
//            
//        default:
            return null;
//        }
        
    }
    
    public final void setFactory(ComponentFactory factory) {
        this.factory = factory;
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
        case COMBO:
            if (((Combo)control_).getListVisible())
                return;
            break;
            
        case CCOMBO:
            if (((CCombo)control_).getListVisible())
                return;
            break;
            
        default:
            return;
        }
            
        if (reference != null) {
            component = table.get(reference);
            
            switch (component.getType()) {
            case CCOMBO:
                object_ = ((CCombo)getControl(component, index)).getText();
                break;
                
            case COMBO:
                object_ = ((Combo)getControl(component, index)).getText();
                break;
                
            case TEXT:
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
        
//        if (((reference == null) && (
//                factory.getValues(component, index).length == 0)) || reference != null)
//            container.setValues(results, index);
    }
}
