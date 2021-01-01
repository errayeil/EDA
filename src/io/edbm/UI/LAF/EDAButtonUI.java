package io.edbm.UI.LAF;

import io.edbm.UI.Component.EDAButton;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAButtonUI extends ComponentUI {

    /**
     *
     */
    public EDAButtonUI( ) {

    }

    /**
     *
     * @param g
     * @param c
     */
    @Override
    public void paint(Graphics g, JComponent c) {
        EDAButton button = (EDAButton) c;

        Graphics2D g2d = ( Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        paintBorders( g2d, button );
        paintFill( g2d, button );
        paintStrings( g2d, button );
    }

    /**
     * Paints the border of the button.
     * @param g2d The graphics of the button.
     * @param button The button we are painting.
     */
    protected void paintBorders(Graphics2D g2d, EDAButton button) {
        int width = button.getWidth();
        int height = button.getHeight();

        g2d.setColor( EDAThemeColors.BUTTON_OUTLINE );
        g2d.drawRect( 0, 0, width - 1, height - 1 );
        g2d.drawRect( 1, 1, width - 2, height - 2 );
        g2d.drawRect( 0, 0, width - 2, height - 2 );
    }

    /**
     * Paints the buttons internal fill. The color changes depending on
     * if the button is selected
     * @param g2d The graphics of the button.
     * @param button The button we are painting.
     */
    protected void paintFill(Graphics2D g2d, EDAButton button) {
        int width = button.getWidth();
        int height = button.getHeight();

        if (button.isSelected())
            g2d.setColor( EDAThemeColors.BUTTON_SELECTED );
        else
            g2d.setColor( EDAThemeColors.BUTTON_UNSELECTED );

        g2d.fillRect( 2, 2, width - 4, height - 4 );
    }

    /**
     * Paints both the primary and secondary text strings of the button, if available.
     * @param g2d The graphics of the button.
     * @param button The button being painted.
     */
    protected void paintStrings(Graphics2D g2d, EDAButton button) {
        Rectangle visibleRect  = button.getVisibleRect();
        FontMetrics primaryMetrics = button.getFontMetrics( button.getPrimaryFont() );
        FontMetrics secondaryMetrics = button.getFontMetrics(button.getSecondaryFont() );
        String primary = button.getPrimaryText();
        String secondary = button.getSecondaryText();

        int x1 = getCenteredStringX( visibleRect, primaryMetrics, primary );
        int x2 = getCenteredStringX( visibleRect, secondaryMetrics, secondary );
        int y1 = getCenteredStringY( visibleRect, primaryMetrics, primary );
        int y2 = getCenteredStringY( visibleRect, secondaryMetrics, secondary );

        if ( button.isSelected( ) )
            g2d.setColor( EDAThemeColors.SELECTED_TEXT_COLOR );
        else
            g2d.setColor( EDAThemeColors.UNSELECTED_TEXT_COLOR );

        g2d.setFont( button.getPrimaryFont() );
        g2d.drawString( primary, x1, y1 - 4 ); //slightly adjust string placement
        g2d.setFont( button.getSecondaryFont() );
        g2d.drawString( secondary, x2, y2 + 10 ); // Adjust string placement
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
    private int getCenteredStringX(Rectangle visibleRect, FontMetrics metrics, String text) {
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
