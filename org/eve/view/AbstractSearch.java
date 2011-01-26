package org.eve.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public abstract class AbstractSearch implements Search {
    private AbstractComponentFactory factory;
    private Button btsearch;
    private Component component;
    private Composite container;
    private Controller controller;
    private String text;
    
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
    protected final AbstractComponentFactory getFactory() {
        return factory;
    }

    @Override
    public final void setFactory(AbstractComponentFactory factory) {
        this.factory = factory;
    }

    protected final void setText(String text) {
        this.text = text;
    }
    
    @Override
    public void setController(Controller controller) {
        this.controller = controller;
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
    protected abstract void openDialog();
    
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
            openDialog();
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
