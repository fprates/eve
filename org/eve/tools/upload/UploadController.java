package org.eve.tools.upload;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.eve.main.EVE;
import org.eve.mm.material.Material;
import org.eve.model.Model;
import org.eve.sd.customer.Customer;
import org.eve.sd.supplier.Supplier;
import org.eve.view.AbstractController;
import org.eve.view.Form;
import org.hibernate.HibernateException;

public class UploadController extends AbstractController {
    private UploadModel uploadmodel;
    private Model customermodel;
    private Model materialmodel;
    
    public void setUploadModel(UploadModel uploadmodel) {
        this.uploadmodel = uploadmodel;
    }
    
    public void setCustomerModel(Model customermodel) {
        this.customermodel = customermodel;
    }
    
    public void setMaterialModel(Model materialmodel) {
        this.materialmodel = materialmodel;
    }
    
    @Override
    public void userInput(String input) {
        BufferedReader reader;
        Set<Customer> customers;
        Set<Material> materials;
        Set<Supplier> suppliers;
        Form form = getForm("main");
        Upload upload = (Upload)getObject();
        
        if (input.equals("upload.start")) {
            for (Object id : upload.getIds())
                upload.setFieldValue((String)id, form.getFieldValue(upload, (String)id));
            
            if (upload.getDocument() == 0) {
                form.setFocus("upload.document");
                setMessage(EVE.error, "obligatory.parameter");
                return;
            }
            
            if (upload.getFilename().equals("")) {
                form.setFocus("upload.filename");
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
                    
                case 2:
                    suppliers = uploadmodel.getSuppliers(reader);
                    
                    break;
                    
                case 3:
                    materials = uploadmodel.getMaterials(reader);
                    materialmodel.insert(materials);
                    
                    break;
                }
            } catch (IOException ex) {
                setMessage(EVE.error, "file.read.error");
                ex.printStackTrace();
                return;
                
            } catch (java.text.ParseException ex) {
                setMessage(EVE.error, "invalid.data.format");
                ex.printStackTrace();
                return;
                
            } catch (HibernateException ex) {
                setMessage(EVE.error, "insert.error");
                ex.printStackTrace();
                return;
            } catch (NumberFormatException ex) {
                setMessage(EVE.error, "invalid.data.format");
                ex.printStackTrace();
                return;
            }
            
            setMessage(EVE.status, "file.upload.ok");
        }
    }

}
