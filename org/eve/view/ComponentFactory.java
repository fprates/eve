package org.eve.view;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import org.eve.main.EveAPI;
import org.eve.model.AbstractDocument;
import org.springframework.context.MessageSource;

public interface ComponentFactory {


    public abstract void setControlSize(Component component);
    
    /**
     * Ajusta valor data para campo
     * @param field
     * @param date
     */
    public abstract void setDate(String field, Date date);
    
    /**
     * 
     * @param field
     * @param index
     * @param date
     */
    public abstract void setDate(String field, int index, Date date);

    /**
     * 
     * @param id
     * @param value
     */
    public abstract void setFieldValue(String id, Object value);
    
    /**
     * 
     * @param id
     * @param index
     * @param value
     */
    public abstract void setFieldValue(String id, int index, Object value);

    /**
     * Ajusta valor de ponto flutuante para campo
     * @param field
     * @param value
     */
    public abstract void setFloat(String field, float value);

    /**
     * 
     * @param field
     * @param index
     * @param value
     */
    public abstract void setFloat(String field, int index, float value);

    /**
     * 
     * @param field
     */
    public abstract void setFocus(String field);

    /**
     * Ajusta valor do campo inteiro do formulário
     * @param field
     * @param value
     */
    public abstract void setInt(String field, int value);

    /**
     * Define valor inteiro em tabela
     * @param id
     * @param row
     * @param value
     */
    public abstract void setInt(String field, int index, int value);

    /**
     * Define localização
     * @param locale
     */
    public abstract void setLocale(Locale locale);

    /**
     * Ajusta valor do campo inteiro longo
     * @param field
     * @param value
     */
    public abstract void setLong(String field, long value);

    /**
     * 
     * @param field
     * @param index
     * @param value
     */
    public abstract void setLong(String field, int index, long value);

    /**
     * 
     * @param status
     * @param id
     */
    public abstract void setMessage(int status, String id);

    /**
     * Define mensagens do sistema
     * @param messages
     */
    public abstract void setMessages(MessageSource messages);

    /**
     * Ajusta valor do campo caractere do formulário
     * @param field
     * @param value
     */
    public abstract void setString(String field, String text);

    /**
     * Define string em tabela
     * @param id
     * @param row
     * @param value
     */
    public abstract void setString(String field, int index, String text);

    /**
     * 
     * @param system
     */
    public abstract void setSystem(EveAPI system);

    /**
     * Ajusta valor hora para campo
     * @param field
     * @param time
     */
    public abstract void setTime(String field, Time time);

    /**
     * Define valor do campo hora
     * @param id
     * @param row
     * @param value
     */
    public abstract void setTime(String field, int index, Time time);

    /**
     * Retorna components do formulário
     * @return
     */
    public abstract Collection<Component> getComponents();

    /**
     * 
     * @param field
     * @return
     */
    public abstract Date getDate(String field);

    /**
     * 
     * @param id
     * @return
     */
    public abstract Object getFieldValue(String id);

    /**
     * Retorna valor de ponto flutuante de um campo
     * @param field
     * @return
     */
    public abstract float getFloat(String field);

    /**
     * Retorna valor do campo inteiro do formulário
     * @param field
     * @return
     */
    public abstract int getInt(String field);

    /**
     * Retorna valor do campo inteiro
     * @param id coluna
     * @param row linha
     * @return conteúdo inteiro
     */
    public abstract int getInt(String field, int row)
            throws NumberFormatException;

    /**
     * Retorna valor do campo inteiro do formulário
     * @param field
     * @return
     */
    public abstract long getLong(String field);

    /**
     * 
     * @param tag
     * @return
     */
    public abstract String getMessage(String tag);
    
    /**
     * Retorna valor do campo caractere do formulário
     * @param field
     * @return
     */
    public abstract String getString(String field);

    /**
     * Define valor do campo string
     * @param id
     * @param row
     * @return
     */
    public abstract String getString(String field, int row);

    /**
     * 
     * @param field
     * @return
     */
    public abstract Time getTime(String field);

    /**
     * Retorna valor do campo hora
     * @param id
     * @param row
     * @return
     */
    public abstract Time getTime(String field, int row);
    
    /**
     * 
     * @return
     */
    public abstract boolean isComboAssistCustomized();

    /**
     * Limpa formulário
     */
    public abstract void clear();
    
    /**
     * 
     * @param document
     */
    public abstract void copyFrom(AbstractDocument document);

    /**
     * 
     * @param itens
     */
    public abstract void copyFrom(Set<AbstractDocument> itens);
    
    /**
     * 
     * @param factory
     */
    public abstract void copyFrom(int index, ComponentFactory factory);
    
    /**
     * 
     * @param document
     * @param id
     */
    public abstract void put(AbstractDocument document, String id);

    /**
     * 
     * @param name
     * @param component
     */
    public abstract void putComponent(String name, Component component);

    /**
     * Seleciona foco do campo
     * @param col
     * @param row
     */
    public abstract void sel(int col, int row);

}