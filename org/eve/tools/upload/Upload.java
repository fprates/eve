package org.eve.tools.upload;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eve.model.AbstractDocument;
import org.eve.model.DataType;

public class Upload extends AbstractDocument {
    public static final String DOCUMENT = "document";
    public static final String FILENAME = "filename";
    private static final long serialVersionUID = -4902617045276756182L;
    private int document;
    private String filename;

    public Upload() {
        Map<String, Integer> values;
        
        put(DOCUMENT, "upload.document", false, DataType.INT, 1);
        values = new LinkedHashMap<String, Integer>();
        
        values.put("customer.data", 1);
        values.put("supplier.data", 2);
        values.put("material.data", 3);
        
        putValues(DOCUMENT, values);
        
        put(FILENAME, "upload.filename", false, DataType.CHAR, 120);
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
