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
     * Boolean determining if the background semi-transparent rectangles
     * are painted.
     */
    private boolean paintRectangles = true;

    /**
     * Boolean determining if the top and bottom borders are painted.
     */
    private boolean paintBorders = true;

    /**
     * Boolean determining if the semi transparent background is painted.
     */
    private boolean paintBackground = true;

    /**
     * Boolean determining if separators are painted.
     */
    private boolean paintSeparators = true;

    /**
     *
     */
    private float backgroundAlpha = 1.0f;

    /**
     * TODO: Populate list to add more borders, such as left or right.
     * Should be used for the ends of the panel.
     */
    java.util.List< EDARectangle > borders = new ArrayList<>( );

    /**
     * TODO: Populate list to add more separators to the panel.
     * Should be used to separate content in the panel.
     */
    java.util.List< EDARectangle > separators = new ArrayList<>( );

    /**
     * Constructor.
     */
    public EDABackgroundContainer( ) {
        setOpaque( false );
        setLayout( null );
    }

    /**
     * Adds a separator to be painted for the border.
     * @param separator
     */
    public void addBorder( EDARectangle separator) {
        borders.add( separator );
    }

    /**
     * Adds a separator to be painted.
     * @param separator
     */
    public void addSeparator( EDARectangle separator) {
        separators.add( separator );
    }

    /**
     *
     * @param alpha
     */
    public void setBackgroundAlpha(float alpha) {
        this.backgroundAlpha = alpha;
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
        if ( paintRectangles )
            drawBackgroundRectangles( g2d );
        if ( paintBackground )
            drawBackground( g2d );
        if ( paintBorders )
            drawBorders( g2d );
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
                AlphaComposite.SRC, backgroundAlpha ) );
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

        for ( EDARectangle sep : borders) {
            g2d.setColor( sep.getColor() );
            g2d.fillRect( sep.x, sep.y, sep.width, sep.height );
        }
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
                AlphaComposite.SRC_OVER, backgroundAlpha ) );

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
