package io.edbm.app;

import io.edbm.Input.ActionDispatcher;
import io.edbm.Input.Controller.ControllerPollerManager;
import io.edbm.Input.Keyboard.NativeHook;
import io.edbm.UI.EDAWindow;
import io.edbm.modules.EDDBM.EDDBParser;
import io.edbm.modules.EDDBM.POJO.Body;
import io.edbm.modules.EDDBM.POJO.Faction;
import io.edbm.modules.EDDBM.POJO.PopulatedSystem;
import io.edbm.modules.EDDBM.POJO.Station;
import io.sentry.Sentry;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.io.IOException;
import java.util.List;
import javax.swing.SwingUtilities;

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
        hook = new NativeHook();
        
        hook.setEventListener( actionDispatcher );
        
        actionDispatcher.setControllerManager( controlManager );
        
        controlManager.createPollers();
        hook.registerHookForKeyPressed();
        hook.registerHookForKeyReleased();
        hook.start();
    
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher( actionDispatcher );
        
        EDDBParser parser = new EDDBParser();
    
        try {
            parser.parseBodiesFor( "Sol" );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    
        SwingUtilities.invokeLater( () -> {
            appWindow.setSize( new Dimension( 1500, 900) );
            appWindow.setLocationRelativeTo( null );
            appWindow.setVisible( true );
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
