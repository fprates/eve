/**
 * 
 */
package org.eve.view;

import org.eclipse.swt.widgets.TableColumn;

/**
 * @author francisco.prates
 *
 */
public class TableComponent {
    private String name;
    private TableColumn column;
    
    public TableComponent(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     */
    public final String getName() {
        return name;
    }

    /**
     * 
     * @param column
     */
    public final void setWidget(TableColumn column) {
        this.column = column;
    }
    
    /**
     * 
     * @return
     */
    public final TableColumn getWidget() {
        return column;
    }
}
