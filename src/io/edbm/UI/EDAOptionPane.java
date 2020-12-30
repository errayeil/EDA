package io.edbm.UI;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class EDAOptionPane {
    
    
    
    /**
     *
     */
    private EDAOptionPane() {}
    
    /**
     *
     * @param parentComponent
     * @param actionMessage
     */
    public static JDialog showProgressDialog(Component parentComponent, String actionMessage) {
        JDialog dialog = new JDialog();
        EDAProgressBar progress = new EDAProgressBar(  );
        JLabel label = new JLabel(actionMessage);

        label.setFont( new Font("Eurostile", Font.PLAIN, 16) );
        label.setForeground( Color.BLACK );
        label.setSize( new Dimension(200, 15) );
        
        progress.setMaximumSize( new Dimension(200, 30) );
        progress.setSize( new Dimension( 200, 30) );
        progress.setIndeterminate( true );
        progress.setPaintedString( actionMessage );

        dialog.setUndecorated( true );
        dialog.setModal( true );
        dialog.setAlwaysOnTop( true );
        dialog.setResizable( false );
        
        dialog.setMinimumSize( new Dimension(400, 100) );
        dialog.setSize( new Dimension(400, 100) );
        
        dialog.setLayout( null );
        dialog.setContentPane( new EDABackgroundContainer() );
        dialog.add( progress );
        dialog.add( label );

        label.setLocation( 120, 15 );
        progress.setLocation( 100, 35 );

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
