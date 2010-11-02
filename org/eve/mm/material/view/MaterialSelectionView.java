package org.eve.mm.material.view;

import org.eclipse.swt.widgets.Composite;
import org.eve.view.AbstractView;
import org.eve.view.Controller;

public class MaterialSelectionView extends AbstractView {

    @Override
    protected void defineView(Composite container) {        
        addAction("material.show.sel");
        addAction("material.edit.sel");
    }

    @Override
    public void reload(String action) {
        Controller controller = getController();
        
        controller.setAction(action);
//        controller.getForm("main").clear();
//        controller.getForm("selpor").clear();
//        controller.getTable("materials").clear();
        
        setTitlebar("material.sel.title");
    }

}
