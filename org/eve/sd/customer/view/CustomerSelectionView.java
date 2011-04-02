/**
 * 
 */
package org.eve.sd.customer.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eve.sd.customer.Customer;
import org.eve.view.AbstractView;
import org.eve.view.Controller;
import org.eve.view.Form;

/**
 * @author francisco.prates
 *
 */
public class CustomerSelectionView extends AbstractView {
    /**
     * 
     */
    public CustomerSelectionView() { }

    /* (non-Javadoc)
     * @see org.eve.ui.View#defineForm()
     */
    @Override
    public void defineView(Composite container) {
        Group selpor;
        Customer customer = (Customer)getController().getObject();
        Form form = addForm("main");
        Form selporform = addForm("selpor");
        
        container.setLayout(new RowLayout(SWT.VERTICAL));
        
        addAction("customer.show.sel");
        addAction("customer.edit.sel");
        
        form.setLocale(getLocale());
        form.put(customer, "customer.ident");
        form.define(container);
        
        selpor = new Group(container, SWT.SHADOW_IN);
        selporform.setLocale(getLocale());
        selpor.setLayout(new RowLayout(SWT.VERTICAL));
        selpor.setText(getMessage("select.by"));
        
        selporform.put(customer, "customer.name");
        selporform.put(customer, "customer.aname");
        selporform.put(customer, "customer.refer");
        selporform.define(selpor);
        selpor.pack();
        
        addButton("customer.sel");
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
        controller.getTable("customers").clear();
        
        setTitlebar("customer.sel.title");
    }

}
