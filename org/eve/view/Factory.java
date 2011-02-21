package org.eve.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eve.model.AbstractDocument;

public class Factory extends AbstractComponentFactory {
    private Composite container;
    
    public Factory() { }
    
    public void setContainer(Composite container) {
        this.container = container;
    }
    
    public void putTextEntry(AbstractDocument document, String field) {
        Label label = new Label(container, SWT.NONE);
        
        new Text(container, SWT.BORDER);
        String name = document.getName(field);
        
        label.setText(getMessage(name));
    }

    @Override
    protected void setControlFocus(Component component) { }

    @Override
    protected void setControlValue(Component component, Object value) { }

    @Override
    protected void setControlValue(Component component, int index, String value) { }

    @Override
    public void setControlSize(Component component) { }

    @Override
    protected String getControlValue(Component component) {
        return null;
    }

    @Override
    protected String getControlValue(Component component, int index) {
        return null;
    }
}
