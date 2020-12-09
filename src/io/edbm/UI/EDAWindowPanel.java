package io.edbm.UI;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Creates the Panel that has a similar design to the Elite Dangerous panels.
 * @author Steven Frizell
 * @version 1.0
 * @since 1.0
 */
public class EDAWindowPanel extends JPanel {
    
    /**
     *
     */
    private float backgroundAlpha;
    
    /**
     *
     */
    private float childrenAlpha;
    
    /**
     * TODO Customizable width
     */
    private int width = 1500;
    
    /**
     *TODO Customizable Height
     */
    private int height = 900 ;
    
    /**
     * C
     */
    public EDAWindowPanel () {
        backgroundAlpha = 0.8f;
        childrenAlpha = 0.4f;
        
        setOpaque( false );
        setMinimumSize( new Dimension(width, height) );
        setSize( new Dimension(width, height) );
        setLayout( null );
    }
    
    /**
     * Constructs a BookmarkPanel
     * @param backgroundAlpha
     */
    public EDAWindowPanel ( float backgroundAlpha) {
        this.backgroundAlpha = backgroundAlpha;
    }
    
    /**
     *
     * @param backgroundAlpha
     * @param childrenAlpha
     */
    public EDAWindowPanel ( float backgroundAlpha, float childrenAlpha) {
        this.backgroundAlpha = backgroundAlpha;
        this.childrenAlpha = childrenAlpha;
        
       
    }

    /**
     * Paints the panel to match the Elite:Dangerous in game UI.
     * @param g
     */
    @Override
    public void paintComponent( Graphics g) {
        Graphics2D g2d = (Graphics2D ) g;
        drawBackground( g2d );
        drawBorders( g2d );
        drawBackgroundRectangles( g2d );
    }
    
    /**
     * Draws the background of the panel to match ED's UI.
     * TODO: Perfectly match ED in game UI.
     * @param g2d
     */
    private void drawBackground( Graphics2D g2d) {
        g2d.setColor( ThemeColors.WINDOW_BACKGROUND );
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite( AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, backgroundAlpha));
        super.paintComponent(g2d);
    }
    
    /**
     * Draws the window and tab button bar borders.
     * TODO: Perfectly match ED in game UI.
     * @param g2d
     */
    private void drawBorders( Graphics2D g2d) {
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //Drawing top and bottom border
        g2d.setColor( ThemeColors.EG_7 );
        g2d.drawRect( 0, 0, width, 1 );
        g2d.drawRect( 0, height , width,  1 );
        g2d.setColor( ThemeColors.EG_6 );
        g2d.drawRect( 0, 1, width, 1 );
        g2d.drawRect( 0,  height - 1, width, 1 );
        g2d.setColor( ThemeColors.EG_1 );
        g2d.drawRect( 0, 2, width, 1  );
        g2d.drawRect( 0 , height - 2, width, 1 );
        g2d.drawRect( 0, height - 3, width, 1 );
        
        //Drawing border to separate button bar
        g2d.fillRect( 0, 55, width, 3);
        super.paintChildren( g2d );
    }
    
    /**
     * Draws the semi-transparent rectangles seen in the background of Elite:Dangerous
     * in game UI's.
     * TODO: Get rectangle spacing correct. Seems off at the moment.
     * @param g2d
     */
    private void drawBackgroundRectangles( Graphics2D g2d) {
        g2d.setColor( ThemeColors.WINDOW_BACKGROUND_RECTANGLES );
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite( AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 0.6f));
        
        int rectangles = height / 4;
        int spacing = 10;
        int x = 0;
        int y = 10;
        
        
        for (int i = 0; i < rectangles; i++) {
            g2d.fillRect( x, y, width, 3 );
            y = y + spacing;

            if ( y >= 940 ) {
                break;
            }
        }
    }
}
