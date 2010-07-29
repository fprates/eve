package org.eve.sd.supplier.view;

import org.eclipse.swt.widgets.Composite;
import org.eve.view.AbstractView;

public class SupplierView extends AbstractView {

    public SupplierView() { }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractView#defineView(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void defineView(Composite container) {
        addAction("supplier.create");
        addAction("supplier.edit", false);
        addAction("supplier.show", false);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#reload(java.lang.String)
     */
    @Override
    public final void reload(String action) {
        
        /*
         * Display mode component's configuration
         */
        if (action.equals("supplier.show")) {
            setTitlebar("supplier.show.title");
//            setButtonVisible("supplier.save", false);
            
            return;
        }
        
        /*
         * Edit mode component's configuration
         */
        if (action.equals("supplier.edit")) {
            setTitlebar("supplier.edit.title");
//            setButtonVisible("supplier.save", true);
                
            return;
        }
        
        /*
         * Creation mode component's configuration
         */
        if (action.equals("supplier.create")) {
            setTitlebar("supplier.create.title");
//            setButtonVisible("supplier.save", true);
            
            return;
        }
    }
}
