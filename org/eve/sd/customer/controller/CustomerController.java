package org.eve.sd.customer.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.eve.sd.supplier.Supplier;
import org.eve.view.AbstractController;
import org.eve.view.Form;
import org.eve.view.TableAssist;
import org.hibernate.HibernateException;

public class CustomerController extends AbstractController {
    private Country country;
    private Map<String, List<?>> cities;
    
    public CustomerController() {
        country = new Country();
        cities = new HashMap<String, List<?>>();
    }
    
    /**
     * 
     * @param tschedule
     * @param customer
     * @param i
     */
    private final void loadSchedule(TableAssist tschedule, Customer customer, int i) {
        CustomerSchedule schedule;
        
        for (int k = 0; k < 2 ; k++) {
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
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractController#getResults(java.lang.String, java.lang.Object)
     */
    @Override
    public final Map<Object, String> getResults(String id, Object object) {
        String ufkey;
        List<?> results;
        Map<Object, String> results_;
        City city;
        Supplier supplier;        
        Model model = getModel();
        
        if (id.equals("customer.stdsp")) {
            results = model.select("sel_suppliers", null);
            
            if (results.size() == 0)
                return null;
            
            results_ = new LinkedHashMap<Object, String>();
            for (Object object_ : results) {
                supplier = (Supplier)object_;                
                results_.put(supplier.getId(), supplier.getName());
            }
            
            return results_;
        }
        
        if (id.equals("address.coduf")) {
            model.load(Country.class, "BRA", country);
            
            if (country == null)
                return null;
            
            if (country.getStates().size() == 0)
                return null;
            
            results_ = new LinkedHashMap<Object, String>();
            for (State state : country.getStates())
                results_.put(state.getIdent(), state.getIdent());
            
            return results_;
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
            
            if (results.size() == 0)
                return null;
            
            results_ = new LinkedHashMap<Object, String>();
            for (Object object_ : results) {
                city = (City)object_;
                results_.put(city.getIdent(), city.getName());
            }
            return results_;
        }
        
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractController#userInput(java.lang.String)
     */
    @Override
    public final void userInput(String input) {
        String ufkey;
        String name;
        CustomerContact contact;
        CustomerAddress address;
        City city;
        List<?> results;
        Customer customer = (Customer)getObject();
        Model model = getModel();
        Form form = getForm("main");
        TableAssist contacts = getTable("contacts");
        TableAssist addresses = getTable("addresses");
        
        if (contacts.hasEvent(input)) {
            contacts.userInput(input);
            return;
        }
        
        if (addresses.hasEvent(input)) {
            addresses.userInput(input);
            return;
        }
        
        if (input.equals("customer.save")) {
            try {
                /*
                 * dados base
                 */
                for (Object id : customer.getIds()) {
                    if (id.equals("regDate") || id.equals("regTime") ||
                            id.equals("regUser"))
                        continue;
                    
                    customer.setFieldValue((String)id, form.getFieldValue(
                            customer.getName((String)id)));
                }
                
                customer.setReference(form.getString("customer.refer"));
                customer.setAlternateName(form.getString("customer.aname"));
                customer.setCodCadFiscal(form.getString("customer.cnpj"));
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
                customer.setStandardSupplier(form.getInt("customer.stdsp"));
                customer.setInscricaoEstadual(form.getLong("customer.ie"));
                customer.setTipoComunicacao(form.getInt("customer.tpcom"));
                customer.setTipoEstabelecimento(form.getInt("customer.tpest"));
                
                /*
                 * inclui contatos
                 */
                customer.getContacts().clear();
                for (int k = 0; k < contacts.getItensSize(); k++) {
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
                for (int k = 0; k < addresses.getItensSize(); k++) {
                    name = addresses.getStringValue("address.logra", k);
                    
                    if (name.equals(""))
                        continue;
                    
                    address = new CustomerAddress();
                    
                    address.setCustomer(customer);
                    address.setItem(k);
                    address.setType(addresses.getIntValue("address.type", k));
                    address.setAddress(name);
                    address.setNumber(addresses.getIntValue("address.numer", k));
                    address.setEstado(addresses.getStringValue("address.coduf", k));

                    ufkey = "BRA"+address.getEstado();
                    results = cities.get(ufkey);
                    if (results == null) {
                        results = model.select("sel_cities", new Object[] {ufkey});
                        cities.put(ufkey, results);
                    }

                    name = addresses.getStringValue("address.munic", k);
                    for (Object object : results) {
                        city = (City)object;
                        if (!city.getName().equals(name))
                            continue;
                        
                        address.setMunicipio(city.getIdent());
                        break;
                    }
                    
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
            } catch (HibernateException ex) {
                setMessage(EVE.error, "customer.save.error");
                ex.printStackTrace();                
            } catch (Exception ex) {
                ex.printStackTrace();
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
