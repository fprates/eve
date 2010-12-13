package org.eve.view;

import java.sql.Time;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.widgets.Control;
import org.eve.model.AbstractDocument;
import org.springframework.context.MessageSource;

public interface Component {
    public enum Extension {NONE, SEARCH, FILESEARCH};
    
    /**
     * Retorna controle
     * @return
     */
    public abstract Control getControl();
    
    /**
     * 
     * @return
     */
    public abstract AbstractDocument.datatype getDataType();
    
    /**
     * 
     * @return
     */
    public abstract Date getDate();
    
    /**
     * 
     * @return
     */
    public abstract Extension getExtension();
    
    /**
     * Retorna numérico flutuante 
     * @return
     */
    public abstract float getFloat();

    /**
     * Retorna numérico inteiro
     * @return
     */
    public abstract int getInt();
    
    /**
     * Retorna comprimento
     * @return
     */
    public abstract int getLength();
    
    /**
     * Retorna numérico long
     * @return
     */
    public abstract long getLong();
    
    /**
     * Retorna nome
     * @return the name
     */
    public abstract String getName();

    /**
     * 
     * @return
     */
    public abstract Time getTime();
    
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
     * Retorna texto
     * @param id
     * @return
     */
    public abstract String getString();
    
    /**
     * Retorna texto em índice
     * @param id
     * @param index
     * @return
     */
    public abstract String getString(int index);
    
    /**
     * Retorna tipo de controle
     * @return
     */
    public abstract int getType();
    
    /**
     * 
     * @param index
     * @return
     */
    public abstract String[] getValues(int index);
    
    /**
     * Retorna flag de habilitação
     * @return
     */
    public abstract boolean isEnabled();
    
    /**
     * Define controle
     * @param control
     */
    public abstract void setControl(Control control);
    
    /**
     * 
     * @param datatype
     */
    public abstract void setDataType(AbstractDocument.datatype datatype);
    
    /**
     * Ajusta data
     * @param date
     */
    public abstract void setDate(Date date);
    
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
     * Ajusta valor ponto flutuante
     * @param value
     */
    public abstract void setFloat(float value);
    
    /**
     * Ajusta valor inteiro
     * @param value
     */
    public abstract void setInt(int value);
    
    /**
     * Ajusta valor inteiro em índice
     * @param value
     * @param index
     */
    public abstract void setInt(int value, int index);
    
    /**
     * Define comprimento
     * @param length
     */
    public abstract void setLength(int length);
    
    /**
     * Define localização
     * @param locale
     */
    public abstract void setLocale(Locale locale);
    
    /**
     * Ajusta valor inteiro longo
     * @param value
     */
    public abstract void setLong(long value);
    
    /**
     * Ajusta valor inteiro long em índice
     * @param value
     * @param index
     */
    public abstract void setLong(long value, int index);
    
    /**
     * 
     * @param messages
     */
    public abstract void setMessages(MessageSource messages);
    
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
     * Ajusta texto
     * @param value
     */
    public abstract void setString(String text);
    
    /**
     * Ajusta texto em índice
     * @param text
     * @param index
     */
    public abstract void setString(String text, int index);
    
    /**
     * Ajusta hora
     * @param time
     */
    public abstract void setTime(Time time);
    
    /**
     * Ajusta hora em índice
     * @param time
     * @param index
     */
    public abstract void setTime(Time time, int index);
    
    /**
     * Define tipo de componente
     * @param type
     */
    public abstract void setType(int type);
    
    /**
     * Define valores possíveis
     * @param values
     * @param index
     */
    public abstract void setValues(Map<Object, String> values, int index);
    
    /**
     * Adiciona editor
     * @param editor
     */
    public abstract void addItem(String text);
    
    /**
     * Ajusta limpa componente
     * @param nocase
     */
    public abstract void clear();
    
    /**
     * Aplica definições
     */
    public abstract void commit();
    
}
