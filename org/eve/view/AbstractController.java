package org.eve.view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.eve.main.EveAPI;
import org.eve.model.Model;

public abstract class AbstractController implements Controller {
    private Object object;
    private String action;
    private Model model;
    private MessageBar messageBar;
    private EveAPI system;
    private Map<Widget, String> widgets;
    private Map<String, Form> forms;
    private Map<String, TableAssist> tables;
    private Map<String, View> views;
    private Locale locale;
    private Composite container;
    
    public AbstractController() {
        widgets = new HashMap<Widget, String>();
    }
    
    /*
     * 
     * Setters
     * 
     */
    
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
     * @see org.eve.view.Controller#setMessageWidget(org.eclipse.swt.widgets.Text)
     */
    @Override
    public final void setMessageBar(MessageBar messageBar) {
        this.messageBar = messageBar;
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
        
        for (String viewname : views.keySet())
            views.get(viewname).setSystem(system);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#setForms(java.util.Map)
     */
    @Override
    public final void setForms(Map<String, Form> forms) {
        this.forms = forms;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#setTables(java.util.Map)
     */
    @Override
    public final void setTables(Map<String, TableAssist> tables) {
        this.tables = tables;
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
     * @see org.eve.view.Controller#setContainer(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public final void setContainer(Composite container) {
        this.container = container;
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#setViews(java.util.Map)
     */
    @Override
    public final void setViews(Map<String, View> views) {
        this.views = views;
    }
    
    /**
     * Ajusta texto da barra de mensagens
     * @param status
     * @param message
     */
    protected final void setMessage(int status, String message) {
        messageBar.setMessage(status, message, locale);
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
     * @see org.eve.view.Controller#getMessageBar()
     */
    @Override
    public final MessageBar getMessageBar() {
        return messageBar;
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
     * @see org.eve.view.Controller#getContainer()
     */
    @Override
    public final Composite getContainer() {
        return container;
    }
    
    /*
     * 
     * Others
     * 
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
    public final void widgetDefaultSelected(SelectionEvent ev) {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public final void widgetSelected(SelectionEvent ev) {        
        messageBar.clear();
        userInput(widgets.get(ev.getSource()));
    }
    
    /**
     * Chamada à visão
     * @param action
     */
    protected void call(String action) {
        system.call(action);
    }
}
