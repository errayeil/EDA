package io.edbm.UI;

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
        JDialog dialog = new JDialog();
        EDAProgressBar progress = new EDAProgressBar(  );
        EDABackgroundContainer container = new EDABackgroundContainer();
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
        container.add( progress );
        container.add( label );

        label.setLocation( 120, 15 );
        progress.setLocation( 100, 45 );

        dialog.setContentPane( container );
        dialog.setLocationRelativeTo( null );
        dialog.pack();

        EDARectangle left = new EDARectangle();
        EDARectangle right = new EDARectangle();
        EDARectangle top = new EDARectangle();
        EDARectangle bottom = new EDARectangle();

        left.setColor( EDAThemeColors.EG_7 );
        left.x = 0;
        left.y = 0;
        left.width = 3;
        left.height = dialog.getHeight();
        right.setColor( EDAThemeColors.EG_7 );
        right.x = dialog.getWidth() - 3;
        right.y = 0;
        right.width = 3;
        right.height = dialog.getHeight();
        top.setColor( EDAThemeColors.EG_7 );
        top.x = 0;
        top.y = 0;
        top.width = dialog.getWidth();
        top.height = 3;
        bottom.setColor( EDAThemeColors.EG_7 );
        bottom.x = 0;
        bottom.y = dialog.getHeight() - 3;
        bottom.width = dialog.getWidth();
        bottom.height = 3;

        container.addBorder( left );
        container.addBorder( right );
        container.addBorder( top );
        container.addBorder( bottom );
    
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
