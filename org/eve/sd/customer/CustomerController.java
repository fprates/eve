package org.eve.sd.customer;

import org.eve.main.EVE;
import org.eve.model.Model;
import org.eve.view.AbstractController;
import org.eve.view.Form;
import org.eve.view.TableAssist;

public class CustomerController extends AbstractController {
    
    @Override
    public final void userInput(String input) {
        int k;
        String name;
        CustomerContact contact;
        Customer customer = (Customer)getObject();
        Model model = getModel();
        Form form = getForm("main");
        TableAssist contacts = getTable("contacts");
        
        if (input.equals("customer.save")) {
            try {
                customer.setAlternateName(form.getString("customer.aname"));
                customer.setCodCadNac(form.getString("customer.cnpj"));
                customer.setId(form.getInt("customer.ident"));
                customer.setName(form.getString("customer.name"));
                customer.setStatus(form.getInt("customer.status"));
                
                for (k = 0; k < contacts.getItensSize(); k++) {
//                    contact = null;
//                    for (CustomerContact contact_: customer.getContacts()) {
//                        contact.setCustomer(customer);
//                        contact.setFunction(contacts.getStringValue("contact.function", row))
//                        (contacts.getStringValue("customer.aname", 0));
//                        contact.setCodCadNac(contacts.getStringValue("customer.cnpj", 0));
////                        customer.setRegDate(customer_.getCreation());
//                        contact.setName(contacts.getSelectedItem(0, "customer.name"));
//                        contact.setStatus(contacts.getSelectedIntItem(0, "customer.status"));
//                    }
//                    
//                    if (contact != null)
//                        continue;
                    name = contacts.getStringValue("contact.rname", k);
                    
                    if (name.equals(""))
                        continue;
                    
                    contact = new CustomerContact();
                    
                    contact.setCustomer(customer);
                    contact.setItem(k+1);
                    contact.setName(name);
                    contact.setFunction(contacts.getStringValue("contact.funct", k));
                    
                    customer.getContacts().add(contact);
                    
                }
                
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
