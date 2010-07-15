package org.eve.sd.customer.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eve.main.EVE;
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
        Composite schedule;
        Controller controller = getController();
        Form form = controller.getForm("main");
        TableAssist ctable = controller.getTable("contacts");
        TableAssist atable = controller.getTable("addresses");
        TableAssist vstable = controller.getTable("vschedule");
        TableAssist dstable = controller.getTable("dschedule");
        
        addAction("customer.create");
        addAction("customer.edit", false);
        addAction("customer.show", false);
        
        container.setLayout(new GridLayout(1, false));
        
        /*
         * Dados básicos
         */
        form.setLocale(getLocale());        
        form.put("customer.ident", 12, false);
        form.put("customer.name", 40);
        form.put("customer.aname", 40);
        form.put("customer.cnpj", 18);
        
        form.putCombo("customer.status", new String[] {
                getMessage("customer.active"),
                getMessage("customer.inactive")}, 7);
        
        form.putCombo("customer.tpinc", new String[] {
                getMessage("incentive.free"),
                getMessage("incentive.product"),
                getMessage("incentive.bill")}, 7);
        
        form.put("customer.homep", 128);
        form.put("customer.email", 128);
        form.putCombo("customer.cdivf", new String[] {
                "A", "B", "C"}, 1);
        
        form.put("customer.vlipr", 13);
        form.put("customer.vlibl", 7);
        form.put("customer.dvcsp", 7);
        form.put("customer.dvcpt", 7);
        
        form.define(container);
        
        bar = new ExpandBar(container, SWT.V_SCROLL);
        bar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        
        /*
         * Contatos
         */
        ctable.setLocale(getLocale());
        ctable.setLines(4);
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
        localcontainer.pack();
        
        /*
         * Endereços
         */
        atable.setLocale(getLocale());
        atable.setLines(3);
        atable.putCombo("address.type", new String[] {
                getMessage("address.billing"),
                getMessage("address.delivery"),
                getMessage("address.charging")});                
        atable.put("address.logra");
        atable.put("address.numer");
        atable.put("address.cdend");
        
        localcontainer = atable.define(bar, controller);
        aitembar = new ExpandItem(bar, SWT.NONE, 1);
        aitembar.setText(getMessage("customer.addresses"));
        aitembar.setHeight(localcontainer.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        aitembar.setControl(localcontainer);
        aitembar.setExpanded(true);
        localcontainer.pack();

        /*
         * Horários
         */        
        schedule = new Composite(bar, SWT.NONE);
        schedule.setLayout(new RowLayout(SWT.VERTICAL));
        
        vstable.setLocale(getLocale());
        vstable.setLines(2);
        vstable.setInsert(false);
        vstable.setRemove(false);
        vstable.put("schedule.per");
        vstable.put("schedule.mon");
        vstable.put("schedule.tue");
        vstable.put("schedule.wed");
        vstable.put("schedule.thu");
        vstable.put("schedule.fri");
        vstable.setColumnProperties("schedule.per", EVE.readonly);
        
        vstable.setName("schedule.visit");
        vstable.define(schedule, controller);
        
        
        dstable.setLocale(getLocale());
        dstable.setLines(2);
        dstable.setInsert(false);
        dstable.setRemove(false);
        dstable.put("schedule.per");
        dstable.put("schedule.mon");
        dstable.put("schedule.tue");
        dstable.put("schedule.wed");
        dstable.put("schedule.thu");
        dstable.put("schedule.fri");
        dstable.setColumnProperties("schedule.per", EVE.readonly);
        
        dstable.setName("schedule.delivery");
        dstable.define(schedule, controller);
        
        sitembar = new ExpandItem(bar, SWT.NONE, 2);
        sitembar.setText(getMessage("customer.schedule"));
        sitembar.setHeight(schedule.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        sitembar.setControl(schedule);
        sitembar.setExpanded(true);
        
        bar.pack();
        
        addButton("customer.save");
    }
    
    private final void fillPeriodColumn(TableAssist tschedule) {
        int i = 0;
        
        for (i = 0; i < 2; i++) {
            switch (i) {
            case 0:
                tschedule.setStringValue("schedule.per", i, getMessage("schedule.am"));
                break;
                
            case 1:
                tschedule.setStringValue("schedule.per", i, getMessage("schedule.pm"));
                break;
            }
        }        
    }
    
    private final void setControlLoad(Customer customer) {
        int i = 0;
        Controller controller = getController();
        Form form = controller.getForm("main");
        TableAssist ctable = controller.getTable("contacts");
        TableAssist atable = controller.getTable("addresses");
        TableAssist vstable = controller.getTable("vschedule");
        TableAssist dstable = controller.getTable("dschedule");
        
        form.setString("customer.aname", customer.getAlternateName());
        form.setString("customer.cnpj", customer.getCodCadFiscal());
        form.setInt("customer.ident", customer.getId());
        form.setString("customer.name", customer.getName());
        form.setInt("customer.status", customer.getStatus());
        form.setString("customer.homep", customer.getHomePage());
        form.setString("customer.email", customer.getEmail());
        form.setInt("customer.tpinc", customer.getIncentive());
        form.setInt("customer.cdivf", customer.getIVF());
        form.setFloat("customer.vlipr", customer.getProductIncentiveValue());
        form.setFloat("customer.vlibl", customer.getBillingIncentiveValue());
        form.setFloat("customer.dvcsp", customer.getSupplierIncentiveValue());
        form.setFloat("customer.dvcpt", customer.getPartnerIncentiveValue());
        
        
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
        
        fillPeriodColumn(vstable);
        fillPeriodColumn(dstable);
        
        i = 0;
        for (CustomerSchedule schedule : customer.getSchedule()) {
            switch (i) {
            case 0:
            case 1:                
                vstable.setTimeValue("schedule.mon", i, schedule.getMonday());
                vstable.setTimeValue("schedule.tue", i, schedule.getTuesday());
                vstable.setTimeValue("schedule.wed", i, schedule.getWednesday());
                vstable.setTimeValue("schedule.thu", i, schedule.getThursday());
                vstable.setTimeValue("schedule.fri", i, schedule.getFriday());
                break;
                
            case 2:
            case 3:                
                dstable.setTimeValue("schedule.mon", i-2, schedule.getMonday());
                dstable.setTimeValue("schedule.tue", i-2, schedule.getTuesday());
                dstable.setTimeValue("schedule.wed", i-2, schedule.getWednesday());
                dstable.setTimeValue("schedule.thu", i-2, schedule.getThursday());
                dstable.setTimeValue("schedule.fri", i-2, schedule.getFriday());
                break;
            }
            i++;
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
        TableAssist vschedule = controller.getTable("vschedule");
        TableAssist dschedule = controller.getTable("dschedule");
        
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
            vschedule.clear();
            vschedule.setEditable(false);
            dschedule.clear();
            dschedule.setEditable(false);
            
            for (FormComponent component : form.getComponents())
                component.setEnabled(false);
            
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
            vschedule.clear();
            vschedule.setEditable(true);
            dschedule.clear();
            dschedule.setEditable(true);

            for (FormComponent component : form.getComponents())
                component.setEnabled(component.isEnabled());
            
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
            vschedule.clear();
            vschedule.setEditable(true);
            dschedule.clear();
            dschedule.setEditable(true);

            for (FormComponent component : form.getComponents())
                component.setEnabled(component.isEnabled());
            
            setControlLoad(customer);
            
            getController().getForm("main").clear();
            return;
        }
    }

}
