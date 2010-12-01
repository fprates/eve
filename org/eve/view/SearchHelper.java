package org.eve.view;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eve.main.EVE;
import org.eve.main.EveAPI;

/**
 * Ajuda de pesquisa
 * @author francisco.prates
 *
 */
public class SearchHelper extends AbstractSearch {
    private Button btcancel;
    private Button btsel;
    private Map<Object, String> results;
    private Shell dialog;
    private TableAssist table;
    
    public SearchHelper() {
        setText("=");
    }
    
    /**
     * Define componentes da tela da ajuda de pesquisa
     */
    @Override
    protected void openDialog() {
        int k;
        Composite btarea;
        Display display;
        Component component = getComponent();
        Composite container = getContainer();
        Controller controller = getController();
        AbstractComponentFactory factory = getFactory();
        EveAPI system = factory.getSystem();
        
        results = controller.getResults(component.getName(), null);
        
        if (results == null) {
            system.setMessage(EVE.status, factory.getMessage("search.no.results"));
            return;
        }
        
        dialog = new Shell(container.getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setLayout(new GridLayout(1, false));
        display = dialog.getDisplay();
        
        k = results.size();
        
        table = new TableAssist();    
        table.setEditable(false);
        table.setSystem(system);
        table.setNoEditBar(true);
        
        if (k > 10)
            table.setLines(10);
        else
            table.setLines(k);
        
        table.putMark("sel", EVE.single);
        table.put("search.resp", component.getLength());
        table.put("search.text", 60);
        table.define(dialog);
        
        k = 0;
        for (Object resp : results.keySet()) {
            table.insert();
            table.setString("search.resp", k, resp.toString());
            table.setString("search.text", k++, results.get(resp));
        }
        
        btarea = new Composite(dialog, SWT.NONE);
        btarea.setLayout(new RowLayout());
        
        btsel = new Button(btarea, SWT.NONE);
        btsel.setText(factory.getMessage("search.sel"));
        btsel.addSelectionListener(this);
        
        btcancel = new Button(btarea, SWT.NONE);
        btcancel.setText(factory.getMessage("search.cancel"));
        btcancel.addSelectionListener(this);
        
        dialog.addDisposeListener(this);
        dialog.pack();
        dialog.open();
        
        while (!dialog.isDisposed())
            if (!display.readAndDispatch())
                display.sleep ();
    }
    
    /**
     * preenche campo com resultado selecionado.
     */
    private void select() {
        int k = 0;
        Component component = getComponent();
        
        for (Object result : results.keySet())
            if (table.getMarkValue(k++)) {
                component.setString(result.toString());
                widgetDisposed(null);
                break;
            }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.AbstractSearch#userWidgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public final void userWidgetSelected(SelectionEvent ev) {
        if (ev.getSource() == btsel) {
            select();
            return;
        }
        
        if (ev.getSource() == btcancel) {
            widgetDisposed(null);
            return;
        }
    }

    /* (non-Javadoc)
     * @see org.eve.view.Search#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
     */
    @Override
    public final void widgetDisposed(DisposeEvent ev) {
        results.clear();
        btsel.dispose();
        btcancel.dispose();
        table.dispose();
        dialog.close();
        dialog.dispose();
    }
}
