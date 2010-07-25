package org.eve.view;

import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Widget;
import org.eve.main.EveAPI;
import org.eve.model.Model;
import org.springframework.context.MessageSource;

/**
 * Interface do controlador
 * @author francisco.prates
 *
 */
public interface Controller extends SelectionListener {

    /*
     * 
     * Setters
     * 
     */
    
    public abstract void setObject(Object object);
    
    public abstract void setModel(Model model);
    
    public abstract void setAction(String action);
    
    public abstract void setSystem(EveAPI system);
    
    public abstract void setForms(Map<String, Form> forms);
    
    public abstract void setTables(Map<String, TableAssist> tables);
    
    public abstract void setViews(Map<String, View> views);
    
    public abstract void setMessages(MessageSource messages);
    
    public abstract void setLocale(Locale locale);
    
    /*
     * 
     * Getters
     * 
     */
    
    public abstract Object getObject();
    
    public abstract Form getForm(String formname);
    
    public abstract TableAssist getTable(String tablename);
    
    public abstract Map<String, View> getViews();
    
    public abstract MessageSource getMessages();
    
    public abstract Object[] getResults(String id, Object object);
    
    /*
     * 
     * Others
     * 
     */
    
    public abstract void putWidget(Widget widget, String id);
}
