package org.eve.view;

import org.eclipse.swt.widgets.Composite;

public interface TableAssist extends ComponentFactory {

    /**
     * 
     * @param controller
     */
    public abstract void setController(Controller controller);

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
     * Define propriedades da coluna
     * @param id
     * @param property
     */
    public abstract void setColumnProperties(String id, int property);

    /**
     * Define campo de referência
     * @param id
     * @param idref
     */
    public abstract void setReference(String id, String idref);

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
     * Insere coluna combo box
     * @param id
     * @param length
     * @param options
     */
    public abstract void putCombo(String id, int length, String[] options);

    /**
     * Insere coluna de marcação de linha
     * @param id
     * @param type
     */
    public abstract void putMark(String id, int type);

}