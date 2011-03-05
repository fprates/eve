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
import org.eve.view.ComponentType;
import org.eve.view.Controller;
import org.eve.view.Form;
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
        Customer customer = (Customer)getController().getObject();
        Form form = addForm("main");
        
        setWidth(1260);
        addAction("customer.create");
        addAction("customer.edit", false);
        addAction("customer.show", false);
        
        container.setLayout(new GridLayout(1, false));
        
        /*
         * Dados básicos
         */
        form.put(customer, Customer.IDENT);
        form.put(customer, Customer.DTREG);
        form.put(customer, Customer.TMREG);
        
        form.put(customer, Customer.REFER);
        form.put(customer, Customer.NAME);
        form.put(customer, Customer.ANAME);
        form.put(customer, Customer.CNPJ);
        form.put(customer, Customer.IE);
        form.putCombo(customer, Customer.TPEST, 12);
        form.putCombo(customer, Customer.STATUS, 7);
        form.putCombo(customer, Customer.TPINC, 7);
        
        form.put(customer, Customer.HOMEP);
        form.put(customer, Customer.EMAIL);
        form.putCombo(customer, Customer.CDIVF, 1);
        
        form.put(customer, Customer.VLIPR);
        form.put(customer, Customer.VLIBL);
        form.put(customer, Customer.DVCSP);
        form.put(customer, Customer.DVCPT);
        form.putSearch(customer, Customer.STDSP);
        
        form.putCombo(customer, Customer.TPCOM, 8);
        
        form.define(container);
        
        bar = new ExpandBar(container, SWT.NONE);
        bar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        
        /*
         * Contatos
         */
        table = (TableAssist)addTable("contacts");
        table.setLines(4);
        table.putMark("contact.mark", ComponentType.MULTI);
        table.put("contact.type", 20);
        table.put("contact.rname", 40);
        table.put("contact.im", 60);
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
        table = (TableAssist)addTable("addresses");
        table.setLines(3);
        table.putMark("address.mark", ComponentType.MULTI);
        table.putAutoCombo("address.type", 11, new String[] {
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

        table = addTable("schedule.visit");
        table.setLines(2);
        table.put("schedule.per");
        table.put("schedule.mon");
        table.put("schedule.tue");
        table.put("schedule.wed");
        table.put("schedule.thu");
        table.put("schedule.fri");
        table.setColumnProperties("schedule.per", EVE.readonly);
        table.define(schedule);
        table.setActionState("schedule.visit.insert", false);
        table.setActionState("schedule.visit.remove", false);
        table.setActionState("schedule.visit.update", true);

        table = addTable("schedule.delivery");
        table.setLines(2);
        table.put("schedule.per");
        table.put("schedule.mon");
        table.put("schedule.tue");
        table.put("schedule.wed");
        table.put("schedule.thu");
        table.put("schedule.fri");
        table.setColumnProperties("schedule.per", EVE.readonly);
        table.define(schedule);
        table.setActionState("schedule.delivery.insert", false);
        table.setActionState("schedule.delivery.remove", false);
        table.setActionState("schedule.delivery.update", true);
        
        itembar = new ExpandItem(bar, SWT.NONE, 2);
        itembar.setText(getMessage("customer.schedule"));
        itembar.setControl(schedule);
        itembar.setHeight(schedule.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        itembar.setExpanded(true);
        
        addButton("save.command");
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
                tschedule.setString("schedule.per", i, getMessage("schedule.am"));
                break;
                
            case 1:
                tschedule.setString("schedule.per", i, getMessage("schedule.pm"));
                break;
            }
        }        
    }
    
    /**
     * 
     * @param customer
     */
    private final void setControlLoad(Customer customer) {
        int i;
        int munic;
        int munic_;
        Controller controller = getController();
        Map<Object, String> results;
        String id_;
        String name;
        Form form = controller.getForm("main");
        TableAssist ctable = controller.getTable("contacts");
        TableAssist atable = controller.getTable("addresses");
        TableAssist vstable = controller.getTable("schedule.visit");
        TableAssist dstable = controller.getTable("schedule.delivery");
        
        for (Object id : customer.getIds()) {
            if (id.equals(Customer.USREG))
                continue;
            
            id_ = (String)id;
            name = customer.getName(id_);
            
            form.setFieldValue(name, customer.getFieldValue(id_));
        }
        
        i = 0;
        for (CustomerContact contact : customer.getContacts()) {
            ctable.setString("contact.rname", i, contact.getName());
            ctable.setString("contact.type", i, contact.getType());
            ctable.setString("contact.im", i, contact.getInstantMessenger());
            ctable.setString("contact.funct", i++, contact.getFunction());
        }
        
        i = 0;
        for (CustomerAddress address : customer.getAddresses()) {
            atable.setString("address.logra", i, address.getAddress());
            atable.setInt("address.numer", i, address.getNumber());
            atable.setString("address.compl", i, address.getComplemento());
            atable.setInt("address.cdend", i, address.getCEP());
            atable.setString("address.coduf", i, address.getEstado());
            
            munic = address.getMunicipio();
            results = controller.getResults("address.munic", address.getEstado());
            if (results != null) {
                for (Object object : results.keySet()) {
                    munic_ = (Integer)object;
                    if (!(munic_ == munic))
                        continue;
                    
                    atable.setString("address.munic", i, results.get(object));
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
                vstable.setTime("schedule.mon", i, schedule.getMonday());
                vstable.setTime("schedule.tue", i, schedule.getTuesday());
                vstable.setTime("schedule.wed", i, schedule.getWednesday());
                vstable.setTime("schedule.thu", i, schedule.getThursday());
                vstable.setTime("schedule.fri", i, schedule.getFriday());
                break;
                
            case 2:
            case 3:                
                dstable.setTime("schedule.mon", i-2, schedule.getMonday());
                dstable.setTime("schedule.tue", i-2, schedule.getTuesday());
                dstable.setTime("schedule.wed", i-2, schedule.getWednesday());
                dstable.setTime("schedule.thu", i-2, schedule.getThursday());
                dstable.setTime("schedule.fri", i-2, schedule.getFriday());
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
        TableAssist vschedule = controller.getTable("schedule.visit");
        TableAssist dschedule = controller.getTable("schedule.delivery");
        
        form.clear();
        
        /*
         * Display mode component's configuration
         */
        if (action.equals("customer.show")) {
            setTitlebar("customer.show.title");
            setButtonVisible("save.command", false);
            contacts.clear();
            contacts.setEditable(false);
            addresses.clear();
            addresses.setEditable(false);
            vschedule.clear();
            vschedule.setEditable(false);
            dschedule.clear();
            dschedule.setEditable(false);
            
            form.setEditable(false);
            form.setBlocked(customer, Customer.IDENT);
            form.setBlocked(customer, Customer.DTREG);
            form.setBlocked(customer, Customer.TMREG);
            form.commit();
            
            setControlLoad(customer);
            
            return;
        }
        
        /*
         * Edit mode component's configuration
         */
        if (action.equals("customer.edit")) {
            setTitlebar("customer.edit.title");
            setButtonVisible("save.command", true);
            contacts.clear();
            contacts.setEditable(true);
            addresses.clear();
            addresses.setEditable(true);
            vschedule.clear();
            vschedule.setEditable(true);
            dschedule.clear();
            dschedule.setEditable(true);
            
            form.setEditable(true);
            form.setBlocked(customer, Customer.IDENT);
            form.setBlocked(customer, Customer.DTREG);
            form.setBlocked(customer, Customer.TMREG);
            form.commit();
            
            setControlLoad(customer);
                
            return;
        }
        
        /*
         * Creation mode component's configuration
         */
        if (action.equals("customer.create")) {            
            setTitlebar("customer.create.title");
            setButtonVisible("save.command", true);
            contacts.clear();
            contacts.setEditable(true);
            addresses.clear();
            addresses.setEditable(true);
            vschedule.clear();
            vschedule.setEditable(true);
            dschedule.clear();
            dschedule.setEditable(true);
            
            form.setEditable(true);
            form.setBlocked(customer, Customer.IDENT);
            form.setBlocked(customer, Customer.DTREG);
            form.setBlocked(customer, Customer.TMREG);
            form.commit();
            
            setControlLoad(customer);
            
            getController().getForm("main").clear();
            
            return;
        }
    }
}
