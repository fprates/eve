package org.eve.sd.customer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eve.view.AbstractView;
import org.eve.view.Controller;
import org.eve.view.Form;
import org.eve.view.FormComponent;
import org.eve.view.TableAssist;

public class CustomerView extends AbstractView {
    public CustomerView() { }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractView#defineView()
     */
    @Override
    public void defineView(Composite container) {
        Controller controller = getController();
        TabFolder main = new TabFolder(container, SWT.BORDER);
        TabItem base = new TabItem(main, SWT.NONE);
        TabItem contacts = new TabItem(main, SWT.NONE);
        TabItem addresses = new TabItem(main, SWT.NONE);
        Form form = controller.getForm("main");
        TableAssist ctable = controller.getTable("contacts");
        TableAssist atable = controller.getTable("addresses");
        
        addAction("customer.create");
        addAction("customer.edit", false);
        addAction("customer.show", false);
        
        form.setLocale(getLocale());        
        form.put("customer.ident", 12, false);
        form.put("customer.name", 40);
        form.put("customer.aname", 40);
        form.put("customer.cnpj", 18);
        form.put("customer.status", 1);
        
        base.setText(getMessage("customer.base"));
        base.setControl(form.define(main));
        
        ctable.setLocale(getLocale());
        ctable.put("contact.rname");
        ctable.put("contact.funct");
        ctable.put("contact.teln1");
        ctable.put("contact.teln2");
        
        contacts.setControl(ctable.define(main, controller));        
        contacts.setText(getMessage("customer.contacts"));
        
        atable.setLocale(getLocale());
        atable.put("address.logra");
        atable.put("address.numer");
        atable.put("address.cdend");
        
        addresses.setControl(atable.define(main, controller));
        addresses.setText(getMessage("customer.addresses"));
        
        addButton("customer.save");
    }
    
    private final void setControlLoad(Customer customer) {
        int i = 0;
        Controller controller = getController();
        Form form = controller.getForm("main");
        TableAssist ctable = controller.getTable("contacts");
        TableAssist atable = controller.getTable("addresses");
        
        form.setString("customer.aname", customer.getAlternateName());
        form.setString("customer.cnpj", customer.getCodCadNac());
//        customer_.setCreation(customer.getCreation());
        form.setInt("customer.ident", customer.getId());
        form.setString("customer.name", customer.getName());
        form.setInt("customer.status", customer.getStatus());
        
        ctable.clear();
        for (CustomerContact contact : customer.getContacts()) {
            ctable.setStringValue("contact.rname", i, contact.getName());
            ctable.setStringValue("contact.funct", i++, contact.getFunction());
        }
        
        i = 0;
        for (CustomerAddress address : customer.getAddresses()) {
            atable.setStringValue("address.logra", i, address.getAddress());
            atable.setIntValue("address.number", i++, address.getNumber());
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#reload(java.lang.String)
     */
    @Override
    public final void reload(String action) {
        Controller controller = getController();
        Customer customer = (Customer)controller.getObject();
        Form form = controller.getForm("main");
        TableAssist contacts = controller.getTable("contacts");
        TableAssist addresses = controller.getTable("addresses");
        
        /*
         * Display mode component's configuration
         */
        if (action.equals("customer.show")) {
//            setTitle("Exibir cliente");
            setButtonVisible("customer.save", false);
            contacts.clear();
            contacts.setEditable(false);
            addresses.clear();
            addresses.setEditable(false);
            
            for (FormComponent component : form.getComponents())
                component.getTextWidget().setEnabled(false);
            
            setControlLoad(customer);
            
            return;
        }
        
        /*
         * Edit mode component's configuration
         */
        if (action.equals("customer.edit")) {
//            setTitle("Editar cliente");            
            setButtonVisible("customer.save", true);
            contacts.clear();
            contacts.setEditable(true);
            addresses.clear();
            addresses.setEditable(true);

            for (FormComponent component : form.getComponents())
                component.getTextWidget().setEnabled(component.isEnabled());
            
            setControlLoad(customer);
                
            return;
        }
        
        /*
         * Creation mode component's configuration
         */
        if (action.equals("customer.create")) {            
//            setTitle("Criar cliente");
            setButtonVisible("customer.save", true);
            contacts.clear();
            contacts.setEditable(true);
            addresses.clear();
            addresses.setEditable(true);

            for (FormComponent component : form.getComponents())
                component.getTextWidget().setEnabled(component.isEnabled());
            
            getController().getForm("main").clear();
            return;
        }
    }

}
