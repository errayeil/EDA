package io.edbm.Input.Binds;

import io.edbm.Input.Actions.ActionImpl;
import java.util.List;
import java.util.Map;
import net.java.games.input.Controller;

/**
 *
 */
public class Bind {
    
    /**
     *
     */
    private ActionImpl bindAction;
    
    /**
     *
     */
    private String bindActionName;
    
    /**
     *
     */
    private Map<Controller, String> boundedControllers;
    
    /**
     *
     */
    public Bind() {
    
    }
    
    /**
     *
     * @param controller
     * @param bind
     */
    public Bind(Controller controller, String bind) {
    
    }
}
