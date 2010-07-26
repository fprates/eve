/**
 * 
 */
package org.eve.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eve.main.EVE;

/**
 * Componente do assistente de tabela
 * @author francisco.prates
 *
 */
public class TableComponent {
    private String name;
    private int type;
    private String[] options;
    private boolean enabled;
    private boolean nocase;
    private int length;
    private ComponentListener complistener;
    private TableColumn column;
    private List<TableEditor> editors;
    
    public TableComponent(String name) {
        this.name = name;
        type = EVE.text;
        editors = new ArrayList<TableEditor>();        
        enabled = true;
        nocase = false;
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
     * Define valores fixos
     * @param options
     */
    public final void setOptions(String[] options) {
        this.options = options;
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
     * Define largura
     * @param length
     */
    public final void setLength(int length) {
        this.length = length;
    }
    
    /**
     * Define coluna do componente Table
     * @param column
     */
    public final void setColumn(TableColumn column) {
        this.column = column;
    }
    
    /**
     * Define sensibilidade para maiúscula/minuscula
     * @param nocase
     */
    public final void setNocase(boolean nocase) {
        this.nocase = nocase;
    }
    
    /**
     * Define campos de referência 
     * @param table
     */
    public final void setTableReference(Map<String, TableComponent> table) {
        complistener.setTableReference(table);
    }
    
    /**
     * Define campo de referência
     * @param id
     */
    public final void setListenerReference(String id) {
        complistener.setReference(id);
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * Retorna título
     * @return
     */
    public final String getName() {
        return name;
    }
    
    /**
     * Retorna tipo
     * @return
     */
    public final int getType() {
        return type;
    }
    
    /**
     * Retorna valores fixos
     * @return
     */
    public final String[] getOptions() {
        return options;
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
     * Retorna comprimento
     * @return
     */
    public final int getLength() {
        return length;
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
    
    /**
     * Retorna indicador de sensibilidade
     * @return
     */
    public final boolean isNocase() {
        return nocase;
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * gera listener de campos
     * @param id
     * @param controller
     * @param table
     * @return
     */
    public final Listener listener(String id, Controller controller, int index) {
        complistener = new ComponentListener(id, controller, index);
        
        return complistener;
    }
    
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

class ComponentListener implements Listener {
    private int index;
    private String id;
    private Controller controller;
    private String reference;
    private Map<String, TableComponent> table;
    private Map<Object, String> results;
    
    public ComponentListener(String id, Controller controller, int index) {
        this.id = id;
        this.controller = controller;
        this.index = index;
    }
    
    /**
     * 
     * @param table
     */
    public final void setTableReference(Map<String, TableComponent> table) {
        this.table = table;
    }
    
    /**
     * 
     * @param reference
     */
    public final void setReference(String reference) {
        this.reference = reference;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    @Override
    public final void handleEvent(Event ev) {
        Object object;
        TableComponent component;
        CCombo combo = (CCombo)ev.widget;
        
        if (combo.getListVisible() || combo.getItems().length > 0)
            return;
        
        combo.clearSelection();
        combo.removeAll();
        
        if (reference != null) {
            component = table.get(reference);
            switch (component.getType()) {
            case EVE.combo:
                object = ((CCombo)component.getControl(index)).getText();
                break;
                
            default:
                object = null;
                break;
            } 
        } else {
            object = null;
        }
        
        results = controller.getResults(id, object);
        
        if (results == null)
            return;
        
        for (Object object_ : results.keySet())
            combo.add(results.get(object_));
    }    
}