package org.eve.view;

import java.sql.Time;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
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
import org.eve.main.EveAPI;
import org.springframework.context.MessageSource;

/**
 * Assistente de tabela
 * @author francisco.prates
 *
 */
public class TableAssist {
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
    private int lines;
    private int currentline;
    private String name;
    private Controller controller;
    private EveAPI system;
    
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
    
    /**
     * Define localização
     * @param locale
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
                
                if (!component.isNocase() && value != null)
                    value = value.toUpperCase(locale);
                
                if (value == null)
                    value = "";
                
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
     * Define campo de seleção de itens
     * @param row
     * @param value
     */
    public final void setMarkValue(int row, boolean value) {
        TableComponent component;
        
        for (String id : table.keySet()) {
            component = table.get(id);
            switch (component.getType()) {
            case EVE.single:
            case EVE.multi:
                ((Button)component.getControl(row)).setSelection(value);
                return;
            }
        }
    }
    
    /**
     * Define valor do campo hora
     * @param id
     * @param row
     * @param value
     */
    public final void setTimeValue(String id, int row, Time time) {
        String value;
        
        if (time == null) {
            setStringValue(id, row, "");
            return;
        }
        
        value = time.toString();
        setStringValue(id, row, (value.equals("00:00:00"))?"":value);
    }
    
    /**
     * Define quantidade de linhas visíveis
     * @param lines
     */
    public final void setLines(int lines) {
        this.lines = lines;
    }
    
    /**
     * Habilita inserção de linha
     * @param insert
     */
    public final void setInsert(boolean insert) {
        this.insert = insert;
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
        TableComponent component = table.get(id);
        
        if ((EVE.readonly & property) == EVE.readonly)
            component.setEnabled(false);
        
        if ((EVE.nocase & property) == EVE.nocase)
            component.setNocase(true);
    }
    
    /**
     * Define campo de referência
     * @param id
     * @param idref
     */
    public final void setReference(String id, String idref) {
        references.put(id, idref);
    }
    
    /**
     * 
     * @param system
     */
    public final void setSystem(EveAPI system) {
        this.system = system;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * 
     * @param message
     * @return
     */
    private final String getMessage(String message) {
        return messages.getMessage(message, null, message, locale);
    }

    /**
     * Define valor do campo string
     * @param id
     * @param row
     * @return
     */
    public final String getStringValue(String id, int row) {
        Control control;
        TableComponent component;
        String value;
        
        for (String id_ : table.keySet())
            if (id_.equals(id)) {
                component = table.get(id);
                control = component.getControl(row);
                
                switch (component.getType()) {
                case EVE.combo:
                    value = ((CCombo)control).getText();
                    if (value == null)
                        return "";
                    
                    if (!component.isNocase())
                        ((CCombo)control).setText(value.toUpperCase(locale));
                    break;
                    
                case EVE.text:
                    value = ((Text)control).getText();
                    if (value == null)
                        return "";
                    
                    if (!component.isNocase())
                        ((Text)control).setText(value.toUpperCase(locale));
                    break;
                    
                default:
                    value = "";
                }
                
                return value;
            }
        
        return "";
    }
    
    /**
     * Retorna número de itens
     * @return
     */
    public final int getItensSize() {
        return comptable.getItems().length;
    }
    
    /**
     * Retorna valor do campo inteiro
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
     * Retorna valor do campo hora
     * @param id
     * @param row
     * @return
     */
    public final Time getTimeValue(String id, int row) {
        String value = getStringValue(id, row);
        int len;
            
        if (value.equals(""))
            return Time.valueOf("00:00:00");
        
        value = value.replace(":", "");
        len = value.length(); 
        if ((len % 2) != 0) {
            system.setMessage(EVE.error, getMessage("invalid.time.format"));
            table.get(id).getControl(row).setFocus();
            throw new IllegalArgumentException();
        }
        
        if ((len != 4) && (len != 6)) {
            system.setMessage(EVE.error, getMessage("invalid.time.format"));
            table.get(id).getControl(row).setFocus();
            throw new IllegalArgumentException();
        }
        
        if (len == 4)
            value = value.concat("00");
        
        value = new StringBuffer(value.substring(0, 2))
            .append(":").append(value.substring(2, 4))
            .append(":").append(value.substring(4, 6)).toString();
        
        setStringValue(id, row, value);
        
        return Time.valueOf(value);
    }
    
    /**
     * Retorna valor do campo de marcação de linha
     * @param row
     * @return
     */
    public final boolean getMarkValue(int row) {
        TableComponent component;
        
        for (String id : table.keySet()) {
            component = table.get(id);
            switch (component.getType()) {
            case EVE.single:
            case EVE.multi:
                return ((Button)component.getControl(row)).getSelection();
            }
        }
        
        return false;
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * Limpa conteúdo da tabela
     */
    public final void clear() {
        currentline = 0;
        
        if (comptable == null)
            return;
        
        for (String id : table.keySet())
            table.get(id).clear();
        
        comptable.removeAll();
        
        for (int k = 0; k < lines; k++)
            addTableItem(new TableItem(comptable, SWT.NONE), controller);
    }
    
    /**
     * Adiciona item em tabela
     */
    private final void addTableItem(TableItem item, Controller controller) {
        String[] options;
        CCombo combo;
        Text text;
        Button button;
        TableEditor editor;
        TableComponent component;
        int charh;
        int charw;
        int k = 0;
        CellListener celllistener = null;
        
        for (String id : table.keySet()) {
            component = table.get(id);
            
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
                
            case EVE.combo:
                combo = new CCombo(comptable, SWT.NONE);
                combo.setEditable(component.isEnabled());
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
            addTableItem(new TableItem(comptable, SWT.NONE), controller);
    }
    
    /**
     * Insere coluna campo texto
     * @param id
     * @param length
     */
    public final void put(String id, int length) {
        TableComponent component = new TableComponent(
                messages.getMessage(id, null, id, locale));
        
        component.setLength(length);
        
        table.put(id, component);
    }
    
    /**
     * Insere coluna campo texto, tamanho 10 caracteres
     * @param id
     */
    public final void put(String id) {
        TableComponent component = new TableComponent(
                messages.getMessage(id, null, id, locale));
        
        component.setLength(10);
        
        table.put(id, component);
    }
    
    /**
     * Insere coluna combo box
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
     * Insere coluna de marcação de linha
     * @param id
     * @param type
     */
    public final void putMark(String id, int type) {
        TableComponent component = new TableComponent(
                messages.getMessage(id, null, id, locale));
        
        component.setType(type);
        component.setLength(1);
        
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
        Label title;

        this.controller = controller;
        
        area = new Composite(container, SWT.NONE);
        area.setLayout(new GridLayout(1, false));

        btarea = new Composite(area, SWT.NONE);
        btarea.setLayout(new RowLayout(SWT.HORIZONTAL));
        btarea.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        btarea.setVisible(editable);
        
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
        
        if (name != null) {
            title = new Label(area, SWT.NONE);
            title.setText(messages.getMessage(name, null, name, locale));
        }
        
        comptable = new Table(area, SWT.BORDER);
        comptable.setHeaderVisible(true);
        comptable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        for (String id : table.keySet()) {
            component = table.get(id);
            tablecol = new TableColumn(comptable, SWT.NONE);
            tablecol.setText(component.getName());
            tablecol.pack();
            component.setColumn(tablecol);
        }
        
        for (int k = 0; k < lines; k++)
            new TableItem(comptable, SWT.NONE);
        
        /*
         * por motivos que não conheço, não se recomenda usar TableEditor
         * logo após TableItem. Ocorrem problemas graves de dimensionamento
         * do controle de tabela.
         */
        for (TableItem item : comptable.getItems())
            addTableItem(item, controller);
        
        return area;
    }
    
    /**
     * Seleciona foco do campo
     * @param col
     * @param row
     */
    public final void sel(int col, int row) {
        Object[] objects = table.values().toArray();
        Control control = ((TableComponent)objects[col]).getEditor(row)
            .getEditor();
        
        if (control instanceof Text)
            ((Text)control).selectAll();
        
        control.setFocus();        
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
