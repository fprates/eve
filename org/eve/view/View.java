package org.eve.view;

import java.util.List;
import java.util.Locale;

import org.eclipse.swt.widgets.Composite;
import org.eve.main.EveAPI;
import org.eve.view.ViewAction;
import org.springframework.context.support.ResourceBundleMessageSource;

public interface View {
    
    /*
     * Setters
     */
    
    public abstract void setMessages(ResourceBundleMessageSource messages);
    
    public abstract void setLocale(Locale locale);
    
    public abstract void setSystem(EveAPI system);
    
    /*
     * Getters
     */
    
    public abstract List<ViewAction> getActions();

    public abstract String getName();
    
    public abstract Composite getContainer();
    
    /*
     * Others
     */
    
    public abstract void buildView(Composite container);
    
    public abstract void reload(String actionText);

}
