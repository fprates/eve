package org.eve.sd.order;

import org.eve.model.AbstractDocument;
import org.eve.model.DataType;

public class Order extends AbstractDocument {
    public static final String IDENT = "id";
    private static final long serialVersionUID = 1944148512440264629L;
    
    private int id;

    public Order() {
        put(IDENT, "order.ident", true, DataType.INT, 10);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
