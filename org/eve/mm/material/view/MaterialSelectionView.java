package org.eve.mm.material.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eve.view.AbstractView;
import org.eve.view.Controller;
import org.eve.view.Form;

public class MaterialSelectionView extends AbstractView {

    @Override
    protected void defineView(Composite container) {
        Form form = addForm("main");
        
        container.setLayout(new RowLayout(SWT.VERTICAL));
        
        addAction("material.show.sel");
        addAction("material.edit.sel");
        
        form.setLocale(getLocale());
        form.put("material.ident", 12);        
        form.define(container);
        
        addButton("material.sel");
    }

    @Override
    public void reload(String action) {
        Controller controller = getController();
        
        controller.setAction(action);
        controller.getForm("main").clear();
//        controller.getForm("selpor").clear();
//        controller.getTable("materials").clear();
        
        setTitlebar("material.sel.title");
    }

}
