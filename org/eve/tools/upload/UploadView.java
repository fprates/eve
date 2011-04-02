package org.eve.tools.upload;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eve.view.AbstractView;
import org.eve.view.Form;

public class UploadView extends AbstractView {

    @Override
    public void reload(String actionText) {
        
    }

    @Override
    protected void defineView(Composite container) {
        Form form = addForm("main");
        Upload upload = (Upload)getController().getObject();
        
        setWidth(1260);
        addAction("data.upload");
        
        container.setLayout(new GridLayout(1, false));
        
        form.putCombo(upload, "upload.document", 10);
        form.putFileSearch(upload, "upload.filename");
        
        form.define(container);
        
        addButton("upload.start");
    }

}
