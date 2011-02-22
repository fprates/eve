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
        Material material = (Material)getController().getObject();
        
        setWidth(1260);
        addAction("material.create");
        addAction("material.edit", false);
        addAction("material.show", false);
        
        container.setLayout(new GridLayout(1, false));
        
        /*
         * Dados básicos
         */
        form.put(material, Material.IDENT);
        form.put(material, Material.DTREG);
        form.put(material, Material.TMREG);
        form.put(material, Material.REFER);
        form.put(material, Material.UNDPS);
        form.put(material, Material.PSLIQ);
        form.put(material, Material.UNDQT);
        form.put(material, Material.QUANT);
        form.put(material, Material.MOEDA);
        form.put(material, Material.VLCST);
        form.put(material, Material.LNGTH);
        form.put(material, Material.WIDTH);
        form.put(material, Material.THICK);
//        form.concat("material.psliq", "material.undps", "material.psliq");
//        form.concat("material.quant", "material.undqt", "material.quant");
        
        form.define(container);
        
        addButton("save.command");
    }
    
    /**
     * 
     * @param customer
     */
    private final void setControlLoad(Material material) {
        String id_;
        String name;
        Controller controller = getController();
        Form form = controller.getForm("main");
        
        for (Object id : material.getIds()) {
            if (id.equals(Material.USREG))
                continue;
            
            id_ = (String)id;
            name = material.getName(id_);
            
            form.setFieldValue(name, material.getFieldValue(id_));
        }
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
            setButtonVisible("save.command", false);
            form.setEditable(false);
            form.setBlocked("material.ident");
            form.setBlocked("material.dtreg");
            form.setBlocked("material.tmreg");
            form.commit();
            
            setControlLoad(material);
            
            return;
        }
        
        /*
         * Edit mode component's configuration
         */
        if (action.equals("material.edit")) {
            setTitlebar("material.edit.title");
            setButtonVisible("save.command", true);
            form.setEditable(true);
            form.setBlocked("material.ident");
            form.setBlocked("material.dtreg");
            form.setBlocked("material.tmreg");
            form.commit();
            
            setControlLoad(material);
                
            return;
        }
        
        /*
         * Creation mode component's configuration
         */
        if (action.equals("material.create")) {            
            setTitlebar("material.create.title");
            setButtonVisible("save.command", true);
            form.setEditable(true);
            form.setUnBlocked("material.ident");
            form.setBlocked("material.dtreg");
            form.setBlocked("material.tmreg");
            form.commit();
            
            setControlLoad(material);
            
            getController().getForm("main").clear();
            
            return;
        }
    }

}
