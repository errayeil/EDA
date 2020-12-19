package io.edbm.UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Creates the Panel that has a similar design to the Elite Dangerous panels.
 *
 * @author Steven Frizell
 * @version 1.0
 * @since 1.0
 */
public class EDABackgroundContainer extends JPanel {

    /**
     *
     */
    private Color backgroundColor = EDAThemeColors.WINDOW_BACKGROUND;

    /**
     *
     */
    private boolean paintRectangles = true;

    /**
     *
     */
    private boolean paintBorders = true;

    /**
     *
     */
    private boolean paintBackground = true;

    /**
     * TODO: Populate list to add more borders, such as left or right.
     * Should be used for the ends of the panel.
     */
    java.util.List< EDASeparator > borders = new ArrayList<>( );

    /**
     * TODO: Populate list to add more separators to the panel.
     * Should be used to separate content in the panel.
     */
    java.util.List< EDASeparator > separators = new ArrayList<>( );

    /**
     * Constructor.
     */
    public EDABackgroundContainer( ) {
        setOpaque( false );
        setLayout( null );
    }

    /**
     * @param paintRectangles
     *
     * @return
     */
    public void setPaintRectangles( boolean paintRectangles ) {
        this.paintRectangles = paintRectangles;
        repaint( );
    }

    /**
     * @param paintBorders
     */
    public void setPaintBorders( boolean paintBorders ) {
        this.paintBorders = paintBorders;
        repaint( );
    }

    /**
     * Sets if the panel should paint the background or not.
     *
     * @param paintBackground
     */
    public void setPaintBackground( boolean paintBackground ) {
        this.paintBackground = paintBackground;
        repaint( );
    }

    /**
     * Returns if the panel is painting the background rectangles.
     *
     * @return
     */
    public boolean isPaintingRectangles( ) {
        return paintRectangles;
    }

    /**
     * Returns if the panel is painting the borders or not.
     *
     * @return
     */
    public boolean isPaintingBorders( ) {
        return paintBorders;
    }

    /**
     * Returns if the panel is painting the background or not.
     *
     * @return
     */
    public boolean isPaintingBackground( ) {
        return paintBackground;
    }

    /**
     *
     * @param newColor
     */
    public void setBackgroundColor(Color newColor) {
        this.backgroundColor = newColor;
    }

    /**
     * @param g
     */
    @Override
    protected void paintComponent( Graphics g ) {
        Graphics2D g2d = ( Graphics2D ) g;
        if ( paintBackground )
            drawBackground( g2d );
        if ( paintBorders )
            drawBorders( g2d );
        if ( paintRectangles )
            drawBackgroundRectangles( g2d );
    }

    /**
     * @param g
     */
    @Override
    protected void paintBorder( Graphics g ) {
        //DO nothing
    }

    /**
     * Draws the background of the panel to match ED's UI.
     * TODO: Perfectly match ED in game UI.
     *
     * @param g2d
     */
    private void drawBackground( Graphics2D g2d ) {
        g2d.setColor( EDAThemeColors.WINDOW_BACKGROUND );
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON );
        g2d.setComposite( AlphaComposite.getInstance(
                AlphaComposite.SRC, 0.8f ) );
    }

    /**
     * Draws the window and tab button bar borders.
     * TODO: Perfectly match ED in game UI.
     * TODO: Update to be more customizable
     *
     * @param g2d
     */
    private void drawBorders( Graphics2D g2d ) {
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON );

        //Drawing top and bottom border
        g2d.setColor( EDAThemeColors.EG_7 );
        g2d.drawRect( 0, 0, getWidth( ), 1 );
        g2d.drawRect( 0, getHeight( ), getWidth( ), 1 );
        g2d.setColor( EDAThemeColors.EG_6 );
        g2d.drawRect( 0, 1, getWidth( ), 1 );
        g2d.drawRect( 0, getHeight( ) - 1, getWidth( ), 1 );
        g2d.setColor( EDAThemeColors.EG_1 );
        g2d.drawRect( 0, 2, getWidth( ), 1 );
        g2d.drawRect( 0, getHeight( ) - 2, getWidth( ), 1 );
        g2d.drawRect( 0, getHeight( ) - 3, getWidth( ), 1 );

        //Drawing border to separate button bar
        g2d.fillRect( 0, 55, getWidth( ), 3 );
    }

    /**
     * TODO
     * @param g2d
     */
    private void drawSeparators( Graphics2D g2d ) {

    }

    /**
     * Draws the semi-transparent rectangles seen in the background of Elite:Dangerous in game UI's.
     * TODO: Get rectangle spacing correct. Seems off at the moment.
     *
     * @param g2d
     */
    private void drawBackgroundRectangles( Graphics2D g2d ) {
        g2d.setColor( EDAThemeColors.WINDOW_BACKGROUND_RECTANGLES );
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON );
        g2d.setComposite( AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 0.8f ) );

        int rectangles = getHeight( ) / 4;
        int spacing = 10;
        int x = 0, y = 10;

        for ( int i = 0; i < rectangles; i++ ) {
            g2d.fillRect( x, y, getWidth( ), 3 );
            y = y + spacing;

            if ( y >= 940 ) {
                break;
            }
        }
    }
}
