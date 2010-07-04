/**
 * 
 */
package org.eve.main;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeItem;

/**
 * @author francisco.prates
 *
 */
public class GeneralListener implements Listener {
    private Map<TreeItem, String> selectorActions;
    private EveApp system;
    
    public GeneralListener() {        
        selectorActions = new HashMap<TreeItem, String>();
    }
    
    public final void setSystem(EveApp system) {
        this.system = system;
    }
    
    public final void putSelectorItem(TreeItem item, String action) {
        selectorActions.put(item, action);
    }
    
    @Override
    public void handleEvent(Event ev) {
        TreeItem item = (TreeItem)ev.item;
        String action = selectorActions.get(item);
        
        system.call(action);
    }
}
