package org.eve.tools.upload;

import org.eve.model.AbstractDocument;

public class Upload extends AbstractDocument {
    public static final String DOCUMENT = "document";
    public static final String FILENAME = "filename";
    private static final long serialVersionUID = -4902617045276756182L;
    private int document;
    private String filename;

    public Upload() {
        put(DOCUMENT, "upload.document", false, datatype.INT, 1);
        putValues(DOCUMENT, new String[] {
                "",
                "customer.data",
                "supplier.data",
                "material.data"
        });
        
        put(FILENAME, "upload.filename", false, datatype.CHAR, 120);
        setLowerCase(FILENAME);
    }

    public int getDocument() {
        return document;
    }

    public String getFilename() {
        return filename;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    
}
