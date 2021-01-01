package io.edbm.app;

import io.edbm.Input.ActionDispatcher;
import io.edbm.Input.Controller.ControllerPollerManager;
import io.edbm.Input.Keyboard.NativeHook;
import io.edbm.UI.EDAOptionPane;
import io.edbm.UI.Windows.EDADialog;
import io.edbm.modules.NDM.NotificationManager;
import io.sentry.Sentry;
import io.sentry.protocol.SentryId;

import javax.swing.*;

/**
 *
 */
public class RunEDA {
    
    /**
     *
     */
    private EDASetup setup;
    
    /**
     * The primary window of the application.
     */
    private EDADialog appWindow;
    
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
        setup = new EDASetup();
        
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        } catch ( ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e ) {
            e.printStackTrace();
            SentryId id = Sentry.captureException( e );
            NotificationManager.captureUserFeedback( id );
        }
        
        init();
    }
    
    /**
     *
     */
    private void init () {
        EDAOptionPane.showProgressDialog( null, "Testing dialog." );
        //setup.putPermissionsForTesting();
        //setup.clearEverythingForTesting();
        //setup.putPermissionsForTesting();

        //setup.startFirstTimeSetup();
//
//        appWindow = new EDAWindow();
//        actionDispatcher = new ActionDispatcher( appWindow );
//        controlManager = new ControllerPollerManager( actionDispatcher );
//        hook = new NativeHook();
//
//        //TODO: Look into OSX support
//        hook.setEventListener( actionDispatcher );
//        actionDispatcher.setControllerManager( controlManager );
//        controlManager.createPollers();
//        hook.registerHookForKeyPressed();
//        hook.registerHookForKeyReleased();
//        hook.start();
//
//        SwingUtilities.invokeLater( ()->{
//            System.out.println( "Invoking" );
//            appWindow.initButtons();
//            appWindow.setSize( new Dimension( 1200 , 750 ) );
//            appWindow.setLocationRelativeTo( null );
//            //appWindow.setVisible(true );
//        } );
//
//        EDAOptionPane.showProgressDialog( null, "Testing progress window" );
    }
    
    /**
     *
     */
    public static void main ( String[] args ) {
        new RunEDA();
    }
}
