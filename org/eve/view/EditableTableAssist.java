package org.eve.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eve.main.EVE;

/**
 * Assistente de tabela
 * @author francisco.prates
 *
 */
public class EditableTableAssist extends AbstractTableAssist {
    private boolean editable;
    private boolean insert;
    private boolean noeditbar;
    private boolean remove;
    private ComboAssist comboassist;
    private Composite btarea;
    private Composite area;
    private String name;
    private Map<Component, List<ControlEditor>> editors;
    
    public EditableTableAssist() {
        editable = true;
        insert = true;
        noeditbar = false;
        remove = true;
        comboassist = new ComboAssist();
        comboassist.setType(EVE.ccombo);
        editors = new HashMap<Component, List<ControlEditor>>();
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    /**
     * Define tabela como editável
     * @param editable
     */
    public final void setEditable(boolean editable) {
        this.editable = editable;
        
        if (btarea != null)
            btarea.setVisible(editable);
        
        for (Component component : getComponents())
            component.setEnabled(editable);
    }
    
    /**
     * Habilita inserção de linha
     * @param insert
     */
    public final void setInsert(boolean insert) {
        this.insert = insert;
    }
    
    /**
     * Define barra de edição
     * @param noeditbar
     */
    public final void setNoEditBar(boolean noeditbar) {
        this.noeditbar = noeditbar;
    }
    
    /**
     * Habilita remoção de linha
     * @param remove
     */
    public final void setRemove(boolean remove) {
        this.remove = remove;
    }
    
    /**
     * Habilita título da tabela
     * @param name
     */
    public final void setName(String name) {
        this.name = name;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractTableAssist#getControl(int)
     */
    protected final Control getControl(Component component, int index) {
        return editors.get(component).get(index).getEditor();
    }
    
    /**
     * 
     * @param event
     * @return
     */
    public final boolean hasEvent(String event) {
        return (event.equals(name+".new") ||
                event.equals(name+".del"))? true : false;
    }
    
    /*
     * 
     * Others
     * 
     */
    
    @Override
    protected final void customizeControl(TableItem item, int k,
            TableComponent component, Control control, int type) {
        TableEditor editor;
        Table table = getTable();
        List<ControlEditor> cols = editors.get(component);
        
        editor = new TableEditor(table);
        editor.grabHorizontal = true;
        
        if (cols == null) {
            cols = new ArrayList<ControlEditor>();
            editors.put(component, cols);
        }
        
        cols.add(editor);
        
        switch(type) {
        case EVE.single:
        case EVE.multi:
            editor.minimumWidth = control.getSize().x;
            editor.horizontalAlignment = SWT.LEFT;
            editor.setEditor(control, item, k);

            break;
        }
    }
    
    @Override
    protected final void addCustomControl(
            TableItem item, int k, TableComponent component, int type) {
        
        int charh;
        int charw;
        CellListener celllistener;
        CCombo combo;
        TableEditor editor;
        Text text;
        Table table = getTable();
        List<ControlEditor> cols = editors.get(component);
        
        editor = new TableEditor(table);
        editor.grabHorizontal = true;
        
        if (cols == null) {
            cols = new ArrayList<ControlEditor>();
            editors.put(component, cols);
        }
        
        cols.add(editor);
        
        celllistener = new CellListener(editor);
        celllistener.setCol(k);
        celllistener.setTableAssist(this);
        
        switch(type) {
        case EVE.text:
            text = new Text(table, SWT.NONE);
            text.setEditable(component.isEnabled());
            text.addListener (SWT.FocusOut, celllistener);
            text.addListener (SWT.Traverse, celllistener);
            
            charw = ViewUtils.getCharWidth(text);
            charh = ViewUtils.getCharHeight(text);
            
            text.setSize(text.computeSize(
                    (component.getLength() * charw), charh));
            
            component.getColumn().setWidth(text.getSize().x);
            editor.setEditor(text, item, k);
            
            break;
            
        case EVE.ccombo:
            comboassist.setItem(table.getItemCount() - 1);
            comboassist.setOptions(component.getOptions());
            comboassist.setReference(getReference(component.getName()));
            comboassist.setLength(component.getLength());
            comboassist.setId(component.getName());
            
            combo = (CCombo)comboassist.newInstance();                
            combo.setEditable(component.isEnabled());
            combo.addListener (SWT.FocusOut, celllistener);
            combo.addListener (SWT.Traverse, celllistener);
            
            component.getColumn().setWidth(combo.getSize().x);
            
            editor.setEditor(combo, item, k);
            
            break;
        
        }
        
    }
    
    /**
     * Constrói tabela
     * @param container
     * @param listener
     * @return
     */
    public final Composite define(Composite container) {
        int lines;
        Button btins;
        Button btdel;
        Label title;
        Table comptable;
        TableColumn tablecol;
        TableComponent component;
        Controller controller = getController();
        
        area = new Composite(container, SWT.NONE);
        area.setLayout(new GridLayout(1, false));

        if (!noeditbar) {
            btarea = new Composite(area, SWT.NONE);
            btarea.setLayout(new RowLayout(SWT.HORIZONTAL));
            btarea.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
            btarea.setVisible(editable);
            
            btins = new Button(btarea, SWT.NONE);
            btins.setText("Novo");
            btins.addSelectionListener(controller);
            btins.setVisible(insert);
            controller.putWidget(btins, name+".new");
            
            btdel = new Button(btarea, SWT.NONE);
            btdel.setText("Remover");
            btdel.addSelectionListener(controller);
            btdel.setVisible(remove);
            controller.putWidget(btdel, name+".del");
            
            btarea.pack();
        }
        
        if (name != null) {
            title = new Label(area, SWT.NONE);
            title.setText(getMessage(name));
        }
        
        comptable = new Table(area, SWT.BORDER);
        setTable(comptable);
        comptable.setHeaderVisible(true);
        comptable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        comboassist.setContainer(comptable);
        comboassist.setController(controller);
        
        for (Component component_ : getComponents()) {
            component = (TableComponent)component_;
            tablecol = new TableColumn(comptable, SWT.NONE);
            tablecol.setText(component.getName());
            tablecol.pack();
            component.setColumn(tablecol);
        }
        
        lines = getLines();
        for (int k = 0; k < lines; k++)
            new TableItem(comptable, SWT.NONE);
        
        /*
         * por motivos que não conheço, não se recomenda usar TableEditor
         * logo após TableItem. Ocorrem problemas graves de dimensionamento
         * do controle de tabela.
         */
        for (TableItem item : comptable.getItems())
            addTableItem(item);
        
        return area;
    }
    
    /**
     * 
     * @param input
     */
    public final void userInput(String input) {
        if (input.equals(name+".new")) {
            System.out.println("nova linha");
            return;
        }
        
        if (input.equals(name+".del")) {
            System.out.println("apagar linha");
            return;
        }
    }
}

/**
 * Listener para campos
 * @author francisco.prates
 *
 */
class CellListener implements Listener {
    private TableEditor editor;
    private EditableTableAssist tableassist;
    private int col;
    private int row;
    
    public CellListener(TableEditor editor) {
        this.editor = editor;
    }
    
    public final void setTableAssist(EditableTableAssist tableassist) {
        this.tableassist = tableassist;
    }
    
    public final void setCol(int col) {
        this.col = col;
    }
    
    public final void setRow(int row) {
        this.row = row;
    }
    
    private String getText() {
        Control control = editor.getEditor();
        
        if (control instanceof Text)
            return ((Text)control).getText();
        
        if (control instanceof CCombo)
            return ((CCombo)control).getText();
        else
            return null;
    }
    
    @Override
    public void handleEvent(Event ev) {
        TableItem item = editor.getItem();
        
        switch (ev.type) {
        case SWT.FocusOut:
            item.setText(col, getText());
            break;
            
        case SWT.Traverse:
            switch (ev.detail) {
            case SWT.TRAVERSE_RETURN:
                item.setText(col, getText());
                ev.doit = false;
                break;
                
            case SWT.TRAVERSE_ESCAPE:
                ev.doit = false;
                break;
                
            case SWT.TRAVERSE_TAB_PREVIOUS:
                item.setText(col, getText());
                ev.doit = false;
                tableassist.sel(col - 1, row);
                break;
                
            case SWT.TRAVERSE_TAB_NEXT:
                item.setText(col, getText());
                ev.doit = false;
                tableassist.sel(col + 1, row);
                break;
            }
            
            break;
        }
    }    
}
