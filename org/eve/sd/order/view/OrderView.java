package org.eve.sd.order.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eve.model.AbstractDocument;
import org.eve.sd.order.Order;
import org.eve.view.AbstractView;
import org.eve.view.ComponentFactory;
import org.eve.view.Controller;

public class OrderView extends AbstractView {
    
    /**
     * 
     * @param order
     */
    private final void setControlLoad(Order order) {
        String id_;
        String name;
        
        for (Object id : order.getIds()) {
            if (id.equals(Order.USREG))
                continue;
            
            id_ = (String)id;
            name = order.getName(id_);
            
//            form.setFieldValue(name, order.getFieldValue(id_));
        }
    }

    @Override
    public void reload(String action) {
        Controller controller = getController();
        Order order = (Order)controller.getObject();
        
        /*
         * Display mode component's configuration
         */
        if (action.equals("order.show")) {
            setTitlebar("order.show.title");
            setButtonVisible("save.command", false);
            
            setControlLoad(order);
            
            return;
        }
        
        /*
         * Edit mode component's configuration
         */
        if (action.equals("order.edit")) {
            setTitlebar("order.edit.title");
            setButtonVisible("save.command", true);
            
            setControlLoad(order);
                
            return;
        }
        
        /*
         * Creation mode component's configuration
         */
        if (action.equals("order.create")) {            
            setTitlebar("order.create.title");
            setButtonVisible("save.command", true);
            
            setControlLoad(order);
            
            return;
        }
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
