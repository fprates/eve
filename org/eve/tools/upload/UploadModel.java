package org.eve.tools.upload;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.TreeSet;

import org.eve.sd.customer.Customer;
import org.eve.sd.customer.CustomerAddress;
import org.eve.sd.customer.CustomerContact;

public class UploadModel {
    private InputStream in;
    
    private int getInteger(String arg) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    public final BufferedReader upload(String filename) throws FileNotFoundException, IOException {
        in = new FileInputStream(filename);
        
        return new BufferedReader(new InputStreamReader(in));
    }
    
    public final Set<Customer> getCustomers(BufferedReader reader) throws IOException, ParseException {
        Customer customer;
        CustomerAddress address;
        CustomerContact contact;
        Set<Customer> customers;
        String[] args;
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        String line = null;
        
        customers = new TreeSet<Customer>();
        
        while(true) {
            line = reader.readLine();
            if (line == null)
                break;
            
            customer = new Customer();
            args = line.split(";");
            customer.setId(Integer.parseInt(args[0]));
            if (args[1].length() == 0)
                args[1] = "0";
            
            customer.setCodCadFiscal(Long.parseLong(args[1]));
            customer.setInscricaoEstadual(args[2]);
            customer.setName(args[3]);
            customer.setAlternateName(args[4]);
            customer.setHomePage(args[14]);
            customer.setEmail(args[15]);
            customer.setRegDate(format.parse(args[45]));
            customer.setIVF(getInteger(args[48]));
            customer.setTipoEstabelecimento(Integer.parseInt(args[50]));
            customer.setIncentive(Integer.parseInt(args[51]));
            customer.setStatus(0);
            customer.setReference("");
            
            address = new CustomerAddress();
            
            address.setCustomer(customer);
            address.setAddress(args[5]);
            address.setNumber(getInteger(args[6]));
            address.setComplemento(args[7]);
//            address.setMunicipio(args[8]);
            address.setLocalidade(args[10]);
            address.setEstado(args[9]);
            address.setCEP(getInteger(args[11]));
            address.setType(0);
            
            customer.getAddresses().add(address);
            
            contact = new CustomerContact();
            contact.setCustomer(customer);
            contact.setItem(0);
            contact.setType("CONTATO");
            contact.setInstantMessenger(args[16]);
            contact.setName(args[17]);
            contact.setFunction(args[18]);
            customer.getContacts().add(contact);
            
            contact = new CustomerContact();
            contact.setCustomer(customer);
            contact.setItem(1);
            contact.setType("RESPONSÁVEL COMPRAS");
            contact.setInstantMessenger("");
            contact.setName(args[19]);
            contact.setFunction(args[20]);
            customer.getContacts().add(contact);
            
            customers.add(customer);
        }
        
        in.close();
        return customers;
    }
    
    public final void suppliersSave(Reader save) {
        
    }
    
    public final void materialsSave(Reader save) {
        
    }
}
