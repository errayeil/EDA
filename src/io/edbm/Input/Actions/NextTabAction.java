package io.edbm.Input.Actions;

import io.edbm.UI.BMWindow;
import net.java.games.input.Controller;

/**
 *
 */
public class NextTabAction implements ActionImpl {
    
    public String actionName = "nextTab";
    
    /**
     *
     */
    private final BMWindow appWindow;
    
    /**
     *
     */
    private Controller sourceController;
    
    /**
     *
     */
    private String bind;
    
    /**
     *
     * @param appWindow
     */
    public NextTabAction( BMWindow appWindow ) {
        this.appWindow = appWindow;
    }
    
    
    /**
     *
     */
    @Override
    public void performAction() {
        if (appWindow.isVisible()) {
            appWindow.setSelectedButton( appWindow.getSelectedIndex() + 1 );
        }
    }
}
