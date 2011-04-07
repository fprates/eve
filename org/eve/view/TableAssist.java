package org.eve.view;

import org.eclipse.swt.widgets.Composite;

public interface TableAssist extends ComponentFactory {

    /**
     * Retorna altura do componente de tabela
     * @return
     */
    public abstract int getHeight();
    
    /**
     * Retorna índice de itens selecionados
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
     * Define um controlador
     * @param controller
     */
    public abstract void setController(Controller controller);

    /**
     * Define propriedade de edição
     * @param editable
     */
    public abstract void setEditable(boolean editable);

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
     * Define tipo de seleção
     * @param seltype
     */
    public abstract void setSelType(ComponentType seltype);
    
    /**
     * Define quantidade de linhas visíveis
     * @param lines
     */
    public abstract void setVisibleLines(int lines);
    
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
     * Libera componente de tabela
     */
    public abstract void dispose();

    /**
     * Adiciona item em tabela
     */
    public abstract void insert();
    
    /**
     * 
     * @param input
     */
    public abstract void userInput(String input);

}