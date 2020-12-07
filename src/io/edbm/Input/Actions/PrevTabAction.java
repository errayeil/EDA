package io.edbm.Input.Actions;

import io.edbm.UI.EDAWindow;

public class PrevTabAction implements ActionImpl {
    
    public String actionName = "prevTab";
    
    private EDAWindow appWindow;
    
    /**
     *
     * @param appWindow
     */
    public PrevTabAction( EDAWindow appWindow ) {
        this.appWindow = appWindow;
    }
    
    
    @Override
    public void performAction () {
        if (appWindow.isVisible()) {
            appWindow.setSelectedButton( appWindow.getSelectedIndex() - 1 );
        }
    }
}
