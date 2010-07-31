/**
 * 
 */
package org.eve.view;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eve.main.EVE;

/**
 * Componente do formulário de entrada
 * @author francisco.prates
 *
 */
public class FormComponent extends AbstractComponent {
    
    public FormComponent(String name, int length, boolean enabled) {
        setType(EVE.text);
        setName(name);
        setLength(length);
        setEnabled(enabled);
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * Retorna conteúdo
     * @return
     */
    public final String getText() {
        switch (getType()) {
        case EVE.text:
            return ((Text)getControl()).getText();
            
        case EVE.combo:
            return ((Combo)getControl()).getText();
        }
        
        return null;
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    /**
     * Ajusta valor do campo
     * @param text
     */
    public final void setText(String text) {
        switch (getType()) {
        case EVE.text:
            ((Text)getControl()).setText(text);
            break;
            
        case EVE.combo:
            ((Combo)getControl()).setText(text);
            break;
            
        }
    }
}
