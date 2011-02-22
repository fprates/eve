package org.eve.sd.order.controller;

import org.eve.main.EVE;
import org.eve.model.EveException;
import org.eve.model.Model;
import org.eve.sd.order.Order;
import org.eve.view.AbstractController;

public class OrderController extends AbstractController {

    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractController#saveCommand()
     */
    @Override
    public final void saveCommand() {
        Order order = (Order)getObject();
        Model model = getModel();
        
        try {
            model.save(order);
        } catch (EveException e) {
            setMessage(EVE.error, "order.save.error");
            e.printStackTrace();
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractController#userInput(java.lang.String)
     */
    @Override
    public void userInput(String input) { }

}
