package org.eve.view;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eve.main.EVE;

public abstract class AbstractComponent implements Component {
    private boolean nocase;
    private boolean enabled;
    private boolean search;
    private int length;
    private int type;
    private Control control;
    private DateFormat dateformat;
    private Locale locale;
    private String name;
    private String title;
    private String[] options;
    private List<ControlEditor> editors;

    public AbstractComponent() {
        nocase = false;
        search = false;
        enabled = true;
        editors = new ArrayList<ControlEditor>();
    }
    
    private final String getText(Control control) {
        String value;
        
        switch (type) {
        case EVE.ccombo:
            value = ((CCombo)control).getText();
            if (value == null)
                return "";
            
            if (!nocase)
                ((CCombo)control).setText(value.toUpperCase(locale));
            
            return value;
            
        case EVE.combo:
            value = ((Combo)control).getText();
            if (value == null)
                return "";
            
            if (!nocase)
                ((Combo)control).setText(value.toUpperCase(locale));
            
            return value;
            
        case EVE.text:
            value = ((Text)control).getText();
            if (value == null)
                return "";
            
            if (!nocase)
                ((Text)control).setText(value.toUpperCase(locale));
            
            return value;
            
        default:
            return "";
        }
        
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#getControl()
     */
    @Override
    public final Control getControl() {
        return control;
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#getControl(int)
     */
    @Override
    public final Control getControl(int index) {
        return editors.get(index).getEditor();
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#getFloat()
     */
    @Override
    public final float getFloat() {
        String test = getString();
        
        return test.equals("")? 0:Float.parseFloat(test);        
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#getInt()
     */
    @Override
    public final int getInt() {
        String test;
        int test_;
        
        switch (type) {
        case EVE.text:
            test = getString();
            return test.equals("")? 0:Integer.parseInt(test);
        
        case EVE.combo:
            test_ = ((Combo)control).getSelectionIndex();
            return (test_ < 0)? 0:test_;
            
        default:
            return 0;
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#getLength()
     */
    @Override
    public final int getLength() {
        return length;
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#getName()
     */
    @Override
    public final String getName() {
        return name;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#getOptions()
     */
    @Override
    public final String[] getOptions() {
        return options;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#getOption(int)
     */
    @Override
    public final String getOption(int index) {
        return options[index];
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#getString()
     */
    @Override
    public final String getString() {
        return getText(control);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#getStringValue(int)
     */
    @Override
    public final String getString(int index) {
        return getText(editors.get(index).getEditor());
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#getTitle()
     */
    @Override
    public final String getTitle() {
        return title;
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#getType()
     */
    @Override
    public final int getType() {
        return type;
    }
    
    @Override
    public final String[] getValues(int index) {
        Combo combo;
        CCombo ccombo;
        
        switch (type) {            
        case EVE.combo:
            combo = (Combo)editors.get(index).getEditor();
            
            return combo.getItems();
            
        case EVE.ccombo:
            ccombo = (CCombo)editors.get(index).getEditor();
            
            return ccombo.getItems();
        }
        
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#hasSearch()
     */
    @Override
    public final boolean hasSearch() {
        return search;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#isEnabled()
     */
    @Override
    public final boolean isEnabled() {
        return enabled;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#isNocase()
     */
    @Override
    public final boolean isNocase() {
        return nocase;
    }
    
    /**
     * 
     * @param control
     * @param text
     */
    private final void setText(Control control, String text) {
        String text_ = text;
        
        if (text_ == null)
            text_ = "";        
        
        if (!nocase)
            text_ = text_.toUpperCase(locale);
        
        switch (type) {
        case EVE.text:
            ((Text)control).setText(text_);
            break;
        
        case EVE.ccombo:
            ((CCombo)control).setText(text_);
            break;
            
        case EVE.combo:
            ((Combo)control).setText(text_);
            break;
        }        
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setControl(org.eclipse.swt.widgets.Control)
     */
    @Override
    public final void setControl(Control control) {
        this.control = control;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setDate(java.util.Date)
     */
    @Override
    public final void setDate(Date date) {
        if (date == null)
            setString("");
        else 
            setString(dateformat.format(date));
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setEnabled(boolean)
     */
    @Override
    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setFloat(float)
     */
    @Override
    public final void setFloat(float value) {
        setString(Float.toString(value));        
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setInt(int)
     */
    @Override
    public final void setInt(int value) {        
        switch (type) {
        case EVE.text:
            setString(Integer.toString(value));
            break;
        
        case EVE.ccombo:
        case EVE.combo:
            if (options == null)
                break;
            
            setString(options[value]);
            break;
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setInt(int, int)
     */
    @Override
    public final void setInt(int value, int index) {
        switch (type) {
        case EVE.text:
            setString(Integer.toString(value), index);
            break;
        
        case EVE.ccombo:
        case EVE.combo:
            if (options == null)
                break;
            
            setString(options[value], index);
            break;
        }
        
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setLength(int)
     */
    @Override
    public final void setLength(int length) {
        this.length = length;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setLocale(java.util.Locale)
     */
    @Override
    public final void setLocale(Locale locale) {
        this.locale = locale;
        dateformat = DateFormat.getDateInstance(DateFormat.SHORT, locale);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setName(java.lang.String)
     */
    @Override
    public final void setName(String name) {
        this.name = name;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setNocase(boolean)
     */
    @Override
    public final void setNocase(boolean nocase) {
        this.nocase = nocase;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setOptions(java.lang.String[])
     */
    @Override
    public final void setOptions(String[] options) {
        this.options = options;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setSearch(boolean)
     */
    @Override
    public final void setSearch(boolean search) {
        this.search = search;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setString(java.lang.String)
     */
    @Override
    public final void setString(String text) {
        setText(control, text);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setString(java.lang.String, int)
     */
    @Override
    public final void setString(String text, int index) {
        setText(editors.get(index).getEditor(), text);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setTime(java.sql.Time)
     */
    @Override
    public final void setTime(Time time) {
        String value;
        
        if (time == null) {
            setString("");
        } else {
            value = time.toString();
            setString(value.equals("00:00:00")?"":value);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setTime(java.sql.Time, int)
     */
    @Override
    public final void setTime(Time time, int index) {
        String value;
        
        if (time == null) {
            setString("", index);
        } else {
            value = time.toString();
            setString(value.equals("00:00:00")?"":value, index);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setTitle(java.lang.String)
     */
    @Override
    public final void setTitle(String title) {
        this.title = title;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setType(int)
     */
    @Override
    public final void setType(int type) {
        this.type = type;
    }
    
    @Override
    public final void setValues(Map<Object, String> values, int index) {
        Combo combo;
        CCombo ccombo;
        
        if (values == null)
            return;
        
        switch (type) {            
        case EVE.combo:
            combo = (Combo)editors.get(index).getEditor();
            combo.removeAll();
            
            for (Object value : values.keySet())
                combo.add(values.get(value));
            break;
            
        case EVE.ccombo:
            ccombo = (CCombo)editors.get(index).getEditor();
            ccombo.removeAll();
            
            for (Object value : values.keySet())
                ccombo.add(values.get(value));
            break;
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#addEditor(org.eclipse.swt.custom.ControlEditor)
     */
    @Override
    public final void addEditor(ControlEditor editor) {
        editors.add(editor);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#clear()
     */
    @Override
    public final void clear() {
        for (ControlEditor editor : editors) {
            editor.getEditor().dispose();
            editor.dispose();
        }
        
        editors.clear();
        
        if (control == null)
            return;
        
        switch (type) {
        case EVE.text:
            ((Text)control).setText("");
            break;
            
        case EVE.combo:
            ((Combo)control).setText("");
            break;
            
        case EVE.ccombo:
            ((CCombo)control).setText("");
            break;
        }        
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#commit()
     */
    @Override
    public final void commit() {
        boolean enabled = isEnabled();
        
        for (ControlEditor editor : editors)
            editor.getEditor().setEnabled(enabled);
        
        if (control == null)
            return;
        
        switch (type) {
        case EVE.text:
            ((Text)control).setEnabled(isEnabled());
            break;
            
        case EVE.combo:
            ((Combo)control).setEnabled(isEnabled());
            break;
            
        case EVE.ccombo:
            ((CCombo)control).setEnabled(isEnabled());
            break;
        }
    }
}
