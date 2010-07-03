package org.eve.sd.customer;

import org.eve.main.EVE;
import org.eve.view.AbstractController;

public class CustomerSelectionController extends AbstractController {

    /* (non-Javadoc)
     * @see org.eve.ui.View#userInput(java.lang.String)
     */
    @Override
    public void userInput(String input) {
        Customer customer = (Customer)getObject();
        CustomerModel model = (CustomerModel)getModel();
//        String action = getAction();
        
        customer.setId(getIntForm("main", "customer.ident"));
        
        if (input.equals("customer.sel")) {
            try {
                model.select(customer);
//                if (action.equals("customer.show.sel"))
//                    call("customer.show");
//                
//                if (action.equals("customer.edit.sel"))
//                    call("customer.edit");
                
            } catch (Exception e) {
                setMessage(EVE.error, "Cliente não encontrado.");
                e.printStackTrace();
            }
        }
        
        setIntForm("main", "customer.ident", customer.getId());
    }

}
