/**
 * 
 */
package org.eve.view;

/**
 * @author francisco.prates
 *
 */
public class ViewAction {
    private View view;
    private String id;
    private String text;
    private boolean visible;
    
    /**
     * 
     */
    public ViewAction(View view, String id, String text, boolean visible) {
        this.id = id;
        this.view = view;
        this.text = text;
        this.visible = visible;
    }

    /**
     * @return the view
     */
    public final View getView() {
        return view;
    }

    /**
     * @return the text
     */
    public final String getText() {
        return text;
    }

    /**
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * @return the visible
     */
    public final boolean isVisible() {
        return visible;
    }
    
}
