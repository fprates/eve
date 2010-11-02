package org.eve.mm.material.view;

import org.eclipse.swt.widgets.Composite;
import org.eve.view.AbstractView;

public class MaterialView extends AbstractView {

    @Override
    protected void defineView(Composite container) {        
        setWidth(1260);
        addAction("material.create");
        addAction("material.edit", false);
        addAction("material.show", false);
    }

    @Override
    public void reload(String action) {
        
        /*
         * Display mode component's configuration
         */
        if (action.equals("material.show")) {
            setTitlebar("material.show.title");
//            setButtonVisible("material.save", false);
//            contacts.clear();
//            contacts.setEditable(false);
//            addresses.clear();
//            addresses.setEditable(false);
//            vschedule.clear();
//            vschedule.setEditable(false);
//            dschedule.clear();
//            dschedule.setEditable(false);
//            
//            form.setEditable(false);
//            form.commit();
//            
//            setControlLoad(material);
            
            return;
        }
        
        /*
         * Edit mode component's configuration
         */
        if (action.equals("material.edit")) {
            setTitlebar("material.edit.title");
//            setButtonVisible("material.save", true);
//            contacts.clear();
//            contacts.setEditable(true);
//            addresses.clear();
//            addresses.setEditable(true);
//            vschedule.clear();
//            vschedule.setEditable(true);
//            dschedule.clear();
//            dschedule.setEditable(true);
//            
//            form.setEditable(true);
//            form.commit();
//            
//            setControlLoad(material);
                
            return;
        }
        
        /*
         * Creation mode component's configuration
         */
        if (action.equals("material.create")) {            
            setTitlebar("material.create.title");
//            setButtonVisible("material.save", true);
//            contacts.clear();
//            contacts.setEditable(true);
//            addresses.clear();
//            addresses.setEditable(true);
//            vschedule.clear();
//            vschedule.setEditable(true);
//            dschedule.clear();
//            dschedule.setEditable(true);
//            
//            form.setEditable(true);
//            form.commit();
//            
//            setControlLoad(material);
//            
//            getController().getForm("main").clear();
            return;
        }
    }

}
