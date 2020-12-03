package io.edbm.Input.controller;

import net.java.games.input.Controller;

/**
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public final class ControllerStateEvent {
    
    /**
     *
     */
    private Controller sourceController;
    
    /**
     *
     * @param sourceOfEvent
     */
    protected ControllerStateEvent(Controller sourceOfEvent) {
        this.sourceController = sourceOfEvent;
    }
    
    /**
     *
     */
    public Controller getController() {
        return sourceController;
    }
    
}
