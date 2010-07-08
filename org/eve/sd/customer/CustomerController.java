package org.eve.sd.customer;

import org.eve.main.EVE;
import org.eve.model.Model;
import org.eve.view.AbstractController;
import org.eve.view.Form;

public class CustomerController extends AbstractController {
    
    @Override
    public final void userInput(String input) {
        Customer customer = (Customer)getObject();
        Model model = getModel();
        Form form = getForm("main");
        
        if (input.equals("customer.save")) {
            try {
                customer.setAlternateName(form.getString("customer.aname"));
                customer.setCodCadNac(form.getString("customer.cnpj"));
                customer.setId(form.getInt("customer.ident"));
                customer.setName(form.getString("customer.name"));
                customer.setStatus(form.getInt("customer.status"));
                
                model.save(customer);                
                form.setInt("customer.ident", customer.getId());                
                setMessage(EVE.status, "customer.save.success");
                
                return;
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
