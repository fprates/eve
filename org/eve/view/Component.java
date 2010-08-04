package org.eve.view;

import java.sql.Time;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.widgets.Control;

public interface Component {
    
    /**
     * Retorna controle
     * @return
     */
    public abstract Control getControl();

    /**
     * Retorna controle em índice
     * @param row
     * @return
     */
    public abstract Control getControl(int index);
    
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
     * Retorna nome
     * @return the name
     */
    public abstract String getName();

    /**
     * Retorna lista de valores fixos
     * @return
     */
    public abstract Map<Object, String> getOptions();

    /**
     * Retorna valor da lista de opções
     * @param index
     * @return
     */
    public abstract String getOption(Object object);
    
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
     * Retorna flag de habilitação
     * @return
     */
    public abstract boolean isEnabled();
    
    /**
     * Retorna indicador de sensibilidade
     * @return
     */
    public abstract boolean isNocase();
    
    /**
     * Define controle
     * @param control
     */
    public abstract void setControl(Control control);
    
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
     * Define nome
     * @param name
     */
    public abstract void setName(String name);
    
    /**
     * Define sensibilidade
     * @param nocase
     */
    public abstract void setNocase(boolean nocase);

    /**
     * Define valores fixos
     * @param options
     */
    public abstract void setOptions(String[] options);
    
    /**
     * Define mapa de valores fixos
     * @param options
     */
    public abstract void setOptions(Map<Object, String> options);
    
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
     * Adiciona editor
     * @param editor
     */
    public abstract void addEditor(ControlEditor editor);
    
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
