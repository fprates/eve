package org.eve.sd.order;

import org.eve.model.AbstractDocument;
import org.eve.model.DataType;

public class Order extends AbstractDocument {
    private static final long serialVersionUID = 1944148512440264629L;

    public Order() {
        put("order.ident", true, DataType.INT, 10);
    }

    /**
     * 
     * @return
     */
    public int getId() {
        return (Integer)getValue("order.ident");
    }

    /**
     * 
     * @param id
     */
    public void setId(int id) {
        setValue("order.ident", id);
    }
    
    
}
