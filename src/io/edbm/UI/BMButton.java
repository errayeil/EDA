package io.edbm.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author Steven Frizell
 * @version 1.0
 */
public class BMButton extends JPanel {
    
    /**
     *
     */
    private String buttonText;
    
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
    private ArrayList<ActionListener> listeners;
    
    /**
     *
     */
    public BMButton(final String buttonText) {
        this(buttonText, 150, 40);
    }
    
    /**
     *
     * @param buttonText
     * @param width
     * @param height
     */
    public BMButton(final String buttonText, int width, int height) {
        this.buttonText = buttonText;
        this.width = width;
        this.height = height;
        
        listeners = new ArrayList<ActionListener>(  );
        setSize( new Dimension( width, height) );
        setMinimumSize( new Dimension(width, height));
        setOpaque( false );
        setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ));
    
        addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                if ( SwingUtilities.isLeftMouseButton( e ) ) {
                    if (isSelected)
                        setSelected( false );
                    else
                        setSelected( true );
                }
            
                for (ActionListener a : listeners) {
                    a.actionPerformed( new ActionEvent( this, 0, "clicked" ) );
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
        g2d.setFont( buttonFont );
        
        if (!buttonText.equals( "" )) {
            g2d.drawString( buttonText, (width / 2) - 32, (height / 2) + 3);
        } else {
            g2d.drawLine( 12, 12, 12, 28 );
            g2d.drawLine( 12, 28, 28, 28 );
            g2d.drawLine( 28, 12, 28, 28 );
            
            g2d.drawLine( 10, 13, 20, 6 );
            g2d.drawLine( 30, 13, 20, 6 );
        }
        
        super.paintComponent( g2d );
    }
    
    /**
     *
     * @param al
     */
    public void addActionListener(ActionListener al) {
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
