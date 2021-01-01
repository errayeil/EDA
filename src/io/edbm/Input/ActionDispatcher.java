package io.edbm.Input;

import io.edbm.Input.Actions.ActionImpl;
import io.edbm.Input.Actions.NextTabAction;
import io.edbm.Input.Actions.PrevTabAction;
import io.edbm.Input.Actions.ScrollDownAction;
import io.edbm.Input.Actions.ScrollUpAction;
import io.edbm.Input.Actions.SelectAction;
import io.edbm.Input.Actions.WindowVisibilityAction;
import io.edbm.Input.Controller.ControllerInputEvent;
import io.edbm.Input.Controller.ControllerInputListener;
import io.edbm.Input.Controller.ControllerPollerManager;
import io.edbm.Input.Controller.ControllerStateEvent;
import io.edbm.Input.Controller.ControllerStateListener;
import io.edbm.Input.Keyboard.NativeKeyListener;
import io.edbm.UI.Windows.EDADialog;
import io.edbm.Utilities.InputUtils;
import io.edbm.Utilities.InputUtils.POVDirection;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import org.simplenativehooks.events.NativeKeyEvent;

/**
 * Receives events when a controller is added or removed, when a controller
 * performs an action, and when a key is pressed on the native level for
 * out of focus events, such as opening the window when Elite Dangerous is visible.
 *
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class ActionDispatcher implements ControllerInputListener,
                                         ControllerStateListener,
                                         NativeKeyListener,
                                         KeyListener,
                                         KeyEventDispatcher {
    
    /**
     *
     */
    private volatile EDADialog appWindow;
    
    /**
     *
     */
    private volatile Map<String, ActionImpl> actionsMap;
    
    /**
     *
     */
    private volatile Map<Controller, Map<String, String>> inputBindsMap;
    
    /**
     * Manages various aspects of the controller environment, such as pollers.
     */
    private ControllerPollerManager controllerManager;
    
    /**
     * The controller environment.
     */
    private final ControllerEnvironment env = ControllerEnvironment.getDefaultEnvironment();
    
    /**
     *
     */
    private final List<String> modifierKeys;
    
    /**
     *
     */
    private final List<String> nonModKeys;
    
    /**
     *
     */
    public ActionDispatcher ( final EDADialog window) {
        
        
        this.appWindow = window;
        this.actionsMap = new ConcurrentHashMap<>();
        this.inputBindsMap = new ConcurrentHashMap<>();
        this.modifierKeys = new ArrayList<>();
        this.nonModKeys = new ArrayList<>();
        
        actionsMap.put( "nextTabAction", new NextTabAction( appWindow ) );
        actionsMap.put( "prevTabAction", new PrevTabAction( appWindow ) );
        actionsMap.put( "scrollDownAction", new ScrollDownAction( appWindow ) );
        actionsMap.put( "scrollUpAction", new ScrollUpAction( appWindow ) );
        actionsMap.put( "selectComponentAction", new SelectAction( appWindow) );
        actionsMap.put( "visibilityWindowAction", new WindowVisibilityAction( appWindow) );
        
        Controller[] controllers = env.getControllers();

        //TODO: look into OSX support
        /*for (Controller c : controllers) {
            if (c != null) {
                if (c.getName().equals( "Stream Deck" )) {
                    System.out.println(c.getType());
                    Component[] cs = c.getComponents();

                    for (Component comp : cs) {
                        System.out.println(comp.getName());
                    }
                }
            } else {
                Sentry.captureMessage( "Controller is null. Operating System : " + System.getProperty( "os.name" ) );
            }
        }*/
        
        //todo load input binds map from external source
        createFakeBinds();
    }
    
    /**
     *
     * @param manager
     */
    public void setControllerManager(final ControllerPollerManager manager) {
        this.controllerManager = manager;
    }
    
    /**
     * For testing.
     * Creates a set of binds.
     */
    private void createFakeBinds() {
        Controller[] controllers = env.getControllers();
        Map<String, String> map;
        
        for (Controller c : controllers) {
            if (c.getType().toString().equals( "Stick" )) {
                map = new ConcurrentHashMap<>();
                Component[] cs = c.getComponents();
    
                for ( Component comp : cs ) {
        
                    String compName = comp.getName();
    
                    switch ( compName ) {
                        case "Button 2":
                            map.put( compName , "prevTabAction" );
                            break;
                        case "Button 3":
                            map.put( compName , "nextTabAction" );
                            break;
                        case "Button 0":
                            map.put( compName , "selectComponentAction" );
                            break;
                        case "Hat Switch":
                            map.put( compName + "-" + "0.75" , "scrollDownAction" );
                            map.put( compName + "-" + "0.25" , "scrollUpAction" );
                            break;
                        case "Button 10":
                            map.put( compName , "visibilityWindowAction" );
                            break;
                    }
                }
    
                if (!map.isEmpty()) {
                    inputBindsMap.put( c, map );
                }
            }
        }
    }
    
    /**
     *
     * Performs any action associated with controller button binds.
     *
     * @param eventStr The string representation of the event.
     * @param eventControl The source of the event.
     */
    private void doBoundedActionForControllerButton ( String eventStr, Controller eventControl) {
        String input = InputUtils.parseInput( eventStr);
    
        if (input.contains( "Button" )) {
            Map<String, String> map = inputBindsMap.get( eventControl );
            
            if (!map.isEmpty() && map.containsKey( input )) {
                String actionStr = map.get( input );
            
                ActionImpl action = actionsMap.get( actionStr );
                assert action != null;
                action.performAction();
            }
        }
    }
    
    /**
     *
     * @param eventStr The string representation of the event.
     * @param eventControl The source of the event.
     * @param direction The direction the HAT/POV switch was pressed.
     */
    private void doBoundedActionForControllerHat(String eventStr, Controller eventControl, POVDirection direction) {
        String input = InputUtils.parseInput( eventStr);
        
        if (input.contains( "Hat Switch" )) {
            float value = InputUtils.parseValue( eventStr );
            String merged = input + "-" + value;
    
            Map<String, String> map = inputBindsMap.get( eventControl );
    
            if (!map.isEmpty() && map.containsKey( merged ) ) {
                String actionStr = map.get( merged );
        
                ActionImpl action = actionsMap.get( actionStr );
                assert action != null;
                action.performAction();
            }
        }
    }
    
    /**
     * Performs any action associated to any keyboard actions with binds.
     */
    private void doBoundedActionForKeyboard(String key) {
    
    }
    
    /**
     * When a controller is removed from the environment those events will arrive here.
     * @param controllerEvent
     */
    @Override
    public void controllerRemoved ( ControllerStateEvent controllerEvent ) {
        Controller eventControl = controllerEvent.getController();
        
        controllerManager.removePollerFor( eventControl );
    }
    
    /**
     * When a controller is added to the environment those events will arrive here.
     * @param controllerEvent
     */
    @Override
    public void controllerAdded ( ControllerStateEvent controllerEvent ) {
        Controller eventControl = controllerEvent.getController();
        
        controllerManager.createPollerFor( eventControl );
    }
    
    /**
     * When any button on a controller is pressed those events will arrive here.
     * @param event
     */
    @Override
    public void buttonPressed ( ControllerInputEvent event ) {
        doBoundedActionForControllerButton( event.toString(), event.getController() );
    }
    
    /**
     * When a HAT/POV switch on a Joystick is pressed those events will arrive here.
     * @param event
     */
    @Override
    public void hatPushed ( ControllerInputEvent event, POVDirection direction) {
        doBoundedActionForControllerHat( event.toString(), event.getController(), direction );
    }
    
    @Override
    public boolean dispatchKeyEvent ( KeyEvent e ) {
        return false;
    }
    
    /**
     * When EDC is not in focus all key events arrive here.
     * @param event
     */
    @Override
    public void nativeKeyPressed( NativeKeyEvent event ) {
        String key = KeyEvent.getKeyText( event.getKey() );
        doBoundedActionForKeyboard( key );
    }
    
    @Override
    public void nativeKeyReleased ( NativeKeyEvent event ) {
        String key = KeyEvent.getKeyText( event.getKey() );
    }
    
    /**
     * When EDC is in focus all key events should arrive here.
     * @param e
     */
    @Override
    public void keyPressed ( KeyEvent e ) {
    
    }
    
    /**METHODS BELOW ARE CURRENTLY UNUSED**/
    
    @Override
    public void buttonReleased ( ControllerInputEvent event ) {
    
    }
    
    @Override
    public void hatReleased ( ControllerInputEvent event ) {
    
    }
    
    @Override
    public void axisMoved ( ControllerInputEvent event ) {
    
    }
    
    @Override
    public void keyTyped ( KeyEvent e ) {
    
    }
    
    @Override
    public void keyReleased ( KeyEvent e ) {
    
    }
}