package org.eve.view;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eve.main.EveAPI;
import org.eve.view.ViewAction;
import org.springframework.context.MessageSource;

/**
 * Implementação parcial de visão
 * @author francisco.prates
 *
 */
public abstract class AbstractView implements View {
    private String name;
    private MessageSource messages;
    private Locale locale;
    private EveAPI system;
    private Composite container;
    private List<ViewAction> actions;
    private Map<String, Button> buttons;
    private int width;
    private int height;
    
    public AbstractView() {
        actions = new LinkedList<ViewAction>();
        buttons = new LinkedHashMap<String, Button>();
        width = 0;
        height = 0;
    }
    
    /*
     * 
     * Setters
     *  
     */
    
    /**
     * Ajusta flag de visibilidade para botão
     * @param id
     * @param visible
     */    
    protected final void setButtonVisible(String id, boolean visible) {
        buttons.get(id).setVisible(visible);
    }
    
    /**
     * Ajusta título
     * @param id
     */
    protected final void setTitlebar(String id) {
        system.setTitleBar(messages.getMessage(id, null, id, locale));
    }
    
    /**
     * Ajusta largura do container da aplicação
     * @param width
     */
    protected final void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * Ajusta altura do container da aplicação
     * @param height
     */
    protected final void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * Ajusta identificador da visão
     * @param name
     */
    protected final void setName(String name) {
        this.name = name;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#setLocale(java.util.Locale)
     */
    @Override
    public final void setLocale(Locale locale) {
        this.locale = locale;
        system.getController(this).setLocale(locale);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#setMessages(org.springframework.context.MessageSource)
     */
    @Override
    public final void setMessages(MessageSource messages) {
        this.messages = messages;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#setSystem(org.eve.main.EveAPI)
     */
    @Override
    public final void setSystem(EveAPI system) {
        this.system = system;
    }
    
    /*
     * 
     * Getters
     * 
     */
    
    /**
     * Retorna a localização atual
     * @return
     */
    protected final Locale getLocale() {
        return locale;
    }
    
    /**
     * Retorna mensagem
     * @param id
     * @return
     */
    protected final String getMessage(String id) {
        return messages.getMessage(id, null, id, locale);
    }
    
    /**
     * Retorna controlador associado
     * @return
     */
    protected final Controller getController() {
        return system.getController(this);
    }

    /*
     *  (non-Javadoc)
     * @see org.eve.view.View#getActions()
     */
    @Override
    public final List<ViewAction> getActions() {
        return actions;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#getName()
     */
    @Override
    public final String getName() {
        return name;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#getContainer()
     */
    @Override
    public final Composite getContainer() {
        return container;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#getWidth()
     */
    @Override
    public final int getWidth() {
        return width;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#getHeight()
     */
    @Override
    public final int getHeight() {
        return height;
    }
    
    /*
     * 
     * Others
     * 
     */
    
    /**
     * Adiciona botão à barra de botões
     * @param id
     */
    protected final void addButton(String id) {
        system.addButton(this, id);
    }
    
    /**
     * Adiciona ação à visão
     * @param id
     */
    protected final void addAction(String id) {
        actions.add(new ViewAction(this, id,
                messages.getMessage(id, null, id, locale), true));
    }
    
    /**
     * Adiciona ação à visão
     * @param id
     * @param visible
     */
    protected final void addAction(String id, boolean visible) {
        actions.add(new ViewAction(this, id,
                messages.getMessage(id, null, id, locale), visible));
    }
    
    /**
     * Processamento da visao do usuário
     * @param container
     */
    protected abstract void defineView(Composite container);
    
    /**
     * Cria nova instância de formulário
     * @param id
     * @return
     */
    protected final Form addForm(String id) {
        Controller controller = getController();
        Form form = new Form(id);
        
        form.setController(controller);
        form.setMessages(messages);
        form.setSystem(system);
        form.setLocale(locale);

        controller.putForm(id, form);
        
        return form;
    }
    
    /**
     * Cria nova instância do assistente de tabela
     * @param id
     * @return
     */
    protected final TableAssist addTable(String id) {
        Controller controller = getController();
        TableAssist table = new TableAssist();
        
        table.setController(controller);
        table.setLocale(locale);
        table.setMessages(messages);
        table.setSystem(system);
        
        controller.putTable(id, table);
        
        return table;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#buildView()
     */
    @Override
    public final void buildView(Composite container) {
        this.container = container;
        name = messages.getMessage("name", null, locale);
        defineView(container);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eve.view.View#addButtonbar(java.lang.String, org.eclipse.swt.widgets.Button)
     */
    @Override
    public final void addButtonbar(String id, Button button) {
        button.setText(messages.getMessage(id, null, id, locale));
        buttons.put(id, button);
    }
}
