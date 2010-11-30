package org.eve.sd.order;

import org.eve.model.AbstractDocument;

public class Order extends AbstractDocument {
    public static final String IDENT = "id";
    private static final long serialVersionUID = 1944148512440264629L;
    
    private int id;

    public Order() {
        put(IDENT, "order.ident", true, datatype.INT, 10);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
