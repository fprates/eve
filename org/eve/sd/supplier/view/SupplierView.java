package org.eve.sd.supplier.view;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eve.sd.supplier.Supplier;
import org.eve.view.AbstractView;
import org.eve.view.Controller;
import org.eve.view.Form;

public class SupplierView extends AbstractView {

    public SupplierView() { }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractView#defineView(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void defineView(Composite container) {
        Form form = addForm("main");
        Supplier supplier = (Supplier)getController().getObject();
        
        setWidth(1260);
        addAction("supplier.create");
        addAction("supplier.edit", false);
        addAction("supplier.show", false);
        
        container.setLayout(new GridLayout(1, false));
        
        /*
         * Dados básicos
         */
        form.put(supplier, Supplier.IDENT);
        form.put(supplier, Supplier.DTREG);
        form.put(supplier, Supplier.TMREG);

        form.put(supplier, Supplier.REFER);
        form.put(supplier, Supplier.NAME);
        form.put(supplier, Supplier.ANAME);
//        form.put(supplier, Supplier.CNPJ);
//        
//        form.putCombo(supplier, Supplier.STATUS, 7);
        
        form.define(container);
        
        addButton("save.command");
    }
    
    /**
     * 
     * @param customer
     */
    private final void setControlLoad(Supplier supplier) {
        String id_;
        String name;
        Controller controller = getController();
        Form form = controller.getForm("main");
        
        for (Object id : supplier.getIds()) {
            if (id.equals(Supplier.USREG))
                continue;
            
            id_ = (String)id;
            name = supplier.getName(id_);
            
            form.setFieldValue(name, supplier.getFieldValue(id_));
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#reload(java.lang.String)
     */
    @Override
    public final void reload(String action) {
        Controller controller = getController();
        Supplier supplier = (Supplier)controller.getObject();
        Form form = controller.getForm("main");
        
        /*
         * Display mode component's configuration
         */
        if (action.equals("supplier.show")) {
            setTitlebar("supplier.show.title");
            setButtonVisible("save.command", false);
            
            form.setEditable(false);
            form.setBlocked("supplier.ident");
            form.setBlocked("supplier.dtreg");
            form.setBlocked("supplier.tmreg");
            form.commit();
            
            setControlLoad(supplier);
            
            return;
        }
        
        /*
         * Edit mode component's configuration
         */
        if (action.equals("supplier.edit")) {
            setTitlebar("supplier.edit.title");
            setButtonVisible("save.command", true);
            
            form.setEditable(true);
            form.setBlocked("supplier.ident");
            form.setBlocked("supplier.dtreg");
            form.setBlocked("supplier.tmreg");
            form.commit();
            
            setControlLoad(supplier);
                
            return;
        }
        
        /*
         * Creation mode component's configuration
         */
        if (action.equals("supplier.create")) {
            setTitlebar("supplier.create.title");
            setButtonVisible("save.command", true);
            
            setControlLoad(supplier);
            form.setBlocked("supplier.ident");
            form.setBlocked("supplier.dtreg");
            form.setBlocked("supplier.tmreg");
            form.commit();
            
            getController().getForm("main").clear();
            
            return;
        }
    }
}
