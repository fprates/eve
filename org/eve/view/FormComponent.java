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
public class FormComponent {
    private String name;
    private int length;
    private boolean enabled;
    private Control control;
    private int type;
    private String[] options;
    private boolean nocase;
    
    public FormComponent(String name, int length, boolean enabled) {
        this.name = name;
        this.length = length;
        this.enabled = enabled;
        this.nocase = false;
        type = EVE.text;        
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * Retorna nome
     * @return the name
     */
    public final String getName() {
        return name;
    }
    
    /**
     * Retorna comprimento
     * @return the length
     */
    public final int getLength() {
        return length;
    }
    
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
     * Retorna lista de valores fixos
     * @return
     */
    public final String[] getOptions() {
        return options;
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
    
    /**
     * Retorna valor da lista de opções
     * @param index
     * @return
     */
    public final String getOption(int index) {
        return options[index];
    }
    
    /**
     * Retorna indicador de sensibilidade
     * @return
     */
    public final boolean isNocase() {
        return nocase;
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
     * Define valores fixos
     * @param options
     */
    public final void setOptions(String[] options) {
        this.options = options;
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
    
    /**
     * Ajusta indicador de sensibilidade
     * @param nocase
     */
    public final void setNocase(boolean nocase) {
        this.nocase = nocase;
    }
}
