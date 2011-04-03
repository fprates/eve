package org.eve.view;

import java.util.Locale;

import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eve.model.AbstractDocument;

public interface Search extends SelectionListener, DisposeListener {

    public abstract void setFactory(AbstractComponentFactory factory);

    public abstract void setController(Controller controller);
    
    public abstract void setDocument(AbstractDocument document);
    
    public abstract void setLocale(Locale locale);
    
    public abstract void define(Component component, Composite container);

}