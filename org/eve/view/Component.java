package org.eve.view;

import org.eve.model.AbstractDocument;

public interface Component {
    public enum Extension {NONE, SEARCH, FILESEARCH};
    
    /**
     * 
     * @return
     */
    public abstract AbstractDocument.datatype getDataType();
    
    /**
     * 
     * @return
     */
    public abstract Extension getExtension();
    
    /**
     * Retorna comprimento
     * @return
     */
    public abstract int getLength();
    
    /**
     * Retorna nome
     * @return the name
     */
    public abstract String getName();
    
    /**
     * Retorna título
     * @return
     */
    public abstract String getTitle();
    
    /**
     * Retorna lista de valores fixos
     * @return
     */
    public abstract String[] getOptions();

    /**
     * Retorna valor da lista de opções
     * @param index
     * @return
     */
    public abstract String getOption(int index);
    
    /**
     * Retorna tipo de controle
     * @return
     */
    public abstract int getType();
    
    /**
     * Retorna flag de habilitação
     * @return
     */
    public abstract boolean isEnabled();
    
    /**
     * 
     * @param datatype
     */
    public abstract void setDataType(AbstractDocument.datatype datatype);
    
    /**
     * Ajusta habilitação
     * @param enabled
     */
    public abstract void setEnabled(boolean enabled);
    
    /**
     * Define extensão do campo
     * @param extension
     */
    public abstract void setExtension(Extension extension);
    
    /**
     * Define comprimento
     * @param length
     */
    public abstract void setLength(int length);
    
    /**
     * Define nome
     * @param name
     */
    public abstract void setName(String name);
    
    /**
     * Define título
     * @param title
     */
    public abstract void setTitle(String title);

    /**
     * Define valores fixos
     * @param options
     */
    public abstract void setOptions(String[] options);
    
    /**
     * Define tipo de componente
     * @param type
     */
    public abstract void setType(int type);
    
    /**
     * Adiciona editor
     * @param editor
     */
    public abstract void addItem(String text);
    
}
