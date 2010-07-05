package org.eve.sd.customer;

//import java.util.List;

import org.eve.view.AbstractView;
import org.eve.view.TableAssist;

public class CustomerChooseView extends AbstractView {

    @Override
    protected void defineView() {
        TableAssist ctable = getController().getTable("customers");
        
        ctable.setLocale(getLocale());
        ctable.put("customer.ident");
        ctable.put("customer.name");
        ctable.put("customer.aname");

        ctable.define(getContainer(), getController());
        
        addButton("customer.sel");
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.View#reload(java.lang.String)
     */
    @Override
    public void reload(String action) {
//        List<Customer> customers = (List<Customer>)getController().getAttribute();
        getController().setAction(action);
        
    }

}
