package org.eve.view;

import java.sql.Time;
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
    private TableEditor editor;
    private TableListener tablelistener;
    private String name;
    
    public TableAssist() {
        table = new LinkedHashMap<String, TableComponent>();
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
        int i = 0;
        
        for (String id_ : table.keySet()) {
            if (id_.equals(id)) {
                comptable.getItem(row).setText(i, value);
                break;
            }
            i++;
        }
    }
    
    /**
     * Define valor inteiro em tabela
     * @param id
     * @param row
     * @param value
     */
    public final void setIntValue(String id, int row, int value) {
        CCombo combo;
        TableComponent component = table.get(id);
        
        switch (component.getType()) {
        case EVE.text:
            setStringValue(id, row, Integer.toString(value));
            break;
            
        case EVE.combo:
            combo = (CCombo)component.getControl(row);
            combo.setText(combo.getItem(value));
        }
    }
    
    /**
     * 
     * @param id
     * @param row
     * @param value
     */
    public final void setTimeValue(String id, int row, Time value) {
        if (value == null)
            value = Time.valueOf("00:00:00");
            
        setStringValue(id, row, value.toString());
    }
    
    /**
     * Define quantidade de linhas visíveis
     * @param lines
     */
    public final void setLines(int lines) {
        this.lines = lines;
    }
    
    public final void setInsert(boolean insert) {
        this.insert = insert;
    }
    
    public final void setRemove(boolean remove) {
        this.remove = remove;
    }
    
    public final void setName(String name) {
        this.name = name;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    public final String getStringValue(String id, int row) {
        String value;
        int i = 0;
        TableItem item = comptable.getItem(row);
        
        for (String id_ : table.keySet()) {
            if (id_.equals(id)) {
                value = item.getText(i);
                return (value == null)?"" : value;
            }
            i++;
        }
        
        return "";
    }
    
    public final int getItensSize() {
        return comptable.getItems().length;
    }
    
    /**
     * Retorna inteiro da tabela
     * @param id coluna
     * @param row linha
     * @return conteúdo inteiro
     */
    public final int getIntValue(String id, int row) {
        CCombo combo;
        String value;
        int value_;
        
        switch (table.get(id).getType()) {
        case EVE.text:
            value = getStringValue(id, row);
            
            if (value.equals(""))
                return 0;
            
            return Integer.parseInt(value);
        case EVE.combo:
            combo = (CCombo)table.get(id).getControl(row);
            value_ = combo.getSelectionIndex();
            
            return (value_ == -1)? 0 : value_;
        }
        
        return 0;
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
            return null;
        
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
    private final void addTableItem() {
        String[] options;
        CCombo combo;
        TableComponent component;
        TableEditor editor;
        int k = 0;
        TableItem item = new TableItem(comptable, SWT.NONE);
        
        for (String id : table.keySet()) {
            component = table.get(id);
                
            switch(component.getType()) {
            case EVE.combo:
                combo = new CCombo(comptable, SWT.NONE);
                combo.setEditable(editable);
                component.addControl(combo);
                options = component.getOptions();
                
                if (options.length > 0)
                    combo.setText(options[0]);
                
                for (String option : options)
                    combo.add(option);
                
                editor = new TableEditor(comptable);
                editor.grabHorizontal = true;
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
            addTableItem();
    }
    
    /**
     * Insere coluna de campo texto
     * @param id
     */
    public final void put(String id) {        
        table.put(id, new TableComponent(
                messages.getMessage(id, null, id, locale)));        
    }
    
    /**
     * Insere coluna de combo box
     * @param id
     * @param options
     */
    public final void putCombo(String id, String[] options) {
        TableComponent component = new TableComponent(
                messages.getMessage(id, null, id, locale));
        
        component.setType(EVE.combo);
        component.setOptions(options);
        
        table.put(id, component);
    }
    
    /**
     * Constrói tabela
     * @param container
     * @param listener
     * @return
     */
    public final Composite define(Composite container, SelectionListener listener) {
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
        btarea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        if (name != null)
            new Label(area, SWT.NONE).setText(
                    messages.getMessage(name, null, name, locale));
        
        comptable = new Table(area, SWT.NONE);
        comptable.setHeaderVisible(true);
        comptable.addSelectionListener(this);
        comptable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        editor = new TableEditor(comptable);
        editor.horizontalAlignment = SWT.LEFT;
        editor.grabHorizontal = true;
        
        tablelistener = new TableListener(comptable);
        tablelistener.setTableAssist(this);
        tablelistener.setEditable(editable);
        comptable.addListener(SWT.MouseDown, tablelistener);
        
        btins = new Button(btarea, SWT.NONE);
        btins.setText("Novo");
        btins.addSelectionListener(listener);
        btins.setVisible(insert);
//      widgetresponse.put(btins, name+".new");
        
        btdel = new Button(btarea, SWT.NONE);
        btdel.setText("Remover");
        btdel.addSelectionListener(listener);
        btdel.setVisible(remove);
//        widgetresponse.put(btdel, name+".del");
        
        btarea.pack();
        
        for (String id : table.keySet()) {
            component = table.get(id);
            tablecol = new TableColumn(comptable, SWT.NONE);
            tablecol.setText(component.getName());
            tablecol.pack();
            component.setWidget(tablecol);
        }
        
        for (k=1; k <= lines; k++)
            addTableItem();
        
        area.pack();
        
        return area;
    }
    
    /**
     * Edita célula
     * @param item
     * @param col
     */
    public void cellEdit(TableItem item, int col) {
        Text cell;
        CellListener celllistener;
        
        cell = new Text(comptable, SWT.NONE);
        celllistener = new CellListener(cell);
        celllistener.setCol(col);
        celllistener.setItem(item);
        celllistener.setTable(this);
        
        cell.addListener (SWT.FocusOut, celllistener);
        cell.addListener (SWT.Traverse, celllistener);
        editor.setEditor (cell, item, col);
        cell.setText (item.getText(col));
        cell.selectAll();
        cell.setFocus();
        
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
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
    private TableAssist table;
    private TableItem item;
    private Text text;
    private int col;
    
    public CellListener(Text text) {
        this.text = text;
    }
    
    public final void setItem(TableItem item) {
        this.item = item;
    }
    
    public final void setCol(int col) {
        this.col = col;
    }
    
    public final void setTable(TableAssist table) {
        this.table = table;
    }
    
    @Override
    public void handleEvent(Event ev) {        
        switch (ev.type) {
            case SWT.FocusOut:
                item.setText(col, text.getText());
                text.dispose();
                break;
                
            case SWT.Traverse:
                switch (ev.detail) {
                    case SWT.TRAVERSE_RETURN:
                        item.setText(col, text.getText());
                        text.dispose();
                        ev.doit = false;
                        break;
                        
                    case SWT.TRAVERSE_ESCAPE:
                        text.dispose();
                        ev.doit = false;
                        break;
                        
                    case SWT.TRAVERSE_TAB_PREVIOUS:
                        item.setText(col, text.getText());
                        text.dispose();
                        ev.doit = false;
                        
                        text.dispose();
                        table.cellEdit(item, --col);
                        break;                        
                        
                    case SWT.TRAVERSE_TAB_NEXT:
                        item.setText(col, text.getText());
                        text.dispose();
                        ev.doit = false;
                        
                        text.dispose();
                        table.cellEdit(item, ++col);
                        break;
                }
                
                break;
        }
    }    
}

class TableListener implements Listener {
    private Table table;
    private TableAssist tableassist;
    private boolean editable;
    
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
        Rectangle clientArea;
        Point pt;        
        int index;
        
        if (!editable)
            return;
        
        clientArea = table.getClientArea();
        pt = new Point(event.x, event.y);        
        index = table.getTopIndex();
        
        while (index < table.getItemCount()) {
            visible = false;
            item = table.getItem(index);
            
            for (int col = 0; col < table.getColumnCount(); col++) {
                rect = item.getBounds(col);
                
                if (rect.contains(pt)) {
                    tableassist.cellEdit(item, col);                    
                    return;
                }
                
                if (!visible && rect.intersects(clientArea))
                    visible = true;
            }
            
            if (!visible)
                return;
            
            index++;
        }
    }
}


