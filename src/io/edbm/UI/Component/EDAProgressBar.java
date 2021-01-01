package io.edbm.UI.Component;

import io.edbm.UI.LAF.EDAThemeColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAProgressBar extends JComponent {

    /**
     *
     */
    private boolean isStringPainted;

    /**
     *
     */
    private boolean isIndeterminate;

    /**
     *
     */
    private String paintedString;

    /**
     *
     */
    private int currentValue = 0;

    /**
     * The color of text painted on the progress bar.
     */
    private Color foregroundColor;

    /**
     * The color of the border of the progress bar.
     */
    private Color borderColor = EDAThemeColors.BUTTON_OUTLINE;

    /**
     * The color of the progress bar fill.
     */
    private Color progressColor = EDAThemeColors.BUTTON_SELECTED;

    /**
     * The color of the progress bar fill when its set to indeterminate.
     */
    private Color indeterminateProgressColor = EDAThemeColors.EG_1;

    /**
     * The font of the text.
     */
    private Font foregroundFont = new Font( "Eurostile", Font.PLAIN, 10 );

    /**
     * The timer used to paint the indeterminate progress bar.
     */
    private Timer indeterminateTimer;

    /**
     * @param progressText
     */
    public EDAProgressBar( String progressText ) {
        isStringPainted = true;
        isIndeterminate = false;
        paintedString = progressText;
    }

    /**
     * Sets the new current value;
     * @param newValue The new current progress. Must be greater than or equal to zero
     *                 or less than or equal to the width of the progress bar.
     */
    public void setCurrentValue(int newValue) {
        if (newValue < 0) {
            newValue = 0;
        } else if (newValue > getWidth()) {
            newValue = getWidth() - 3; //Account for border width.
        }
        currentValue = newValue;
    }
    /**
     * Sets the font of the text drawn on the progress bar when
     * it is determinate. The default used is "Eurostile".
     * @param newFont
     */
    public void setForegroundFont( Font newFont ) {
        foregroundFont = newFont;
    }

    /**
     * Sets the text color.
     * @param newColor The new color.
     */
    public void setForegroundColor( Color newColor ) {
        foregroundColor = newColor;
    }

    /**
     * Sets the color of the progress bar border.
     * @param newColor
     */
    public void setBorderColor( Color newColor ) {
        borderColor = newColor;
        drawBorder( getGraphics( ) );
    }

    /**
     * Sets the color of the fill when the progress bar is indeterminate.
     * @param newColor
     */
    public void setProgressColor( Color newColor ) {
        progressColor = newColor;
        drawFill( getGraphics( ) );
    }

    /**
     * @param newColor
     */
    public void setIndeterminateProgressColor( Color newColor ) {
        indeterminateProgressColor = newColor;
        repaint( );
    }

    /**
     * @param newString
     */
    public void setPaintedString( String newString ) {
        paintedString = newString;
        drawString( getGraphics( ) );
    }

    /**
     * @param isStringPainted
     */
    public void setStringPainted( boolean isStringPainted ) {
        this.isStringPainted = isStringPainted;
    }

    /**
     * @param isIndeterminate
     */
    public void setIndeterminate( boolean isIndeterminate ) {
        this.isIndeterminate = isIndeterminate;
        repaint(  );
    }

    /**
     * @param g
     */
    @Override
    protected void paintComponent( Graphics g ) {
            drawBackground( g );
            drawBorder( g );
            drawString( g );

            if (isIndeterminate) {
                ActionListener actionListener;
                indeterminateTimer = new Timer( 20, e -> {
                    final int fillXStart = 3;
                    final int fillYStart = 3;

                    int currentValue = 0;
                    int lastValue = 0;
                    final int maxValue = getWidth() - 3;

                    if (currentValue == maxValue) {
                        g.clearRect( fillXStart, fillYStart, lastValue, getHeight() - 6 );
                    }

                    paintIndeterminate( g, currentValue, lastValue );

                    currentValue++;
                } );
            } else {
                drawFill( g );
            }
    }

    /**
     * Paints the fill for when the progress bar is set to indeterminate.
     * @param g
     */
    private void paintIndeterminate(Graphics g, int value, int lastValue) {
        //Create offsets to account for the space taken up from the border.
        final int fillXStart = 3;
        final int fillYStart = 3;

        final int maxValue = getWidth( ) - 5; //Accommodate for right border width

        g.setColor( indeterminateProgressColor );

    }

    /**
     * @param g
     */
    private void drawBackground( Graphics g ) {
        Graphics2D g2d = ( Graphics2D ) g;

        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        //g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_IN, 1.0f ) );
        g2d.setColor( Color.BLACK );
        g2d.fillRect( 0, 0, getWidth( ), getHeight( ) );
    }

    /**
     *
     */
    private void drawBorder( Graphics g ) {
        g.setColor( borderColor );
        g.drawRect( 0, 0, getWidth( ), getHeight( ) );
        g.drawRect( 1, 1, getWidth( ) - 3, getHeight( ) - 3 );
        g.drawRect( 2, 2, getWidth( ) - 3, getHeight( ) - 3 );
    }

    /**
     * Draws the 'fill' that represents the progress.
     *
     * @param g The graphics of the component.
     */
    private void drawFill( Graphics g ) {
        //Create offsets to account for the space taken up from the border.
        final int fillXStart = 3;
        final int fillYStart = 3;

        if ( !isIndeterminate ) {
            g.setColor( progressColor );
            g.drawRect( fillXStart, fillYStart, currentValue, getHeight( ) - 3 );
        }
    }

    /**
     * Draws the string on top of the progress bar, if the progress bar is not indeterminate and a string has been
     * specified.
     *
     * @param g The graphics of the component;
     */
    private void drawString( Graphics g ) {
        if ( !isIndeterminate ) {
            Rectangle visibleRect = getVisibleRect( );
            FontMetrics primaryMetrics = getFontMetrics( foregroundFont );

            int x1 = getCenteredStringX( visibleRect, primaryMetrics, paintedString );
            int y1 = getCenteredStringY( visibleRect, primaryMetrics, paintedString );

            g.setColor( foregroundColor );
            g.setFont( foregroundFont );
            g.drawString( paintedString, x1, y1 );
        }
    }


    /**
     * Returns the x coordinate within the specified rectangle accommodating font metrics. This helps us center the text
     * of the button.
     * <br><br>
     *
     * @param visibleRect The visible part of the buttons rectangle.
     * @param metrics     The metrics of the buttons font.
     * @param text        The text we are positioning/painting.
     *
     * @return
     */
    private int getCenteredStringX( Rectangle visibleRect, FontMetrics metrics, String text ) {
        return visibleRect.x + ( visibleRect.width - metrics.stringWidth( text ) ) / 2;
    }

    /**
     * Returns the y coordinate within the specified rectangle accommodating font metrics. This helps us center the text
     * of the button.
     * <br><br>
     *
     * @param visibleRect The visible part of the buttons rectangle.
     * @param metrics     The metrics of the buttons font.
     * @param text        The text we are positioning/painting;
     *
     * @return
     */
    private int getCenteredStringY( Rectangle visibleRect, FontMetrics metrics, String text ) {
        return visibleRect.y + ( ( visibleRect.height - metrics.getHeight( ) ) / 2 ) + metrics.getAscent( );
    }
}
