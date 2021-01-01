package io.edbm.UI.Component;

import io.edbm.UI.LAF.EDAThemeColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    private int minValue = 0;

    /**
     *
     */
    private int currentValue = 0;

    /**
     *
     */
    private int maxValue = 100;

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
     *
     */
    private Timer indeterminateTimer;

    /**
     *
     */
    public EDAProgressBar( ) {
        this( 0, 100 );
    }

    /**
     * @param minValue
     * @param maxValue
     */
    public EDAProgressBar( int minValue, int maxValue ) {
        this( "", minValue, maxValue );
    }

    /**
     * @param progressText
     * @param minValue
     * @param maxValue
     */
    public EDAProgressBar( String progressText, int minValue, int maxValue ) {
        isStringPainted = true;
        isIndeterminate = false;
        paintedString = progressText;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * @param newCurrent
     */
    public void setCurrentValue( int newCurrent ) {
        if ( newCurrent > 100 || newCurrent < 0 ) {
            throw new IllegalArgumentException( "Current value can not be greater than 100 or less than 0." );
        }

        currentValue = newCurrent;
        drawFill( getGraphics( ) );
    }

    /**
     * @param newFont
     */
    public void setForegroundFont( Font newFont ) {
        foregroundFont = newFont;
    }

    /**
     * @param newColor
     */
    public void setForegroundColor( Color newColor ) {
        foregroundColor = newColor;
    }

    /**
     * @param newColor
     */
    public void setBorderColor( Color newColor ) {
        borderColor = newColor;
        drawBorder( getGraphics( ) );
    }

    /**
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

        if ( isIndeterminate ) {
            indeterminateTimer = new Timer( 0, new ActionListener( ) {
                //Create offsets to account for the space taken up from the border.
                int fillXStart = 3;
                int fillYStart = 3;

                int value = 0;
                int maxValue = getWidth( ) - 5; //Accommodate for right border width
                int lastValue;


                /**
                 *
                 * @param e
                 */
                @Override
                public void actionPerformed( ActionEvent e ) {
                    Graphics g = getGraphics( );
                    System.out.println( "Performing" );

                    g.setColor( indeterminateProgressColor );

                    if ( value != 0 ) {
                        g.clearRect( fillXStart, fillYStart, lastValue, getHeight( ) - 6 );
                    }

                    g.fillRect( fillXStart, fillYStart, value, getHeight( ) - 6 );

                    if ( value != maxValue ) {
                        lastValue = value;
                        value++;
                    } else {
                        g.clearRect( fillXStart, fillYStart, value, getHeight( ) - 6 );
                        drawBorder( g );
                        lastValue = 0;
                        value = 0;
                    }
                }
            } );

            indeterminateTimer.setRepeats( true );
            indeterminateTimer.setDelay( 10 );
            indeterminateTimer.start( );
        } else {
            if ( indeterminateTimer.isRunning( ) ) {
                indeterminateTimer.stop( );
            }
        }
    }

    /**
     * @param g
     */
    @Override
    protected void paintComponent( Graphics g ) {
        drawBackground( g );
        drawBorder( g );
        drawFill( g );
        drawString( g );
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
        int fillXStart = 3;
        int fillYStart = 3;

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
