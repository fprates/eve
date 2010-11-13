package org.eve.tools.upload;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.eve.main.EVE;
import org.eve.model.Model;
import org.eve.sd.customer.Customer;
import org.eve.view.AbstractController;
import org.eve.view.Form;

public class UploadController extends AbstractController {
    private UploadModel uploadmodel;
    private Model customermodel;
    
    public void setUploadModel(UploadModel uploadmodel) {
        this.uploadmodel = uploadmodel;
    }
    
    public void setCustomerModel(Model customermodel) {
        this.customermodel = customermodel;
    }
    
    @Override
    public void userInput(String input) {
        BufferedReader reader;
        Set<Customer> customers;
        Form form = getForm("main");
        Upload upload = (Upload)getObject();
        
        if (input.equals("upload.start")) {
            for (Object id : upload.getIds())
                upload.setFieldValue((String)id, form.getFieldValue(upload, (String)id));
            
            if ((upload.getDocument() == 0) || (upload.getFilename().equals(""))) {
                setMessage(EVE.error, "obligatory.parameter");
                return;
            }
            
            try {
                reader = uploadmodel.upload(upload.getFilename());
            } catch (FileNotFoundException ex) {
                setMessage(EVE.error, "file.not.found");
                ex.printStackTrace();
                return;
                
            } catch (IOException ex) {
                setMessage(EVE.error, "io.error");
                ex.printStackTrace();
                return;
                
            }
            
            try {
                switch(upload.getDocument()) {
                case 1:
                    customers = uploadmodel.getCustomers(reader);
                    customermodel.insert(customers);
                    
                    break;
//                    
//                case 2:
//                    model.suppliersSave(reader);
//                    break;
//                    
//                case 3:
//                    model.materialsSave(reader);
//                    break;
                }
            } catch (IOException ex) {
                setMessage(EVE.error, "file.read.error");
                ex.printStackTrace();
                return;
            } catch (java.text.ParseException ex) {
                setMessage(EVE.error, "invalid.data.format");
                ex.printStackTrace();
                return;
            }
            
            setMessage(EVE.status, "file.read.ok");
        }
    }

}
