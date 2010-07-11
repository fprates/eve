/**
 * 
 */
package org.eve.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Widget;
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
    private List<Control> controls;
    
    public TableComponent(String name) {
        this.name = name;
        type = EVE.text;
        controls = new ArrayList<Control>();
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
    
    /**
     * 
     * @param editable
     */
    public final void setEnabled(boolean enabled) {
        for (Control control : controls)
            control.setEnabled(enabled);
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
    
    /**
     * 
     * @param row
     * @return
     */
    public final Widget getControl(int row) {
        return controls.get(row);
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * 
     */
    public final void addControl(Control control) {
        controls.add(control);
    }
}
