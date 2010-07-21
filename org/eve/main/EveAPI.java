package org.eve.main;

import org.eve.view.Controller;
import org.eve.view.View;

public interface EveAPI {
    public abstract void call(String action);
    
    public abstract Controller getController(View view);
    
    public abstract void setTitleBar(String id);
    
    public abstract void addButton(String id);
    
    public abstract void setMessage(int status, String id);
    
    public abstract void clearMessage();
}
