package org.eve.tools.upload;

import org.eve.model.AbstractDocument;

public class Upload extends AbstractDocument {
    public static final String DOCUMENT = "document";
    public static final String FILENAME = "filename";
    private static final long serialVersionUID = -4902617045276756182L;

    public Upload() {
        put(DOCUMENT, "upld.doc", false, datatype.INT, 1);
        putValues(DOCUMENT, new String[] {
                "customer.data",
                "supplier.data",
                "material.data"
        });
        
        put(FILENAME, "upld.filename", false, datatype.CHAR, 120);
        setLowerCase(FILENAME);
    }
}
