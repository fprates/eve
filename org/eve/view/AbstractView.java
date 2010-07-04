package org.eve.view;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eve.view.ViewAction;
import org.springframework.context.support.ResourceBundleMessageSource;

public abstract class AbstractView implements View {
    private String name;
    private ResourceBundleMessageSource messages;
    private Locale locale;
    private Composite container;
    private Controller controller;
    private List<ViewAction> actions;
    private List<String> buttonbarlist;
    private Map<String, Button> buttons;
    
    public AbstractView() {
        actions = new LinkedList<ViewAction>();
        buttonbarlist = new LinkedList<String>();
        buttons = new LinkedHashMap<String, Button>();
    }
    
    /*
     * 
     * Setters
     *  
     */
    
    protected final void setButtonVisible(String id, boolean visible) {
        buttons.get(id).setVisible(visible);
    }
    
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
     * @see org.eve.view.View#setController(org.eve.view.Controller)
     */
    @Override
    public final void setController(Controller controller) {
        this.controller = controller;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#setLocale(java.util.Locale)
     */
    @Override
    public final void setLocale(Locale locale) {
        this.locale = locale;
        controller.setLocale(locale);
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
    
    /**
     * Ajusta nome da visão
     * @param name
     */
    protected final void setName(String name) {
        this.name = name;
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
     * (non-Javadoc)
     * @see org.eve.view.View#getController()
     */
    @Override
    public final Controller getController() {
        return controller;
    }
    
    /**
     * Retorna a localização atual
     * @return
     */
    protected final Locale getLocale() {
        return locale;
    }
    
    protected final String getMessage(String id) {
        return messages.getMessage(id, null, locale);
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * Adiciona botão à barra de botões
     * @param id
     */
    protected final void addButton(String id) {
        buttonbarlist.add(id);
    }
    
    protected abstract void defineView();
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#buildView()
     */
    @Override
    public final void buildView() {
        Button button;
        Composite buttonbar;
        
        name = messages.getMessage("name", null, locale);
        defineView();

        buttonbar = new Composite(container, SWT.NONE);
        buttonbar.setLayout(new FormLayout());
        
        for (String id : buttonbarlist) {
            button = new Button(buttonbar, SWT.PUSH);
            button.setText(messages.getMessage(id, null, locale));
            button.addSelectionListener(controller);
            
            controller.putWidget(button, id);
            buttons.put(id, button);
        }
        
        buttonbar.pack();
        controller.getMessageBar().init(container);
    }
    
    /**
     * Adiciona ação à visão
     * @param id
     */
    protected final void addAction(String id) {
        actions.add(new ViewAction(this, id,
                messages.getMessage(id, null, locale), true));        
    }
    
    /**
     * Adiciona ação à visão
     * @param id
     * @param visible
     */
    protected final void addAction(String id, boolean visible) {
        actions.add(new ViewAction(this, id,
                messages.getMessage(id, null, locale), visible));
    }
}
