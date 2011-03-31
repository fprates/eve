package org.eve.view;

import java.util.HashMap;
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
    private boolean editable;
    private int currentline;
    private int lines;
    private String name;
//    private ComponentType type;
    private Map<String, TableActionState> actionstates;
    private Controller controller;
    private Map<String, String> references;
    private Table table;
    
    public AbstractTableAssist(String name) {
        references = new HashMap<String, String>();
        actionstates = new HashMap<String, TableActionState>();
        currentline = 0;
        lines = LINES;
//        type = ComponentType.SINGLE;
        getComboAssist().setCustomized(true);
        editable = false;
        this.name = name;
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
    protected final Controller getController() {
        return controller;
    }
    
    /* 
     * (non-Javadoc)
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
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#getControlValue(org.eve.view.Component)
     */
    @Override
    protected String getControlValue(Component component) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#getControlValue(org.eve.view.Component, int)
     */
    @Override
    protected String getControlValue(Component component, int index) {
        int k = 0;
        
        for (Component component_ : getComponents()) {
            if (component_ == component)
                return table.getItem(index).getText(k);
            
            k++;
        }
        
        return null;
    }
    
    /**
     * 
     * @return
     */
    protected final boolean getEditable() {
        return editable;
    }
    
    /**
     * 
     * @return
     */
    protected final String getName() {
        return name;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#getNewComponent(java.lang.String, int, boolean)
     */
    @Override
    protected final Component getNewComponent(String name, int length, boolean key) {
        return new TableComponent(name, length, key);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.TableAssist#getSelectedItens()
     */
    @Override
    public final int[] getSelectedItens() {
        return table.getSelectionIndices();
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.TableAssist#hasEvent(java.lang.String)
     */
    @Override
    public final boolean hasEvent(String event) {
        return actionstates.containsKey(event);
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.TableAssist#setActionState(java.lang.String, boolean)
     */
    @Override
    public final void setActionState(String action, boolean state) {
        TableActionState actionstate = actionstates.get(action);
        
        actionstate.setVisible(state);
        setComponentState(action, state);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.TableAssist#setColumnProperties(java.lang.String, int)
     */
    @Override
    public final void setColumnProperties(String id, int property) {
        Component component = getComponent(id);
        
        if ((EVE.readonly & property) == EVE.readonly)
            component.setEnabled(false);
    }
    
    /**
     * 
     * @param action
     * @param state
     */
    protected abstract void setComponentState(String action, boolean state);
    
    /* (non-Javadoc)
     * @see org.eve.view.TableAssist#setController(org.eve.view.Controller)
     */
    @Override
    public final void setController(Controller controller) {
        this.controller = controller;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.TableAssist#setEditable(boolean)
     */
    @Override
    public final void setEditable(boolean editable) {
        this.editable = editable;
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
//        this.type = type;
    }
    
    /**
     * 
     * @param table
     */
    protected final void setTable(Table table) {
        this.table = table;
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#setControlFocus(org.eve.view.Component)
     */
    @Override
    protected final void setControlFocus(Component component) { }

    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#setControlValue(org.eve.view.Component, java.lang.Object)
     */
    @Override
    protected void setControlValue(Component component, Object value) { }

    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#setControlValue(org.eve.view.Component, int, java.lang.String)
     */
    @Override
    protected void setControlValue(Component component, int index, String value) {
        int k = 0;
        
        for (Component component_ : getComponents()) {
            if (component_ == component) {
                table.getItem(index).setText(k, value);
                break;
            }
            
            k++;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#setControlSize(org.eve.view.Component)
     */
    @Override
    public final void setControlSize(Component component) { }
    
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
    
    /* 
     * (non-Javadoc)
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
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.TableAssist#define(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public abstract Composite define(Composite container);
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.TableAssist#dispose()
     */
    @Override
    public final void dispose() {        
        table.dispose();
    }
    
    /* 
     * (non-Javadoc)
     * @see org.eve.view.TableAssist#insert()
     */
    @Override
    public final void insert() {
        currentline++;
        if (currentline > lines)
            addTableItem(new TableItem(table, SWT.NONE));
    }
    
    /**
     * 
     * @param action
     */
    protected final void insertActionState(String action) {
        actionstates.put(action, new TableActionState());
    }
    
    @Override
    public void userInput(String input) { }
}
