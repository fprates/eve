package org.eve.sd.customer;

import org.eve.main.EVE;
import org.eve.view.AbstractController;

public class CustomerController extends AbstractController {
    
    @Override
    public final void userInput(String input) {
        Customer customer = (Customer)getObject();
        CustomerModel model = (CustomerModel)getModel();
        
        customer.setId(getIntForm("main", "customer.ident"));
        customer.setName(getStringForm("main", "customer.name"));
        customer.setAlternateName(getStringForm("main", "customer.aname"));
        customer.setStatus(getIntForm("main", "customer.status"));
        
        if (input.equals("customer.save")) {
            try {
                model.save(customer);
                setMessage(EVE.status, "Cliente gravado com sucesso.");
                setIntForm("main", "ident", customer.getId());
            } catch (Exception ev) {                
                setMessage(EVE.error, "Erro na gravação do cliente.");
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
