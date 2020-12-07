package io.edbm.Input.Controller;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Event;

public class ControllerInputEvent {
    
    private Controller source;
    private Component component;
    private float value;
    private long nanos;
    
    private String action;
    
    
    public ControllerInputEvent ( final Event event) {
        component = event.getComponent();
        value = event.getValue();
        nanos = System.nanoTime();
    }
    
    
    public final void set ( Controller source ) {
       this.source = source;
    }
    
    public final Controller getController() {
        return source;
    }
    /**
     *
     * @return
     */
    public final String getAction() {
        return action;
    }
    
    public final Component getComponent () {
        return this.component;
    }
    
    public final float getValue () {
        return this.value;
    }
    
    public final long getNanos () {
        return this.nanos;
    }
    
    public final String toString () {
        return "Event: component = " + this.component + " | value = " + this.value;
    }
}
