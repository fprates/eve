package org.eve.view;

import java.util.Locale;

import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eve.main.EveAPI;
import org.springframework.context.MessageSource;

public interface Search extends SelectionListener, DisposeListener {

    public abstract void setLocale(Locale locale);

    public abstract void setMessages(MessageSource messages);

    public abstract void setSystem(EveAPI system);

    public abstract void setController(Controller controller);
    
    public abstract void define(Component component, Composite container);

}