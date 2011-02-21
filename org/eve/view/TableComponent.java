/**
 * 
 */
package org.eve.view;

import java.util.Map;

import org.eclipse.swt.widgets.TableColumn;

/**
 * Componente do assistente de tabela
 * @author francisco.prates
 *
 */
public class TableComponent extends AbstractComponent {
    private ComboListener combolistener;
    private TableColumn column;
    
    public TableComponent(String name) {
        setType(ComponentType.TEXT);
        setName(name);
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
    
    /**
     * Define campos de referência 
     * @param table
     */
    public final void setTableReference(Map<String, Component> table) {
        combolistener.setTableReference(table);
    }
    
    /**
     * Define campo de referência
     * @param id
     */
    public final void setListenerReference(String id) {
        combolistener.setReference(id);
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