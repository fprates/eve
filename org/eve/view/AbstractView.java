package org.eve.view;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
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
    private Map<String, FormComponent> form;
    private Map<String, Map<String, FormComponent>> forms;
    private Map<String, Button> buttons;
    
    public AbstractView() {
        actions = new LinkedList<ViewAction>();
        form = new LinkedHashMap<String, FormComponent>();
        forms = new HashMap<String, Map<String, FormComponent>>();
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
    }
    
    /**
     * Ajusta nome da visão
     * @param name
     */
    protected final void setName(String name) {
        this.name = name;
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
    
    /**
     * Retorna componentes do formulário
     * @param form
     * @return
     */
    protected final Map<String, FormComponent> getForm(String form) {
        return forms.get(form);
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
    
    /**
     * Adiciona campo ao formulário
     * @param id
     * @param length
     */
    protected final void addForm(String id, int length) {
        form.put(id, new FormComponent(
                messages.getMessage(id, null, locale), length, true));
    }
    
    /**
     * Adiciona campo ao formulário
     * @param id
     * @param length
     * @param enabled
     */
    protected final void addForm(String id, int length, boolean enabled) {
        form.put(id, new FormComponent(
                messages.getMessage(id, null, locale), length, enabled));
    }
    
    /**
     * Constrói formulário e inicializa o próximo
     * @param form
     * @param composite
     */
    protected final Composite defineForm(String formname, Composite main) {
        Label label;
        Text text;
        int charw = 0;
        int charh = 0;
        Composite fieldComposite;
        FormComponent component;
        Composite composite = new Composite(main, SWT.NONE);
        
        composite.setLayout(new GridLayout(2, false));
        
        for(String field : form.keySet()) {
            component = form.get(field);
            
            label = new Label(composite, SWT.NONE);
            label.setText(component.getName());
            label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

            fieldComposite = new Composite(composite, SWT.NONE);
            text = new Text(fieldComposite, SWT.BORDER);
            
            if (charw == 0) {
                charw = ViewUtils.getCharWidth(text);
                charh = ViewUtils.getCharHeight(text);             
            }
            
            text.setSize(text.computeSize(component.getLength() * charw, charh));
            component.setText(text);            
        }
        
        controller.putForm(formname, form);
        forms.put(formname, form);
        form = new LinkedHashMap<String, FormComponent>();
        
        return composite;
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
