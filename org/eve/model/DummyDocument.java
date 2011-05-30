package org.eve.model;

import org.eve.view.Component;

public class DummyDocument extends AbstractDocument {
    private static final long serialVersionUID = -7209856418319597848L;

    public void put(Component component) {
        put(component.getName(), false,
                component.getDataType(), component.getLength());
    }
}
