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
//        int charw;
//        int charh;
//        Text text;
//        Label label = new Label(container, SWT.NONE);
//        Composite fieldComposite = new Composite(container, SWT.NONE);
//        
//        label.setText(messages.getMessage(field, null, field, locale));
//        
//        charw = ViewUtils.getCharWidth(text);
//        charh = ViewUtils.getCharHeight(text);
//        
//        text.setSize(text.computeSize(
//                component.getLength() * charw, charh));
//        
//        component.setControl(text);
//        
//        switch(component.getExtension()) {
//        case SEARCH:
//            search = new SearchHelper();
//            search.setLocale(locale);
//            search.setMessages(messages);
//            search.setController(controller);
//            search.setSystem(system);
//            search.define(component, fieldComposite);
//            break;
//            
//        case FILESEARCH:
//            search = new FileSearch(fieldComposite.getShell());
//            search.setLocale(locale);
//            search.setMessages(messages);
//            search.setController(controller);
//            search.setSystem(system);
//            search.define(component, fieldComposite);
//        }
    }
}
