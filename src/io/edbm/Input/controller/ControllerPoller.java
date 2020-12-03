package io.edbm.Input.controller;

import io.edbm.utilities.InputUtils;
import java.util.Timer;
import java.util.TimerTask;
import net.java.games.input.Controller;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

/**
 * @author Steven Frizell
 * @version 1
 * @since 1
 */
public class ControllerPoller {
    
    /**
     *
     */
    private final Controller controlToPoll;
    
    /**
     *
     */
    private final String controlType;
    
    /**
     *
     */
    private Timer pollTimer;
    
    /**
     *
     */
    private TimerTask pollTask;
    
    
    /**
     *
     */
    public ControllerPoller ( Controller controlToPoll , ControllerInputListener listener ) {
        this.controlToPoll = controlToPoll;
        this.controlType = this.controlToPoll.getType().toString();
        
        EventQueue queue = controlToPoll.getEventQueue();
        
        pollTimer = new Timer();
        pollTask = new TimerTask() {
            @Override
            public void run () {
                controlToPoll.poll();
                
                Event event = new Event();
                while ( queue.getNextEvent( event ) ) {
                    
                    ControllerInputEvent cEvent = new ControllerInputEvent( event );
                    cEvent.set( controlToPoll );
                    
                    String eventStr = event.toString();
                    String input = InputUtils.parseInput( eventStr );
                    float value = InputUtils.parseValue( eventStr );
                    
                    if ( controlType.equals( "Stick" ) ) {
                        if ( input.contains( "Button" ) ) {
                            if ( value == 1.0f ) {
                                listener.buttonPressed( cEvent );
                                
                                
                            } else if ( value == 0.0f ) {
                                listener.buttonReleased( cEvent );
                            }
                        } else if ( input.contains( "Hat Switch" ) ) {
                            if ( value == 1.0f || value == 0.50f || value == 0.75f || value == 0.25f ) {
                                listener.hatPushed( cEvent, InputUtils.getDirection( value ) );
                            } else {
                                listener.hatReleased( cEvent );
                            }
                        } else if ( input.contains( "Axis" ) ) {
                            listener.axisMoved( cEvent );
                        }
                    }
                }
            }
        };
        
        pollTimer.scheduleAtFixedRate( pollTask , 0 , 10 );
    }
    
    /**
     * Starts polling the controller.
     */
    public void pollController () {
        pollTask.run();
    }
    
    /**
     *
     */
    public void stopPolling () {
        pollTask.cancel();
    }
    
    
    
    /**
     *
     */
    public Controller getPolledController () {
        return controlToPoll;
    }
}
