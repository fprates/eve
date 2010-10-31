package org.eve.view;

import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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
public class Search implements SelectionListener, DisposeListener {
    private Button btcancel;
    private Button btsearch;
    private Button btsel;
    private Component component;
    private Composite container;
    private Controller controller;
    private EveAPI system;
    private Locale locale;
    private Map<Object, String> results;
    private MessageSource messages;
    private Shell dialog;
    private TableAssist table;
    
    public Search(Component component, Composite container) {        
        int charw;
        int charh;
        Control control = component.getControl();
        
        this.container = container;
        this.container.setLayout(new RowLayout());
        this.component = component;
        
        charw = ViewUtils.getCharWidth(control);
        charh = ViewUtils.getCharHeight(control);
        
        control.setSize(control.computeSize(
                component.getLength() * charw, charh));
        
        btsearch = new Button(container, SWT.BORDER);
        btsearch.setText("=");
        btsearch.addSelectionListener(this);
        container.pack();
    }

    public final void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    public final void setMessages(MessageSource messages) {
        this.messages = messages;
    }
    
    public final void setSystem(EveAPI system) {
        this.system = system;
    }
    
    public final void setController(Controller controller) {
        this.controller = controller;
    }
    
    /**
     * Define componentes da tela da ajuda de pesquisa
     */
    private void openDialog() {
        int k;
        Composite btarea;
        Display display;
        
        dialog = new Shell(container.getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setLayout(new GridLayout(1, false));
        display = dialog.getDisplay();
        
        results = controller.getResults(component.getName(), null);
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
        
        for (Object result : results.keySet())
            if (table.getMarkValue(k++)) {
                component.setString(result.toString());
                widgetDisposed(null);
                break;
            }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public final void widgetDefaultSelected(SelectionEvent ev) { }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public final void widgetSelected(SelectionEvent ev) {
        if (ev.getSource() == btsearch) {
            openDialog();
            return;
        }
        
        if (ev.getSource() == btsel) {
            select();
            return;
        }
        
        if (ev.getSource() == btcancel) {
            widgetDisposed(null);
            return;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
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
