/**
 * 
 */
package org.eve.sd.customer;

import org.eve.view.AbstractView;
import org.eve.view.Form;

/**
 * @author francisco.prates
 *
 */
public class CustomerSelectionView extends AbstractView {
    Customer customer;

    /**
     * 
     */
    public CustomerSelectionView() { }

    /* (non-Javadoc)
     * @see org.eve.ui.View#defineForm()
     */
    @Override
    public void defineView() {
        Form form = getController().getForm("main");
        
        addAction("customer.show.sel");
        addAction("customer.edit.sel");
        
        form.put("customer.ident", 12);
        
        form.define(getContainer());
        
        addButton("customer.sel");

    }

    /* (non-Javadoc)
     * @see org.eve.ui.View#reload(java.lang.String)
     */
    @Override
    public void reload(String action) {
        getController().setAction(action);
        
//        setTitle("Selecionar cliente");
    }

}
