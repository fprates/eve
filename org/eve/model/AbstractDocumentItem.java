package org.eve.model;

public class AbstractDocumentItem extends AbstractDocument {
    private static final long serialVersionUID = -3662147713889205422L;

    public final Object getDocument() {
        return getValue("document");
    }
    
    public final Object getDocumentItem() {
        return getValue("document.item");
    }
    
    public final void setDocument(Object object) {
        setValue("document", object);
    }
    
    public final void setDocumentItem(Object object) {
        setValue("document.item", object);
    }
}
