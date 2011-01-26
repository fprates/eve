package org.eve.view;

import java.util.ArrayList;
import java.util.List;
import org.eve.model.AbstractDocument;

public abstract class AbstractComponent implements Component {
    private boolean enabled;
    private int length;
    private int type;
    private AbstractDocument.datatype datatype;
    private Extension extension;
    private String name;
    private String title;
    private String[] options;
    private List<String> values;

    public AbstractComponent() {
        extension = Extension.NONE;
        enabled = true;
        values = new ArrayList<String>();
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#getDataType()
     */
    @Override
    public final AbstractDocument.datatype getDataType() {
        return datatype;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#getExtension()
     */
    @Override
    public final Extension getExtension() {
        return extension;
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
//    
//    /*
//     * (non-Javadoc)
//     * @see org.eve.view.Component#getValues(int)
//     */
//    @Override
//    public final String[] getValues(int index) {
//        Combo combo;
//        CCombo ccombo;
//        
//        switch (type) {            
//        case EVE.combo:
//            combo = (Combo)values.get(index).getControl();
//            
//            return combo.getItems();
//            
//        case EVE.ccombo:
//            ccombo = (CCombo)values.get(index).getControl();
//            
//            return ccombo.getItems();
//        }
//        
//        return null;
//    }
    
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
     * @see org.eve.view.Component#setDataType(org.eve.model.AbstractDocument.datatype)
     */
    @Override
    public final void setDataType(AbstractDocument.datatype datatype) {
        this.datatype = datatype;
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
     * @see org.eve.view.Component#setExtension(int)
     */
    @Override
    public final void setExtension(Extension extension) {
        this.extension = extension;
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
     * @see org.eve.view.Component#setName(java.lang.String)
     */
    @Override
    public final void setName(String name) {
        this.name = name;
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
    
//    @Override
//    public final void setValues(Map<Object, String> values, int index) {
//        Combo combo;
//        CCombo ccombo;
//        
//        if (values == null)
//            return;
//        
//        switch (type) {            
//        case EVE.combo:
//            combo = (Combo)values.get(index).getControl();
//            combo.removeAll();
//            
//            for (Object value : values.keySet())
//                combo.add(values.get(value));
//            break;
//            
//        case EVE.ccombo:
//            ccombo = (CCombo)values.get(index).getControl();
//            ccombo.removeAll();
//            
//            for (Object value : values.keySet())
//                ccombo.add(values.get(value));
//            break;
//        }
//    }
    
    
    @Override
    public final void addItem(String text) {
        values.add(text);
    }
    
//    /*
//     * (non-Javadoc)
//     * @see org.eve.view.Component#clear()
//     */
//    @Override
//    public final void clear() {
//        Control control_;
//        Widget widget;
//        
//        for (ComponentItem editor : values) {
//            control_ = editor.getControl();
//            widget = editor.getWidget();
//            
//            if (control_ != null)
//                control_.dispose();
//            
//            if (widget != null)
//                widget.dispose();
//        }
//        
//        values.clear();
//        
//        if (control == null)
//            return;
//        
//        switch (type) {
//        case EVE.text:
//            ((Text)control).setText("");
//            break;
//            
//        case EVE.combo:
//            ((Combo)control).setText("");
//            break;
//            
//        case EVE.ccombo:
//            ((CCombo)control).setText("");
//            break;
//        }        
//    }
//    
//    /*
//     * (non-Javadoc)
//     * @see org.eve.view.Component#commit()
//     */
//    @Override
//    public final void commit() {
//        boolean enabled = isEnabled();
//        
//        for (ComponentItem editor : values)
//            editor.getControl().setEnabled(enabled);
//        
//        if (control == null)
//            return;
//        
//        switch (type) {
//        case EVE.text:
//            ((Text)control).setEnabled(enabled);
//            break;
//            
//        case EVE.combo:
//            ((Combo)control).setEnabled(enabled);
//            break;
//            
//        case EVE.ccombo:
//            ((CCombo)control).setEnabled(enabled);
//            break;
//        }
//    }
}
