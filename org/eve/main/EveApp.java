package org.eve.main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FillLayout;
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
    private final static String VERSION = "1.9.8";
    private GeneralListener listener;
    private ScrolledComposite scontainer;
    private Composite container;
    private Tree selector;
    private Label titlebar;
    private Composite buttonbar;
    private MessageBar messagebar;
    private List<Controller> controllers;
    private Map<View, List<String>> buttonbarmap;
    private Map<View, Controller> controlmap;
    private Map<String, View> viewmap;
    private Map<View, Composite> buttonmap;
    
    public EveApp() {
        viewmap = new HashMap<String, View>();
        controlmap = new HashMap<View, Controller>();
        buttonbarmap = new HashMap<View, List<String>>();
        buttonmap = new HashMap<View, Composite>();
    }
    
    /*
     * 
     * Setters
     * 
     */
    
    /**
     * Define listeners
     * @param listener
     */
    public final void setListener(GeneralListener listener) {
        this.listener = listener;
        this.listener.setSystem(this);
    }
    
    /**
     * Define controladores
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
     * Define seletor
     * @param selector
     */
    public final void setSelector(Tree selector) {
        this.selector = selector;
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
     * @see org.eve.main.EveAPI#setMessage(int, java.lang.String)
     */
    @Override
    public final void setMessage(int status, String message) {
        messagebar.setMessage(status, message);
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * Retorna controladores
     * @return
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
     * (non-Javadoc)
     * @see org.eve.main.EveAPI#getController(org.eve.view.View)
     */
    @Override
    public final Controller getController(View view) {
        return controlmap.get(view);
    }
    
    /*
     * 
     * Services
     * 
     */
    
    /*
     * (non-Javadoc)
     * @see org.eve.main.EveAPI#call(java.lang.String)
     */
    @Override
    public final void call(String action) {
        int width;
        int height;
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
        
        width = view.getWidth();
        if (width > 0)
            container.setSize(width, container.getSize().y);

        height = view.getHeight();
        if (height > 0)
            container.setSize(container.getSize().x, height);
        
        layout = (StackLayout)buttonbar.getLayout();
        layout.topControl = buttonmap.get(view);
        buttonbar.layout();
        buttonbar.pack();
        
        scontainer.setOrigin(0, 0);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.main.EveAPI#addButton(java.lang.String)
     */
    @Override
    public final void addButton(View view, String id) {
        buttonbarmap.get(view).add(id);        
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
     * Constrói conteineres da aplicação
     * @param container
     */
    public final void buildUserArea(Composite userarea) {
        Composite globalcontainer = new Composite(userarea, SWT.NONE);
        
        globalcontainer.setLayout(new GridLayout(1, false));
        
        titlebar = new Label(globalcontainer, SWT.NONE);
        titlebar.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        
        scontainer = new ScrolledComposite(globalcontainer,
                SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        scontainer.setLayout(new FillLayout());
        scontainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        container = new Composite(scontainer, SWT.NONE);
        container.setLayout(new StackLayout());
        
        scontainer.setContent(container);
        
        buttonbar = new Composite(globalcontainer, SWT.NONE);
        buttonbar.setLayout(new StackLayout());
        buttonbar.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));

        messagebar = new MessageBar(globalcontainer);
    }
    
    /**
     * Adiciona visões ao container principal
     * @param lviews
     * @param listener
     */
    public void addControllers(List<Controller> lcontrollers,
            GeneralListener listener) {
        
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
                
                buttonbarmap.put(view, new LinkedList<String>());
                
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
                item.setExpanded(true);
                
                appbuttonbar = new Composite(buttonbar, SWT.NONE);
                appbuttonbar.setLayout(new FormLayout());
                appbuttonbar.pack();
                
                for (String id : buttonbarmap.get(view)) {
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
