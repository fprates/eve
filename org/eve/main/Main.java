package org.eve.main;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main extends ApplicationWindow {
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
        Tree selector = new Tree(apparea, SWT.BORDER);
        
        selector.addListener(SWT.Selection, listener);
        
        app.setSelector(selector);
        app.buildUserArea(apparea);
        app.addControllers(app.getControllers(), listener);
        
        apparea.setWeights(new int[] {2, 8});
        
        shell.setText("Eve "+app.getVersion());
        shell.pack();
        shell.setMaximized(true);
        
        return apparea;
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
