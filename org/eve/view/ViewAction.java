/**
 * 
 */
package org.eve.view;

/**
 * Ação para visão
 * @author francisco.prates
 *
 */
public class ViewAction {
    private View view;
    private String id;
    private String text;
    private boolean visible;
    
    public ViewAction(View view, String id, String text, boolean visible) {
        this.id = id;
        this.view = view;
        this.text = text;
        this.visible = visible;
    }

    /**
     * @return visão
     */
    public final View getView() {
        return view;
    }

    /**
     * @return título
     */
    public final String getText() {
        return text;
    }

    /**
     * @return identificador
     */
    public final String getId() {
        return id;
    }

    /**
     * @return indicador de visibilidade
     */
    public final boolean isVisible() {
        return visible;
    }
    
}
