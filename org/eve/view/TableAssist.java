package org.eve.view;

import java.sql.Time;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
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
import org.springframework.context.MessageSource;

public class TableAssist implements SelectionListener {
    private static final int LINES = 5;
    private Map<String, TableComponent> table;
    private Map<String, String> references;
    private MessageSource messages;
    private Locale locale;
    private Table comptable;
    private Composite btarea;
    private Composite area;
    private boolean editable;
    private boolean insert;
    private boolean remove;
    private TableItem[] selectedItens;
    private int lines;
    private int currentline;
    private TableListener tablelistener;
    private String name;
    private Controller controller;
    
    public TableAssist() {
        table = new LinkedHashMap<String, TableComponent>();
        references = new HashMap<String, String>();
        editable = true;
        insert = true;
        remove = true;
        currentline = 0;
        lines = LINES;
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    public final void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    /**
     * Define mensagens para tabela
     * @param messages
     */
    public final void setMessages(MessageSource messages) {
        this.messages = messages;
    }
    
    /**
     * Define tabela como editável
     * @param editable
     */
    public final void setEditable(boolean editable) {        
        this.editable = editable;
        
        if (tablelistener != null)
            tablelistener.setEditable(editable);
        
        if (btarea != null)
            btarea.setVisible(editable);
        
        for (String id : table.keySet())
            table.get(id).setEnabled(editable);
    }
    
    /**
     * Define string em tabela
     * @param id
     * @param row
     * @param value
     */
    public final void setStringValue(String id, int row, String value) {
        TableComponent component;
        
        for (String id_ : table.keySet())
            if (id_.equals(id)) {
                component = table.get(id);
                switch (component.getType()) {
                case EVE.text:
                    ((Text)component.getControl(row)).setText(value);
                    break;
                    
                case EVE.combo:
                    ((CCombo)component.getControl(row)).setText(value);
                    break;
                }
                break;
            }
    }
    
    /**
     * Define valor inteiro em tabela
     * @param id
     * @param row
     * @param value
     */
    public final void setIntValue(String id, int row, int value) {
        TableComponent component = table.get(id);
        
        switch (component.getType()) {
        case EVE.text:
            setStringValue(id, row, Integer.toString(value));
            break;
            
        case EVE.combo:
            setStringValue(id, row, component.getOptions()[value]);
            break;
        }
    }
    
    /**
     * 
     * @param id
     * @param row
     * @param value
     */
    public final void setTimeValue(String id, int row, Time time) {
        setStringValue(id, row, (time == null)?"":time.toString());
    }
    
    /**
     * Define quantidade de linhas visíveis
     * @param lines
     */
    public final void setLines(int lines) {
        this.lines = lines;
    }
    
    /**
     * 
     * @param insert
     */
    public final void setInsert(boolean insert) {
        this.insert = insert;
    }
    
    /**
     * 
     * @param remove
     */
    public final void setRemove(boolean remove) {
        this.remove = remove;
    }
    
    /**
     * 
     * @param name
     */
    public final void setName(String name) {
        this.name = name;
    }
    
    /**
     * 
     * @param id
     * @param property
     */
    public final void setColumnProperties(String id, int property) {
        TableComponent component = table.get(id);
        
        if ((EVE.readonly & property) == EVE.readonly)
            component.setEnabled(false);
    }
    
    /**
     * 
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

    public final String getStringValue(String id, int row) {
        TableComponent component;
        String value = null;
        
        for (String id_ : table.keySet())
            if (id_.equals(id)) {
                component = table.get(id);
                switch (component.getType()) {
                case EVE.combo:
                    value = ((CCombo)component.getControl(row)).getText();
                    break;
                    
                case EVE.text:
                    value = ((Text)component.getControl(row)).getText();
                    break;
                }
                
                return (value == null)?"" : value;
            }
        
        return "";
    }
    
    /**
     * 
     * @return
     */
    public final int getItensSize() {
        return comptable.getItems().length;
    }
    
    /**
     * Retorna inteiro da tabela
     * @param id coluna
     * @param row linha
     * @return conteúdo inteiro
     */
    public final int getIntValue(String id, int row)
        throws NumberFormatException {
        String value;
        int value_;
        
        switch (table.get(id).getType()) {
        case EVE.text:
            value = getStringValue(id, row);            
            return (value.equals(""))? 0 : Integer.parseInt(value);
            
        case EVE.combo:
            value_ = ((CCombo)table.get(id).getControl(row)).getSelectionIndex();            
            return (value_ == -1)? 0 : value_;
            
        default:            
            return 0;
        }
    }
    
    /**
     * 
     * @param id
     * @param row
     * @return
     */
    public final Time getTimeValue(String id, int row) {
        String value = getStringValue(id, row);
        
        if (value.equals(""))
            return Time.valueOf("00:00:00");
        
        return Time.valueOf(value);
    }
    
    /**
     * Retorna string da linha selecionada na tabela
     * @param id coluna
     * @param row linha
     * @return conteúdo string
     */
    public final String getSelectedStringValue(String id, int row) {
        String value;
        int i = 0;
        
        for (String id_ : table.keySet()) {
            if (id_.equals(id)) {
                value = selectedItens[row].getText(i);
                return (value == null)?"" : value;
            }
            i++;
        }
        
        return "";            
    }
    
    /**
     * Retorna inteiro da linha selecionada na tabela
     * @param id coluna
     * @param row linha
     * @return conteúdo inteiro
     */
    public final int getSelectedIntValue(String id, int row) {
        String value = getSelectedStringValue(id, row);
        
        if (value.equals(""))
            return 0;
        
        return Integer.parseInt(value);
    }
    
    /**
     * 
     * @return
     */
    public final int getSelectedItensSize() {
        return (selectedItens == null)?0:selectedItens.length;
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * Limpa conteúdo da tabela e itens selecionados
     */
    public final void clear() {
        if (comptable != null)
            comptable.clearAll();
        
        selectedItens = null;
    }
    
    /**
     * Limpa itens selecionados
     */
    public final void clearSelectedItens() {
        selectedItens = null; 
    }
    
    /**
     * Adiciona item em tabela
     */
    private final void addTableItem(Controller controller, int row) {
        String[] options;
        CCombo combo;
        Text text;
        TableEditor editor;
        TableComponent component;
        int charh;
        int charw;
        int k = 0;
        CellListener celllistener = null;
        TableItem item = new TableItem(comptable, SWT.NONE);
        
        for (String id : table.keySet()) {
            component = table.get(id);
            
            editor = new TableEditor(comptable);
            editor.grabHorizontal = true;
            component.addEditor(editor);
            
            celllistener = new CellListener(editor);
            celllistener.setCol(k);
            celllistener.setTableAssist(this);

            switch(component.getType()) {
            case EVE.text:
                text = new Text(comptable, SWT.NONE);
                text.setEditable(component.isEnabled());
                text.setTextLimit(component.getLength());                
                text.addListener (SWT.FocusOut, celllistener);
                text.addListener (SWT.Traverse, celllistener);
                
                charw = ViewUtils.getCharWidth(text);
                charh = ViewUtils.getCharHeight(text);
                
                text.setSize(text.computeSize(
                        (component.getLength() * charw), charh));
                
                component.getColumn().setWidth(text.getSize().x);
                editor.setEditor(text, item, k);
                
                break;
                
            case EVE.combo:
                combo = new CCombo(comptable, SWT.NONE);
                combo.setEditable(component.isEnabled());
                combo.setTextLimit(component.getLength());                
                combo.addListener (SWT.FocusOut, celllistener);
                combo.addListener (SWT.Traverse, celllistener);
                
                charw = ViewUtils.getCharWidth(combo);
                charh = ViewUtils.getCharHeight(combo);
                
                combo.setSize(combo.computeSize(
                        (component.getLength() * charw) + 35, charh));
                
                component.getColumn().setWidth(combo.getSize().x);
                options = component.getOptions();
                
                /*
                 * definições para carga dinâmica de valores
                 */
                if (options != null && options.length > 0) {
                    combo.setText(options[0]);
                    combo.setItems(options);
                }
                
                if (options == null) {
                    combo.addListener(SWT.MouseDown, component.listener(
                            id, controller, comptable.getItemCount() - 1));
                    component.setTableReference(table);
                    component.setListenerReference(references.get(id));
                }
                
                editor.setEditor(combo, item, k);
                
                break;
            }
            
            k++;
        }
    }
    
    /**
     * Adiciona item em tabela
     */
    public final void insert() {
        currentline++;
        if (currentline > lines)
            addTableItem(controller, currentline);
    }
    
    /**
     * 
     * @param id
     * @param length
     */
    public final void put(String id, int length) {
        TableComponent component = new TableComponent(
                messages.getMessage(id, null, id, locale));
        
        component.setLength(length);
        
        table.put(id, component);
    }
    
    
    public final void put(String id) {
        TableComponent component = new TableComponent(
                messages.getMessage(id, null, id, locale));
        
        component.setLength(10);
        
        table.put(id, component);
    }
    
    /**
     * 
     * @param id
     * @param length
     * @param options
     */
    public final void putCombo(String id, int length, String[] options) {
        TableComponent component = new TableComponent(
                messages.getMessage(id, null, id, locale));
        
        component.setType(EVE.combo);
        component.setOptions(options);
        component.setLength(length);
        
        table.put(id, component);
    }
    
    /**
     * Constrói tabela
     * @param container
     * @param listener
     * @return
     */
    public final Composite define(Composite container, Controller controller) {
        TableColumn tablecol;
        TableComponent component;
        Button btins;
        Button btdel;
        int k;

        area = new Composite(container, SWT.NONE);
        area.setLayout(new GridLayout(1, false));

        btarea = new Composite(area, SWT.NONE);
        btarea.setLayout(new RowLayout(SWT.HORIZONTAL));
        btarea.setVisible(editable);
        btarea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        
        if (name != null)
            new Label(area, SWT.NONE).setText(
                    messages.getMessage(name, null, name, locale));
        
        comptable = new Table(area, SWT.NONE);
        comptable.setHeaderVisible(true);
        comptable.addSelectionListener(this);
        comptable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        
        tablelistener = new TableListener(comptable);
        tablelistener.setEditable(editable);
        comptable.addListener(SWT.MouseDown, tablelistener);
        
        btins = new Button(btarea, SWT.NONE);
        btins.setText("Novo");
        btins.addSelectionListener(controller);
        btins.setVisible(insert);
//      widgetresponse.put(btins, name+".new");
        
        btdel = new Button(btarea, SWT.NONE);
        btdel.setText("Remover");
        btdel.addSelectionListener(controller);
        btdel.setVisible(remove);
//        widgetresponse.put(btdel, name+".del");
        
        btarea.pack();
        
        for (String id : table.keySet()) {
            component = table.get(id);
            tablecol = new TableColumn(comptable, SWT.NONE);
            tablecol.setText(component.getName());
            component.setColumn(tablecol);
//            tablecol.pack();
        }
        
        for (k = 0; k < lines; k++)
            addTableItem(controller, k);
        
        this.controller = controller;
        
        area.pack();
        
        return area;
    }
    
    public final void sel(int col, int row) {
        Object[] objects = table.values().toArray();
        Control control = ((TableComponent)objects[col]).getEditor(row)
            .getEditor();
        
        if (control instanceof Text)
            ((Text)control).selectAll();
        
        control.setFocus();        
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) { }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public void widgetSelected(SelectionEvent ev) {        
        selectedItens = comptable.getSelection();
    }
}

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
        else
            return ((CCombo)control).getText();
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

class TableListener implements Listener {
    private Table table;
    private boolean editable;
    private TableAssist tableassist;
    
    public TableListener(Table table) {
        this.table = table;
    }
    
    public final void setTableAssist(TableAssist tableassist) {
        this.tableassist = tableassist;
    }
    
    public final void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    @Override
    public void handleEvent (Event event) {
        boolean visible;
        Rectangle rect;
        TableItem item;
        Rectangle clientarea;
        int index;
        int col;
        Point pt;
        
        if (!editable)
            return;
        
        clientarea = table.getClientArea();
        pt = new Point(event.x, event.y);
        index = table.getTopIndex();
        
        while (index < table.getItemCount()) {
            visible = false;
            item = table.getItem(index);
            
            for (col = 0; col < table.getColumnCount(); col++) {
                rect = item.getBounds(col);
                
                if (rect.contains(pt)) {
                    tableassist.sel(col, index);
                    return;
                }
                
                if (!visible && rect.intersects(clientarea))
                    visible = true;
            }
            
            if (!visible)
                return;
            
            index++;
        }
    }
}


