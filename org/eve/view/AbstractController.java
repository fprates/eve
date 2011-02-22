package org.eve.view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Widget;
import org.eve.main.EveAPI;
import org.eve.model.Model;
import org.springframework.context.MessageSource;

/**
 * Implementação parcial de controlador
 * @author francisco.prates
 *
 */
public abstract class AbstractController implements Controller {
    private Object object;
    private String action;
    private Model model;
    private EveAPI system;
    private Locale locale;
    private MessageSource messages;
    private Map<Widget, String> widgets;
    private Map<String, Form> forms;
    private Map<String, TableAssist> tables;
    private Map<String, View> views;
    
    public AbstractController() {
        widgets = new HashMap<Widget, String>();
        forms = new HashMap<String, Form>();
        tables = new HashMap<String, TableAssist>();
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    /**
     * Ajusta mensagem
     * @param status
     * @param id
     */
    protected final void setMessage(int status, String id) {
        system.setMessage(status, messages.getMessage(id, null, id, locale));
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#setObject(java.lang.Object)
     */
    @Override
    public final void setObject(Object object) {
        this.object = object;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#setModel(org.eve.Model)
     */
    @Override
    public final void setModel(Model model) {
        this.model = model;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#setAction(java.lang.String)
     */
    @Override
    public final void setAction(String action) {
        this.action = action;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#setSystem(org.eve.main.EveSystem)
     */
    @Override
    public final void setSystem(EveAPI system) {        
        this.system = system;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#setLocale(java.util.Locale)
     */
    @Override
    public final void setLocale(Locale locale) {
        this.locale = locale;
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#setViews(java.util.Map)
     */
    @Override
    public final void setViews(Map<String, View> views) {
        this.views = views;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#setMessages(org.springframework.context.MessageSource)
     */
    @Override
    public final void setMessages(MessageSource messages) {
        this.messages = messages;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * Retorna modelo
     * @return
     */
    protected final Model getModel() {
        return model;
    }
    
    /**
     * Retorna ação que chamou a visão
     * @return
     */
    protected final String getAction() {
        return action;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#getObject()
     */
    @Override
    public final Object getObject() {
        return object;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#getForm(java.lang.String)
     */
    @Override
    public final Form getForm(String formname) {
        return forms.get(formname);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#getTable(java.lang.String)
     */
    @Override
    public final TableAssist getTable(String tablename) {
        return tables.get(tablename);
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#getViews()
     */
    @Override
    public final Map<String, View> getViews() {
        return views;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#getMessages()
     */
    @Override
    public final MessageSource getMessages() {
        return messages;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#getResults(java.lang.String, java.lang.Object)
     */
    @Override
    public Map<Object, String> getResults(String id, Object object) {
        return null;
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * Chamada à visão
     * @param action
     */
    protected void call(String action) {
        system.call(action);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#loadCommand()
     */
    @Override
    public void loadCommand() { }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#saveCommand()
     */
    @Override
    public void saveCommand() { }
    
    /**
     * Processamento de eventos do usuário
     * @param input
     */
    public abstract void userInput(String input);

    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#putWidgetView(org.eclipse.swt.widgets.Widget, org.eve.view.View)
     */
    @Override
    public final void putWidget(Widget widget, String id) {
        widgets.put(widget, id);
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public final void widgetDefaultSelected(SelectionEvent ev) { }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public final void widgetSelected(SelectionEvent ev) {
        String action = widgets.get(ev.getSource());
        
        system.clearMessage();
        
        if (action.equals("save.command")) {
            saveCommand();
            return;
        }
        
        if (action.equals("load.command")) {
            loadCommand();
            return;
        }
        
        userInput(action);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#putForm(java.lang.String, org.eve.view.Form)
     */
    @Override
    public final void putForm(String id, Form form) {
        forms.put(id, form);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#putTable(java.lang.String, org.eve.view.TableAssist)
     */
    @Override
    public final void putTable(String id, TableAssist table) {
        tables.put(id, table);
    }
}
