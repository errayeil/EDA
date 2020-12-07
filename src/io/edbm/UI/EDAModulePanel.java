package io.edbm.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAModulePanel extends JPanel {
    
    /**
     *
     */
    private float backgroundAlpha;
    
    /**
     *
     */
    public EDAModulePanel(int width, int height) {
        this(0.8f, width, height);
    }
    
    /**
     *
     * @param backgroundAlpha
     * @param width
     * @param height
     */
    public EDAModulePanel(float backgroundAlpha, int width, int height) {
        this.backgroundAlpha = backgroundAlpha;
    
        setOpaque( false );
        setMinimumSize( new Dimension( width, height) );
        setSize( new Dimension(width, height) );
        setLayout( null );
        setBackground( new Color( 0, 0, 0, 0) );
    }
    
    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent ( Graphics g ) {
        super.paintComponent( g );
    }
    
}
