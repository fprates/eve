/**
 * 
 */
package org.eve.view;

import org.eclipse.swt.widgets.TableColumn;
import org.eve.main.EVE;

/**
 * @author francisco.prates
 *
 */
public class TableComponent {
    private String name;
    private TableColumn column;
    private int type;
    private String[] options;
    
    public TableComponent(String name) {
        this.name = name;
        type = EVE.text;
    }

    /*
     * 
     * Setters
     * 
     */
    /**
     * 
     * @param column
     */
    public final void setWidget(TableColumn column) {
        this.column = column;
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
     * @param options
     */
    public final void setOptions(String[] options) {
        this.options = options;
    }
    
    /*
     * 
     * Getters
     * 
     */
    /**
     * 
     * @return
     */
    public final String getName() {
        return name;
    }
    
    /**
     * 
     * @return
     */
    public final TableColumn getWidget() {
        return column;
    }
    
    /**
     * 
     * @return
     */
    public final int getType() {
        return type;
    }
    
    /**
     * 
     * @return
     */
    public final String[] getOptions() {
        return options;
    }
}
