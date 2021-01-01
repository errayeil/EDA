package io.edbm.UI;

import io.edbm.UI.Component.EDAProgressBar;
import io.edbm.UI.LAF.EDAThemeColors;
import io.edbm.UI.Windows.EDABorder;
import io.edbm.UI.Windows.EDAPane;

import javax.swing.*;
import java.awt.*;

/**
 * Custom option pane class to display various dialogs skinned like the ED
 * interface.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAOptionPane {
    
    
    
    /**
     *
     */
    private EDAOptionPane() {}
    
    /**
     * Shows a dialog with an indeterminate progress bar to show the user
     * a background process is occurring.
     *
     * @param parentComponent Component the dialog should be centered on.
     * @param actionMessage The message displayed in the dialog.
     */
    public static JDialog showProgressDialog(Component parentComponent, String actionMessage) {
        EDAProgressBar progress = new EDAProgressBar(  );
        JDialog dialog = new JDialog() {
            @Override
            public void dispose( ) {
                super.dispose( );
            }
        };

        EDAPane container = new EDAPane();
        JLabel label = new JLabel(actionMessage);

        label.setFont( new Font("Eurostile", Font.PLAIN, 16) );
        label.setBackground( new Color(0,0,0,255));
        label.setForeground( EDAThemeColors.UNSELECTED_TEXT_COLOR );
        label.setSize( new Dimension(200, 15) );
        
        progress.setMaximumSize( new Dimension(200, 30) );
        progress.setSize( new Dimension( 200, 30) );
        progress.setIndeterminate( true );
        progress.setPaintedString( actionMessage );

        dialog.setUndecorated( true );
        dialog.setBackground( new Color(0,0,0, 200) );
        dialog.setModal( true );
        dialog.setAlwaysOnTop( true );
        dialog.setResizable( false );
        
        dialog.setMinimumSize( new Dimension(400, 100) );
        dialog.setSize( new Dimension(400, 100) );

        container.setLayout( null );
        container.setBorder( new EDABorder() );
        container.add( progress );
        container.add( label );

        label.setLocation( 120, 15 );
        progress.setLocation( 100, 45 );

        dialog.setContentPane( container );
        dialog.setLocationRelativeTo( null );
        dialog.pack();

    
        SwingUtilities.invokeLater( () -> {
            dialog.setVisible( true );
        } );
        
        return dialog;
    }
    
    /**
     *
     * @return
     */
    public static String showInputDialog( Component parentComponent, String message, String title) {
        
        return null;
    }
}
