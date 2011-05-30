package org.eve.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class FileSearch extends AbstractSearch {
    private FileDialog fd;

    public FileSearch(Shell shell) {        
        fd = new FileDialog(shell, SWT.OPEN);
        setText("search.file.open");
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractSearch#openDialog()
     */
    @Override
    protected void openDialog(int mode) {
        /*
         * TODO
         */
        ComponentFactory factory = getFactory();

        factory.setString(getComponent().getName(), fd.open());
    }

    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractSearch#userWidgetSelected(
     *     org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    protected void userWidgetSelected(SelectionEvent ev) { }

    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractSearch#widgetDisposed(
     *     org.eclipse.swt.events.DisposeEvent)
     */
    @Override
    public void widgetDisposed(DisposeEvent arg0) { }
    

}
