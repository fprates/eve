package org.eve.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eve.main.EVE;

public abstract class AbstractComponent implements Component {
    private String name;
    private int length;
    private int type;
    private boolean nocase;
    private boolean enabled;
    private String[] options;
    private Control control;
    private List<ControlEditor> editors;

    public AbstractComponent() {
        nocase = false;
        enabled = true;
        editors = new ArrayList<ControlEditor>();
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
     * @see org.eve.view.Component#getOption(int)
     */
    @Override
    public final String getOption(int index) {
        return options[index];
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
     * @see org.eve.view.Component#getType()
     */
    @Override
    public final int getType() {
        return type;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#isNocase()
     */
    @Override
    public final boolean isNocase() {
        return nocase;
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
     * @see org.eve.view.Component#setControl(org.eclipse.swt.widgets.Control)
     */
    @Override
    public final void setControl(Control control) {
        this.control = control;
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
     * @see org.eve.view.Component#setNocase(boolean)
     */
    @Override
    public final void setNocase(boolean nocase) {
        this.nocase = nocase;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#setType(int)
     */
    @Override
    public final void setType(int type) {
        this.type = type;
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
        
        switch (type) {
        case EVE.text:
            ((Text)control).setEnabled(isEnabled());
            break;
            
        case EVE.combo:
            ((Combo)control).setEnabled(isEnabled());
            break;
        }        
    }
}
