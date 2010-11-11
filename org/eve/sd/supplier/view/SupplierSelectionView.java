/**
 * 
 */
package org.eve.sd.supplier.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eve.sd.supplier.Supplier;
import org.eve.view.AbstractView;
import org.eve.view.Controller;
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
        Supplier supplier = (Supplier)getController().getObject();
        
        container.setLayout(new RowLayout(SWT.VERTICAL));

        addAction("supplier.show.sel");
        addAction("supplier.edit.sel");
        
        form.setLocale(getLocale());
        form.put(supplier, Supplier.IDENT);
        form.define(container);
        
        selpor = new Group(container, SWT.SHADOW_IN);
        selporform.setLocale(getLocale());
        selpor.setLayout(new RowLayout(SWT.VERTICAL));
        selpor.setText(getMessage("supplier.select.by"));
        
        selporform.put(supplier, Supplier.NAME);
        selporform.put(supplier, Supplier.ANAME);
        selporform.put(supplier, Supplier.REFER);
        selporform.define(selpor);
        selpor.pack();
        
        addButton("supplier.sel");
    }

    /* (non-Javadoc)
     * @see org.eve.ui.View#reload(java.lang.String)
     */
    @Override
    public void reload(String action) {
        Controller controller = getController();
        
        controller.setAction(action);
        controller.getForm("main").clear();
        controller.getForm("selpor").clear();
        controller.getTable("suppliers").clear();
        
        setTitlebar("supplier.sel.title");
    }

}
