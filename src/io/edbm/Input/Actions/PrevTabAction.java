package io.edbm.Input.Actions;

import io.edbm.UI.Windows.EDADialog;

public class PrevTabAction implements ActionImpl {
    
    public String actionName = "prevTab";
    
    private EDADialog appWindow;
    
    /**
     *
     * @param appWindow
     */
    public PrevTabAction( EDADialog appWindow ) {
        this.appWindow = appWindow;
    }
    
    
    @Override
    public void performAction () {
        if (appWindow.isVisible()) {
            appWindow.setSelectedButton( appWindow.getSelectedIndex() - 1 );
        }
    }
}
