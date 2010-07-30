/**
 * 
 */
package org.eve.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableColumn;
import org.eve.main.EVE;

/**
 * Componente do assistente de tabela
 * @author francisco.prates
 *
 */
public class TableComponent extends AbstractComponent {
    private int type;
    private ComboListener combolistener;
    private TableColumn column;
    private List<TableEditor> editors;
    
    public TableComponent(String name) {
        type = EVE.text;
        editors = new ArrayList<TableEditor>();
        setName(name);
    }

    /*
     * 
     * Setters
     * 
     */

    /**
     * Define tipo
     * @param type
     */
    public final void setType(int type) {
        this.type = type;
    }
    
    /**
     * Define permissão para edição
     * @param editable
     */
    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
        
        for (TableEditor editor : editors)
            editor.getEditor().setEnabled(enabled);
    }
    
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
     * Retorna tipo
     * @return
     */
    public final int getType() {
        return type;
    }
    
    /**
     * Retorna componente gráfico
     * @param row
     * @return
     */
    public final Control getControl(int row) {
        return editors.get(row).getEditor();
    }
    
    /**
     * Retorna indicador de habilitação
     * @return
     */
    public final boolean isEnabled() {
        return enabled;
    }
    
    /**
     * Retorna componente do Table
     * @return
     */
    public final TableColumn getColumn() {
        return column;
    }
    
    /**
     * Retorna componente editor
     * @param row
     * @return
     */
    public final TableEditor getEditor(int row) {
        return editors.get(row);
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * Adiciona editor
     * @param editor
     */
    public final void addEditor(TableEditor editor) {
        editors.add(editor);
    }
    
    /**
     * Limpa campos da coluna
     */
    public final void clear() {
        for (TableEditor editor : editors) {
            editor.getEditor().dispose();
            editor.dispose();
        }
        
        editors.clear();
    }
}