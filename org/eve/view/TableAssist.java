package org.eve.view;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
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
public class TableAssist extends AbstractComponentFactory {
    private static final int LINES = 5;
    private boolean editable;
    private boolean insert;
    private boolean noeditbar;
    private boolean remove;
    private int lines;
    private int currentline;
    private ComboAssist comboassist;
    private Composite btarea;
    private Composite area;
    private Controller controller;
    private Map<String, String> references;
    private String name;
    private Table comptable;
    
    public TableAssist() {
        references = new HashMap<String, String>();
        editable = true;
        insert = true;
        noeditbar = false;
        remove = true;
        currentline = 0;
        lines = LINES;
        comboassist = new ComboAssist();
        comboassist.setType(EVE.ccombo);
        comboassist.setControlType(EVE.multi);
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    /**
     * 
     * @param controller
     */
    public final void setController(Controller controller) {
        this.controller = controller;
    }
    
    /**
     * Define tabela como editável
     * @param editable
     */
    public final void setEditable(boolean editable) {
        this.editable = editable;
        
        if (btarea != null)
            btarea.setVisible(editable);
        
        for (Component component : getComponents()) {
            component.setEnabled(editable);
            component.commit();
        }
    }
    
    /**
     * Habilita inserção de linha
     * @param insert
     */
    public final void setInsert(boolean insert) {
        this.insert = insert;
    }
    
    /**
     * Define quantidade de linhas visíveis
     * @param lines
     */
    public final void setLines(int lines) {
        this.lines = lines;
    }
    
    /**
     * Define campo de seleção de itens
     * @param row
     * @param value
     */
    public final void setMarkValue(int row, boolean value) {
        for (Component component : getComponents()) {
            switch (component.getType()) {
            case EVE.single:
            case EVE.multi:
                ((Button)component.getControl(row)).setSelection(value);
                return;
            }
        }
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
    
    /**
     * Define propriedades da coluna
     * @param id
     * @param property
     */
    public final void setColumnProperties(String id, int property) {
        Component component = getComponent(id);
        
        if ((EVE.readonly & property) == EVE.readonly)
            component.setEnabled(false);
    }
    
    /**
     * Define campo de referência
     * @param id
     * @param idref
     */
    public final void setReference(String id, String idref) {
        references.put(id, idref);
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * Retorna número de itens
     * @return
     */
    public final int getItensSize() {
        return comptable.getItems().length;
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
    
    /**
     * Adiciona item em tabela
     */
    private final void addTableItem(TableItem item) {
        int charh;
        int charw;
        Button button;
        CCombo combo;
        TableComponent component;
        TableEditor editor;
        Text text;
        int k = 0;
        CellListener celllistener = null;
        
        comboassist.setFactory(this);
        
        for (Component component_ : getComponents()) {
            component = (TableComponent)component_;
            editor = new TableEditor(comptable);
            editor.grabHorizontal = true;
            component.addEditor(editor);
            
            celllistener = new CellListener(editor);
            celllistener.setCol(k);
            celllistener.setTableAssist(this);

            switch(component.getType()) {
            case EVE.single:
                button = new Button(comptable, SWT.RADIO);
                button.setBackground(item.getBackground());
                button.pack();
                
                editor.minimumWidth = button.getSize().x;
                editor.horizontalAlignment = SWT.LEFT;
                editor.setEditor(button, item, k);
                
                component.getColumn().setWidth(button.getSize().x);
                
                break;
                
            case EVE.multi:
                button = new Button(comptable, SWT.CHECK);
                button.setBackground(item.getBackground());
                button.pack();
                
                editor.minimumWidth = button.getSize().x;
                editor.horizontalAlignment = SWT.LEFT;
                editor.setEditor(button, item, k);
                
                component.getColumn().setWidth(button.getSize().x);

                break;
                
            case EVE.text:
                text = new Text(comptable, SWT.NONE);
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
                comboassist.setItem(comptable.getItemCount() - 1);
                comboassist.setOptions(component.getOptions());
                comboassist.setReference(references.get(component.getName()));
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
            
            k++;
        }
    }
    
    /**
     * Limpa conteúdo da tabela
     */
    public final void clear() {
        currentline = 0;
        
        if (comptable == null)
            return;
        
        for (Component component : getComponents())
            component.clear();
        
        /*
         * restaura quantidade de linhas na tabela
         */
        comptable.removeAll();
        
        for (int k = 0; k < lines; k++)
            addTableItem(new TableItem(comptable, SWT.NONE));
    }
    
    /**
     * Constrói tabela
     * @param container
     * @param listener
     * @return
     */
    public final Composite define(Composite container) {
        TableColumn tablecol;
        TableComponent component;
        Button btins;
        Button btdel;
        Label title;
        
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
            component.setLocale(getLocale());
        }
        
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
    
    public final void dispose() {        
        comptable.dispose();
    }
    
    /**
     * Adiciona item em tabela
     */
    public final void insert() {
        currentline++;
        if (currentline > lines)
            addTableItem(new TableItem(comptable, SWT.NONE));
    }
    
    /**
     * Insere coluna campo texto
     * @param id
     * @param length
     */
    public final void put(String id, int length) {
        Component component = new TableComponent(getMessage(id));
        
        component.setLength(length);
        
        putComponent(id, component);
    }
    
    /**
     * Insere coluna campo texto, tamanho 10 caracteres
     * @param id
     */
    public final void put(String id) {
        TableComponent component = new TableComponent(getMessage(id));
        
        component.setLength(10);
        
        putComponent(id, component);
    }
    
    /**
     * Insere coluna combo box
     * @param id
     * @param length
     * @param options
     */
    public final void putCombo(String id, int length, String[] options) {
        TableComponent component = new TableComponent(getMessage(id));
        
        component.setType(EVE.ccombo);
        component.setOptions(options);
        component.setLength(length);
        
        putComponent(id, component);
    }
    
    /**
     * Insere coluna de marcação de linha
     * @param id
     * @param type
     */
    public final void putMark(String id, int type) {
        TableComponent component = new TableComponent(getMessage(id));
        
        component.setType(type);
        component.setLength(1);
        
        putComponent(id, component);
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
    private TableAssist tableassist;
    private int col;
    private int row;
    
    public CellListener(TableEditor editor) {
        this.editor = editor;
    }
    
    public final void setTableAssist(TableAssist tableassist) {
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
