package org.eve.sd.customer;

import org.eve.main.EVE;
import org.eve.model.Model;
import org.eve.view.AbstractController;

public class CustomerSelectionController extends AbstractController {

    /* (non-Javadoc)
     * @see org.eve.ui.View#userInput(java.lang.String)
     */
    @Override
    public void userInput(String input) {
        Customer customer;
        int ident = getIntForm("main", "customer.ident");
        Model model = getModel();
        String action = getAction();
        
        
        if (input.equals("customer.sel")) {
            customer = (Customer)model.selectUnique("sel_customer", new Object[] {ident});
            if (customer != null) {
                if (action.equals("customer.show.sel"))
                    call("customer.show");
                
                if (action.equals("customer.edit.sel"))
                    call("customer.edit");
            } else {
                setMessage(EVE.error, "Cliente não encontrado.");
            }
        }
        
        setIntForm("main", "customer.ident", ident);
    }

}
