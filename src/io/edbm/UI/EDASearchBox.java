package io.edbm.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 */
public class EDASearchBox extends JPanel {
    
    /**
     *
     */
    private Color textColor = ThemeColors.UNSELECTED_TEXT_COLOR;
    
    /**
     *
     */
    private Color borderColor = ThemeColors.BUTTON_OUTLINE;
    
    /**
     *
     */
    private Color backgroundFill = ThemeColors.WINDOW_BACKGROUND;
    
    /**
     *
     */
    private Font typedTextFont = new Font( "Verdana", Font.PLAIN, 10);
    
    /**
     *
     */
    private Font hintTextFont = new Font("Verdana", Font.PLAIN, 7);
    
    /**
     *
     */
    private float backgroundAlpha = 0.5f;

    /**
     *
     */
    private String typedText;

    /**
     *
     */
    private String hintText;

    /**
     *
     */
    public EDASearchBox() {
        addMouseListener( new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked ( java.awt.event.MouseEvent e ) {
                if ( javax.swing.SwingUtilities.isLeftMouseButton( e ) ) {
                    if (getCursor().getType() == java.awt.Cursor.TEXT_CURSOR) {
                        setCursor( java.awt.Cursor.getDefaultCursor( ) );
                    } else {
                        setCursor( java.awt.Cursor.getPredefinedCursor( java.awt.Cursor.TEXT_CURSOR) );
                    }
                }
            }
        } );

        addKeyListener( new java.awt.event.KeyAdapter( ) {

            @Override
            public void keyPressed( java.awt.event.KeyEvent e ) {

            }

            @Override
            public void keyReleased( java.awt.event.KeyEvent e) {

            }
        } );
    }

    /**
     *
     * @param g Graphics of the component.
     * @param typed Text typed by user that needs to be drawn.
     */
    private void drawTypedText(Graphics g, String typed) {
    
    }
    
    /**
     *
     * @param g Graphics of the component.
     * @param hint The text hint that appears in the "background" of the box.
     */
    private void drawHintText(Graphics g, String hint) {

    }

    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent ( Graphics g ) {
        super.paintComponent( g );
    }
    
    /**
     *
     * @param textHint
     */
    public void setTextBoxHint(final String textHint) {
        this.hintText = textHint;
    }
}
