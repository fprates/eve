package org.eve.sd.supplier.controller;

import org.eve.main.EVE;
import org.eve.model.Model;
import org.eve.sd.supplier.Supplier;
import org.eve.view.AbstractController;
import org.eve.view.Form;
import org.hibernate.HibernateException;

public class SupplierController extends AbstractController {

    @Override
    public void userInput(String input) {
        Supplier supplier = (Supplier)getObject();
        Form form = getForm("main");
        Model model = getModel();
        
        if (input.equals("supplier.save")) {
            try {
                /*
                 * dados base
                 */
                supplier.setAlternateName(form.getString("supplier.aname"));
//                supplier.setCodCadFiscal(form.getString("supplier.cnpj"));
                supplier.setId(form.getInt("supplier.ident"));
                supplier.setName(form.getString("supplier.name"));
                supplier.setReference(form.getString("supplier.refer"));
//                supplier.setStatus(form.getInt("supplier.status"));
//                supplier.setHomePage(form.getString("supplier.homep"));
//                supplier.setEmail(form.getString("supplier.email"));
//                supplier.setIVF(form.getInt("supplier.cdivf"));
//                supplier.setIncentive(form.getInt("supplier.tpinc"));
//                supplier.setProductIncentiveValue(form.getFloat("supplier.vlipr"));
//                supplier.setBillingIncentiveValue(form.getFloat("supplier.vlibl"));
//                supplier.setSupplierIncentiveValue(form.getFloat("supplier.dvcsp"));
//                supplier.setPartnerIncentiveValue(form.getFloat("supplier.dvcpt"));
                
                /*
                 * inclui contatos
                 */
//                supplier.getContacts().clear();
//                for (int k = 0; k < contacts.getItensSize(); k++) {
//                    name = contacts.getStringValue("contact.rname", k);
//                    
//                    if (name.equals(""))
//                        continue;
//                    
//                    contact = new CustomerContact();
//                    
//                    contact.setCustomer(supplier);
//                    contact.setItem(k);
//                    contact.setName(name);
//                    contact.setFunction(contacts.getStringValue("contact.funct", k));
//                    
//                    supplier.getContacts().add(contact);
//                }
//                
//                /*
//                 * inclui endereços
//                 */
//                supplier.getAddresses().clear();
//                for (int k = 0; k < addresses.getItensSize(); k++) {
//                    name = addresses.getStringValue("address.logra", k);
//                    
//                    if (name.equals(""))
//                        continue;
//                    
//                    address = new CustomerAddress();
//                    
//                    address.setCustomer(supplier);
//                    address.setItem(k);
//                    address.setType(addresses.getIntValue("address.type", k));
//                    address.setAddress(name);
//                    address.setNumber(addresses.getIntValue("address.numer", k));
//                    address.setEstado(addresses.getStringValue("address.coduf", k));
//
//                    ufkey = "BRA"+address.getEstado();
//                    results = cities.get(ufkey);
//                    if (results == null) {
//                        results = model.select("sel_cities", new Object[] {ufkey});
//                        cities.put(ufkey, results);
//                    }
//
//                    name = addresses.getStringValue("address.munic", k);
//                    for (Object object : results) {
//                        city = (City)object;
//                        if (!city.getName().equals(name))
//                            continue;
//                        
//                        address.setMunicipio(city.getIdent());
//                        break;
//                    }
//                    
//                    supplier.getAddresses().add(address);
//                }
//                
//                /*
//                 * inclui horários
//                 */
//                supplier.getSchedule().clear();
//                loadSchedule(getTable("vschedule"), supplier, 0);
//                loadSchedule(getTable("dschedule"), supplier, 1);
                
                model.save(supplier);
                form.setInt("supplier.ident", supplier.getId());
                form.setDate("supplier.dtreg", supplier.getRegDate());
                form.setTime("supplier.tmreg", supplier.getRegTime());
                
                setMessage(EVE.status, "supplier.save.success");
                
                return;
            } catch (HibernateException ex) {
                setMessage(EVE.error, "supplier.save.error");
                ex.printStackTrace();                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

}
