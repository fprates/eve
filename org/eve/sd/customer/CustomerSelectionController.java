package org.eve.sd.customer;

import org.eve.main.EVE;
import org.eve.model.Model;
import org.eve.view.AbstractController;
import org.eve.view.Form;

public class CustomerSelectionController extends AbstractController {

    /* (non-Javadoc)
     * @see org.eve.ui.View#userInput(java.lang.String)
     */
    @Override
    public void userInput(String input) {
        Customer customer_;
        Customer customer = (Customer)getObject();
        Form form = getForm("main");
        int ident = form.getInt("customer.ident");
        Model model = getModel();
        String action = getAction();
        
        
        if (input.equals("customer.sel")) {
            customer_ = (Customer)model.selectUnique("sel_customer", new Object[] {ident});
            
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
        
        form.setInt("customer.ident", ident);
    }

}
