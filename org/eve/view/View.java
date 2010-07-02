package org.eve.view;

import java.util.List;
import java.util.Locale;

import org.eclipse.swt.widgets.Composite;
import org.eve.view.ViewAction;
import org.springframework.context.support.ResourceBundleMessageSource;

public interface View {
    
    /*
     * Setters
     */
    public abstract void setContainer(Composite container);
    
    public abstract void setController(Controller controller);
    
    public abstract void setMessages(ResourceBundleMessageSource messages);
    
    public abstract void setLocale(Locale locale);
    
    /*
     * Getters
     */
    public abstract Composite getContainer();
    
    public abstract List<ViewAction> getActions();

    public abstract String getName();
    
    /*
     * Others
     */
    public abstract void buildView();
    
    public abstract void reload(String actionText);

}
