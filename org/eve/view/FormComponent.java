/**
 * 
 */
package org.eve.view;

/**
 * Componente do formulário de entrada
 * @author francisco.prates
 *
 */
public class FormComponent extends AbstractComponent {
    
    public FormComponent(String name, int length, boolean enabled) {
        setType(ComponentType.TEXT);
        setName(name);
        setLength(length);
        setEnabled(enabled);
    }
}
