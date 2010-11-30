package org.eve.sd.order.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eve.model.AbstractDocument;
import org.eve.sd.order.Order;
import org.eve.view.AbstractView;
import org.eve.view.ComponentFactory;

public class OrderView extends AbstractView {

    @Override
    public void reload(String actionText) {
        // TODO Auto-generated method stub

    }

//    nº cabeçalho: gerado auto
//    cliente: ferramenta de busca, transporte de dados
//    data do pedido: data do dia (auto)
//    forma de pagamento: trazer do cad. cliente
//    frete (cif/fob) no pedido
//    flag pedido/cotação
    
    @Override
    protected void defineView(Composite container) {
        AbstractDocument order = (AbstractDocument)getController().getObject();
        Composite header = new Composite(container, SWT.NONE);
        ComponentFactory factory = getFactory();
        
        header.setLayout(new GridLayout(4, false));
        
        setWidth(1260);
        addAction("order.create");
        addAction("order.edit", false);
        addAction("order.show", false);
        
        factory.setContainer(header);
        factory.putTextEntry(order, Order.IDENT);
        
        header.pack();
        
        addButton("save.command");
    }

}
