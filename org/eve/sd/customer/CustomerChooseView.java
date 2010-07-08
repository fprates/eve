package org.eve.sd.customer;

import org.eclipse.swt.widgets.Composite;
import org.eve.view.AbstractView;
import org.eve.view.Controller;
import org.eve.view.TableAssist;

public class CustomerChooseView extends AbstractView {

    @Override
    protected void defineView(Composite container) {
        Controller controller = getController();
        TableAssist ctable = controller.getTable("customers");

        addAction("customer.show.choose", false);
        addAction("customer.edit.choose", false);
        
        ctable.setLocale(getLocale());
        ctable.put("customer.ident");
        ctable.put("customer.name");
        ctable.put("customer.aname");
        
        ctable.setEditable(false);
        ctable.define(container, getController());
        
        addButton("customer.choose");
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.View#reload(java.lang.String)
     */
    @Override
    public void reload(String action) {
        getController().setAction(action);        
    }

}
