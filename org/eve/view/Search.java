package org.eve.view;

import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;

public interface Search extends SelectionListener, DisposeListener {

    public abstract void setFactory(AbstractComponentFactory factory);

    public abstract void setController(Controller controller);
    
    public abstract void define(Component component, Composite container);

}