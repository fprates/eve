package org.eve.sd.customer;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eve.view.AbstractView;
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
        
        addAction("customer.create");
        addAction("customer.edit", false);
        addAction("customer.show", false);
        
        addForm("customer.ident", 12, false);
        addForm("customer.name", 40);
        addForm("customer.aname", 40);
        addForm("customer.cnpj", 18);
        addForm("customer.status", 1);
        
        base.setText("Base");
        base.setControl(defineForm("main", main));
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
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#reload(java.lang.String)
     */
    @Override
    public final void reload(String action) {
        FormComponent component;
        Map<String, FormComponent>form = getForm("main");
        
        /*
         * Display mode component's configuration
         */
        if (action.equals("customer.show")) {
//            setTitle("Exibir cliente");
            setButtonVisible("customer.save", false);
            
            for (String field : form.keySet()) {
                component = form.get(field);
                component.getTextWidget().setEnabled(false);
            }
            
            return;
        }
        
        /*
         * Edit mode component's configuration
         */
        if (action.equals("customer.edit")) {
//            setTitle("Editar cliente");            
            setButtonVisible("customer.save", true);
            
            for (String field : form.keySet()) {
                component = form.get(field);
                component.getTextWidget().setEnabled(component.isEnabled());
            }
                
            return;
        }
        
        /*
         * Creation mode component's configuration
         */
        if (action.equals("customer.create")) {            
//            setTitle("Criar cliente");
            setButtonVisible("customer.save", true);
            
            for (String field : form.keySet()) {
                component = form.get(field);
                component.getTextWidget().setEnabled(component.isEnabled());
            }
            
            return;
        }
    }

}
