package org.eve.sd.customer.controller;

import java.util.List;

import org.eve.main.EVE;
import org.eve.model.Model;
import org.eve.sd.customer.Customer;
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
        Form form;
        int ident;
        Customer customer = (Customer)getObject();
        Model model = getModel();
        String action = getAction();
        
        if (input.equals("customer.choose")) {
            table = getTable("customers");
            ident = 0;
            
            for (int k = 0; k < table.getItensSize(); k++) {
                if (table.getMarkValue(k)) {
                    ident = table.getIntValue("customer.ident", k);
                    break;
                }                    
            }
            
            if (ident == 0) {
                setMessage(EVE.error, "select.one");
                return;
            }
            
            model.load(Customer.class, ident, customer);
            
            if (action.equals("customer.show.choose"))
                call("customer.show");
            
            if (action.equals("customer.edit.choose"))
                call("customer.edit");
            
            return;
        }
        
        form = getForm("main");
        ident = form.getInt("customer.ident");
        
        if (input.equals("customer.sel")) {
            if (ident == 0) {
                selporform = getForm("selpor");
                
                customers = model.select("selby_customers", new Object[] {
                        selporform.getStringLike("customer.name"),
                        selporform.getStringLike("customer.aname")});
                
                if (customers != null) {
                    if (customers.size() > 0) {
                        table = getTable("customers");
                        ident = 0;
                        
                        for(Object object : customers) {
                            table.insert();
                            customer_ = (Customer)object;
                            table.setIntValue("customer.ident", ident,
                                    customer_.getId());
                            table.setStringValue("customer.name", ident,
                                    customer_.getName());
                            table.setStringValue("customer.aname", ident,
                                    customer_.getAlternateName());
                            ident++;
                        }
                        
                        if (action.equals("customer.show.sel"))
                            call("customer.show.choose");
                        
                        if (action.equals("customer.edit.sel"))
                            call("customer.edit.choose");
                        
                    } else {
                        setMessage(EVE.error, "customer.select.empty");
                        return;
                    }
                } else {
                    setMessage(EVE.error, "customer.not.found");
                    return;
                }
            } else {            
                model.load(Customer.class, ident, customer);
                if (customer != null) {
                    if (action.equals("customer.show.sel"))
                        call("customer.show");
                    
                    if (action.equals("customer.edit.sel"))
                        call("customer.edit");
                } else {
                    setMessage(EVE.error, "customer.not.found");
                    return;
                }
            }
        }
        
        form.setInt("customer.ident", ident);
    }

}
