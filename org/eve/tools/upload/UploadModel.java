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

public class UploadModel {
    private InputStream in;
    
    public final BufferedReader upload(String filename) throws FileNotFoundException, IOException {
        in = new FileInputStream(filename);
        
        return new BufferedReader(new InputStreamReader(in));
    }
    
    public final Set<Customer> getCustomers(BufferedReader reader) throws IOException, ParseException {
        Customer customer;
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
            
            if (args[48].length() == 0)
                args[48] = "0";
            
            customer.setIVF(Integer.parseInt(args[48]));
            customer.setTipoEstabelecimento(Integer.parseInt(args[50]));
            customer.setIncentive(Integer.parseInt(args[51]));
            customer.setStatus(0);
            customer.setReference("");
            
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
