package org.eve.view;

import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eve.main.EVE;
import org.springframework.context.MessageSource;

public class MessageBar {
    private Group msggrp;
    private Label msgtxt;
    private MessageSource messages;
    
    public MessageBar() { }
    
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
    public final void setMessage(int status, String message, Locale locale) {        
        switch (status) {
        case EVE.error:
            msggrp.setText("Erro");
            break;
            
        case EVE.status:
            msggrp.setText("");
            break;
        }
        
        msgtxt.setText(messages.getMessage(message, null, locale));
        msgtxt.setSize(msgtxt.computeSize(
                message.length() * ViewUtils.getCharWidth(msgtxt),
                ViewUtils.getCharHeight(msgtxt)));
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
    public final void init(Composite container) {
        msggrp = new Group(container, SWT.SHADOW_ETCHED_IN);
        msggrp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        msgtxt = new Label(msggrp, SWT.NONE);
        msggrp.pack();        
    }
    
    /**
     * Limpa barra de mensagem
     */
    public final void clear() {
        msgtxt.setText("");
    }
}
