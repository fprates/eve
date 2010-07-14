package org.eve.sd.supplier;

import org.eclipse.swt.widgets.Composite;
import org.eve.view.AbstractView;

public class SupplierView extends AbstractView {

    public SupplierView() { }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractView#defineView(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void defineView(Composite container) {
        addAction("supplier.create");
        addAction("supplier.edit", false);
        addAction("supplier.show", false);        
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
