package org.eve.view;

import java.util.Locale;

import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.widgets.Control;

public interface Component {

    /**
     * 
     * @return
     */
    public abstract int getInt();
    
    /**
     * 
     * @return
     */
    public abstract int getType();
    
    /**
     * 
     * @return
     */
    public abstract Control getControl();

    /**
     * 
     * @param row
     * @return
     */
    public abstract Control getControl(int row);
    
    /**
     * 
     * @return
     */
    public abstract int getLength();
    
    /**
     * Retorna nome
     * @return the name
     */
    public abstract String getName();
    
    /**
     * Retorna indicador de sensibilidade
     * @return
     */
    public abstract boolean isNocase();
    
    /**
     * 
     * @return
     */
    public abstract boolean isEnabled();

    /**
     * Retorna valor da lista de opções
     * @param index
     * @return
     */
    public abstract String getOption(int index);

    /**
     * Retorna lista de valores fixos
     * @return
     */
    public abstract String[] getOptions();
    
    /**
     * 
     * @param id
     * @return
     */
    public abstract String getString();
    
    /**
     * 
     * @param id
     * @param index
     * @return
     */
    public abstract String getString(int index);
    
    /**
     * 
     * @param control
     */
    public abstract void setControl(Control control);
    
    /**
     * 
     * @param name
     */
    public abstract void setName(String name);
    
    /**
     * 
     * @param enabled
     */
    public abstract void setEnabled(boolean enabled);
    
    /**
     * 
     * @param value
     */
    public abstract void setInt(int value);
    
    /**
     * 
     * @param locale
     */
    public abstract void setLocale(Locale locale);
    
    /**
     * 
     * @param nocase
     */
    public abstract void setNocase(boolean nocase);
    
    /**
     * 
     * @param length
     */
    public abstract void setLength(int length);

    /**
     * Define valores fixos
     * @param options
     */
    public abstract void setOptions(String[] options);
    
    /**
     * 
     * @param type
     */
    public abstract void setType(int type);
    
    /**
     * 
     * @param value
     */
    public abstract void setString(String text);
    
    /**
     * 
     * @param editor
     */
    public abstract void addEditor(ControlEditor editor);
    
    /**
     * Ajusta indicador de sensibilidade
     * @param nocase
     */
    public abstract void clear();
    
    public abstract void commit();
    
}
