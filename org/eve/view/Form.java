package org.eve.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eve.model.AbstractDocument;

/**
 * Assistente para formulário de entrada
 * @author francisco.prates
 *
 */
public class Form extends AbstractComponentFactory {
    private boolean editable;
    private Controller controller;
    private Set<String> blocked;
    private Map<Component, Control> controls;
    
    public Form(String id) {
        controls = new HashMap<Component, Control>();
        blocked = new HashSet<String>();
        editable = true;
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    /**
     * 
     * @param document
     * @param id
     */
    public final void setBlocked(AbstractDocument document, String id) {
        blocked.add(id);
    }
    
    /**
     * 
     * @param field
     */
    public final void setBlocked(String field) {
        blocked.add(field);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#setControlFocus(org.eve.view.Component)
     */
    @Override
    protected final void setControlFocus(Component component) {
        controls.get(component).setFocus();
    }
    
    /**
     * 
     * @param controller
     */
    public final void setController(Controller controller) {
        this.controller = controller;
    }
    
    @Override
    public final void setControlSize(Component component) {
        ViewUtils.setControlSize(component, controls.get(component));
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#setControlValue(org.eve.view.Component, java.lang.Object)
     */
    @Override
    protected final void setControlValue(Component component, Object value) {
        ViewUtils.setControlText(component, controls.get(component), value, this);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#setControlValue(org.eve.view.Component, int, java.lang.String)
     */
    @Override
    protected final void setControlValue(Component component, int index, String value) { }
    
    /**
     * 
     * @param editable
     */
    public final void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    /**
     * 
     * @param field
     */
    public final void setUnBlocked(String field) {
        blocked.remove(field);
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#getControlValue(org.eve.view.Component)
     */
    @Override
    protected final Object getControlValue(Component component) {
        return ViewUtils.getControlValue(component, controls.get(component), this);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#getControlValue(org.eve.view.Component, int)
     */
    @Override
    protected final String getControlValue(Component component, int index) {
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#getNewComponent(java.lang.String, int, boolean)
     */
    @Override
    protected final Component getNewComponent(String name, int length, boolean key) {
        return new FormComponent(name, length, key);
    }
    
    /**
     * Retorna valor do campo caractere do formulário,
     * em formato "like" para seleção
     * @param field
     * @return
     */
    public final String getStringLike(String field) {
        String value = getString(field).replace("*", "%");
        
        return (value.equals(""))?"%":value;
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#clear()
     */
    @Override
    public final void clear() {
        for (Component component : controls.keySet())
            setControlValue(component, "");
    }
    
    /**
     * 
     */
    public final void commit() {
        Control control;
        
        for (Component component : getComponents()) {
            control = controls.get(component);
            
            if (blocked.contains(component.getName()))
                ViewUtils.setEnabledControl(control, false);
            else
                ViewUtils.setEnabledControl(control, editable);
        }
    }
    
    /**
     * Constrói formulário e inicializa o próximo
     * @param form
     * @param composite
     */
    public final Composite define(Composite container) {
        int charw;
        int charh;
        Combo combo;
        Composite fieldComposite;
        Label label;
        Text text;
        Search search;
        ComboAssist comboassist = getComboAssist();
        Composite composite = new Composite(container, SWT.NONE);
        
        composite.setLayout(new GridLayout(2, false));
        
        for(Component component : getComponents()) {
            label = new Label(composite, SWT.NONE);
            label.setText(component.getTitle());
            label.setLayoutData(
                    new GridData(SWT.RIGHT, SWT.CENTER, false, false));

            fieldComposite = new Composite(composite, SWT.NONE);
            
            switch (component.getType()) {
            case TEXT:
                text = new Text(fieldComposite, SWT.BORDER);
                
                charw = ViewUtils.getCharWidth(text);
                charh = ViewUtils.getCharHeight(text);
                
                text.setSize(text.computeSize(
                        component.getLength() * charw, charh));
                
                controls.put(component, text);
                
                switch(component.getExtension()) {
                case SEARCH:
                    search = new SearchHelper();
                    search.setFactory(this);
                    search.setController(controller);
                    search.define(component, fieldComposite);
                    break;
                    
                case FILESEARCH:
                    search = new FileSearch(fieldComposite.getShell());
                    search.setFactory(this);
                    search.setController(controller);
                    search.define(component, fieldComposite);
                }
                
                break;
                
            case COMBO:
                comboassist.setContainer(fieldComposite);
                comboassist.setOptions(component.getOptions());
                comboassist.setLength(component.getLength());
                comboassist.setFactory(this);
                
                combo = (Combo)comboassist.newInstance();
                
                controls.put(component, combo);
                
                break;
            }
        }
        
        return composite;
    }
    
    /**
     * 
     * @param document
     * @param id
     */
    public final void putSearch(AbstractDocument document, String id) {
        Component component;
        
        put(document, id);
        component = getComponent(id);
        component.setExtension(Component.Extension.SEARCH);
    }
    
    /**
     * 
     * @param document
     * @param id
     */
    public final void putFileSearch(AbstractDocument document, String id) {
        Component component;
        
        put(document, id);
        component = getComponent(id);
        component.setExtension(Component.Extension.FILESEARCH);
    }
}