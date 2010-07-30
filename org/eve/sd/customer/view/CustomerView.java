package org.eve.sd.customer.view;

import java.util.Map;

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
        ExpandItem itembar;
        Composite localcontainer;
        ExpandBar bar;
        Composite schedule;
        TableAssist table;
        Form form = addForm("main");
        
        setWidth(1260);
        addAction("customer.create");
        addAction("customer.edit", false);
        addAction("customer.show", false);
        
        container.setLayout(new GridLayout(1, false));
        
        /*
         * Dados básicos
         */
        form.put("customer.ident", 12, false);
        form.put("customer.dtreg", 10, false);
        form.put("customer.tmreg", 8, false);
        form.put("customer.refer", 12);
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
        
        bar = new ExpandBar(container, SWT.NONE);
        bar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        
        /*
         * Contatos
         */
        table = addTable("contacts");
        table.setLines(4);
        table.putMark("contact.mark", EVE.multi);
        table.put("contact.rname", 40);
        table.put("contact.funct", 20);
        table.put("contact.teln1", 12);
        table.put("contact.teln2", 12);
        
        localcontainer = table.define(bar);
        itembar = new ExpandItem(bar, SWT.NONE, 0);
        itembar.setText(getMessage("customer.contacts"));
        itembar.setControl(localcontainer);
        itembar.setHeight(localcontainer.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        itembar.setExpanded(true);
        
        /*
         * Endereços
         */
        table = addTable("addresses");
        table.setLines(3);
        table.putMark("address.mark", EVE.multi);
        table.putCombo("address.type", 11, new String[] {
                getMessage("address.billing"),
                getMessage("address.delivery"),
                getMessage("address.charging")});
        
        table.put("address.logra", 60);
        table.put("address.numer", 5);
        table.put("address.compl", 8);
        table.put("address.cdend", 8);
        table.putCombo("address.coduf", 2, null);
        table.putCombo("address.munic", 40, null);
        table.setReference("address.munic", "address.coduf");
        
        localcontainer = table.define(bar);
        itembar = new ExpandItem(bar, SWT.NONE, 1);
        itembar.setText(getMessage("customer.addresses"));
        itembar.setControl(localcontainer);
        itembar.setHeight(localcontainer.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        itembar.setExpanded(true);

        /*
         * Horários
         */        
        schedule = new Composite(bar, SWT.NONE);
        schedule.setLayout(new RowLayout(SWT.VERTICAL));

        table = addTable("vschedule");
        table.setLines(2);
        table.setInsert(false);
        table.setRemove(false);
        table.put("schedule.per");
        table.put("schedule.mon");
        table.put("schedule.tue");
        table.put("schedule.wed");
        table.put("schedule.thu");
        table.put("schedule.fri");
        table.setColumnProperties("schedule.per", EVE.readonly);
        table.setName("schedule.visit");
        table.define(schedule);

        table = addTable("dschedule");
        table.setLines(2);
        table.setInsert(false);
        table.setRemove(false);
        table.put("schedule.per");
        table.put("schedule.mon");
        table.put("schedule.tue");
        table.put("schedule.wed");
        table.put("schedule.thu");
        table.put("schedule.fri");
        table.setColumnProperties("schedule.per", EVE.readonly);
        table.setName("schedule.delivery");
        table.define(schedule);
        
        itembar = new ExpandItem(bar, SWT.NONE, 2);
        itembar.setText(getMessage("customer.schedule"));
        itembar.setControl(schedule);
        itembar.setHeight(schedule.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        itembar.setExpanded(true);
        
        addButton("customer.save");
    }
    
    /**
     * 
     * @param tschedule
     */
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
    
    /**
     * 
     * @param customer
     */
    private final void setControlLoad(Customer customer) {
        int munic;
        int munic_;
        Map<Object, String> results;
        int i = 0;
        Controller controller = getController();
        Form form = controller.getForm("main");
        TableAssist ctable = controller.getTable("contacts");
        TableAssist atable = controller.getTable("addresses");
        TableAssist vstable = controller.getTable("vschedule");
        TableAssist dstable = controller.getTable("dschedule");
        
        form.setString("customer.refer", customer.getReference());
        form.setDate("customer.dtreg", customer.getRegDate());
        form.setTime("customer.tmreg", customer.getRegTime());
        form.setString("customer.aname", customer.getAlternateName());
        form.setString("customer.cnpj", customer.getCodCadFiscal());
        form.setInt("customer.ident", customer.getId());
        form.setString("customer.name", customer.getName());
        form.setInt("customer.status", customer.getStatus());
        form.setString("customer.homep", customer.getHomePage());
        form.setProperties("customer.homep", EVE.nocase);
        form.setString("customer.email", customer.getEmail());
        form.setProperties("customer.email", EVE.nocase);
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
            atable.setIntValue("address.numer", i, address.getNumber());
            atable.setStringValue("address.compl", i, address.getComplemento());
            atable.setStringValue("address.cdend", i, address.getCEP());
            atable.setStringValue("address.coduf", i, address.getEstado());
            
            munic = address.getMunicipio();
            results = controller.getResults("address.munic", address.getEstado());
            if (results != null) {
                for (Object object : results.keySet()) {
                    munic_ = (Integer)object;
                    if (!(munic_ == munic))
                        continue;
                    
                    atable.setStringValue("address.munic", i, results.get(object));
                    break;
                }
                i++;
            }
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
            setTitlebar("customer.show.title");
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
            setTitlebar("customer.edit.title");
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
            setTitlebar("customer.create.title");
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
