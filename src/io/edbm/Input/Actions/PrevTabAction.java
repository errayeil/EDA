package io.edbm.Input.Actions;

import io.edbm.UI.BMWindow;
import net.java.games.input.Controller;

public class PrevTabAction implements ActionImpl {
    
    public String actionName = "prevTab";
    
    private BMWindow appWindow;
    
    /**
     *
     * @param appWindow
     */
    public PrevTabAction( BMWindow appWindow ) {
        this.appWindow = appWindow;
    }
    
    
    @Override
    public void performAction () {
        if (appWindow.isVisible()) {
            appWindow.setSelectedButton( appWindow.getSelectedIndex() - 1 );
        }
    }
}
