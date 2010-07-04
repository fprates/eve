package org.eve.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eve.main.EVE;

public class MessageBar {
    private Group msggrp;
    private Label msgtxt;
    
    public MessageBar() { }
    
    /**
     * Ajusta mensagem da barra
     * @param status
     * @param message
     */
    public final void setMessage(int status, String message) {        
        switch (status) {
        case EVE.error:
            msggrp.setText("Erro");
            break;
            
        case EVE.status:
            msggrp.setText("");
            break;
        }
        
        msgtxt.setText(message);
        msgtxt.setSize(msgtxt.computeSize(
                message.length() * ViewUtils.getCharWidth(msgtxt),
                ViewUtils.getCharHeight(msgtxt)));
    }
    
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
}
