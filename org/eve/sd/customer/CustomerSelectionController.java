package org.eve.sd.customer;

import java.util.List;

import org.eve.main.EVE;
import org.eve.model.Model;
import org.eve.view.AbstractController;
import org.eve.view.Form;
import org.eve.view.TableAssist;

public class CustomerSelectionController extends AbstractController {

    /* (non-Javadoc)
     * @see org.eve.ui.View#userInput(java.lang.String)
     */
    @Override
    public void userInput(String input) {
        Customer customer_;
        List<?> customers;
        Form selporform;
        TableAssist table;
        Customer customer = (Customer)getObject();
        Form form = getForm("main");
        int ident = form.getInt("customer.ident");
        Model model = getModel();
        String action = getAction();
        
        if (input.equals("customer.choose")) {            
            if (action.equals("customer.show.choose"))
                call("customer.show");
            
            if (action.equals("customer.edit.choose"))
                call("customer.edit");
            
            return;
        }
        
        if (input.equals("customer.sel")) {
            if (ident == 0) {
                selporform = getForm("selpor");
                
                customers = model.select("selby_customers", new Object[] {
                        selporform.getStringLike("customer.name"),
                        selporform.getStringLike("customer.aname")});
                
                if (customers != null && customers.size() > 0) {
                    table = getTable("customers");
                    ident = 0;
                    for(Object object : customers) {
                        table.insert();
                        customer_ = (Customer)object;
                        table.setIntValue("customer.ident", ident, customer_.getId());
                        table.setStringValue("customer.name", ident, customer_.getName());
                        table.setStringValue("customer.aname", ident, customer_.getAlternateName());
                        ident++;
                    }
                    
                    if (action.equals("customer.show.sel"))
                        call("customer.show.choose");
                    
                    if (action.equals("customer.edit.sel"))
                        call("customer.edit.choose");
                } else {
                    setMessage(EVE.error, "customer.not.found");
                }
            } else {
                customer_ = (Customer)model.selectUnique("sel_customer",
                        new Object[] {ident});
            
                if (customer_ != null) {
                    customer.setAlternateName(customer_.getAlternateName());
                    customer.setCodCadNac(customer_.getCodCadNac());
                    customer.setCreation(customer_.getCreation());
                    customer.setId(customer_.getId());
                    customer.setName(customer_.getName());
                    customer.setStatus(customer_.getStatus());
                    
                    if (action.equals("customer.show.sel"))
                        call("customer.show");
                    
                    if (action.equals("customer.edit.sel"))
                        call("customer.edit");
                } else {
                    setMessage(EVE.error, "customer.not.found");
                }
            }
        }
        
        form.setInt("customer.ident", ident);
    }

}
