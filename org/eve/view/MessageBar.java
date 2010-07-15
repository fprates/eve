package org.eve.view;

import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eve.main.EVE;
import org.springframework.context.MessageSource;

public class MessageBar {
    private Group msggrp;
    private Label msgtxt;
    private MessageSource messages;
    private Composite container;
    
    public MessageBar(Composite container) {
        this.container = container;
    }
    
    /*
     * 
     * Setters
     * 
     */
    /**
     * Define fonte de mensagens
     * @param messages
     */
    public final void setMessages(MessageSource messages) {
        this.messages = messages;
    }
    
    /**
     * Ajusta mensagem da barra
     * @param status
     * @param message
     */
    public final void setMessage(int status, String id, Locale locale) {
        String message;
        Color color;
        
        switch (status) {
        case EVE.error:
            msggrp.setText("Erro");
            color = Display.getCurrent().getSystemColor(SWT.COLOR_RED);
            break;
            
        case EVE.status:
            msggrp.setText("");
            color = Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
            break;
        default:
            color = Display.getCurrent().getSystemColor(SWT.COLOR_INFO_FOREGROUND);
        }
        
        message = messages.getMessage(id, null, id, locale);
        msgtxt.setText(message);
        msgtxt.setForeground(color);
        msggrp.pack();
        container.pack();
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * Inicializa barra de mensagens
     * @param container
     */
    public final void init() {
        msggrp = new Group(container, SWT.SHADOW_IN);
        msggrp.setLayout(new RowLayout(SWT.VERTICAL));
        msgtxt = new Label(msggrp, SWT.NONE);
    }
    
    /**
     * Limpa barra de mensagem
     */
    public final void clear() {
        msggrp.setText("");
        msgtxt.setText("");
    }
}
