package org.eve.mm.material.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eve.mm.material.Material;
import org.eve.view.AbstractView;
import org.eve.view.Controller;
import org.eve.view.Form;

public class MaterialSelectionView extends AbstractView {

    @Override
    protected void defineView(Composite container) {
        Group selpor;
        Form form = addForm("main");
        Form selporform = addForm("selpor");
        Material material = (Material)getController().getObject();
        
        container.setLayout(new RowLayout(SWT.VERTICAL));
        
        addAction("material.show.sel");
        addAction("material.edit.sel");
        
        form.setLocale(getLocale());
        form.put(material, Material.IDENT);
        form.define(container);
        
        selpor = new Group(container, SWT.SHADOW_IN);
        selporform.setLocale(getLocale());
        selpor.setLayout(new RowLayout(SWT.VERTICAL));
        selpor.setText(getMessage("select.by"));
        
        selporform.put(material, Material.REFER);
        selporform.define(selpor);
        selpor.pack();
        
        addButton("material.sel");
    }

    @Override
    public void reload(String action) {
        Controller controller = getController();
        
        controller.setAction(action);
        controller.getForm("main").clear();
        controller.getForm("selpor").clear();
        controller.getTable("materials").clear();
        
        setTitlebar("material.sel.title");
    }

}
