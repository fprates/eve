package org.eve.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.eclipse.swt.widgets.Composite;
import org.eve.Model;
import org.eve.view.ViewAction;
import org.springframework.context.support.ResourceBundleMessageSource;

public abstract class AbstractView implements View {
    private Model model;
    private List<ViewAction> actions;
    private String name;
    private ResourceBundleMessageSource messages;
    private Locale locale;
    private Composite container;
    
    public AbstractView() {
        actions = new ArrayList<ViewAction>();
    }
    
    /*
     * 
     * Setters
     *  
     */
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#setContainer(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public final void setContainer(Composite container) {
        this.container = container;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#setLocale(java.util.Locale)
     */
    @Override
    public final void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    /**
     * 
     * @param name
     */
    protected final void setName(String name) {
        this.name = name;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#setModel(org.eve.Model)
     */
    public final void setModel(Model model) {
        this.model = model;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#setMessages(
     *    org.springframework.context.support.ResourceBundleMessageSource)
     */
    @Override
    public final void setMessages(ResourceBundleMessageSource messages) {
        this.messages = messages;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    @Override
    public final Composite getContainer() {
        return container;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.View#getModel()
     */
    public final Model getModel() {
        return model;
    }

    /* (non-Javadoc)
     * @see org.eve.view.View#getActions()
     */
    @Override
    public final List<ViewAction> getActions() {
        return actions;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#getName()
     */
    @Override
    public final String getName() {
        return name;
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * 
     */
    protected abstract void defineView();
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#buildView()
     */
    @Override
    public final void buildView() {
        name = messages.getMessage("name", null, locale);
        defineView();
    }
    
    /**
     * 
     * @param id
     */
    protected final void addAction(String id) {
        actions.add(new ViewAction(this, id,
                messages.getMessage(id, null, locale), true));        
    }
    
    /**
     * 
     * @param id
     * @param visible
     */
    protected final void addAction(String id, boolean visible) {
        actions.add(new ViewAction(this, id,
                messages.getMessage(id, null, locale), visible));
    }
}
