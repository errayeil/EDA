package io.edbm.UI.Windows;

import io.edbm.UI.LAF.EDARectangle;
import io.edbm.UI.LAF.EDAThemeColors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Creates the background pane used for EDADialogs to provide a
 * similar UI experience like the in game UI's.
 *
 * @author Steven Frizell
 * @version 1.0
 * @since 1.0
 */
public class EDAPane extends JPanel {

    /**
     * The border of the content pane.
     */
    private EDABorder border;

    /**
     * The color used when painting the background of the pane.
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
     * The alpha value used when painting the background of the pane.
     */
    private float backgroundAlpha = 1.0f;

    /**
     * TODO: Populate list to add more separators to the panel.
     * Should be used to separate content in the panel.
     */
    java.util.List< EDARectangle > separators = new ArrayList<>( );

    /**
     * Constructor.
     */
    public EDAPane( ) {
        setOpaque( false );
        setLayout( null );
    }

    /**
     * Sets the border to be used.
     * @param border
     */
    public void setBorder( EDABorder border) {
        border.setWidthHeightFromParent( getWidth(), getHeight() );
        this.border = border;
        repaint(  );
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

        g2d.setColor(border.topBorder.color);

        if (border.topBorder.shouldFill) {
            g2d.fillRect( border.topBorder.x, border.topBorder.y, border.topBorder.width, border.topBorder.height );
        } else {
            g2d.drawRect( border.topBorder.x, border.topBorder.y, border.topBorder.width, border.topBorder.height );
        }

        g2d.setColor( border.rightBorder.color );

        if (border.rightBorder.shouldFill) {
            g2d.fillRect( border.rightBorder.x, border.rightBorder.y, border.rightBorder.width, border.rightBorder.height );
        } else {
            g2d.drawRect( border.rightBorder.x, border.rightBorder.y, border.rightBorder.width, border.rightBorder.height );
        }

        g2d.setColor(border.bottomBorder.color);

        if (border.bottomBorder.shouldFill) {
            g2d.fillRect( border.bottomBorder.x, border.bottomBorder.y, border.bottomBorder.width, border.bottomBorder.height );
        } else {
            g2d.drawRect( border.bottomBorder.x, border.bottomBorder.y, border.bottomBorder.width, border.bottomBorder.height );
        }

        g2d.setColor(border.leftBorder.color);

        if (border.leftBorder.shouldFill) {
            g2d.fillRect( border.leftBorder.x, border.leftBorder.y, border.leftBorder.width, border.leftBorder.height );
        } else {
            g2d.drawRect( border.leftBorder.x, border.leftBorder.y, border.leftBorder.width, border.leftBorder.height );
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
