/**
 * 
 */
package org.eve.sd.supplier.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eve.view.AbstractView;
import org.eve.view.Form;

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
        Group selpor;
        Form form = addForm("main");
        Form selporform = addForm("selpor");
        
        container.setLayout(new RowLayout(SWT.VERTICAL));

        addAction("supplier.show.sel");
        addAction("supplier.edit.sel");
        
        form.setLocale(getLocale());
        form.put("supplier.ident", 12);        
        form.define(container);
        
        selpor = new Group(container, SWT.SHADOW_IN);
        selporform.setLocale(getLocale());
        selpor.setLayout(new RowLayout(SWT.VERTICAL));
        selpor.setText(getMessage("supplier.select.by"));
        
        selporform.put("supplier.name", 40);
        selporform.put("supplier.aname", 40);
        selporform.define(selpor);
        selpor.pack();
        
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
