package io.edbm.Input.controller;

import io.edbm.Input.controller.ControllerStateEvent;
import io.edbm.Input.controller.ControllerStateListener;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 * Creates a timer task to poll the ControllerEnvironment at intervals to
 * detect changes, such as new controllers added.
 *
 * @author Steven Frizell
 * @version A
 * @since A
 */
public class ControllerEnvironmentPoller {
    
    /**
     * The original list of controllers within the environment detected
     * at application launch.
     */
    private final List<Controller> originalList;
    
    /**
     * The listener we will notify when a controller is added or removed.
     */
    private ControllerStateListener listener;
    
    /**
     * The timer that executes the polling timer task.
     */
    private Timer pollTimer;
    
    /**
     * The timer task that executes the code to poll for changes.
     */
    private TimerTask pollTask;
    
    /**
     * Constructs a new Controller environment poller. The poller is started as soon as everything is ready.
     */
    public ControllerEnvironmentPoller( final List<Controller> originalList, ControllerStateListener listener) {
        this.originalList = originalList;
        this.listener = listener;
        
        pollTimer = new Timer();
        pollTask = new TimerTask() {
            
            @Override
            public void run () {
                ControllerEnvironment env = ControllerEnvironment.getDefaultEnvironment();
                Controller[] newArray = env.getControllers();
                List<Controller> newList = Arrays.asList( newArray );
                
                for (Controller c : originalList) {
                    ControllerStateEvent cEvent = new ControllerStateEvent( c );
                    if (!newList.contains( c )) {
                        listener.controllerRemoved( cEvent );
                    }
                }
                
                for (Controller c : newList) {
                    ControllerStateEvent cEvent = new ControllerStateEvent( c );
                    if (!originalList.contains( c )) {
                        listener.controllerAdded( cEvent );
                    }
                }
            }
        };
    
        pollTimer.scheduleAtFixedRate(pollTask, 0, 10);
    }
    
    /**
     * Starts polling the environment if the poller was previously stopped.
     */
    public void startPolling() {
        pollTimer.scheduleAtFixedRate(pollTask, 0, 10);
    }
    
    /**
     * Stops the environment poller.
     */
    public void stopPolling() {
        pollTimer.cancel();
    }
}
