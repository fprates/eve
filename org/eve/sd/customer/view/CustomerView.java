package org.eve.sd.customer.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eve.main.EVE;
import org.eve.model.AbstractDocument;
import org.eve.sd.customer.Customer;
import org.eve.sd.customer.CustomerSchedule;
import org.eve.view.AbstractView;
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
        Composite schedarea;
        TableAssist table;
        Controller controller = getController();
        AbstractDocument customer = (Customer)controller.getObject();
        AbstractDocument address = controller.getDocument("address");
        AbstractDocument contact = controller.getDocument("contact");
        AbstractDocument schedule = controller.getDocument("schedule");
        Form form = addForm("main");
        
        setWidth(1260);
        addAction("customer.create");
        addAction("customer.edit", false);
        addAction("customer.show", false);
        
        container.setLayout(new GridLayout(1, false));
        
        /*
         * Dados básicos
         */
        form.put(customer, "customer.ident");
        form.put(customer, "document.dtreg");
        form.put(customer, "document.tmreg");
        
        form.put(customer, "customer.refer");
        form.put(customer, "customer.name");
        form.put(customer, "customer.aname");
        form.put(customer, "customer.cnpj");
        form.put(customer, "customer.ie");
        form.putCombo(customer, "customer.tpest", 12);
        form.putCombo(customer, "customer.status", 7);
        form.putCombo(customer, "customer.tpinc", 7);
        
        form.put(customer, "customer.homep");
        form.put(customer, "customer.email");
        form.putCombo(customer, "customer.cdivf", 1);
        
        form.put(customer, "customer.vlipr");
        form.put(customer, "customer.vlibl");
        form.put(customer, "customer.dvcsp");
        form.put(customer, "customer.dvcpt");
        form.putSearch(customer, "customer.stdsp");
        
        form.putCombo(customer, "customer.tpcom", 8);
        
        form.define(container);
        
        bar = new ExpandBar(container, SWT.NONE);
        bar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        
        /*
         * Contatos
         */
        table = (TableAssist)addTable("contacts");
        table.setEditable(true);
        table.setVisibleLines(4);
        table.put(contact, "contact.type");
        table.put(contact, "contact.rname");
        table.put(contact, "contact.im");
        table.put(contact, "contact.funct");
        table.put(contact, "contact.telf1");
        table.put(contact, "contact.telf2");
        
        localcontainer = table.define(bar);
        itembar = new ExpandItem(bar, SWT.NONE, 0);
        itembar.setText(getMessage("customer.contacts"));
        itembar.setControl(localcontainer);
        itembar.setHeight(table.getHeight());
        itembar.setExpanded(true);
        
        /*
         * Endereços
         */
        table = (TableAssist)addTable("addresses");
        table.setEditable(true);
        table.setVisibleLines(4);
//        table.putAutoCombo("address.type", 11, new String[] {
//                getMessage("address.billing"),
//                getMessage("address.delivery"),
//                getMessage("address.charging")});
        
        table.put(address, "address.logra");
        table.put(address, "address.numer");
        table.put(address, "address.compl");
        table.put(address, "address.cdend");
//        table.putCombo(address, "address.coduf");
//        table.putSearch(address, "address.munic");
        table.setReference("address.munic", "address.coduf");
        
        localcontainer = table.define(bar);
        itembar = new ExpandItem(bar, SWT.NONE, 1);
        itembar.setText(getMessage("customer.addresses"));
        itembar.setControl(localcontainer);
        itembar.setHeight(table.getHeight());
        itembar.setExpanded(true);

        /*
         * Horários
         */        
        schedarea = new Composite(bar, SWT.NONE);
        schedarea.setLayout(new RowLayout(SWT.VERTICAL));

        table = addTable("schedule.visit");
        table.setEditable(true);
        table.setVisibleLines(2);
        table.put(schedule, "schedule.per");
        table.put(schedule, "schedule.mon");
        table.put(schedule, "schedule.tue");
        table.put(schedule, "schedule.wed");
        table.put(schedule, "schedule.thu");
        table.put(schedule, "schedule.fri");
        table.setColumnProperties("schedule.per", EVE.readonly);
        table.define(schedarea);
        table.setActionState("schedule.visit.insert", false);
        table.setActionState("schedule.visit.remove", false);
        table.setActionState("schedule.visit.update", true);

        table = addTable("schedule.delivery");
        table.setEditable(true);
        table.setVisibleLines(2);
        table.put(schedule, "schedule.per");
        table.put(schedule, "schedule.mon");
        table.put(schedule, "schedule.tue");
        table.put(schedule, "schedule.wed");
        table.put(schedule, "schedule.thu");
        table.put(schedule, "schedule.fri");
        table.setColumnProperties("schedule.per", EVE.readonly);
        table.define(schedarea);
        table.setActionState("schedule.delivery.insert", false);
        table.setActionState("schedule.delivery.remove", false);
        table.setActionState("schedule.delivery.update", true);
        
        itembar = new ExpandItem(bar, SWT.NONE, 2);
        itembar.setText(getMessage("customer.schedule"));
        itembar.setControl(schedarea);
        itembar.setHeight(schedarea.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
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
            tschedule.insert();
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
        CustomerSchedule schedule;
        Controller controller = getController();
        Form form = controller.getForm("main");
        TableAssist ctable = controller.getTable("contacts");
        TableAssist atable = controller.getTable("addresses");
        TableAssist vstable = controller.getTable("schedule.visit");
        TableAssist dstable = controller.getTable("schedule.delivery");
        
        form.copyFrom(customer);
        ctable.copyFrom(customer.getContacts());
        atable.copyFrom(customer.getAddresses());
        
        fillPeriodColumn(vstable);
        fillPeriodColumn(dstable);
        
        i = 0;
        for (AbstractDocument item: customer.getSchedule()) {
            schedule = (CustomerSchedule)item;
            
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
            form.setBlocked(customer, "customer.ident");
            form.setBlocked(customer, "customer.dtreg");
            form.setBlocked(customer, "customer.tmreg");
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
            form.setBlocked(customer, "customer.ident");
            form.setBlocked(customer, "customer.dtreg");
            form.setBlocked(customer, "customer.tmreg");
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
            form.setBlocked(customer, "customer.ident");
            form.setBlocked(customer, "customer.dtreg");
            form.setBlocked(customer, "customer.tmreg");
            form.commit();
            
            setControlLoad(customer);
            
            getController().getForm("main").clear();
            
            return;
        }
    }
}
