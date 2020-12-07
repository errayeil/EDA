package io.edbm.Input.Controller;

public interface ControllerStateListener {
    
    void controllerAdded(ControllerStateEvent event);
    
    void controllerRemoved(ControllerStateEvent event);
}
