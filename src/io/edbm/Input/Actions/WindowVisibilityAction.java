package io.edbm.Input.Actions;

import io.edbm.UI.Windows.EDADialog;
import javax.swing.SwingUtilities;

public class WindowVisibilityAction implements ActionImpl {
    
    /**
     *
     */
    private EDADialog appWindow;
    
    /**
     *
     * @param appWindow
     */
    public WindowVisibilityAction ( EDADialog appWindow ) {
        this.appWindow = appWindow;
    }
    
    @Override
    public void performAction () {
        SwingUtilities.invokeLater( () -> {
            appWindow.setVisible();
        } );
    }
    
}
