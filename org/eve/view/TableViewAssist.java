package org.eve.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eve.main.EVE;

/**
 * Assistente de tabela
 * @author francisco.prates
 *
 */
public class TableViewAssist extends AbstractTableAssist {
    private ComboAssist comboassist;
    private Composite area;
    private String name;
    
    public TableViewAssist() {
        comboassist = new ComboAssist();
        comboassist.setType(EVE.ccombo);
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * Constrói tabela
     * @param container
     * @param listener
     * @return
     */
    @Override
    public final Composite define(Composite container) {
        int lines;
        TableColumn tablecol;
        TableComponent component;
        Label title;
        Table table;
        
        area = new Composite(container, SWT.NONE);
        area.setLayout(new GridLayout(1, false));
        
        if (name != null) {
            title = new Label(area, SWT.NONE);
            title.setText(getMessage(name));
        }
        
        table = new Table(area, SWT.BORDER);
        setTable(table);
        
        table.setHeaderVisible(true);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        comboassist.setContainer(table);
        comboassist.setController(getController());
        
        for (Component component_ : getComponents()) {
            component = (TableComponent)component_;
            tablecol = new TableColumn(table, SWT.NONE);
            tablecol.setText(component.getName());
            tablecol.pack();
            component.setColumn(tablecol);
        }
        
        lines = getLines();
        for (int k = 0; k < lines; k++)
            new TableItem(table, SWT.NONE);
        
        /*
         * por motivos que não conheço, não se recomenda usar TableEditor
         * logo após TableItem. Ocorrem problemas graves de dimensionamento
         * do controle de tabela.
         */
        for (TableItem item : table.getItems())
            addTableItem(item);
        
        return area;
    }
}
