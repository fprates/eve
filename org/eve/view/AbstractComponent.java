package org.eve.view;

import org.eclipse.swt.widgets.Control;

public abstract class AbstractComponent implements Component {
    private String name;
    private int length;
    private boolean nocase;
    private boolean enabled;
    private String[] options;

    public AbstractComponent() {
        nocase = false;
        enabled = true;
    }
    
    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public Control getControl() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Control getControl(int row) {
        // TODO Auto-generated method stub
        return null;
    }
    
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

    @Override
    public int getType() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.Component#isNocase()
     */
    @Override
    public final boolean isNocase() {
        return nocase;
    }

    @Override
    public void setEnabled(boolean enabled) {
        // TODO Auto-generated method stub

    }

    @Override
    public final void setLength(int length) {
        this.length = length;
    }
    
    @Override
    public final void setName(String name) {
        this.name = name;
    }
    
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

}
