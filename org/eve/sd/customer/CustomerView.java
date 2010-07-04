package org.eve.sd.customer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eve.view.AbstractView;
import org.eve.view.Form;
import org.eve.view.FormComponent;

public class CustomerView extends AbstractView {
    public CustomerView() { }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractView#defineView()
     */
    @Override
    public void defineView() {
        TabFolder main = new TabFolder(getContainer(), SWT.BORDER);
        TabItem base = new TabItem(main, SWT.NONE);
        TabItem contacts = new TabItem(main, SWT.NONE);
        Form form = getController().getForm("main");
        
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
//        
//        addTable("rname", "Nome");
//        addTable("funct", "Função");
//        addTable("teln1", "Telefone");
//        addTable("teln2", "Celular");
//        
//        contacts.setControl(defineTable("contacts", main));
//        
//        tableItem = new TableItem(contactsTable, SWT.NONE);
//        tableItem.setText(0, "teste1");
//        tableItem.setText(1, "teste2");

//        tableCol1.pack();
//        tableCol2.pack();
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
