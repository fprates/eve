package org.eve.mm.material.view;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eve.mm.material.Material;
import org.eve.view.AbstractView;
import org.eve.view.Controller;
import org.eve.view.Form;

public class MaterialView extends AbstractView {

    @Override
    protected void defineView(Composite container) {
        Form form = addForm("main");
        
        setWidth(1260);
        addAction("material.create");
        addAction("material.edit", false);
        addAction("material.show", false);
        
        container.setLayout(new GridLayout(1, false));
        
        /*
         * Dados básicos
         */
        form.put("material.ident", 18, false);
        form.put("material.dtreg", 10, false);
        form.put("material.tmreg", 8, false);
        form.setBlocked("material.dtreg");
        form.setBlocked("material.tmreg");
        form.put("material.refer", 60);
        form.put("material.undps", 3);
        form.put("material.psliq", 13);
//        form.concat("material.psliq", "material.undps", "material.psliq");
        form.put("material.undqt", 3);
        form.put("material.quant", 13);
//        form.concat("material.quant", "material.undqt", "material.quant");
        
        form.define(container);
        
        addButton("material.save");
    }
    
    /**
     * 
     * @param customer
     */
    private final void setControlLoad(Material material) {
        Controller controller = getController();
        Form form = controller.getForm("main");
        
        form.setString("material.ident", material.getId());
        form.setDate("material.dtreg", material.getRegDate());
        form.setTime("material.tmreg", material.getRegTime());
        form.setString("material.refer", material.getReference());
        form.setString("material.undps", material.getWeightUnit());
        form.setFloat("material.psliq", material.getNetWeight());
//        form.concat("material.psliq", "material.undps", "material.psliq");
        form.setString("material.undqt", material.getQuantityUnit());
        form.setFloat("material.quant", material.getQuantity());
//        form.concat("material.quant", "material.undqt", "material.quant");
    }

    @Override
    public void reload(String action) {
        Controller controller = getController();
        Material material = (Material)controller.getObject();
        Form form = controller.getForm("main");
        
        /*
         * Display mode component's configuration
         */
        if (action.equals("material.show")) {
            setTitlebar("material.show.title");
            setButtonVisible("material.save", false);
//            contacts.clear();
//            contacts.setEditable(false);
//            addresses.clear();
//            addresses.setEditable(false);
//            vschedule.clear();
//            vschedule.setEditable(false);
//            dschedule.clear();
//            dschedule.setEditable(false);
//            
            form.setEditable(false);
            form.commit();
            
            setControlLoad(material);
            
            return;
        }
        
        /*
         * Edit mode component's configuration
         */
        if (action.equals("material.edit")) {
            setTitlebar("material.edit.title");
            setButtonVisible("material.save", true);
//            contacts.clear();
//            contacts.setEditable(true);
//            addresses.clear();
//            addresses.setEditable(true);
//            vschedule.clear();
//            vschedule.setEditable(true);
//            dschedule.clear();
//            dschedule.setEditable(true);
            
            form.setEditable(true);
            form.commit();
            
            setControlLoad(material);
                
            return;
        }
        
        /*
         * Creation mode component's configuration
         */
        if (action.equals("material.create")) {            
            setTitlebar("material.create.title");
            setButtonVisible("material.save", true);
//            contacts.clear();
//            contacts.setEditable(true);
//            addresses.clear();
//            addresses.setEditable(true);
//            vschedule.clear();
//            vschedule.setEditable(true);
//            dschedule.clear();
//            dschedule.setEditable(true);
            
            form.setEditable(true);
            form.commit();
            
            setControlLoad(material);
            
            getController().getForm("main").clear();
            
            return;
        }
    }

}
