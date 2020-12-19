package io.edbm.app;

import io.edbm.Input.ActionDispatcher;
import io.edbm.Input.Controller.ControllerPollerManager;
import io.edbm.Input.Keyboard.NativeHook;
import io.edbm.UI.EDAWindow;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class Run {
    
    /**
     * The primary window of the application.
     */
    private EDAWindow appWindow;
    
    /**
     * Detects controller (including mouse and keyboard) events.
     */
    private ControllerPollerManager controlManager;
    
    /**
     * Performs action based off input events from controllers or keyboards.
     */
    private ActionDispatcher actionDispatcher;
    
    /**
     *
     */
    private NativeHook hook;
    
    /**
     *
     */
    private Run() {
       boolean installed = checkInstall();
       
       if (!installed) {
           install();
       }
       
       boolean updates = checkUpdates();
       
       if (updates) {
       
       }
       
       init();
    }
    
    /**
     *
     */
    private boolean checkInstall() {
        
        return true;
    }
    
    /**
     *
     */
    private void install() {
    
    }
    
    /**
     *
     */
    private boolean checkUpdates() {
        
        return true;
    }
    
    /**
     *
     */
    private void init() {

        appWindow = new EDAWindow();
        actionDispatcher = new ActionDispatcher( appWindow );
        controlManager = new ControllerPollerManager( actionDispatcher );
        //hook = new NativeHook();

        /*
        TODO: Look into OSX support
        hook.setEventListener( actionDispatcher );
        actionDispatcher.setControllerManager( controlManager );
        controlManager.createPollers();
        hook.registerHookForKeyPressed();
        hook.registerHookForKeyReleased();
        hook.start();
        */

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher( actionDispatcher );

        SwingUtilities.invokeLater( () -> {
            appWindow.initButtons();
            appWindow.setSize( new Dimension( 1200, 750) );
            appWindow.setLocationRelativeTo( null );
            appWindow.setVisible();
        } );
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        new Run();
    }
}
