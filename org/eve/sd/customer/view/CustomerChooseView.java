package org.eve.sd.customer.view;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eve.model.AbstractDocument;
import org.eve.sd.customer.Customer;
import org.eve.view.AbstractView;
import org.eve.view.ComponentType;
import org.eve.view.Controller;
import org.eve.view.TableAssist;

public class CustomerChooseView extends AbstractView {

    @Override
    protected void defineView(Composite container) {
        TableAssist ctable = addTable("customers");
        AbstractDocument customer = (Customer)getController().getObject();
        
        setHeight(350);
        container.setLayout(new FillLayout());
        
        addAction("customer.show.choose", false);
        addAction("customer.edit.choose", false);
        
        ctable.setLines(10);
        ctable.setSelType(ComponentType.SINGLE);
        ctable.put(customer, Customer.IDENT);
        ctable.put(customer, Customer.REFER);
        ctable.put(customer, Customer.NAME);
        ctable.put(customer, Customer.ANAME);
        
        ctable.define(container);
        
        addButton("customer.choose");
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.View#reload(java.lang.String)
     */
    @Override
    public void reload(String action) {
        Controller controller = getController();
        controller.setAction(action);
        
        setTitlebar("customer.sel.title");
        
    }

}
