package org.eve.sd.customer.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eve.main.EVE;
import org.eve.view.AbstractView;
import org.eve.view.Controller;
import org.eve.view.TableAssist;

public class CustomerChooseView extends AbstractView {

    @Override
    protected void defineView(Composite container) {
        Controller controller = getController();
        TableAssist ctable = controller.getTable("customers");

        container.setLayout(new RowLayout(SWT.VERTICAL));
        
        addAction("customer.show.choose", false);
        addAction("customer.edit.choose", false);
        
        ctable.setLocale(getLocale());
        ctable.putMark("customer.mark", EVE.single);
        ctable.put("customer.ident", 12);
        ctable.put("customer.name", 40);
        ctable.put("customer.aname", 40);
        
        ctable.setEditable(false);
        ctable.define(container, getController());
        
        addButton("customer.choose");
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.View#reload(java.lang.String)
     */
    @Override
    public void reload(String action) {
        getController().setAction(action);
        setTitlebar("customer.sel.title");
    }

}
