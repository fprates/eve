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
    public void defineView() {
        Controller controller = getController();
        Composite container = controller.getContainer();
        TabFolder main = new TabFolder(container, SWT.BORDER);
        TabItem base = new TabItem(main, SWT.NONE);
        TabItem contacts = new TabItem(main, SWT.NONE);
        Form form = controller.getForm("main");
        TableAssist ctable = controller.getTable("contacts");
        
        addAction("customer.create");
        addAction("customer.edit", false);
        addAction("customer.show", false);
        
        form.setLocale(getLocale());        
        form.put("customer.ident", 12, false);
        form.put("customer.name", 40);
        form.put("customer.aname", 40);
        form.put("customer.cnpj", 18);
        form.put("customer.status", 1);
        
        base.setText("Base");
        base.setControl(form.define(main));
        
        ctable.setLocale(getLocale());
        ctable.put("contact.rname");
        ctable.put("contact.funct");
        ctable.put("contact.teln1");
        ctable.put("contact.teln2");
        
        contacts.setControl(ctable.define(main, controller));        
        contacts.setText("Contatos");
        
        addButton("customer.save");
    }
    
    private final void setControlLoad(Customer customer) {
        Form form = getController().getForm("main");
        
        form.setString("customer.aname", customer.getAlternateName());
        form.setString("customer.cnpj", customer.getCodCadNac());
//        customer_.setCreation(customer.getCreation());
        form.setInt("customer.ident", customer.getId());
        form.setString("customer.name", customer.getName());
        form.setInt("customer.status", customer.getStatus());
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#reload(java.lang.String)
     */
    @Override
    public final void reload(String action) {
        Customer customer = (Customer)getController().getObject();
        Form form = getController().getForm("main");
        
        /*
         * Display mode component's configuration
         */
        if (action.equals("customer.show")) {
//            setTitle("Exibir cliente");
            setButtonVisible("customer.save", false);
            
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

            for (FormComponent component : form.getComponents())
                component.getTextWidget().setEnabled(component.isEnabled());
            
            getController().getForm("main").clear();
            return;
        }
    }

}
