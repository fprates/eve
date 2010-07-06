package org.eve.view;

import java.util.LinkedHashMap;import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.springframework.context.MessageSource;

public class TableAssist {
    private Map<String, TableComponent> table;
    private MessageSource messages;
    private Locale locale;
    private Table comptable;
    private Composite btarea;
    private Composite area;
    private boolean editable;
    private int lines;
    
    public TableAssist() {
        table = new LinkedHashMap<String, TableComponent>();
        editable = true;
        lines = 5;
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    public final void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    public final void setMessages(MessageSource messages) {
        this.messages = messages;
    }
    
    public final void setEditable(boolean editable) {
        this.editable = editable;
    }
    
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
    
    public final void setIntValue(String id, int row, int value) {
        setStringValue(id, row, Integer.toString(value));
    }
    
    /*
     * 
     * Others
     * 
     */
    
    public final void insert() {
        new TableItem(comptable, SWT.NONE);
    }
    
    public final void put(String id) {
        table.put(id, new TableComponent(messages.getMessage(id, null, locale)));        
    }
    
    public final void setLines(int lines) {
        comptable.setItemCount(lines);
    }
    
    public final Composite define(Composite container, SelectionListener listener) {
        TableColumn tablecol;
        TableComponent component;
        Button btins;
        Button btdel;

        area = new Composite(container, SWT.NONE);
        area.setLayout(new RowLayout(SWT.VERTICAL));

        btarea = new Composite(area, SWT.NONE);
        btarea.setLayout(new RowLayout(SWT.HORIZONTAL));
        btarea.setVisible(editable);
        
        comptable = new Table(area, SWT.NONE);
        comptable.setHeaderVisible(true);
        comptable.setItemCount(lines);
        
//        TableEditor editor = new TableEditor(comptable);
        
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
}
