package org.eve.sd.order.view;

import org.eclipse.swt.widgets.Composite;
import org.eve.view.AbstractView;

public class OrderView extends AbstractView {

    @Override
    public void reload(String actionText) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void defineView(Composite container) {
        
        setWidth(1260);
        addAction("order.create");
        addAction("order.edit", false);
        addAction("order.show", false);
        
        addButton("save.command");
    }

}
