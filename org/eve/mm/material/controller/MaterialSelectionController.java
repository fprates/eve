package org.eve.mm.material.controller;

import org.eve.main.EVE;
import org.eve.mm.material.Material;
import org.eve.model.Model;
import org.eve.view.AbstractController;
import org.eve.view.Form;
import org.eve.view.TableAssist;

public class MaterialSelectionController extends AbstractController {

	private void setMaterial(Material material, Model model) {
        TableAssist table = getTable("materials");
        String ident = null;
        
        for (int k = 0; k < table.getItensSize(); k++)
            if (table.getMarkValue(k)) {
                ident = table.getStringValue("material.ident", k);
                break;
            }
        
        if (ident == null) {
            setMessage(EVE.error, "select.one");
            return;
        }
        
        model.load(Material.class, ident, material);		
	}
	
    @Override
    public void userInput(String input) {
    	Form form;
    	String ident;
        Material material = (Material)getObject();
        Model model = getModel();
        String action = getAction();
        
        if (input.equals("material.choose")) {
        	setMaterial(material, model);
            
            if (action.equals("material.show.choose"))
                call("material.show");
            
            if (action.equals("material.edit.choose"))
                call("material.edit");
            
            return;
        }
        
        form = getForm("main");
        ident = form.getString("material.ident");
        
        if (input.equals("material.sel")) {
            if (ident.equals("")) {
//                selporform = getForm("selpor");
//                
//                materials = model.select("selby_materials", new Object[] {
//                        selporform.getStringLike("material.name"),
//                        selporform.getStringLike("material.aname"),
//                        selporform.getStringLike("material.refer")});
//                
//                if (materials == null) {
//                    setMessage(EVE.error, "material.not.found");
//                    return;
//                }
//                
//                if (materials.size() == 0) {
//                    setMessage(EVE.error, "material.select.empty");
//                    return;
//                }
//                
//                table = getTable("materials");
//                ident = 0;
//                
//                for(Object object : materials) {
//                    table.insert();
//                    material_ = (Material)object;
//                    table.setIntValue("material.ident", ident,
//                            material_.getId());
//                    table.setStringValue("material.name", ident,
//                            material_.getName());
//                    table.setStringValue("material.aname", ident,
//                            material_.getAlternateName());
//                    table.setStringValue("material.refer", ident,
//                            material_.getReference());
//                    ident++;
//                }
//                
//                if (action.equals("material.show.sel"))
//                    call("material.show.choose");
//                
//                if (action.equals("material.edit.sel"))
//                    call("material.edit.choose");
//                
            } else {            
                model.load(Material.class, ident, material);
                if (material == null) {
                    setMessage(EVE.error, "material.not.found");
                    return;
                }
                
                if (action.equals("material.show.sel"))
                    call("material.show");
                
                if (action.equals("material.edit.sel"))
                    call("material.edit");
            }
        }
    }

}
