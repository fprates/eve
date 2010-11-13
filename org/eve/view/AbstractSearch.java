package org.eve.view;

import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eve.main.EveAPI;
import org.springframework.context.MessageSource;

public abstract class AbstractSearch implements Search {
    private Button btsearch;
    private Component component;
    private Composite container;
    private Controller controller;
    private EveAPI system;
    private MessageSource messages;
    private Locale locale;
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
    protected final Locale getLocale() {
        return locale;
    }
    
    /**
     * 
     * @return
     */
    protected final EveAPI getSystem() {
        return system;
    }

    @Override
    public final void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public final void setMessages(MessageSource messages) {
        this.messages = messages;
    }

    @Override
    public final void setSystem(EveAPI system) {
        this.system = system;
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
        int charw;
        int charh;
        Control control = component.getControl();
        
        this.container = container;
        this.container.setLayout(new RowLayout());
        this.component = component;
        
        charw = ViewUtils.getCharWidth(control);
        charh = ViewUtils.getCharHeight(control);
        
        control.setSize(control.computeSize(
                component.getLength() * charw, charh));
        
        btsearch = new Button(container, SWT.BORDER);
        btsearch.setText(messages.getMessage(text, null, text, locale));
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
