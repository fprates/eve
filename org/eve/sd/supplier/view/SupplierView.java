package org.eve.sd.supplier.view;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eve.sd.supplier.Supplier;
import org.eve.view.AbstractView;
import org.eve.view.Component;
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
        form.put("supplier.refer", 12);
        form.put("supplier.name", 40);
        form.put("supplier.aname", 40);
        form.put("supplier.cnpj", 18);
        
        form.putCombo("supplier.status", new String[] {
                getMessage("supplier.active"),
                getMessage("supplier.inactive")}, 7);
        
        form.define(container);
        
        addButton("supplier.save");
    }
    
    /**
     * 
     * @param customer
     */
    private final void setControlLoad(Supplier supplier) {
//        int munic;
//        int munic_;
//        Map<Object, String> results;
//        int i = 0;
        Controller controller = getController();
        Form form = controller.getForm("main");
//        TableAssist ctable = controller.getTable("contacts");
//        TableAssist atable = controller.getTable("addresses");
//        TableAssist vstable = controller.getTable("vschedule");
//        TableAssist dstable = controller.getTable("dschedule");
        form.setString("supplier.refer", supplier.getReference());
        form.setDate("supplier.dtreg", supplier.getRegDate());
        form.setTime("supplier.tmreg", supplier.getRegTime());
        form.setString("supplier.aname", supplier.getAlternateName());
//        form.setString("supplier.cnpj", supplier.getCodCadFiscal());
        form.setInt("supplier.ident", supplier.getId());
        form.setString("supplier.name", supplier.getName());
//        form.setInt("supplier.status", supplier.getStatus());
//        form.setString("supplier.homep", supplier.getHomePage());
//        form.setProperties("supplier.homep", EVE.nocase);
//        form.setString("supplier.email", supplier.getEmail());
//        form.setProperties("supplier.email", EVE.nocase);
//        form.setInt("supplier.tpinc", supplier.getIncentive());
//        form.setInt("supplier.cdivf", supplier.getIVF());
//        form.setFloat("supplier.vlipr", supplier.getProductIncentiveValue());
//        form.setFloat("supplier.vlibl", supplier.getBillingIncentiveValue());
//        form.setFloat("supplier.dvcsp", supplier.getSupplierIncentiveValue());
//        form.setFloat("supplier.dvcpt", supplier.getPartnerIncentiveValue());        
//        
//        ctable.clear();
//        for (SupplierContact contact : supplier.getContacts()) {
//            ctable.setStringValue("contact.rname", i, contact.getName());
//            ctable.setStringValue("contact.funct", i++, contact.getFunction());
//        }
//        
//        i = 0;
//        for (SupplierAddress address : supplier.getAddresses()) {
//            atable.setStringValue("address.logra", i, address.getAddress());
//            atable.setIntValue("address.numer", i, address.getNumber());
//            atable.setStringValue("address.compl", i, address.getComplemento());
//            atable.setStringValue("address.cdend", i, address.getCEP());
//            atable.setStringValue("address.coduf", i, address.getEstado());
//            
//            munic = address.getMunicipio();
//            results = controller.getResults("address.munic", address.getEstado());
//            if (results != null) {
//                for (Object object : results.keySet()) {
//                    munic_ = (Integer)object;
//                    if (!(munic_ == munic))
//                        continue;
//                    
//                    atable.setStringValue("address.munic", i, results.get(object));
//                    break;
//                }
//                i++;
//            }
//        }
//        
//        fillPeriodColumn(vstable);
//        fillPeriodColumn(dstable);
//        
//        i = 0;
//        for (SupplierSchedule schedule : supplier.getSchedule()) {
//            switch (i) {
//            case 0:
//            case 1:                
//                vstable.setTimeValue("schedule.mon", i, schedule.getMonday());
//                vstable.setTimeValue("schedule.tue", i, schedule.getTuesday());
//                vstable.setTimeValue("schedule.wed", i, schedule.getWednesday());
//                vstable.setTimeValue("schedule.thu", i, schedule.getThursday());
//                vstable.setTimeValue("schedule.fri", i, schedule.getFriday());
//                break;
//                
//            case 2:
//            case 3:                
//                dstable.setTimeValue("schedule.mon", i-2, schedule.getMonday());
//                dstable.setTimeValue("schedule.tue", i-2, schedule.getTuesday());
//                dstable.setTimeValue("schedule.wed", i-2, schedule.getWednesday());
//                dstable.setTimeValue("schedule.thu", i-2, schedule.getThursday());
//                dstable.setTimeValue("schedule.fri", i-2, schedule.getFriday());
//                break;
//            }
//            i++;
//        }
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
            setButtonVisible("supplier.save", false);
            
            for (Component component : form.getComponents()) {
                component.setEnabled(false);
                component.commit();
            }
            
            setControlLoad(supplier);
            
            return;
        }
        
        /*
         * Edit mode component's configuration
         */
        if (action.equals("supplier.edit")) {
            setTitlebar("supplier.edit.title");
            setButtonVisible("supplier.save", true);

            for (Component component : form.getComponents()) {
                component.setEnabled(component.isEnabled());
                component.commit();
            }
            
            setControlLoad(supplier);
                
            return;
        }
        
        /*
         * Creation mode component's configuration
         */
        if (action.equals("supplier.create")) {
            setTitlebar("supplier.create.title");
            setButtonVisible("supplier.save", true);

            for (Component component : form.getComponents()) {
                component.setEnabled(component.isEnabled());
                component.commit();
            }
            
            setControlLoad(supplier);
            
            getController().getForm("main").clear();
            
            return;
        }
    }
}
