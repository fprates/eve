package org.eve.view;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
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
    private TableItem[] selectedItens;
    private int lines;
    private int currentline;
    private TableEditor editor;
    
    public TableAssist() {
        table = new LinkedHashMap<String, TableComponent>();
        editable = true;
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
        setStringValue(id, row, Integer.toString(value));
    }
    
    /**
     * Define quantidade de linhas visíveis
     * @param lines
     */
    public final void setLines(int lines) {
        this.lines = lines;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    public final String getSelectedItem(int row, String id) {
        int i = 0;
        
        for (String id_ : table.keySet()) {
            if (id_.equals(id))
                return selectedItens[row].getText(i);
            i++;
        }
        
        return null;            
    }
    
    /**
     * 
     * @param row
     * @param id
     * @return
     */
    public final int getSelectedIntItem(int row, String id) {
        String value = getSelectedItem(row, id);
        
        if ((value == null) || (value.equals("")))
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
    
    public final void clearSelectedItens() {
        selectedItens = null; 
    }
    
    public final void insert() {
        currentline++;
        if (currentline <= lines)
            new TableItem(comptable, SWT.NONE);
    }
    
    /**
     * 
     * @param id
     */
    public final void put(String id) {        
        table.put(id, new TableComponent(messages.getMessage(id, null, locale)));        
    }
    
    /**
     * 
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
        TableListener tablelistener;

        area = new Composite(container, SWT.NONE);
        area.setLayout(new RowLayout(SWT.VERTICAL));

        btarea = new Composite(area, SWT.NONE);
        btarea.setLayout(new RowLayout(SWT.HORIZONTAL));
        btarea.setVisible(editable);
        
        comptable = new Table(area, SWT.NONE);
        comptable.setHeaderVisible(true);
        comptable.addSelectionListener(this);
        
        editor = new TableEditor(comptable);
        editor.horizontalAlignment = SWT.LEFT;
        editor.grabHorizontal = true;
        
        tablelistener = new TableListener(comptable);
        tablelistener.setTableAssist(this);
        comptable.addListener(SWT.MouseDown, tablelistener);
        
        for (k=1; k <= lines; k++)
            insert();
        
        btins = new Button(btarea, SWT.NONE);
        btdel = new Button(btarea, SWT.NONE);
        btins.setText("Novo");
        btins.addSelectionListener(listener);
//        widgetresponse.put(btins, name+".new");
        btdel.setText("Remover");
        btdel.addSelectionListener(listener);
//        widgetresponse.put(btdel, name+".del");
        
        btarea.pack();
        
        for (String id : table.keySet()) {
            component = table.get(id);
            tablecol = new TableColumn(comptable, SWT.NONE);
            tablecol.setText(component.getName());
            tablecol.pack();
            component.setWidget(tablecol);
        }
        
        return area;        
    }
    
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
    
    public TableListener(Table table) {
        this.table = table;
    }
    
    public final void setTableAssist(TableAssist tableassist) {
        this.tableassist = tableassist;
    }
    
    @Override
    public void handleEvent (Event event) {
        boolean visible;
        Rectangle rect;
        TableItem item;
        Rectangle clientArea = table.getClientArea();
        Point pt = new Point(event.x, event.y);        
        int index = table.getTopIndex();
        
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


