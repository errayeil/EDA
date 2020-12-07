package io.edbm.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author Steven Frizell
 * @version 1.0
 */
public class EDAButton extends JPanel {
    
    /**
     *
     */
    private int index;
    
    /**
     *
     */
    private String buttonText;
    
    /**
     *
     */
    private String buttonSubText;
    
    /**
     *
     */
    private int width;
    
    /**
     *
     */
    private int height;
    
    /**
     *
     */
    private boolean isSelected = false;
    
    /**
     *
     */
    private Color buttonFill = ThemeColors.BUTTON_UNSELECTED;
    
    /**
     *
     */
    private Color selectedTextColor = ThemeColors.UNSELECTED_TEXT_COLOR;
    
    /**
     *
     */
    private Font buttonFont = new Font( "Verdana", Font.BOLD, 12);
    
    /**
     *
     */
    private ArrayList<EDATabListener> listeners;
    
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
        this.buttonText = buttonText;
        this.width = width;
        this.height = height;
        
        listeners = new ArrayList<>(  );
        setSize( new Dimension( width, height) );
        setMinimumSize( new Dimension(width, height));
        setOpaque( false );
        setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ));
    
        addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                for (EDATabListener a : listeners) {
                    a.tabClicked( new TabEvent( EDAButton.this ) );
                }
            }
        } );
    }
    
    @Override
    protected void paintComponent( Graphics g) {
        Graphics2D g2d = ( Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite( AlphaComposite.getInstance(
                AlphaComposite.SRC, 1.0f));
        
        //Drawing button outline
        g2d.setColor( ThemeColors.BUTTON_OUTLINE );
        g2d.drawRect( 0, 0, width - 1, height - 1 );
        g2d.drawRect( 1, 1, width - 2, height - 2 );
        g2d.drawRect( 0, 0, width - 2, height - 2 );
        g2d.setColor( buttonFill );
        g2d.fillRect( 2, 2, width - 4, height - 4 );
    
        //Drawing button text
        g2d.setColor( selectedTextColor);
        
        if (!buttonText.equals( "" )) {
            drawCenteredString( g, buttonText, getVisibleRect(), buttonFont );
            
            if (!buttonSubText.equals( "" )) {
                g2d.setFont( new Font("Verdana", Font.BOLD, 7) );
                drawLoweredCenteredString( g, buttonSubText, getVisibleRect(), new Font("Verdana", Font.BOLD, 8) );
            }
            
        } else {
            g2d.drawLine( 12, 12, 12, 28 );
            g2d.drawLine( 12, 28, 28, 28 );
            g2d.drawLine( 28, 12, 28, 28 );
            
            g2d.drawLine( 10, 13, 20, 6 );
            g2d.drawLine( 30, 13, 20, 6 );
        }
        
        super.paintComponent( g2d );
    }
    
    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }
    
    private void drawLoweredCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y + 10);
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
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }
    /**
     *
     * @param al
     */
    public void addTabListener ( EDATabListener al) {
        listeners.add( al );
    }
    
    /**
     *
     * @param isSelected
     */
    public void setSelected(boolean isSelected) {
        if (isSelected) {
            this.isSelected = isSelected;
            buttonFill = ThemeColors.BUTTON_SELECTED;
            selectedTextColor = ThemeColors.SELECTED_TEXT_COLOR;
        } else {
            this.isSelected = isSelected;
            buttonFill = ThemeColors.BUTTON_UNSELECTED;
            selectedTextColor = ThemeColors.UNSELECTED_TEXT_COLOR;
        }
        super.repaint( );
    }
    
    /**
     *
     * @param text
     */
    public void setButtonText(String text) {
        this.buttonText = text;
        super.repaint();
    }
    
    /**
     *
     * @param subText
     */
    public void setButtonSubText(String subText) {
        this.buttonSubText = subText;
        super.repaint();
    }
    
    /**
     *
     * @param newFont
     */
    public void setButtonFont(Font newFont) {
        this.buttonFont = newFont;
    }
    
    /**
     *
     * @return
     */
    public String getButtonText() {
        return buttonText;
    }
    
    /**
     *
     * @return
     */
    public Font getButtonFont() {
        return buttonFont;
    }
    
    /**
     *
     * @return
     */
    public boolean isSelected() {
        return isSelected;
    }
}
