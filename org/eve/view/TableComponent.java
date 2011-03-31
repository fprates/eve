/**
 * 
 */
package org.eve.view;

import org.eclipse.swt.widgets.TableColumn;

/**
 * Componente do assistente de tabela
 * @author francisco.prates
 *
 */
public class TableComponent extends AbstractComponent {
    private TableColumn column;
    
    public TableComponent(String name, int length, boolean enabled) {
        setType(ComponentType.TEXT);
        setName(name);
        setLength(length);
        setEnabled(enabled);
    }

    /*
     * 
     * Setters
     * 
     */
    
    /**
     * Define coluna do componente Table
     * @param column
     */
    public final void setColumn(TableColumn column) {
        this.column = column;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * Retorna componente do Table
     * @return
     */
    public final TableColumn getColumn() {
        return column;
    }
}