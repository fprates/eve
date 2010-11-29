package org.eve.sd.order;

import org.eve.model.AbstractDocument;

public class Order extends AbstractDocument {
    public static final String IDENT = "id";
    private static final long serialVersionUID = 1944148512440264629L;
    
    private int id;

    public Order() {
        put(IDENT, "order.ident", true, datatype.INT, 10);
        put(DTREG, "order.dtreg", false, datatype.DATE, 10);
        put(TMREG, "order.tmreg", false, datatype.TIME, 8);
        put(USREG, "order.usreg", false, datatype.CHAR, 12);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
