package org.eve.view;

import java.util.Locale;
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
import org.springframework.context.MessageSource;

/**
 * Ajuda de pesquisa
 * @author francisco.prates
 *
 */
public class SearchHelper extends AbstractSearch {
    private Button btcancel;
    private Button btsel;
    private Map<Object, String> results;
    private MessageSource messages;
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
        EveAPI system = getSystem();
        Locale locale = getLocale();
        
        results = controller.getResults(component.getName(), null);
        
        if (results == null) {
            system.setMessage(EVE.status, messages.getMessage(
                    "search.no.results", null, "search.no.results", locale));
            return;
        }
        
        dialog = new Shell(container.getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setLayout(new GridLayout(1, false));
        display = dialog.getDisplay();
        
        k = results.size();
        
        table = new TableAssist();    
        table.setEditable(false);
        table.setLocale(locale);
        table.setMessages(messages);
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
            table.setStringValue("search.resp", k, resp.toString());
            table.setStringValue("search.text", k++, results.get(resp));
        }
        
        btarea = new Composite(dialog, SWT.NONE);
        btarea.setLayout(new RowLayout());
        
        btsel = new Button(btarea, SWT.NONE);
        btsel.setText(messages.getMessage("search.sel", null, "search.sel", locale));
        btsel.addSelectionListener(this);
        
        btcancel = new Button(btarea, SWT.NONE);
        btcancel.setText(messages.getMessage("search.cancel", null, "search.cancel", locale));
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
