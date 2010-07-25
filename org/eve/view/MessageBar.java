package org.eve.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eve.main.EVE;

/**
 * Barra de mensagens
 * @author francisco.prates
 *
 */
public class MessageBar {
    private Label msgtxt;
    
    public MessageBar(Composite container) {
        msgtxt = new Label(container, SWT.NONE);
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    /**
     * Ajusta mensagem da barra
     * @param status
     * @param message
     */
    public final void setMessage(int status, String text) {
        Color color;
        
        switch (status) {
        case EVE.error:
            color = Display.getCurrent().getSystemColor(SWT.COLOR_RED);
            break;
            
        case EVE.status:
            color = Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
            break;
            
        default:
            color = Display.getCurrent().getSystemColor(SWT.COLOR_INFO_FOREGROUND);
        }
        
        msgtxt.setText(text);
        msgtxt.setForeground(color);
        msgtxt.pack();
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * Limpa barra de mensagem
     */
    public final void clear() {
        msgtxt.setText("");
    }
}
