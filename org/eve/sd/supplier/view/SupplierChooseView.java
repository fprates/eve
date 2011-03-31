package org.eve.sd.supplier.view;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eve.view.AbstractView;
import org.eve.view.Controller;
import org.eve.view.TableAssist;

public class SupplierChooseView extends AbstractView {

    @Override
    protected void defineView(Composite container) {
        TableAssist stable = addTable("suppliers");

        setHeight(350);
        container.setLayout(new FillLayout());
        
        addAction("supplier.show.choose", false);
        addAction("supplier.edit.choose", false);
        
        stable.setLines(10);
//        stable.put("supplier.ident", 12);
//        stable.put("supplier.refer", 12);
//        stable.put("supplier.name", 40);
//        stable.put("supplier.aname", 40);
        
        stable.setEditable(false);
        stable.define(container);
        
        addButton("supplier.choose");
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.View#reload(java.lang.String)
     */
    @Override
    public void reload(String action) {
        Controller controller = getController();
        controller.setAction(action);
        
        setTitlebar("supplier.sel.title");        
    }

}
