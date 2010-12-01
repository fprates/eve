package org.eve.view;

import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eve.model.AbstractDocument;
import org.springframework.context.MessageSource;

public class ComponentFactory {
    private Composite container;
    private Locale locale;
    private MessageSource messages;
    
    public ComponentFactory() { }
    
    public void setContainer(Composite container) {
        this.container = container;
    }
    
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    public void setMessages(MessageSource messages) {
        this.messages = messages;
    }
    
    public void putTextEntry(AbstractDocument document, String field) {
        Label label = new Label(container, SWT.NONE);
        Text text = new Text(container, SWT.BORDER);
        String name = document.getName(field);
        
        label.setText(messages.getMessage(name, null, name, locale));
    }
}
