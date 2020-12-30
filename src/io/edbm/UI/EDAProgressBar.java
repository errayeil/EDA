package io.edbm.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;

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
     *
     */
    private Color foregroundColor;
    
    /**
     *
     */
    private Color borderColor = EDAThemeColors.BUTTON_OUTLINE;
    
    /**
     *
     */
    private Color progressColor = EDAThemeColors.BUTTON_SELECTED;
    
    /**
     *
     */
    private Color indeterminateProgressColor;
    
    /**
     *
     */
    private Font foregroundFont = new Font( "Eurostile", Font.PLAIN, 10);
    
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
     * @param newMin
     */
    public void setMinValue(int newMin) {
        minValue = newMin;
        repaint();
    }
    
    /**
     *
     * @param newMax
     */
    public void setMaxValue(int newMax) {
        maxValue = newMax;
        repaint();
    }
    
    public void setCurrentValue(int newCurrent) {
        currentValue = newCurrent;
        repaint();
    }
    
    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent ( Graphics g ) {
        drawBorder( g );
        
    }
    
    /**
     *
     */
    private void drawBorder ( Graphics g) {
    
    }
    
    /**
     *
     * @param g
     */
    private void drawFill(Graphics g) {
    
    }
    
    /**
     *
     * @param g
     */
    private void drawString(Graphics g) {
    
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
