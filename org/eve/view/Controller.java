package org.eve.view;

import java.util.Map;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Widget;
import org.eve.model.Model;

public interface Controller extends SelectionListener {

    public abstract void setObject(Object object);
    
    public abstract void setModel(Model model);
    
    public abstract MessageBar getMessageBar();
    
    public abstract void putForm(
            String formname, Map<String, FormComponent> form);
    
    public abstract void setMessageBar(MessageBar messageWidget);
    
    public abstract void putWidget(Widget widget, String id);
}
