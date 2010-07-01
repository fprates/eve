/**
 * 
 */
package org.eve.main;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeItem;
//import org.eclipse.swt.widgets.Widget;

/**
 * @author francisco.prates
 *
 */
public class GeneralListener implements Listener, SelectionListener {
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

    @Override
    public void widgetDefaultSelected(SelectionEvent ev) { }

    @Override
    public void widgetSelected(SelectionEvent ev) {
//        Button item = (Button)ev.getSource();
//        View view = toolitemViews.get(item);
//        
//        view.userWidgetInput(item);
    }

}
