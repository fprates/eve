/**
 * 
 */
package org.eve.sd.customer;

import org.eve.view.AbstractView;

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
        addAction("customer.show.sel");
        addAction("customer.edit.sel");
        
        addForm("customer.ident", 12);
        
        defineForm("main", getContainer());
        
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
