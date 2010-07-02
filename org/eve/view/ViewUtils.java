package org.eve.view;

import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;

public class ViewUtils {
    
    /**
     * Retorna largura do texto
     * @param text
     * @return
     */
    public static final int getCharWidth(Drawable text) {
        GC gc = new GC(text);
        int charw = gc.getFontMetrics().getAverageCharWidth();
        
        gc.dispose();
        
        return charw;
    }
    
    /**
     * Retorna altura do texto
     * @param text
     * @return
     */
    public static final int getCharHeight(Drawable text) {
        GC gc = new GC(text);
        int charh = gc.getFontMetrics().getHeight();
        
        gc.dispose();
        
        return charh;
    }

}
