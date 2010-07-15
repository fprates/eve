package org.eve.sd.customer.controller;

import org.eve.main.EVE;
import org.eve.model.Model;
import org.eve.sd.customer.Customer;
import org.eve.sd.customer.CustomerAddress;
import org.eve.sd.customer.CustomerContact;
import org.eve.sd.customer.CustomerSchedule;
import org.eve.view.AbstractController;
import org.eve.view.Form;
import org.eve.view.TableAssist;

public class CustomerController extends AbstractController {
    
    private final void loadSchedule(TableAssist tschedule, Customer customer, int i) {
        int k;
        CustomerSchedule schedule;
        
        customer.getSchedule().clear();
        for (k = i; k <= i+1; k++) {                    
            schedule = new CustomerSchedule();
            
            schedule.setCustomer(customer);
            schedule.setItem(k);
            schedule.setType((i == 0)?0 : 1);                    
            schedule.setMonday(tschedule.getTimeValue("schedule.mon", k));
            schedule.setTuesday(tschedule.getTimeValue("schedule.tue", k));
            schedule.setWednesday(tschedule.getTimeValue("schedule.wed", k));
            schedule.setThursday(tschedule.getTimeValue("schedule.thu", k));
            schedule.setFriday(tschedule.getTimeValue("schedule.fri", k));
            
            customer.getSchedule().add(schedule);
        }
    }
    
    @Override
    public final void userInput(String input) {
        int k;
        String name;
        CustomerContact contact;
        CustomerAddress address;
        Customer customer = (Customer)getObject();
        Model model = getModel();
        Form form = getForm("main");
        TableAssist contacts = getTable("contacts");
        TableAssist addresses = getTable("addresses");
        
        if (input.equals("customer.save")) {
            try {
                /*
                 * dados base
                 */
                customer.setAlternateName(form.getString("customer.aname"));
                customer.setCodCadNac(form.getString("customer.cnpj"));
                customer.setId(form.getInt("customer.ident"));
                customer.setName(form.getString("customer.name"));
                customer.setStatus(form.getInt("customer.status"));
                
                /*
                 * inclui contatos
                 */
                customer.getContacts().clear();
                for (k = 0; k < contacts.getItensSize(); k++) {
                    name = contacts.getStringValue("contact.rname", k);
                    
                    if (name.equals(""))
                        continue;
                    
                    contact = new CustomerContact();
                    
                    contact.setCustomer(customer);
                    contact.setItem(k);
                    contact.setName(name);
                    contact.setFunction(contacts.getStringValue("contact.funct", k));
                    
                    customer.getContacts().add(contact);
                }
                
                /*
                 * inclui endereços
                 */
                customer.getAddresses().clear();
                for (k = 0; k < contacts.getItensSize(); k++) {
                    name = addresses.getStringValue("address.logra", k);
                    
                    if (name.equals(""))
                        continue;
                    
                    address = new CustomerAddress();
                    
                    address.setCustomer(customer);
                    address.setItem(k);
                    address.setAddress(name);
                    address.setNumber(addresses.getIntValue("address.numer", k));
                    
                    customer.getAddresses().add(address);
                }
                
                /*
                 * inclui horários
                 */
                
                loadSchedule(getTable("vschedule"), customer, 0);
                loadSchedule(getTable("dschedule"), customer, 2);
                
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
