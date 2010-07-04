package org.eve.view;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Widget;
import org.eve.main.EveAPI;
import org.eve.model.Model;

public abstract class AbstractController implements Controller {
    private Object object;
    private String action;
    private Model model;
    private MessageBar messageBar;
    private EveAPI system;
    private Map<String, Map<String, FormComponent>> forms;
    private Map<Widget, String> widgets;
    
    public AbstractController() {
        widgets = new HashMap<Widget, String>();
        forms = new HashMap<String, Map<String, FormComponent>>();
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
     * @see org.eve.view.Controller#putForm(java.lang.String, java.util.Map)
     */
    @Override
    public final void putForm(String formname, Map<String, FormComponent> form) {
        forms.put(formname, form);
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
    }
    
    /**
     * Ajusta valor do campo caractere do formulário
     * @param field
     * @param value
     */
    protected final void setStringForm(String formname, String field, String value) {
        forms.get(formname).get(field).getTextWidget().setText(value);        
    }
    
    /**
     * Ajusta valor do campo inteiro do formulário
     * @param field
     * @param value
     */
    protected final void setIntForm(String formname, String field, int value) {
        setStringForm(formname, field, Integer.toString(value));
    }

    /**
     * Ajusta texto da barra de mensagens
     * @param status
     * @param message
     */
    protected final void setMessage(int status, String message) {
        messageBar.setMessage(status, message);
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    
    /**
     * Retorna valor do campo caractere do formulário
     * @param field
     * @return
     */
    protected final String getStringForm(String formname, String field) {
        return forms.get(formname).get(field).getTextWidget().getText();
    }
    
    /**
     * Retorna valor do campo inteiro do formulário
     * @param field
     * @return
     */
    protected final int getIntForm(String formname, String field) {
        String test = getStringForm(formname, field);
        
        return test.equals("")? 0:Integer.parseInt(test);
    }
    
    /**
     * Retorna objeto
     * @return
     */
    protected final Object getObject() {
        return object;
    }
    
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
     * @see org.eve.view.Controller#getMessageBar()
     */
    @Override
    public final MessageBar getMessageBar() {
        return messageBar;
    }
    
    /*
     * 
     * Others
     * 
     */
    
    protected void call(String action) {
        system.call(action);
    }
    
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
        Button item = (Button)ev.getSource();
        
        userInput(widgets.get(item));
    }
}
