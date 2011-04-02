package org.eve.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TableItemForm extends AbstractSearch {
    private int item;
    private TableAssist table;
    private Composite container;
    private Shell dialog;
    private Button next;
    private Button prev;
    private Button done;
    private Button cancel;
    
    public TableItemForm(Composite container, TableAssist table) {
        this.container = container;
        this.table = table;
        item = 1;
    }
    
    public final void setItem(int item) {
        this.item = item;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractSearch#openDialog()
     */
    @Override
    public final void openDialog() {
        Display display;
        Form itemform = new Form("");
        
//        for (Component component : table.getComponents()) {
//            switch (component.getType()) {
//            case CCOMBO:
//                itemform.put(document, id)
//                break;
//                
//            case TEXT:
//                break;
//            }
//        }
        
        itemform.define(container);
        
        dialog = new Shell(container.getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setLayout(new GridLayout(1, false));
        display = dialog.getDisplay();
        
        dialog.addDisposeListener(this);
        dialog.pack();
        dialog.open();
        
        while (!dialog.isDisposed())
            if (!display.readAndDispatch())
                display.sleep ();
        
    }

    @Override
    protected void userWidgetSelected(SelectionEvent ev) {
        if (ev.getSource() == next) {
            item++;
            return;
        }
        
        if (ev.getSource() == prev && item > 1) {
            item--;
            return;
        }
        
        if (ev.getSource() == done) {
            widgetDisposed(null);
            return;
        }
        
        if (ev.getSource() == cancel) {
            widgetDisposed(null);
            return;
        }
    }

    @Override
    public void widgetDisposed(DisposeEvent arg0) {
        next.dispose();
        prev.dispose();
        done.dispose();
        cancel.dispose();
        dialog.close();
        dialog.dispose();
    }
}