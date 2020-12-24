package io.edbm.app;

import io.edbm.Input.ActionDispatcher;
import io.edbm.Input.Controller.ControllerPollerManager;
import io.edbm.Input.Keyboard.NativeHook;
import io.edbm.UI.EDAWindow;

import io.edbm.Utilities.Utils;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class RunEDA {
    
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
    private RunEDA () {
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
        /*actionDispatcher = new ActionDispatcher( appWindow );
        controlManager = new ControllerPollerManager( actionDispatcher );*/
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
        //KeyboardFocusManager.getCu

        //KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher( actionDispatcher );

        EDASetup setup = new EDASetup();
        System.out.println( "Is Java path null " + Utils.isJavaPathNull());
        System.out.println("Java Path " + Utils.getJavaPath());
        System.out.println("Java Version " + Utils.getJavaVersion());
        System.out.println("User home " + Utils.getUserHome());

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
        new RunEDA();
    }
}
