package org.eve.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eve.Model;
import org.eve.view.ViewAction;
import org.springframework.context.support.ResourceBundleMessageSource;

public abstract class AbstractView implements View {
    private Model model;
    private String name;
    private ResourceBundleMessageSource messages;
    private Locale locale;
    private Composite container;
    private List<ViewAction> actions;
    private Map<String, FormComponent> form;
    
    public AbstractView() {
        actions = new ArrayList<ViewAction>();
        form = new LinkedHashMap<String, FormComponent>();
    }
    
    /*
     * 
     * Setters
     *  
     */
    
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
     * @see org.eve.view.View#setLocale(java.util.Locale)
     */
    @Override
    public final void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    /**
     * 
     * @param name
     */
    protected final void setName(String name) {
        this.name = name;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#setModel(org.eve.Model)
     */
    public final void setModel(Model model) {
        this.model = model;
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
    
    /*
     * (non-Javadoc)
     * @see org.eve.View#getModel()
     */
    public final Model getModel() {
        return model;
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
     * 
     * Others
     * 
     */
    
    /**
     * 
     * @param text
     * @return
     */
    private final int getCharWidth(Drawable text) {
        GC gc = new GC(text);
        int charw = gc.getFontMetrics().getAverageCharWidth();
        
        gc.dispose();
        
        return charw;
    }
    
    /**
     * 
     * @param text
     * @return
     */
    private final int getCharHeight(Drawable text) {
        GC gc = new GC(text);
        int charh = gc.getFontMetrics().getHeight();
        
        gc.dispose();
        
        return charh;
    }
    
    protected final void addForm(String id, int length) {
        form.put(id, new FormComponent(
                messages.getMessage(id, null, locale), length, true));
    }
    
    protected final void addForm(String id, int length, boolean enabled) {
        form.put(id, new FormComponent(
                messages.getMessage(id, null, locale), length, enabled));
    }
    
    /**
     * 
     * @param form
     * @param composite
     */
    protected final Composite defineForm(Composite main) {
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
                charw = getCharWidth(text);
                charh = getCharHeight(text);             
            }
            
            text.setSize(text.computeSize(component.getLength() * charw, charh));
            component.setText(text);            
        }
        
        return composite;
    }
    
    /**
     * 
     */
    protected abstract void defineView();
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#buildView()
     */
    @Override
    public final void buildView() {
        name = messages.getMessage("name", null, locale);
        defineView();
    }
    
    /**
     * 
     * @param id
     */
    protected final void addAction(String id) {
        actions.add(new ViewAction(this, id,
                messages.getMessage(id, null, locale), true));        
    }
    
    /**
     * 
     * @param id
     * @param visible
     */
    protected final void addAction(String id, boolean visible) {
        actions.add(new ViewAction(this, id,
                messages.getMessage(id, null, locale), visible));
    }
}
