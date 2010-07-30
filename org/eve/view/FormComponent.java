/**
 * 
 */
package org.eve.view;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eve.main.EVE;

/**
 * Componente do formulário de entrada
 * @author francisco.prates
 *
 */
public class FormComponent extends AbstractComponent {
    private boolean enabled;
    private Control control;
    private int type;
    
    public FormComponent(String name, int length, boolean enabled) {
        this.enabled = enabled;
        type = EVE.text;
        setName(name);
        setLength(length);
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * Retorna indicador de habilitação
     * @return
     */
    public final boolean isEnabled() {
        return enabled;
    }
    
    /**
     * Retorna controle associado
     * @return
     */
    public final Control getControl() {
    	return control;
    }
    
    /**
     * Retorna conteúdo
     * @return
     */
    public final String getText() {
        switch (type) {
        case EVE.text:
            return ((Text)control).getText();
            
        case EVE.combo:
            return ((Combo)control).getText();
        }
        
        return null;
    }
    
    /**
     * Retorna tipo
     * @return
     */
    public final int getType() {
        return type;
    }
    
    /*
     * 
     * Setters
     * 
     */    
    
    /**
     * Ajusta controle associado
     * @param text
     */
    public final void setControl(Control control) {
    	this.control = control;
    }
    
    /**
     * Ajusta tipo
     * @param type
     */
    public final void setType(int type) {
        this.type = type;
    }
    
    /**
     * Ajusta valor do campo
     * @param text
     */
    public final void setText(String text) {
        switch (type) {
        case EVE.text:
            ((Text)control).setText(text);
            break;
            
        case EVE.combo:
            ((Combo)control).setText(text);
            break;
            
        }
    }
    
    /**
     * Ajusta indicador de habilitação
     * @param enabled
     */
    public final void setEnabled(boolean enabled) {
        switch (type) {
        case EVE.text:
            ((Text)control).setEnabled(enabled);
            break;
            
        case EVE.combo:
            ((Combo)control).setEnabled(enabled);
            break;
        }
    }
}
