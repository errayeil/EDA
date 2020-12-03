package io.edbm.Input.controller;

public interface ControllerStateListener {
    
    void controllerAdded(ControllerStateEvent event);
    
    void controllerRemoved(ControllerStateEvent event);
}
