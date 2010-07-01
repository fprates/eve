package org.eve.view;

import java.util.List;
import java.util.Locale;

import org.eclipse.swt.widgets.Composite;
import org.eve.Model;
import org.eve.view.ViewAction;
import org.springframework.context.support.ResourceBundleMessageSource;

public interface View {
    
    /*
     * Setters
     */
    public abstract void setContainer(Composite container);
    
    public abstract void setModel(Model model);
    
    public abstract void setMessages(ResourceBundleMessageSource messages);
    
    public abstract void setLocale(Locale locale);
    
    /*
     * Getters
     */
    public abstract Composite getContainer();
    
    public abstract Model getModel();
    
    public abstract List<ViewAction> getActions();

    public abstract String getName();
    
    /*
     * Others
     */
    public abstract void buildView();
    
    public abstract void reload(String actionText);

}
