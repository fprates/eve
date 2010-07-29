package org.eve.sd.supplier.view;

import org.eclipse.swt.widgets.Composite;
import org.eve.view.AbstractView;

public class SupplierChooseView extends AbstractView {

    @Override
    protected void defineView(Composite container) {
        addAction("supplier.show.choose", false);
        addAction("supplier.edit.choose", false);
        
        addButton("supplier.choose");
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.View#reload(java.lang.String)
     */
    @Override
    public void reload(String action) {        
        setTitlebar("supplier.sel.title");        
    }

}
