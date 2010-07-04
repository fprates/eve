package org.eve.sd.customer;

import org.eve.main.EVE;
import org.eve.model.Model;
import org.eve.view.AbstractController;

public class CustomerController extends AbstractController {
    
    @Override
    public final void userInput(String input) {
        Customer customer = (Customer)getObject();
        Model model = getModel();
        
        if (input.equals("customer.save")) {
            try {
                model.save(customer);
                getForm("main").setInt("customer.ident", customer.getId());
                
                setMessage(EVE.status, "customer.save.successful");
            } catch (Exception ev) {                
                setMessage(EVE.error, "customer.save.error");
                ev.printStackTrace();
            }
        }
        
        if (input.equals("contacts.new")) {
            System.out.println("inserir");
        }
        
        if (input.equals("contacts.del")) {
            System.out.println("remover");
        }
    }
}
