package org.eve.view;

import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.eve.main.EVE;
import org.springframework.context.MessageSource;

public class ComboAssist {
    private int type;
    private int length;
    private int item;
    private int controltype;
    private Locale locale;
    private String id;
    private String reference;
    private String[] options;
    private Composite container;
    private Controller controller;
    private Map<String, Component> table;
    private MessageSource messages;
    
    public ComboAssist() { }
    
    /**
     * 
     * @param container
     */
    public final void setContainer(Composite container) {
        this.container = container;
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
     * @param controltype
     */
    public final void setControlType(int controltype) {
        this.controltype = controltype;
    }
    
    /**
     * 
     * @param id
     */
    public final void setId(String id) {
        this.id = id;
    }
    
    /**
     * 
     * @param item
     */
    public final void setItem(int item) {
        this.item = item;
    }
    
    /**
     * 
     * @param length
     */
    public final void setLength(int length) {
        this.length = length;
    }
    
    /**
     * 
     * @param locale
     */
    public final void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    /**
     * 
     * @param messages
     */
    public final void setMessages(MessageSource messages) {
        this.messages = messages;
    }
    
    /**
     * 
     * @param options
     */
    public final void setOptions(String[] options) {
        this.options = options;
    }
    
    /**
     * 
     * @param reference
     */
    public final void setReference(String reference) {
        this.reference = reference;
    }
    
    /**
     * 
     * @param type
     */
    public final void setType(int type) {
        this.type = type;
    }
    
    /**
     * 
     * @param table
     */
    public final void setTableReference(Map<String, Component> table) {
        this.table = table;
    }
    
    /**
     * 
     * @return
     */
    public final Widget newInstance() {
        int charh;
        int charw;
        CCombo ccombo;
        Combo combo;
        ComboListener listener;
        
        switch(type) {
        case EVE.ccombo:
            ccombo = new CCombo(container, SWT.NONE);
            
            charw = ViewUtils.getCharWidth(ccombo);
            charh = ViewUtils.getCharHeight(ccombo);
            
            ccombo.setSize(ccombo.computeSize(
                    (length * charw) + 40, charh));
            
            /*
             * definições para carga dinâmica de valores
             */
            if (options != null && options.length > 0) {
                ccombo.setText(options[0]);
                ccombo.setItems(options);
            }
            
            if (options == null) {
                listener = new ComboListener(id, controller);
                listener.setIndex(item);
                listener.setTableReference(table);
                listener.setReference(reference);
                listener.setType(controltype);
                
                ccombo.addListener(SWT.MouseDown, listener);
            }
            
            return ccombo;
            
        case EVE.combo:
            combo = new Combo(container, SWT.BORDER);
            
            charw = ViewUtils.getCharWidth(combo);
            charh = ViewUtils.getCharHeight(combo);

            combo.setSize(combo.computeSize((length * charw) + 35, charh));
            
            /*
             * definições para carga dinâmica de valores
             */
            if (options != null && options.length > 0) {
                combo.setText(options[0]);
                for (String option : options)
                    combo.add(messages.getMessage(option, null, option, locale));
            }
            
            if (options == null) {
                listener = new ComboListener(id, controller);
                listener.setTableReference(table);
                listener.setReference(reference);
                listener.setType(controltype);
                
                combo.addListener(SWT.MouseDown, listener);
            }
            
            return combo;
            
        default:
            return null;
        }
    }
}
