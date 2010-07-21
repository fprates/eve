package org.eve.main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eve.view.Controller;
import org.eve.view.MessageBar;
import org.eve.view.View;
import org.eve.view.ViewAction;

public class EveApp implements EveAPI {
    private final static String VERSION = "1.9.7";
    private GeneralListener listener;
    private ScrolledComposite scontainer;
    private Composite container;
    private Tree selector;
    private Label titlebar;
    private Composite buttonbar;
    private MessageBar messagebar;
    private List<Controller> controllers;
    private List<String> buttonbarlist;
    private Map<View, Controller> controlmap;
    private Map<String, View> viewmap;
    private Map<View, Composite> buttonmap;
    
    
    public EveApp() {
        viewmap = new HashMap<String, View>();
        controlmap = new HashMap<View, Controller>();
        buttonbarlist = new LinkedList<String>();
        buttonmap = new HashMap<View, Composite>();
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    public final void setListener(GeneralListener listener) {
        this.listener = listener;
        this.listener.setSystem(this);
    }
    
    /**
     * 
     * @param controllers
     */
    public final void setControllers(List<Controller> controllers) {
        Map<String, View> views;
        
        this.controllers = controllers;
        
        controlmap.clear();
        for(Controller controller : controllers) {
            views = controller.getViews();
            for(String viewname: views.keySet())
                controlmap.put(views.get(viewname), controller);
        }
        
    }
    
    /**
     * 
     * @param selector
     */
    public final void setSelector(Tree selector) {
        this.selector = selector;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    public final List<Controller> getControllers() {
        return controllers;
    }
    
    /**
     * Retorna o listener da árvore de opções
     * @return
     */
    public final GeneralListener getListener() {
        return listener;
    }
    
    /**
     * Retorna versão do sistema
     * @return
     */
    public final String getVersion() {
        return VERSION;
    }
    
    /*
     * 
     * Services
     * 
     */
    
    @Override
    public final void call(String action) {
        StackLayout layout;
        View view = viewmap.get(action);
        
        if (view == null)
            return;
        
        messagebar.clear();
        view.reload(action);
        
        layout = (StackLayout)container.getLayout();
        layout.topControl = view.getContainer();
        container.layout();
        container.pack();
        
        layout = (StackLayout)buttonbar.getLayout();
        layout.topControl = buttonmap.get(view);
        buttonbar.layout();
        buttonbar.pack();
        
        scontainer.setOrigin(0, 0);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.main.EveAPI#getController(org.eve.view.View)
     */
    @Override
    public final Controller getController(View view) {
        return controlmap.get(view);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.main.EveAPI#setTitleBar(java.lang.String)
     */
    @Override
    public final void setTitleBar(String text) {
        titlebar.setText(text);
        titlebar.pack();
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.main.EveAPI#addButton(java.lang.String)
     */
    @Override
    public final void addButton(String id) {
        buttonbarlist.add(id);        
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.main.EveAPI#setMessage(int, java.lang.String)
     */
    @Override
    public final void setMessage(int status, String message) {
        messagebar.setMessage(status, message);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.main.EveAPI#clearMessage()
     */
    @Override
    public final void clearMessage() {
        messagebar.clear();
    }
    
    /**
     * 
     * @param container
     */
    public final void buildUserArea(Composite userarea) {
        Composite globalcontainer = new Composite(userarea, SWT.NONE);
        
        globalcontainer.setLayout(new GridLayout(1, false));
        titlebar = new Label(globalcontainer, SWT.NONE);
        titlebar.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));
        
        scontainer = new ScrolledComposite(globalcontainer,
                SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        scontainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        container = new Composite(scontainer, SWT.BORDER);
        
        scontainer.setContent(container);
        container.setLayout(new StackLayout());
        
        buttonbar = new Composite(globalcontainer, SWT.NONE);
        buttonbar.setLayout(new StackLayout());

        messagebar = new MessageBar(globalcontainer);
    }
    
    /**
     * Adiciona visões ao container principal
     * @param lviews
     * @param listener
     */
    public void addControllers(List<Controller> lcontrollers, GeneralListener listener) {
        Button button;
        TreeItem item;
        TreeItem subitem;
        Composite container;
        Composite appbuttonbar;
        View view;
        Map<String, View> views;
        Map<String, TreeItem> tree = new HashMap<String, TreeItem>();
        Locale locale = Locale.getDefault();
        
        /*
         * assemblies main tree.
         */
        for (Controller controller : lcontrollers) {
            controller.setLocale(locale);
            controller.setSystem(this);

            views = controller.getViews();
            for (String viewname : views.keySet()) {
                container = new Composite(this.container, SWT.NONE);
                
                view = views.get(viewname);
                view.setSystem(this);
                view.setMessages(controller.getMessages());
                view.setLocale(locale);
                view.buildView(container);
                
                if (tree.containsKey(view.getName())) {
                    item = tree.get(view.getName());
                } else {
                    item = new TreeItem(selector, SWT.NONE);
                    item.setText(view.getName());
                    tree.put(view.getName(), item);
                }
                
                for (ViewAction action : view.getActions()) {
                    viewmap.put(action.getId(), view);
                    
                    if (!action.isVisible())
                        continue;
                    
                    subitem = new TreeItem(item, SWT.NONE);
                    subitem.setText(action.getText());
                    listener.putSelectorItem(subitem, action.getId());
                }
                
                appbuttonbar = new Composite(buttonbar, SWT.NONE);
                appbuttonbar.setLayout(new FormLayout());
                appbuttonbar.pack();
                
                for (String id : buttonbarlist) {
                    button = new Button(appbuttonbar, SWT.PUSH);
                    button.addSelectionListener(controller);
                    
                    controller.putWidget(button, id);
                    view.addButtonbar(id, button);
                }
                
                buttonmap.put(view, appbuttonbar);
            }
        }        
    }
}
