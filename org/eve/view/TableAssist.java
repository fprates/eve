package org.eve.view;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.springframework.context.MessageSource;

public class TableAssist {
    private List<TableItem> values;
    private Map<String, TableComponent> table;
    private MessageSource messages;
    private Locale locale;
    Table comptable;
    
    public TableAssist() {
        table = new LinkedHashMap<String, TableComponent>();
        values = new LinkedList<TableItem>();
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
    
    public final void insert() {
        values.add(new TableItem(comptable, SWT.NONE));
    }
    
    public final void setStringValue(String id, int row, String value) {
        int i = 0;
        
        for (String id_ : table.keySet()) {
            if (id_.equals(id)) {
                values.get(row).setText(i, value);
                break;
            }
            i++;
        }
    }
    
    public final void setIntValue(String id, int row, int value) {
        setStringValue(id, row, Integer.toString(value));
    }
    
    public final void put(String id) {
        table.put(id, new TableComponent(messages.getMessage(id, null, locale)));        
    }
    
    public final Composite define(Composite container, SelectionListener listener) {
        TableColumn tablecol;
        TableComponent component;
        Composite area = new Composite(container, SWT.NONE);
        Composite btarea = new Composite(area, SWT.NONE);
        Button btins = new Button(btarea, SWT.NONE);
        Button btdel = new Button(btarea, SWT.NONE);
        
        comptable = new Table(area, SWT.NONE);
//        TableEditor editor = new TableEditor(comptable);
        
        area.setLayout(new GridLayout(1, false));
        
        btarea.setLayout(new RowLayout());
        btarea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        btins.setText("Novo");
        btins.addSelectionListener(listener);
//        widgetresponse.put(btins, name+".new");
        btdel.setText("Remover");
        btdel.addSelectionListener(listener);
//        widgetresponse.put(btdel, name+".del");
        btarea.pack();
        
        comptable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        comptable.setLinesVisible(true);
        comptable.setHeaderVisible(true);
        
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
