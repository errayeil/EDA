package io.edbm.Input.Controller;

import io.edbm.Utilities.InputUtils.POVDirection;

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