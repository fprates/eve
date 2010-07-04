package org.eve.main;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eve.view.ViewAction;
import org.eve.view.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main extends ApplicationWindow {
    private Composite container;
    private Tree selector;
    private EveApp app;

    public Main() {
        super(null);
        ApplicationContext context =
            new ClassPathXmlApplicationContext("/META-INF/services.xml");
        
        app = context.getBean("eve_app", EveApp.class);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        Shell shell = getShell();
        GeneralListener listener = app.getListener();
        SashForm apparea = new SashForm(parent, SWT.HORIZONTAL);
        
        selector = new Tree(apparea, SWT.BORDER);
        container = new Composite(apparea, SWT.BORDER);
        
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        container.setLayout(new FormLayout());

        selector.addListener(SWT.Selection, listener);
        selector.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));        
        addViews(app.getViews(), listener);
        
        shell.setText("Eve "+app.getVersion());
        shell.pack();
        shell.setMaximized(true);
        
        return apparea;        
    }
    
    /**
     * Adiciona visões ao container principal
     * @param lviews
     * @param listener
     */
    public void addViews(List<View> lviews, GeneralListener listener) {
        TreeItem item;
        TreeItem subitem;
        Composite container;
        Map<String, View> viewmap = app.getViewMap();
        
        /*
         * assemblies main tree.
         */
        for (View view : lviews) {            
            container = new Composite(this.container, SWT.NONE);
            container.setLayout(new GridLayout(1, false));
            container.setVisible(false);
            
            view.setContainer(container);
            view.setLocale(Locale.getDefault());
            view.buildView();
            
            view.getController().setSystem(app);
            
            item = new TreeItem(selector, SWT.NONE);
            item.setText(view.getName());
            
            for (ViewAction action : view.getActions()) {
                viewmap.put(action.getId(), view);
                
                if (!action.isVisible())
                    continue;
                
                subitem = new TreeItem(item, SWT.NONE);
                subitem.setText(action.getText());
                listener.putSelectorItem(subitem, action.getId());
            }
        }        
    }
    
    /**
     * Ponto de entrada
     * @param args
     */
    public static void main(String[] args) {
        Main core = new Main();
        
        core.setBlockOnOpen(true);
        core.open();
        Display.getCurrent().dispose();
    }

}
