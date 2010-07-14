package org.eve.sd.customer.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eve.sd.customer.Customer;
import org.eve.sd.customer.CustomerAddress;
import org.eve.sd.customer.CustomerContact;
import org.eve.sd.customer.CustomerSchedule;
import org.eve.view.AbstractView;
import org.eve.view.Controller;
import org.eve.view.Form;
import org.eve.view.FormComponent;
import org.eve.view.TableAssist;

public class CustomerView extends AbstractView {
    public CustomerView() { }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractView#defineView(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void defineView(Composite container) {
        ExpandItem citembar;
        ExpandItem aitembar;
        ExpandItem sitembar;
        Composite localcontainer;
        ExpandBar bar;
        Controller controller = getController();
        Form form = controller.getForm("main");
        TableAssist ctable = controller.getTable("contacts");
        TableAssist atable = controller.getTable("addresses");
        TableAssist stable = controller.getTable("schedule");
        
        addAction("customer.create");
        addAction("customer.edit", false);
        addAction("customer.show", false);
        
        container.setLayout(new GridLayout(1, false));
        
        form.setLocale(getLocale());        
        form.put("customer.ident", 12, false);
        form.put("customer.name", 40);
        form.put("customer.aname", 40);
        form.put("customer.cnpj", 18);
        form.put("customer.status", 1);
        form.define(container);
        
        bar = new ExpandBar(container, SWT.V_SCROLL);
        bar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        
        ctable.setLocale(getLocale());
        ctable.put("contact.rname");
        ctable.put("contact.funct");
        ctable.put("contact.teln1");
        ctable.put("contact.teln2");
        
        localcontainer = ctable.define(bar, controller);
        citembar = new ExpandItem(bar, SWT.NONE, 0);
        citembar.setText(getMessage("customer.contacts"));
        citembar.setHeight(localcontainer.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        citembar.setControl(localcontainer);
        citembar.setExpanded(true);
        
        atable.setLocale(getLocale());
        atable.put("address.logra");
        atable.put("address.numer");
        atable.put("address.cdend");
        
        localcontainer = atable.define(bar, controller);
        aitembar = new ExpandItem(bar, SWT.NONE, 1);
        aitembar.setText(getMessage("customer.addresses"));
        aitembar.setHeight(localcontainer.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        aitembar.setControl(localcontainer);
        aitembar.setExpanded(true);

        stable.setLocale(getLocale());
        stable.setLines(4);
        stable.setInsert(false);
        stable.setRemove(false);
        stable.putCombo("schedule.typ", new String[] {"Entrega", "Visita"});
        stable.put("schedule.mon");
        stable.put("schedule.tue");
        stable.put("schedule.wed");
        stable.put("schedule.thu");
        stable.put("schedule.fri");
        stable.put("schedule.sat");
        stable.put("schedule.sun");
        
        localcontainer = stable.define(bar, controller);
        sitembar = new ExpandItem(bar, SWT.NONE, 2);
        sitembar.setText(getMessage("customer.schedule"));
        sitembar.setHeight(localcontainer.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        sitembar.setControl(stable.define(bar, controller));
        sitembar.setExpanded(true);
        
        bar.pack();
        
        addButton("customer.save");
    }
    
    private final void setControlLoad(Customer customer) {
        int i = 0;
        Controller controller = getController();
        Form form = controller.getForm("main");
        TableAssist ctable = controller.getTable("contacts");
        TableAssist atable = controller.getTable("addresses");
        TableAssist stable = controller.getTable("schedule");
        
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
            atable.setIntValue("address.numer", i++, address.getNumber());
        }
        
        i = 0;
        for (CustomerSchedule schedule : customer.getSchedule()) {
            stable.setIntValue("schedule.typ", i, schedule.getType());
            stable.setTimeValue("schedule.mon", i, schedule.getMonday());
            stable.setTimeValue("schedule.tue", i, schedule.getTuesday());
            stable.setTimeValue("schedule.wed", i, schedule.getWednesday());
            stable.setTimeValue("schedule.thu", i, schedule.getThursday());
            stable.setTimeValue("schedule.fri", i, schedule.getFriday());
            stable.setTimeValue("schedule.sat", i, schedule.getSaturday());
            stable.setTimeValue("schedule.sun", i++, schedule.getSunday());
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
        TableAssist schedule = controller.getTable("schedule");
        
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
            schedule.clear();
            schedule.setEditable(false);
            
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
            schedule.clear();
            schedule.setEditable(true);

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
            schedule.clear();
            schedule.setEditable(true);

            for (FormComponent component : form.getComponents())
                component.getTextWidget().setEnabled(component.isEnabled());
            
            getController().getForm("main").clear();
            return;
        }
    }

}
