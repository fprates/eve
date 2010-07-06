package org.eve.sd.customer;

import org.eve.view.AbstractView;
import org.eve.view.TableAssist;

public class CustomerChooseView extends AbstractView {

    @Override
    protected void defineView() {
        TableAssist ctable = getController().getTable("customers");

        addAction("customer.show.choose", false);
        addAction("customer.edit.choose", false);
        
        ctable.setLocale(getLocale());
        ctable.put("customer.ident");
        ctable.put("customer.name");
        ctable.put("customer.aname");
        
        ctable.setEditable(false);        
        ctable.define(getContainer(), getController());
        
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
