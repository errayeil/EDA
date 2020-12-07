package io.edbm.Input.Controller;


import io.edbm.Input.ActionDispatcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 * @author Steven Frizell
 * @version 1
 * @since 1
 */
public class ControllerPollerManager {
    
    /**
     * The controller environment
     */
    private ControllerEnvironment env = ControllerEnvironment.getDefaultEnvironment();
    
    /**
     *
     */
    private ControllerEnvironmentPoller envPoller;
    
    /**
     * A list of all controllers currently available in the Controller Environment.
     */
    private final List<Controller> controllers;
    
    /**
     * Pollers attached to controllers.
     */
    private final Map<Controller, ControllerPoller> pollers;
    
    /**
     * Dispatches actions when notified of controller events.
     */
    private final ActionDispatcher listener;
    
    /**
     *
     */
    private boolean pollersCreated = false;
    
    /**
     *
     */
    public ControllerPollerManager ( ActionDispatcher listener) {
        this.controllers = new ArrayList<>();
        this.pollers = new HashMap<>();
        this.listener = listener;

        controllers.addAll(Arrays.asList( env.getControllers() ) );
    
        this.envPoller = new ControllerEnvironmentPoller( controllers, listener  );
    }
    
    /**
     * Returns a map containing all controllers with attached pollers.
     * @return
     */
    public Map<Controller, ControllerPoller> getPollers() {
        return pollers;
    }
    
    /**
     * Stops the poller for the specified  controller (if there is one) and removes from the ControllerManager.
     * @param controller
     * @return
     */
    public void removePollerFor(Controller controller) {
        if (pollers.containsKey( controller )) {
            ControllerPoller poller = pollers.get( controller );
            poller.stopPolling();
    
            pollers.remove( controller, poller );
        }
    }
    
    /**
     *
     */
    public void stopEnvPoller() {
        envPoller.stopPolling();
    }
    
    /**
     *
     */
    public void startEnvPoller() {
        envPoller.startPolling();
    }
    
    /**
     *
     * @param controller
     */
    public void createPollerFor(final Controller controller) {
        String type = controller.getType().toString();
    
        if ( type.equals( "Stick" ) || type.equals( "Mouse" ) || type.equals( "Keyboard" ) ) {
            ControllerPoller poller = new ControllerPoller( controller , listener );
        
            pollers.put( controller, poller );
        }
    }
    
    /**
     * Creates pollers to listen for input actions from controllers.
     */
    public boolean createPollers() {
        
        if ( pollersCreated ) {
            return false;
        }
    
        for ( Controller c : controllers ) {
            String type = c.getType().toString();
        
            if ( type.equals( "Stick" ) || type.equals( "Mouse" ) || type.equals( "Keyboard" ) ) {
                ControllerPoller poller = new ControllerPoller( c , listener );
                
                pollers.put( c, poller );
            }
        
        }
        
        if (!pollers.isEmpty()) {
            pollersCreated = true;
        }
        
        return pollersCreated;
    }
}

