package org.eve.view;

//import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eve.main.EVE;
import org.eve.model.AbstractDocument;

/**
 * Assistente para formulário de entrada
 * @author francisco.prates
 *
 */
public class Form extends AbstractComponentFactory {
    private boolean editable;
    private ComboAssist comboassist;
    private Controller controller;
//    private Map<String, ComponentConcat> concatenates;
    private Set<String> blocked;
    
    public Form(String id) {
        blocked = new HashSet<String>();
//        concatenates = new HashMap<String, ComponentConcat>();
        comboassist = new ComboAssist();
        comboassist.setType(EVE.combo);
        comboassist.setControlType(EVE.single);
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
        blocked.add(document.getName(id));
    }
    
    /**
     * 
     * @param field
     */
    public final void setBlocked(String field) {
        blocked.add(field);
    }
    
    /**
     * 
     * @param controller
     */
    public final void setController(Controller controller) {
        this.controller = controller;
    }
    
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
    
    /**
     * 
     */
    public final void commit() {
        for (Component component : getComponents()) {
            if (blocked.contains(component.getName()))
                component.setEnabled(false);
            else
                component.setEnabled(editable);
            
            component.commit();
        }
    }
    
//    public final void concat(String id, String component1, String component2) {
//    	concatenates.put(component1, new ComponentConcat(id, component2));
//    }
    
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
        Composite composite = new Composite(container, SWT.NONE);
        
        composite.setLayout(new GridLayout(2, false));
        comboassist.setController(controller);
        comboassist.setFactory(this);
        
        for(Component component : getComponents()) {
            label = new Label(composite, SWT.NONE);
            label.setText(component.getTitle());
            label.setLayoutData(
                    new GridData(SWT.RIGHT, SWT.CENTER, false, false));

            fieldComposite = new Composite(composite, SWT.NONE);
            
            switch (component.getType()) {
            case EVE.text:
                text = new Text(fieldComposite, SWT.BORDER);
                
                charw = ViewUtils.getCharWidth(text);
                charh = ViewUtils.getCharHeight(text);
                
                text.setSize(text.computeSize(
                        component.getLength() * charw, charh));
                
                component.setControl(text);
                
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
                
            case EVE.combo:
                comboassist.setContainer(fieldComposite);
                comboassist.setOptions(component.getOptions());
                comboassist.setLength(component.getLength());
                comboassist.setId(component.getName());
                
                combo = (Combo)comboassist.newInstance();
                
                component.setControl(combo);
                
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
    public final void put(AbstractDocument document, String id) {
        String name = document.getName(id);
        FormComponent component = new FormComponent(
                name, document.getLength(id), !document.isKey(id));
        
        component.setTitle(getMessage(name));
        component.setDataType(document.getType(id));
        
        putComponent(name, component);
    }
    
    /**
     * 
     * @param document
     * @param id
     * @param length
     */
    public final void putCombo(AbstractDocument document, String id, int length) {
        String[] options;
        String name = document.getName(id);
        FormComponent component = new FormComponent(
                name, length, !document.isKey(id));
        
        component.setTitle(getMessage(name));
        component.setType(EVE.combo);
        
        options = document.getValues(id);
        if (options == null) {
            options = new String[1];
            options[0] = "";
        }
        
        component.setOptions(options);
        component.setDataType(document.getType(id));
        
        putComponent(name, component);
    }
    
    /**
     * 
     * @param document
     * @param id
     */
    public final void putSearch(AbstractDocument document, String id) {
        Component component;
        
        put(document, id);
        component = getComponent(document.getName(id));
        component.setExtension(Component.Extension.SEARCH);
    }
    
    public final void putFileSearch(AbstractDocument document, String id) {
        Component component;
        
        put(document, id);
        component = getComponent(document.getName(id));
        component.setExtension(Component.Extension.FILESEARCH);
    }
}

//class ComponentConcat {
//	private String component;
//	private String id;
//	
//	public ComponentConcat(String id, String component) {
//		this.id = id;
//		this.component = component;
//	}
//	
//	public final String getComponent() {
//		return component;
//	}
//	
//	public final String getId() {
//		return id;
//	}
//}