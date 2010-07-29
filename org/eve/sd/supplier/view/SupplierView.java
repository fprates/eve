package org.eve.sd.supplier.view;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eve.view.AbstractView;
import org.eve.view.Controller;
import org.eve.view.Form;
import org.eve.view.FormComponent;

public class SupplierView extends AbstractView {

    public SupplierView() { }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractView#defineView(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void defineView(Composite container) {
        Form form = addForm("main");
        
        setWidth(1260);
        addAction("supplier.create");
        addAction("supplier.edit", false);
        addAction("supplier.show", false);
        
        container.setLayout(new GridLayout(1, false));
        
        /*
         * Dados básicos
         */
        form.put("supplier.ident", 12, false);
        form.put("supplier.dtreg", 10, false);
        form.put("supplier.tmreg", 8, false);
        form.put("supplier.name", 40);
        form.put("supplier.aname", 40);
        form.put("supplier.cnpj", 18);
        
        form.putCombo("supplier.status", new String[] {
                getMessage("supplier.active"),
                getMessage("supplier.inactive")}, 7);
        
        form.define(container);
        
        addButton("supplier.save");
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#reload(java.lang.String)
     */
    @Override
    public final void reload(String action) {
        Controller controller = getController();
        Form form = controller.getForm("main");
        
        /*
         * Display mode component's configuration
         */
        if (action.equals("supplier.show")) {
            setTitlebar("supplier.show.title");
            setButtonVisible("supplier.save", false);
            
            for (FormComponent component : form.getComponents())
                component.setEnabled(false);
            
            return;
        }
        
        /*
         * Edit mode component's configuration
         */
        if (action.equals("supplier.edit")) {
            setTitlebar("supplier.edit.title");
            setButtonVisible("supplier.save", true);

            for (FormComponent component : form.getComponents())
                component.setEnabled(component.isEnabled());
                
            return;
        }
        
        /*
         * Creation mode component's configuration
         */
        if (action.equals("supplier.create")) {
            setTitlebar("supplier.create.title");
            setButtonVisible("supplier.save", true);

            for (FormComponent component : form.getComponents())
                component.setEnabled(component.isEnabled());
            
            getController().getForm("main").clear();
            
            return;
        }
    }
}
