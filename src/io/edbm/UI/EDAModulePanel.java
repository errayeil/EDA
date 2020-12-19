package io.edbm.UI;

import javax.swing.*;
import java.awt.*;

/**
 * Custom JPanel designed to contain module panels.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAModulePanel extends JPanel {
    
    /**
     * The background opacity.
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
        setBackground( new Color( 0, 0, 0, 200) );
    }
    
}
