package org.eve.view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.eve.main.EveAPI;
import org.eve.model.Model;
import org.springframework.context.MessageSource;

public abstract class AbstractController implements Controller {
    private Object object;
    private String action;
    private Model model;
    private EveAPI system;
    private Locale locale;
    private MessageSource messages;
    private View currentview;
    private Map<Widget, String> widgets;
    private Map<String, Form> forms;
    private Map<String, TableAssist> tables;
    private Map<String, View> views;
    private Map<View, MessageBar> msgbars;
    
    public AbstractController() {
        widgets = new HashMap<Widget, String>();
        msgbars = new HashMap<View, MessageBar>();
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
     * (non-Javadoc)
     * @see org.eve.view.Controller#setView(org.eve.view.View)
     */
    @Override
    public final void setView(View view) {
        currentview = view;
    }
    
    /**
     * Ajusta texto da barra de mensagens
     * @param status
     * @param message
     */
    protected final void setMessage(int status, String message) {
        msgbars.get(currentview).setMessage(status, message, locale);
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
        Form form = forms.get(formname);
        form.setMessages(messages);
        
        return form;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#getTable(java.lang.String)
     */
    @Override
    public final TableAssist getTable(String tablename) {
        TableAssist table = tables.get(tablename);
        table.setMessages(messages);
        
        return table;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Controller#getMessageBar()
     */
    @Override
    public final MessageBar getMessageBar() {
        return msgbars.get(currentview);
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
        msgbars.get(currentview).clear();
        userInput(widgets.get(ev.getSource()));
    }
    
    @Override
    public final void initMsgBar(Composite container) {
        MessageBar messagebar = new MessageBar(container);
        messagebar.setMessages(messages);
        messagebar.init();
        
        msgbars.put(currentview, messagebar);        
    }
    
    /**
     * Chamada à visão
     * @param action
     */
    protected void call(String action) {
        system.call(action);
    }
}
