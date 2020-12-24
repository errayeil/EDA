package io.edbm.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @author Steven Frizell
 * @version 1.0
 */
public class EDAButton extends JComponent {

    /**
     * TODO: use built in JComponent name function instead of index
     */
    private String name;

    /**
     * TODO: Get rid of this
     */
    private int index;
    
    /**
     * The string used for the primary text.
     */
    private String primaryText;

    /**
     * The string that appears under the centered primary text.
     */
    private String secondaryText;
    
    /**
     *
     */
    private ImageIcon buttonIcon;

    /**
     * A list of EDAButtonListeners listening for events.
     */
    private final ArrayList< EDAButtonListener > listeners;
    
    /**
     * If the button is selected.
     */
    private boolean isSelected = false;

    /**
     * If true, the button is no longer considered a toggle button.
     */
    private boolean isMomentary = false;

    /**
     * The font used for the primary string.
     */
    private Font primaryFont = new Font( "Eurostile", Font.BOLD, 15);

    /**
     * The font used for the secondary string appearing under the centered primary string.
     */
    private Font secondaryFont = new Font("Eurostile", Font.PLAIN, 13);
    
    /**
     *
     */
    public EDAButton ( final String buttonText) {
        this(buttonText, 150, 40);
    }
    
    /**
     *
     * @param buttonText
     * @param width
     * @param height
     */
    public EDAButton ( final String buttonText, int width, int height) {
        this.primaryText = buttonText;
        listeners = new ArrayList<>(  );

        setSize( new Dimension( width, height) );
        setMinimumSize( new Dimension(width, height));
        setOpaque( false );
        setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ));

        addMouseListener( new MouseAdapter( ) {

            @Override
            public void mousePressed( MouseEvent e ) {
                if (isMomentary) {
                    isSelected = true;
                } else {
                    isSelected = ! isSelected;
                }
                
                repaint( );
            }

            @Override
            public void mouseReleased( MouseEvent e ) {
                if (isMomentary) {
                    isSelected = false;
                }
                repaint( );
            }
        } );
    
        addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                for ( EDAButtonListener a : listeners) {
                    a.buttonClicked( new EDAButtonEvent( EDAButton.this ) );
                }
            }
        } );
    }

    /**
     *
     * @param al
     */
    public void addButtonListener( EDAButtonListener al) {
        listeners.add( al );
    }
    
    /**
     *
     * @return
     */
    public ImageIcon getIcon() {
        return buttonIcon;
    }
    
    /**
     * Returns the primary text that appears in the center of the button.
     * @return
     */
    public String getPrimaryText() {
        return primaryText;
    }

    /**
     * Returns the secondary text that appears under the primary
     * text.
     * @return
     */
    public String getSecondaryText() {
        return secondaryText;
    }

    /**
     * Returns the font used for the primary text.
     * @return
     */
    public Font getPrimaryFont() {
        return primaryFont;
    }

    /**
     * Returns the font used for the secondary text.
     */
    public Font getSecondaryFont() {
        return secondaryFont;
    }
    
    /**
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     *
     * @return
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Returns if this button is momentary or not. If true,
     * it is no longer treated as a 'switch' button.
     * @return
     */
    public boolean isMomentary() {
        return isMomentary;
    }
    
    /**
     *
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }
    
    /**
     *
     * @param isSelected
     */
    public void setSelected(boolean isSelected) {
        if (isSelected) {
            this.isSelected = isSelected;
        } else {
            this.isSelected = isSelected;
        }
        repaint( );
    }

    /**
     *
     * @param isMomentary
     */
    public void setMomentary(boolean isMomentary) {
        this.isMomentary = isMomentary;
    }
    
    /**
     *
     * @param icon
     */
    public void setImageIcon(ImageIcon icon) {
       this.buttonIcon = icon;
    }
    
    /**
     *
     * @param text
     */
    public void setPrimaryText(String text) {
        this.primaryText = text;
        super.repaint();
    }
    
    /**
     *
     * @param subText
     */
    public void setSecondaryText( String subText) {
        this.secondaryText = subText;
        super.repaint();
    }
    
    /**
     *
     * @param newFont
     */
    public void setPrimaryFont( Font newFont) {
        this.primaryFont = newFont;
    }

    /**
     *
     * @param newFont
     */
    public void setSecondaryFont(Font newFont) {
        this.secondaryFont = newFont;
    }

    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = ( Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        paintBorders( g2d, this );
        paintFill( g2d, this );
        paintStrings( g2d, this );
        paintIcon( g2d );
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

        int x1 = getCenteredStringX( visibleRect, primaryMetrics, primaryText );
        int x2 = getCenteredStringX( visibleRect, secondaryMetrics, secondaryText );
        int y1 = getCenteredStringY( visibleRect, primaryMetrics, primaryText );
        int y2 = getCenteredStringY( visibleRect, secondaryMetrics, secondaryText );

        if ( isSelected )
            g2d.setColor( EDAThemeColors.SELECTED_TEXT_COLOR );
        else
            g2d.setColor( EDAThemeColors.UNSELECTED_TEXT_COLOR );

        g2d.setFont( primaryFont );
        g2d.drawString( primaryText, x1, y1 - 4 ); //slightly adjust string placement
        g2d.setFont( secondaryFont );
        g2d.drawString( secondaryText, x2, y2 + 10 ); // Adjust string placement
    }
    
    /**
     *
     * @param g2d
     */
    protected void paintIcon(Graphics g2d) {
        if (buttonIcon != null) {
            int x = getWidth() / 2;
            int y = getHeight() / 2;
            g2d.drawImage( buttonIcon.getImage(), x - 12, y - 12, null);
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
