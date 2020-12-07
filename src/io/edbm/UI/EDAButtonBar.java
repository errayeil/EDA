package io.edbm.UI;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Creates the ButtonBar
 * @author Steven Frizell
 * @version 1
 * @since 1
 */
public class EDAButtonBar extends JPanel {
    
    /**
     *
     */
    public EDAButtonBar () {
        setOpaque( false );
        setSize( new Dimension(1500, 40) );
        setMinimumSize( new Dimension(1500, 40) );
        setLayout( null );
        
        createButtons();
    }
    
    @Override
    protected void paintComponent ( Graphics g ) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor( Color.BLACK);
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite( AlphaComposite.getInstance(
                AlphaComposite.SRC_ATOP, 0.0f));
        
        super.paintComponent( g );
    }
    
    private void createButtons() {
        EDAButton bookmark = new EDAButton( "Bookmarks" );
        EDAButton materials = new EDAButton( "Materials" );
        EDAButton list = new EDAButton( "Shopping list" );
        
        add(bookmark);
        add(materials);
        add(list);
        
        bookmark.setLocation( 0, 5 );
        materials.setLocation( 104, 5 );
        list.setLocation( 204, 5 );
    }
}
