package org.eve.view;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eve.main.EveAPI;
import org.eve.view.ViewAction;
import org.springframework.context.MessageSource;

public abstract class AbstractView implements View {
    private String name;
    private MessageSource messages;
    private Locale locale;
    private EveAPI system;
    private Composite container;
    private List<ViewAction> actions;
    private Map<String, Button> buttons;
    
    public AbstractView() {
        actions = new LinkedList<ViewAction>();
        buttons = new LinkedHashMap<String, Button>();
    }
    
    /*
     * 
     * Setters
     *  
     */
    
    /**
     * 
     * @param id
     * @param visible
     */    
    protected final void setButtonVisible(String id, boolean visible) {
        buttons.get(id).setVisible(visible);
    }
    
    /**
     * 
     * @param id
     */
    protected final void setTitlebar(String id) {
        system.setTitleBar(messages.getMessage(id, null, id, locale));
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#setLocale(java.util.Locale)
     */
    @Override
    public final void setLocale(Locale locale) {
        this.locale = locale;
        system.getController(this).setLocale(locale);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#setMessages(org.springframework.context.MessageSource)
     */
    @Override
    public final void setMessages(MessageSource messages) {
        this.messages = messages;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#setSystem(org.eve.main.EveAPI)
     */
    @Override
    public final void setSystem(EveAPI system) {
        this.system = system;
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

    /*
     *  (non-Javadoc)
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
     * @see org.eve.view.View#getContainer()
     */
    @Override
    public final Composite getContainer() {
        return container;
    }
    
    /**
     * Retorna a localização atual
     * @return
     */
    protected final Locale getLocale() {
        return locale;
    }
    
    /**
     * 
     * @param id
     * @return
     */
    protected final String getMessage(String id) {
        return messages.getMessage(id, null, id, locale);
    }
    
    /**
     * 
     * @return
     */
    protected final Controller getController() {
        return system.getController(this);
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
        system.addButton(id);
    }
    
    /**
     * 
     * @param container
     */
    protected abstract void defineView(Composite container);
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#buildView()
     */
    @Override
    public final void buildView(Composite container) {
        this.container = container;
        name = messages.getMessage("name", null, locale);
        defineView(container);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#addButtonbar(java.lang.String, org.eclipse.swt.widgets.Button)
     */
    @Override
    public final void addButtonbar(String id, Button button) {
        button.setText(messages.getMessage(id, null, id, locale));
        buttons.put(id, button);
    }
    
    /**
     * Adiciona ação à visão
     * @param id
     */
    protected final void addAction(String id) {
        actions.add(new ViewAction(this, id,
                messages.getMessage(id, null, id, locale), true));
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
