package org.eve.tools.upload;

import org.eve.model.AbstractDocument;

public class Upload extends AbstractDocument {
    public static final String DOCUMENT = "upload.document";
    public static final String FILENAME = "upload.filename";
    private static final long serialVersionUID = -4902617045276756182L;

    public Upload() {
        put(DOCUMENT, "upld.doc", false, datatype.INT, 1);
        putValues(DOCUMENT, new String[] {
                "customer.data",
                "supplier.data",
                "material.data"
        });
    }
}
