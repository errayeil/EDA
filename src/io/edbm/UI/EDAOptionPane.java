package io.edbm.UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

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
        JProgressBar progress = new JProgressBar();
        
        progress.setMaximumSize( new Dimension(200, 40) );
        progress.setSize( new Dimension( 200, 40) );
        progress.setIndeterminate( true );
        progress.setString( actionMessage );
        
        dialog.setTitle( "Processing" );
        dialog.setModal( true );
        dialog.setAlwaysOnTop( true );
        
        dialog.setMinimumSize( new Dimension(400, 100) );
        dialog.setSize( new Dimension(400, 100) );
        
        dialog.setLocationRelativeTo( null );
        dialog.pack();
        
        Container contentPane = dialog.getContentPane();
        
        contentPane.setLayout( new BorderLayout() );
        contentPane.add( progress, BorderLayout.CENTER );
    
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
