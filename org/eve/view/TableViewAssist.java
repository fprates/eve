package org.eve.view;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
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
    private Composite area;
    private Composite btarea;
    private Map<String, Button> buttons;
    private TableItemForm itemform;
    
    public TableViewAssist(String name) {
        super(name);
        buttons = new HashMap<String, Button>();
    }
    
    @Override
    protected final void setComponentState(String name, boolean state) {
        buttons.get(name).setEnabled(state);
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * 
     * @param controller
     * @param visible
     * @param tag
     * @param action
     */
    private void defButton(Controller controller, String tag, String action) {
        Button bt = new Button(btarea, SWT.NONE);
        
        buttons.put(action, bt);
        insertActionState(action);
        setActionState(action, isEditable());
        
        bt.setText(getMessage(tag));
        bt.addSelectionListener(controller);
        controller.putWidget(bt, action);
    }
    
    /**
     * Constrói tabela
     * @param container
     * @param listener
     * @return
     */
    @Override
    public final Composite define(Composite container) {
        TableColumn tablecol;
        TableComponent component;
        Label title;
        Table table;
        ComboAssist comboassist = getComboAssist();
        Controller controller = getController();
        String name = getName();
        
        area = new Composite(container, SWT.NONE);
        area.setLayout(new GridLayout(1, false));
        
        if (name != null) {
            btarea = new Composite(area, SWT.NONE);
            btarea.setLayout(new RowLayout(SWT.HORIZONTAL));
            btarea.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
            btarea.setVisible(isEditable());
            
            defButton(controller, "tableline.insert", name+".insert");
            defButton(controller, "tableline.remove", name+".remove");
            defButton(controller, "tableline.update", name+".update");
            
            btarea.pack();
            
            title = new Label(area, SWT.NONE);
            title.setText(getMessage(name));
        }
        
        table = new Table(area, SWT.BORDER);
        setTable(table);
        
        table.setHeaderVisible(true);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        comboassist.setContainer(table);
        
        for (Component component_ : getComponents()) {
            component = (TableComponent)component_;
            tablecol = new TableColumn(table, SWT.NONE);
            tablecol.setText(getMessage(component.getName()));
            tablecol.pack();
            component.setColumn(tablecol);
        }
        
        /*
         * por motivos que não conheço, não se recomenda usar TableEditor
         * logo após TableItem. Ocorrem problemas graves de dimensionamento
         * do controle de tabela.
         */
        for (TableItem item : table.getItems())
            addTableItem(item);
        
        itemform = new TableItemForm(area, this);
        itemform.setController(getController());
        itemform.setLocale(getLocale());
        
        table.pack();
        
        return area;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractTableAssist#userInput(java.lang.String)
     */
    @Override
    public final void userInput(String input) {
        Table table;
        int selitens[];
        String name = getName();
        
        if (input.equals(name+".insert")) {
            itemform.openDialog(EVE.insert);
            
            return;
        }
        
        if (input.equals(name+".update")) {
            for (int sel : getSelectedItens()) {
                itemform.setItem(sel);
                itemform.openDialog(EVE.update);
                break;
            }
            
            return;
        }
        
        if (input.equals(name+".remove")) {
            table = getTable();
            selitens = getSelectedItens();
            
            if (selitens.length > 0)
                table.remove(selitens);
        }
    }
}
