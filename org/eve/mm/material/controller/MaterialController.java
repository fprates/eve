package org.eve.mm.material.controller;

import org.eve.main.EVE;
import org.eve.mm.material.Material;
import org.eve.model.EveException;
import org.eve.model.Model;
import org.eve.view.AbstractController;
import org.eve.view.Form;
import org.hibernate.HibernateException;

public class MaterialController extends AbstractController {

    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractController#saveCommand()
     */
    @Override
	public void saveCommand() {
        boolean firstsave = false;
        Model model = getModel();
        Material material = (Material)getObject();
        Form form = getForm("main");
	
        try {
            /*
             * dados base
             */
            
            for (Object id : material.getIds()) {
                if (id.equals(Material.USREG))
                    continue;
                
                material.setFieldValue((String)id,
                        form.getFieldValue(material, (String)id));
            }
            
            if (material.getId().equals("") ||
                material.getReference().equals("")) {
                setMessage(EVE.error, "material.obligatory.field");
                
                return;
            }
        	
        	if (material.getRegUser() == null)
        	    firstsave = true;
        	
            model.save(material);
            form.setDate("material.dtreg", material.getRegDate());
            form.setTime("material.tmreg", material.getRegTime());
            
            setMessage(EVE.status, "material.save.success");
            
            if (firstsave) {
                form.setBlocked("material.ident");
                form.commit();
            }
            
        } catch (HibernateException ex) {
            setMessage(EVE.error, "material.save.error");
            ex.printStackTrace();
        } catch (EveException ex) {
            setMessage(EVE.error, ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
    @Override
    public void userInput(String input) { }

}
