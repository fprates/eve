package org.eve.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eve.model.AbstractDocument;

public class Factory extends AbstractComponentFactory {
    private Composite container;
    
    public Factory() { }
    
    /**
     * 
     * @param container
     */
    public final void setContainer(Composite container) {
        this.container = container;
    }
    
    /**
     * 
     * @param document
     * @param field
     */
    public final void putTextEntry(AbstractDocument document, String field) {
        Label label = new Label(container, SWT.NONE);
        
        new Text(container, SWT.BORDER);
        
        label.setText(getMessage(field));
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#setControlFocus(
     *     org.eve.view.Component)
     */
    @Override
    protected void setControlFocus(Component component) { }

    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#setControlFocus(
     *     org.eve.view.Component, int)
     */
    @Override
    protected void setControlFocus(Component component, int index) { }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#setControlValue(
     *     org.eve.view.Component, java.lang.Object)
     */
    @Override
    protected void setControlValue(Component component, Object value) { }

    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#setControlValue(
     *     org.eve.view.Component, int, java.lang.String)
     */
    @Override
    protected void setControlValue(
            Component component, int index, String value) { }

    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#setControlSize(
     *     org.eve.view.Component)
     */
    @Override
    public void setControlSize(Component component) { }

    /*
     * (non-Javadoc)
     * @see org.eve.view.ComponentFactory#clear()
     */
    @Override
    public void clear() { }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractComponentFactory#getControlValue(
     *     org.eve.view.Component)
     */
    @Override
    protected String getControlValue(Component component) {
        return null;
    }

    @Override
    protected String getControlValue(Component component, int index) {
        // TODO
        return null;
    }

    @Override
    protected Component getNewComponent(String name, int length, boolean key) {
        // TODO Auto-generated method stub
        return null;
    }
}
