package io.edbm.Input.Actions;

import io.edbm.UI.EDAWindow;
import javax.swing.SwingUtilities;

public class WindowVisibilityAction implements ActionImpl {
    
    /**
     *
     */
    private EDAWindow appWindow;
    
    /**
     *
     * @param appWindow
     */
    public WindowVisibilityAction ( EDAWindow appWindow ) {
        this.appWindow = appWindow;
    }
    
    @Override
    public void performAction () {
        SwingUtilities.invokeLater( () -> {
            appWindow.setVisible();
        } );
    }
    
}
