package io.edbm.Input.controller;

import io.edbm.utilities.InputUtils.POVDirection;
import net.java.games.input.Component.POV;
import net.java.games.input.Controller;

public interface ControllerInputListener {
    
    /**
     * Used for controller related events.
     */
    void buttonPressed ( ControllerInputEvent event );
    
    /**
     * Used for controller related events.
     */
    void buttonReleased ( ControllerInputEvent event );
    
    /**
     *
     */
    void hatPushed( ControllerInputEvent event, POVDirection direction );
    
    /**
     *
     * @param event
     */
    void hatReleased( ControllerInputEvent event);
    
    /**
     * Used for controller related events.
     */
    void axisMoved ( ControllerInputEvent event );
}