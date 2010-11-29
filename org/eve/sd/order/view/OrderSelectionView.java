package org.eve.sd.order.view;

import org.eclipse.swt.widgets.Composite;
import org.eve.view.AbstractView;

public class OrderSelectionView extends AbstractView {

    @Override
    public void reload(String actionText) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void defineView(Composite container) {
        addAction("order.show.sel");
        addAction("order.edit.sel");

        addButton("sel.command");
    }

}
