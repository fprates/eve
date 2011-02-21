package org.eve.view;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eve.main.EVE;

/**
 * Assistente de tabela
 * @author francisco.prates
 *
 */
abstract class AbstractTableAssist extends AbstractComponentFactory implements TableAssist {
    private static final int LINES = 5;
    private int currentline;
    private int lines;
    private ComponentType type;
    private ComboAssist comboassist;
    private Controller controller;
    private Map<String, String> references;
    private Table table;
    
    public AbstractTableAssist() {
        references = new HashMap<String, String>();
        currentline = 0;
        lines = LINES;
        type = ComponentType.SINGLE;
        comboassist = new ComboAssist();
        comboassist.setType(ComponentType.CCOMBO);
    }
    
    @Override
    public final int[] getSelectedItens() {
        return table.getSelectionIndices();
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#setController(org.eve.view.Controller)
     */
    @Override
    public final void setController(Controller controller) {
        this.controller = controller;
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#setLines(int)
     */
    @Override
    public final void setLines(int lines) {
        this.lines = lines;
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#setMarkValue(int, boolean)
     */
    @Override
    public final void setMarkValue(int index, boolean value) {
        if (value)
            table.select(index);
        else
            table.deselect(index);
//        
//        for (Component component : getComponents()) {
//            switch (component.getType()) {
//            case EVE.single:
//            case EVE.multi:
//                ((Button)getControl(component, index)).setSelection(value);
//                
//                return;
//            }
//        }
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#setColumnProperties(java.lang.String, int)
     */
    @Override
    public final void setColumnProperties(String id, int property) {
        Component component = getComponent(id);
        
        if ((EVE.readonly & property) == EVE.readonly)
            component.setEnabled(false);
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#setReference(java.lang.String, java.lang.String)
     */
    @Override
    public final void setReference(String id, String idref) {
        references.put(id, idref);
    }
    
    @Override
    public final void setSelType(ComponentType type) {
        this.type = type;
    }
    
    /**
     * 
     * @param table
     */
    protected final void setTable(Table table) {
        this.table = table;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * 
     * @return
     */
//    protected abstract Control getControl(Component component, int index);
    
    /**
     * 
     * @return
     */
    protected final Controller getController() {
        return controller;
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#getItensSize()
     */
    @Override
    public final int getItensSize() {
        return table.getItems().length;
    }
    
    /**
     * 
     * @return
     */
    protected final int getLines() {
        return lines;
    }
    
    /**
     * 
     * @param reference
     * @return
     */
    protected String getReference(String reference) {
        return references.get(reference);
    }
    
    /**
     * 
     * @return
     */
    protected final Table getTable() {
        return table;
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * 
     * @param item
     * @param k
     * @param component
     * @param control
     * @param type
     */
    protected void customizeControl(TableItem item, int k,
            TableComponent component, Control control, ComponentType type) { };
    
    /**
     * 
     * @param item
     * @param k
     * @param component
     * @param type
     */
    protected void addCustomControl(TableItem item,
            int k, TableComponent component, ComponentType type) { };
    
    /**
     * Adiciona item em tabela
     */
    protected final void addTableItem(TableItem item) {
        ComponentType type;
        Button button;
        TableComponent component;
        int k = 0;
        
        for (Component component_ : getComponents()) {
            component = (TableComponent)component_;

            type = component.getType();
            
            switch(type) {
            case SINGLE:
                button = new Button(table, SWT.RADIO);
                button.setBackground(item.getBackground());
                button.pack();
                
                customizeControl(item, k, component, button, type);
                component.getColumn().setWidth(button.getSize().x);
                
                break;
                
            case MULTI:
                button = new Button(table, SWT.CHECK);
                button.setBackground(item.getBackground());
                button.pack();
                
                customizeControl(item, k, component, button, type);
                component.getColumn().setWidth(button.getSize().x);

                break;
                
            default:
                addCustomControl(item, k, component, type);
                break;
            }
            
            k++;
        }
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#clear()
     */
    @Override
    public final void clear() {
        currentline = 0;
        
        if (table == null)
            return;
        
        table.clearAll();
        
        /*
         * restaura quantidade de linhas na tabela
         */
        table.removeAll();
        
        for (int k = 0; k < lines; k++)
            addTableItem(new TableItem(table, SWT.NONE));
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#define(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public abstract Composite define(Composite container);
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#dispose()
     */
    @Override
    public final void dispose() {        
        table.dispose();
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#insert()
     */
    @Override
    public final void insert() {
        currentline++;
        if (currentline > lines)
            addTableItem(new TableItem(table, SWT.NONE));
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#put(java.lang.String, int)
     */
    @Override
    public final void put(String id, int length) {
        Component component = new TableComponent(getMessage(id));
        
        component.setLength(length);
        
        putComponent(id, component);
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#put(java.lang.String)
     */
    @Override
    public final void put(String id) {
        TableComponent component = new TableComponent(getMessage(id));
        
        component.setLength(10);
        
        putComponent(id, component);
    }
    
    @Override
    public final void putAutoCombo(String id, int length, String[] keys) {
        int k = 0;
        Map<String, Integer> values = new LinkedHashMap<String, Integer>();
        
        for (String key : keys)
            values.put(key, k++);
        
        putCombo(id, length, values);
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#putCombo(java.lang.String, int, java.lang.String[])
     */
    @Override
    public final void putCombo(String id, int length, Map<String, ?> options) {
        TableComponent component = new TableComponent(getMessage(id));
        
        component.setType(ComponentType.CCOMBO);
        component.setOptions(options);
        component.setLength(length);
        
        putComponent(id, component);
    }
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#putMark(java.lang.String, int)
     */
    @Override
    public final void putMark(String id, ComponentType type) {
        TableComponent component = new TableComponent(getMessage(id));
        
        component.setType(type);
        component.setLength(1);
        
        putComponent(id, component);
    }

    @Override
    protected final void setControlFocus(Component component) { }

    @Override
    protected void setControlValue(Component component, String value) { }

    @Override
    protected void setControlValue(Component component, int index, String value) {
        int k = 0;
        
        for (Component component_ : getComponents()) {
            k++;
            if (component_ != component)
                continue;
            
            table.getItem(index).setText(k, value);
            
            break;
        }
    }

    @Override
    public final void setControlSize(Component component) { }

    @Override
    protected String getControlValue(Component component) {
        return null;
    }

    @Override
    protected String getControlValue(Component component, int index) {
        int k = 0;
        
        for (Component component_ : getComponents()) {
            k++;
            if (component_ != component)
                continue;
            
            return table.getItem(index).getText(k);
        }
        
        return null;
    }
}
