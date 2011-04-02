package org.eve.tools.upload;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eve.model.AbstractDocument;
import org.eve.model.DataType;

public class Upload extends AbstractDocument {
    private static final long serialVersionUID = -4902617045276756182L;

    public Upload() {
        Map<String, Integer> values;
        
        put("upload.document", false, DataType.INT, 1);
        values = new LinkedHashMap<String, Integer>();
        
        values.put("customer.data", 1);
        values.put("supplier.data", 2);
        values.put("material.data", 3);
        
        putValues("upload.document", values);
        
        put("upload.filename", false, DataType.CHAR, 120);
        setLowerCase("upload.filename");
    }

    public int getDocument() {
        return (Integer)getValue("upload.document");
    }

    public String getFilename() {
        return (String)getValue("upload.filename");
    }

    public void setDocument(int document) {
        setValue("upload.document", document);
    }

    public void setFilename(String filename) {
        setValue("upload.filename", filename);
    }
}
