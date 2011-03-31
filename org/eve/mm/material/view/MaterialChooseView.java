package org.eve.mm.material.view;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eve.mm.material.Material;
import org.eve.view.AbstractView;
import org.eve.view.Controller;
import org.eve.view.TableAssist;

public class MaterialChooseView extends AbstractView {

    @Override
    protected void defineView(Composite container) {
        TableAssist ctable = addTable("materials");
        Material material = (Material)getController().getObject();
        
        setHeight(350);
        container.setLayout(new FillLayout());
        
        addAction("material.show.choose", false);
        addAction("material.edit.choose", false);
        
        ctable.setLines(10);
        ctable.put(material, Material.IDENT);
        ctable.put(material, Material.REFER);
        
        ctable.define(container);
        
        addButton("material.choose");
    }

    @Override
    public void reload(String action) {
        Controller controller = getController();
        controller.setAction(action);
        
        setTitlebar("material.sel.title");
    }

}
