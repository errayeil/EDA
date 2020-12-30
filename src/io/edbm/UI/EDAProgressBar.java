package io.edbm.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

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
    private int minValue = 0 ;
    
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
    private Color indeterminateProgressColor = EDAThemeColors.BUTTON_UNSELECTED;
    
    /**
     * The font of the text.
     */
    private Font foregroundFont = new Font( "Eurostile", Font.PLAIN, 10);
    
    /**
     *
     */
    private Timer indeterminateTimer;
    
    /**
     *
     */
    public EDAProgressBar() {
        this(0, 100);
    }
    
    /**
     *
     * @param minValue
     * @param maxValue
     */
    public EDAProgressBar(int minValue, int maxValue) {
        this("", minValue, maxValue);
    }
    
    /**
     *
     * @param progressText
     * @param minValue
     * @param maxValue
     */
    public EDAProgressBar(String progressText, int minValue, int maxValue) {
        isStringPainted = true;
        isIndeterminate = false;
        paintedString = progressText;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     *
     * @param newCurrent
     */
    public void setCurrentValue(int newCurrent) {
        if (newCurrent > 100 || newCurrent < 0) {
            throw new IllegalArgumentException("Current value can not be greater than 100 or less than 0.");
        }

        currentValue = newCurrent;
        drawFill( getGraphics() );
    }

    /**
     *
     * @param newFont
     */
    public void setForegroundFont(Font newFont) {
        foregroundFont = newFont;
    }

    /**
     *
     * @param newColor
     */
    public void setForegroundColor(Color newColor) {
        foregroundColor = newColor;
    }

    /**
     *
     * @param newColor
     */
    public void setBorderColor(Color newColor) {
        borderColor = newColor;
        drawBorder( getGraphics() );
    }

    /**
     *
     * @param newColor
     */
    public void setProgressColor(Color newColor) {
        progressColor = newColor;
        drawFill( getGraphics() );
    }

    /**
     *
     * @param newColor
     */
    public void setIndeterminateProgressColor(Color newColor) {
        indeterminateProgressColor = newColor;
        repaint();
    }

    /**
     *
     * @param newString
     */
    public void setPaintedString(String newString) {
        paintedString = newString;
        drawString( getGraphics() );
    }

    /**
     *
     * @param isStringPainted
     */
    public void setStringPainted(boolean isStringPainted) {
        this.isStringPainted = isStringPainted;
    }

    /**
     *
     * @param isIndeterminate
     */
    public void setIndeterminate(boolean isIndeterminate) {
        this.isIndeterminate = isIndeterminate;
    }
    
    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent ( Graphics g ) {
        drawBorder( g );
        drawFill( g );
        drawString( g );
    }
    
    /**
     *
     */
    private void drawBorder ( Graphics g) {
        g.setColor( borderColor );
        g.drawRect( 0, 0, getWidth(), getHeight() );
        g.drawRect( 1, 1, getWidth() - 1, getHeight() - 1 );
        g.drawRect( 2, 2, getWidth() - 2, getHeight() - 2 );
    }
    
    /**
     * Draws the 'fill' that represents the progress.
     * @param g The graphics of the component.
     */
    private void drawFill(Graphics g) {
        //Create offsets to account for the space taken up from the border.
        int fillXStart = 3;
        int fillYStart = 3;
        
        if (!isIndeterminate) {
            g.setColor( progressColor );
            g.drawRect( fillXStart, fillYStart, currentValue, getHeight() - 3 );
        } else {
            indeterminateTimer = new Timer( 0 , new ActionListener() {
                int value = 0;
                Color in = indeterminateProgressColor;
                Color nc = new Color(in.getRed(), in.getGreen(), in.getBlue());
                
                /**
                 *
                 * @param e
                 */
                @Override
                public void actionPerformed ( ActionEvent e ) {
                    if (value == 0) {
                        g.setColor(indeterminateProgressColor);
                    } else {
                        Color cc;
                        
                        if (nc.getRed() + 10 > 255) {
                            cc = nc;
                        } else {
                            cc = new Color(nc.getRed() + 10, in.getGreen(), in.getBlue());
                        }
                        
                        g.setColor(cc);
                        nc = cc;
                    }
                    g.drawRect( fillXStart, fillYStart, value, getHeight() - 3 );
                    value = value + 5;
                }
            } );
            
            indeterminateTimer.setRepeats( true );
            indeterminateTimer.setDelay( 100 );
            indeterminateTimer.start();
        }
    }
    
    /**
     * Draws the string on top of the progress bar, if the progress bar is not indeterminate and
     * a string has been specified.
     * @param g The graphics of the component;
     */
    private void drawString(Graphics g) {
        if (!isIndeterminate) {
            Rectangle visibleRect  = getVisibleRect();
            FontMetrics primaryMetrics = getFontMetrics( foregroundFont );
    
            int x1 = getCenteredStringX( visibleRect, primaryMetrics, paintedString );
            int y1 = getCenteredStringY( visibleRect, primaryMetrics, paintedString );
            
            g.setColor( foregroundColor );
            g.setFont( foregroundFont );
            g.drawString( paintedString, x1, y1 );
        }
    }
    
    
    /**
     * Returns the x coordinate within the specified rectangle accommodating font metrics.
     * This helps us center the text of the button.
     * <br><br>
     * @param visibleRect The visible part of the buttons rectangle.
     * @param metrics The metrics of the buttons font.
     * @param text The text we are positioning/painting.
     * @return
     */
    private int getCenteredStringX( Rectangle visibleRect, FontMetrics metrics, String text) {
        return visibleRect.x + (visibleRect.width - metrics.stringWidth(text)) / 2;
    }
    
    /**
     * Returns the y coordinate within the specified rectangle accommodating font metrics.
     * This helps us center the text of the button.
     * <br><br>
     * @param visibleRect The visible part of the buttons rectangle.
     * @param metrics The metrics of the buttons font.
     * @param text The text we are positioning/painting;
     * @return
     */
    private int getCenteredStringY(Rectangle visibleRect, FontMetrics metrics, String text) {
        return visibleRect.y + ((visibleRect.height - metrics.getHeight()) / 2) + metrics.getAscent();
    }
}
