package org.eve.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TableItemForm extends AbstractSearch {
    private int item;
    private TableAssist table;
    private Composite btarea;
    private Composite container;
    private Shell dialog;
    private Form itemform;
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
        
        dialog = new Shell(
                container.getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setText(getMessage("itemform.new"));
        dialog.setLayout(new GridLayout(1, false));
        
        itemform = new Form("");
        itemform.setMessages(getController().getMessages());
        itemform.setLocale(getLocale());
        
        for (Component component : table.getComponents())
            itemform.put(
                    component.getName(), component, component.isEnabled());
        
        itemform.define(dialog);
        
        btarea = new Composite(dialog, SWT.NONE);
        btarea.setLayout(new RowLayout());

        prev = new Button(btarea, SWT.NONE);
        prev.setText(getMessage("itemform.prev"));
        prev.addSelectionListener(this);
        
        next = new Button(btarea, SWT.NONE);
        next.setText(getMessage("itemform.next"));
        next.addSelectionListener(this);
        
        done = new Button(btarea, SWT.NONE);
        done.setText(getMessage("itemform.done"));
        done.addSelectionListener(this);
        
        cancel = new Button(btarea, SWT.NONE);
        cancel.setText(getMessage("itemform.cancel"));
        cancel.addSelectionListener(this);
        
        dialog.addDisposeListener(this);
        dialog.pack();
        dialog.open();
        
        display = dialog.getDisplay();
        
        while (!dialog.isDisposed())
            if (!display.readAndDispatch())
                display.sleep();
        
    }

    @Override
    protected void userWidgetSelected(SelectionEvent ev) {
        int k;
        
        if (ev.getSource() == next) {
            item++;
            return;
        }
        
        if (ev.getSource() == prev && item > 1) {
            item--;
            return;
        }
        
        if (ev.getSource() == done) {
            k = table.getItensSize();
            table.insert();
            table.copyFrom(k, itemform);
            
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
        btarea.dispose();
        dialog.close();
        dialog.dispose();
    }
}
