package org.eve.sd.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eve.main.EVE;
import org.eve.model.Model;
import org.eve.sd.common.City;
import org.eve.sd.common.Country;
import org.eve.sd.common.State;
import org.eve.sd.customer.Customer;
import org.eve.sd.customer.CustomerAddress;
import org.eve.sd.customer.CustomerContact;
import org.eve.sd.customer.CustomerSchedule;
import org.eve.view.AbstractController;
import org.eve.view.Form;
import org.eve.view.TableAssist;

public class CustomerController extends AbstractController {
    private Country country;
    private Map<String, List<?>> cities;
    
    public CustomerController() {
        country = new Country();
        cities = new HashMap<String, List<?>>();
    }
    
    private final void loadSchedule(TableAssist tschedule, Customer customer, int i) {
        int k;
        CustomerSchedule schedule;
        
        for (k = 0; k < 2 ; k++) {
            schedule = new CustomerSchedule();
            
            schedule.setCustomer(customer);
            schedule.setItem((k + (i * 2)) + 1);
            schedule.setType(i);                    
            schedule.setMonday(tschedule.getTimeValue("schedule.mon", k));
            schedule.setTuesday(tschedule.getTimeValue("schedule.tue", k));
            schedule.setWednesday(tschedule.getTimeValue("schedule.wed", k));
            schedule.setThursday(tschedule.getTimeValue("schedule.thu", k));
            schedule.setFriday(tschedule.getTimeValue("schedule.fri", k));
            
            customer.getSchedule().add(schedule);
        }
    }
    
    public final Object[] getResults(String id, Object object) {
        int size;
        String ufkey;
        Object[] objects;
        List<?> results;
        City city;
        Model model = getModel();
        
        if (id.equals("address.coduf")) {
            model.load(Country.class, "BRA", country);
            
            if (country == null)
                return null;
            
            size = country.getStates().size();
            if (size == 0)
                return null;
            
            objects = new Object[size];
            size = 0;
            for (State state : country.getStates())
                objects[size++] = state.getIdent();
            
            return objects;
        }
        
        if (id.equals("address.munic")) {
            if (object == null)
                return null;
            
            ufkey = "BRA".concat((String)object);
            results = cities.get(ufkey);
            
            if (results == null) {
                results = model.select("sel_cities", new Object[] {ufkey});
                if (results != null)
                    cities.put(ufkey, results);
            }
                
            if (results == null)
                return null;
            
            size = results.size();
            if (size == 0)
                return null;
            
            objects = new Object[size];
            size = 0;
            for (Object object_ : results) {
                city = (City)object_;
                objects[size++] = new StringBuffer(city.getIdent())
                    .append(" ").append(city.getName()).toString();
            }
            
            return objects;
        }
        
        return null;
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
                customer.setCodCadFiscal(form.getString("customer.cnpj"));
                customer.setId(form.getInt("customer.ident"));
                customer.setName(form.getString("customer.name"));
                customer.setStatus(form.getInt("customer.status"));
                customer.setHomePage(form.getString("customer.homep"));
                customer.setEmail(form.getString("customer.email"));
                customer.setIVF(form.getInt("customer.cdivf"));
                customer.setIncentive(form.getInt("customer.tpinc"));
                customer.setProductIncentiveValue(form.getFloat("customer.vlipr"));
                customer.setBillingIncentiveValue(form.getFloat("customer.vlibl"));
                customer.setSupplierIncentiveValue(form.getFloat("customer.dvcsp"));
                customer.setPartnerIncentiveValue(form.getFloat("customer.dvcpt"));
                
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
                for (k = 0; k < addresses.getItensSize(); k++) {
                    name = addresses.getStringValue("address.logra", k);
                    
                    if (name.equals(""))
                        continue;
                    
                    address = new CustomerAddress();
                    
                    address.setCustomer(customer);
                    address.setItem(k);
                    address.setType(addresses.getIntValue("address.type", k));
                    address.setAddress(name);
                    address.setNumber(addresses.getIntValue("address.numer", k));
                    
                    customer.getAddresses().add(address);
                }
                
                /*
                 * inclui horários
                 */
                customer.getSchedule().clear();
                loadSchedule(getTable("vschedule"), customer, 0);
                loadSchedule(getTable("dschedule"), customer, 1);
                
                model.save(customer);
                form.setInt("customer.ident", customer.getId());
                form.setDate("customer.dtreg", customer.getRegDate());
                form.setTime("customer.tmreg", customer.getRegTime());
                
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
