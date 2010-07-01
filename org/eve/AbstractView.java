package org.eve;

public abstract class AbstractView implements View {
    private Model model;
    
    /*
     * (non-Javadoc)
     * @see org.eve.View#setModel(org.eve.Model)
     */
    public final void setModel(Model model) {
        this.model = model;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.View#getModel()
     */
    public final Model getModel() {
        return model;
    }
}
