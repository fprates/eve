package org.eve.mm.material.controller;

import org.eve.main.EVE;
import org.eve.mm.material.Material;
import org.eve.model.Model;
import org.eve.view.AbstractController;
import org.eve.view.Form;
import org.hibernate.HibernateException;

public class MaterialController extends AbstractController {

	private void save() {
        Model model = getModel();
        Material material = (Material)getObject();
        Form form = getForm("main");
		
        try {
            /*
             * dados base
             */
        	material.setId(form.getString("material.ident"));
        	material.setReference(form.getString("material.refer"));
        	
        	if (material.getId().equals("") ||
        	    material.getReference().equals("")) {
                setMessage(EVE.error, "material.obligatory.field");
                return;
        	}
            
        	material.setNetWeight(form.getFloat("material.psliq"));
        	material.setWeightUnit(form.getString("material.undps"));
        	material.setQuantity(form.getFloat("material.quant"));
        	material.setQuantityUnit(form.getString("material.undqt"));
        	
            model.save(material);
            form.setDate("material.dtreg", material.getRegDate());
            form.setTime("material.tmreg", material.getRegTime());
            
            setMessage(EVE.status, "material.save.success");
            
            return;
        } catch (HibernateException ex) {
            setMessage(EVE.error, "material.save.error");
            ex.printStackTrace();                
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
    @Override
    public void userInput(String input) {
    	if (input.equals("material.save"))
    		save();
    }

}
