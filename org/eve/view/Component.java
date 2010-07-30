package org.eve.view;

import org.eclipse.swt.widgets.Control;

public interface Component {

    public abstract int getType();
    
    public abstract Control getControl();

    public abstract Control getControl(int row);
    
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
    
    public abstract void setName(String name);
    
    public abstract void setEnabled(boolean enabled);
    
    public abstract void setNocase(boolean nocase);
    
    public abstract void setLength(int length);

    /**
     * Define valores fixos
     * @param options
     */
    public abstract void setOptions(String[] options);
    
    /**
     * Ajusta indicador de sensibilidade
     * @param nocase
     */
    public abstract void clear();
    
}
