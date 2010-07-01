package org.eve.sd.customer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eve.view.AbstractView;

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
        
//        addForm("ident", "Código", 12, false);
//        addForm("name", "Razão social", 40);
//        addForm("aname", "Nome fantasia", 40);
//        addForm("cnpj", "CNPJ", 18);
//        addForm("status", "Status", 1);
        
        base.setText("Base");
//        base.setControl(defineForm(main));
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
        
//        addButton("save", "Salvar");
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#reload(java.lang.String)
     */
    @Override
    public final void reload(String action) {
//        Customer customer;
//        FormComponent component;
//        Map<String, FormComponent> form = getForm();
//        
//        /*
//         * Display mode component's configuration
//         */
//        if (action.equals("customer.show")) {
//            setTitle("Exibir cliente");
//            setButtonVisible("save", false);
//            
//            for (String field : form.keySet()) {
//                component = form.get(field);
//                component.getText().setEnabled(false);
//            }
//            
//            customer = (Customer)model.getObject();
//            setIntForm("ident", customer.getId());
//            setStringForm("name", customer.getName());
//            setStringForm("aname", customer.getAlternateName());
//            setIntForm("status", customer.getStatus());
//            
//            return;
//        }
//        
//        /*
//         * Edit mode component's configuration
//         */
//        if (action.equals("customer.edit")) {
//            setTitle("Editar cliente");            
//            setButtonVisible("save", true);
//            
//            for (String field : form.keySet()) {
//                component = form.get(field);
//                component.getText().setEnabled(component.isEnabled());
//            }
//            
//            customer = (Customer)model.getObject();
//            setIntForm("ident", customer.getId());
//            setStringForm("name", customer.getName());
//            setStringForm("aname", customer.getAlternateName());
//            setIntForm("status", customer.getStatus());
//                
//            return;
//        }
//        
//        /*
//         * Creation mode component's configuration
//         */
//        if (action.equals("customer.create")) {            
//            setTitle("Criar cliente");
//            setButtonVisible("save", true);
//            
//            for (String field : form.keySet()) {
//                component = form.get(field);
//                component.getText().setEnabled(component.isEnabled());
//            }
//            
//            setIntForm("ident", 0);
//            setStringForm("name", "");
//            setStringForm("aname", "");
//            setIntForm("status", 0);
//            
//            return;
//        }
    }

}
