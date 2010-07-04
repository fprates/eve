package org.eve.view;

import java.util.Map;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Widget;
import org.eve.main.EveAPI;
import org.eve.model.Model;

public interface Controller extends SelectionListener {

    /*
     * 
     * Setters
     * 
     */
    public abstract void setObject(Object object);
    
    public abstract void setModel(Model model);
    
    public abstract void setMessageBar(MessageBar messageWidget);
    
    public abstract void setAction(String action);
    
    public abstract void setSystem(EveAPI system);
    
    public abstract void setForms(Map<String, Form> forms);
    
    /*
     * 
     * Getters
     * 
     */
    
    public abstract Object getObject();
    
    public abstract MessageBar getMessageBar();
    
    public abstract Form getForm(String formname);
    
    /*
     * 
     * Others
     * 
     */
    
    public abstract void putWidget(Widget widget, String id);
}
