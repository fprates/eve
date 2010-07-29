/**
 * 
 */
package org.eve.sd.supplier.view;

import org.eclipse.swt.widgets.Composite;
import org.eve.view.AbstractView;

/**
 * @author francisco.prates
 *
 */
public class SupplierSelectionView extends AbstractView {
    
    /**
     * 
     */
    public SupplierSelectionView() { }

    /* (non-Javadoc)
     * @see org.eve.ui.View#defineForm()
     */
    @Override
    public void defineView(Composite container) {        
        addAction("supplier.show.sel");
        addAction("supplier.edit.sel");
        
        addButton("supplier.sel");
    }

    /* (non-Javadoc)
     * @see org.eve.ui.View#reload(java.lang.String)
     */
    @Override
    public void reload(String action) {        
        setTitlebar("supplier.sel.title");
    }

}
