package io.edbm.Input.Actions;

import io.edbm.UI.BMWindow;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import net.java.games.input.Controller;

public class VisibilityAction implements ActionImpl {
    
    /**
     *
     */
    private BMWindow appWindow;
    
    /**
     *
     * @param appWindow
     */
    public VisibilityAction( BMWindow appWindow ) {
        this.appWindow = appWindow;
    }
    
    @Override
    public void performAction () {
        SwingUtilities.invokeLater( () -> {
            appWindow.setVisible();
        } );
    }
    
}
