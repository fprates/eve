package org.eve.view;

import java.util.Map;

import org.eclipse.swt.widgets.Composite;

public interface TableAssist extends ComponentFactory {

    /**
     * 
     * @return
     */
    public abstract int[] getSelectedItens();
    
    /**
     * 
     * @param event
     * @return
     */
    public abstract boolean hasEvent(String event);
    
    /**
     * 
     * @param action
     * @param state
     */
    public abstract void setActionState(String action, boolean state);
    
    /**
     * Define propriedades da coluna
     * @param id
     * @param property
     */
    public abstract void setColumnProperties(String id, int property);
    
    /**
     * 
     * @param controller
     */
    public abstract void setController(Controller controller);

    /**
     * 
     * @param editable
     */
    public abstract void setEditable(boolean editable);
    
    /**
     * Define quantidade de linhas visíveis
     * @param lines
     */
    public abstract void setLines(int lines);

    /**
     * Define campo de seleção de itens
     * @param row
     * @param value
     */
    public abstract void setMarkValue(int row, boolean value);

    /**
     * Define campo de referência
     * @param id
     * @param idref
     */
    public abstract void setReference(String id, String idref);

    /**
     * 
     * @param seltype
     */
    public abstract void setSelType(ComponentType seltype);
    
    /**
     * Retorna número de itens
     * @return
     */
    public abstract int getItensSize();

    /**
     * Limpa conteúdo da tabela
     */
    public abstract void clear();

    /**
     * Constrói tabela
     * @param container
     * @param listener
     * @return
     */
    public abstract Composite define(Composite container);

    /**
     * 
     */
    public abstract void dispose();

    /**
     * Adiciona item em tabela
     */
    public abstract void insert();

    /**
     * Insere coluna campo texto
     * @param id
     * @param length
     */
    public abstract void put(String id, int length);

    /**
     * Insere coluna campo texto, tamanho 10 caracteres
     * @param id
     */
    public abstract void put(String id);

    /**
     * 
     * @param id
     * @param length
     * @param keys
     */
    public abstract void putAutoCombo(
            String id, int length, String[] keys);
    
    /**
     * Insere coluna combo box
     * @param id
     * @param length
     * @param options
     */
    public abstract void putCombo(
            String id, int length, Map<String, ?> options);
    
    /**
     * 
     * @param input
     */
    public abstract void userInput(String input);

}