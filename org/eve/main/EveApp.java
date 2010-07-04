package org.eve.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eve.view.View;

public class EveApp implements EveAPI {
    private final static String VERSION = "1.9.3";
    private List<View> views;
    private Map<String, View> viewmap;
    private GeneralListener listener;
    private Composite container;
    
    public EveApp() {
        viewmap = new HashMap<String, View>();
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
    
    public final void setViews(List<View> views) {
        this.views = views;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    public final List<View> getViews() {
        return views;
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
    
    /*
     * 
     * Services
     * 
     */
    
    @Override
    public final void call(String action) {
        View view = viewmap.get(action);
        
        if (view == null)
            return;
        
        if (container != null)
            container.setVisible(false);
        
        view.reload(action);
        container = view.getContainer();
        container.setVisible(true);
    }
}
