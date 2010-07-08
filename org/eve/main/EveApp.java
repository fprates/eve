package org.eve.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eve.view.Controller;
import org.eve.view.View;

public class EveApp implements EveAPI {
    private final static String VERSION = "1.9.3";
    private List<Controller> controllers;
    private Map<View, Controller> controlmap;
    private Map<String, View> viewmap;
    private GeneralListener listener;
    private Composite container;
    
    public EveApp() {
        viewmap = new HashMap<String, View>();
        controlmap = new HashMap<View, Controller>();
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
    
    public final void setContainer(Composite container) {
        this.container = container;
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
     * Retorna o mapa de visões
     * @return
     */
    public final Map<String, View> getViewMap() {
        return viewmap;
    }
    
    /**
     * Retorna versão do sistema
     * @return
     */
    public final String getVersion() {
        return VERSION;
    }
    
    /**
     * Retorna container de aplicações
     * @return
     */
    public final Composite getContainer() {
        return container;
    }
    
    /*
     * 
     * Services
     * 
     */
    
    @Override
    public final void call(String action) {
        StackLayout layout;
        Controller controller;
        View view = viewmap.get(action);
        
        if (view == null)
            return;
        
        controller = controlmap.get(view);
        controller.getMessageBar().clear();
        view.reload(action);
        
        System.err.println(view.getContainer().toString());
        layout = (StackLayout)container.getLayout();
        layout.topControl = view.getContainer();
        container.layout();
        container.pack();
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.main.EveAPI#getController(org.eve.view.View)
     */
    @Override
    public final Controller getController(View view) {
        return controlmap.get(view);
    }
}
