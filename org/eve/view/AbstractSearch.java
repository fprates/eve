package org.eve.view;

import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eve.main.EVE;
import org.eve.model.AbstractDocument;

public abstract class AbstractSearch implements Search {
    private int mode;
    private AbstractComponentFactory factory;
    private Button btsearch;
    private Component component;
    private Composite container;
    private Controller controller;
    private Locale locale;
    private String text;
    private AbstractDocument document;
    
    public AbstractSearch() {
        mode = EVE.insert;
    }
    
    /**
     * 
     * @return
     */
    protected final Component getComponent() {
        return component;
    }
    
    /**
     * 
     * @return
     */
    protected final Composite getContainer() {
        return container;
    }
    
    /**
     * 
     * @return
     */
    protected final Controller getController() {
        return controller;
    }
    
    /**
     * 
     * @return
     */
    protected final AbstractDocument getDocument() {
        return document;
    }
    
    /**
     * 
     * @return
     */
    protected final AbstractComponentFactory getFactory() {
        return factory;
    }
    
    /**
     * 
     * @return
     */
    protected final Locale getLocale() {
        return locale;
    }
    
    /**
     * 
     * @param message
     * @return
     */
    protected final String getMessage(String message) {
        return getController().getMessages().getMessage(message, null, message, locale);
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Search#setController(org.eve.view.Controller)
     */
    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.Search#setDocument(org.eve.model.AbstractDocument)
     */
    @Override
    public final void setDocument(AbstractDocument document) {
        this.document = document;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Search#setFactory(org.eve.view.AbstractComponentFactory)
     */
    @Override
    public final void setFactory(AbstractComponentFactory factory) {
        this.factory = factory;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Search#setLocale(java.util.Locale)
     */
    @Override
    public final void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    /**
     * 
     * @param text
     */
    protected final void setText(String text) {
        this.text = text;
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Search#define(org.eve.view.Component, org.eclipse.swt.widgets.Composite)
     */
    @Override
    public final void define(Component component, Composite container) {
        this.container = container;
        this.container.setLayout(new RowLayout());
        this.component = component;
        
        factory.setControlSize(component);
        
        btsearch = new Button(container, SWT.BORDER);
        btsearch.setText(factory.getMessage(text));
        btsearch.addSelectionListener(this);
        
        container.pack();
    }
    
    /**
     * 
     */
    protected abstract void openDialog(int mode);
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) { }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public final void widgetSelected(SelectionEvent ev) {
        if (ev.getSource() == btsearch) {
            openDialog(mode);
            return;
        }
        
        userWidgetSelected(ev);
    }

    /**
     * 
     * @param ev
     */
    protected abstract void userWidgetSelected(SelectionEvent ev);
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
     */
    @Override
    public abstract void widgetDisposed(DisposeEvent arg0);

}
