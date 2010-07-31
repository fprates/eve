/**
 * 
 */
package org.eve.view;

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
}
