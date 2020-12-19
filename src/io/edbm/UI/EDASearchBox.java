package io.edbm.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import static java.awt.event.KeyEvent.*;

/**
 *
 */
public class EDASearchBox extends JComponent {

    /**
     * The dialog used to show search results.
     */
    private EDAOptionsDialog searchBoxDialog;

    /**
     *
     */
    private boolean isEditable = true;

    /**
     *
     */
    private Color textColor = EDAThemeColors.UNSELECTED_TEXT_COLOR;

    /**
     *
     */
    private Color borderColor = EDAThemeColors.BUTTON_OUTLINE;

    /**
     *
     */
    private Color backgroundFill = EDAThemeColors.WINDOW_BACKGROUND;

    /**
     * The font used for the text box.
     */
    private Font typedTextFont = new Font( "Verdana", Font.PLAIN, 14 );

    /**
     * The font used for the hint text.
     */
    private Font hintTextFont = new Font( "Verdana", Font.PLAIN, 1 );

    /**
     * The text currently displayed as the hint text in the text box.
     */
    private String displayedHintText = "";

    /**
     * The empty String displayed when the user has entered text into the text box.
     */
    private String emptyHintText = "";

    /**
     * The text to be displayed when the user has not typed anything into the text box.
     */
    private String filledHintText = "";

    /**
     *
     */
    private String currentTypedText = "";

    public EDASearchBox() {
        this (true);
    }

    /**
     * @param editable if the search box is editable or not.
     */
    public EDASearchBox( boolean editable ) {

        setSize( new Dimension(140, 30) );
        setOpaque( false );
        setFont( typedTextFont );
        JTextField field;

        addMouseListener( new MouseAdapter( ) {
            @Override
            public void mouseClicked( MouseEvent e ) {
                requestFocusInWindow( FocusEvent.Cause.MOUSE_EVENT );
                setCursor( Cursor.getPredefinedCursor( Cursor.TEXT_CURSOR ) );
                //drawCursor(getGraphics());
            }
        } );

        addKeyListener( new KeyAdapter( ) {
            @Override
            public void keyPressed( KeyEvent e ) {
                if (editable) {
                    if (!e.isActionKey()) {
                        currentTypedText = currentTypedText + e.getKeyChar();
                    } else {
                        switch (e.getKeyCode()) {
                            case VK_BACK_SPACE:
                                currentTypedText = currentTypedText.substring( 0, currentTypedText.length() - 1 );
                                break;
                            case VK_ESCAPE:
                                transferFocus();
                                break;
                            case VK_ENTER:

                                break;

                        }
                    }
                    repaint();
                }
            }

            @Override
            public void keyReleased( KeyEvent e ) {

            }
        } );

        addKeyListener( new KeyAdapter( ) {
            @Override
            public void keyTyped( KeyEvent e ) {
                if ( currentTypedText.length( ) > 0 ) {
                    displayedHintText = emptyHintText;
                } else {
                    displayedHintText = filledHintText;
                }
                repaint( );
            }
        } );

    }

    /**
     *
     * @param G
     * @param x
     * @param y
     */
    private void drawCursor(Graphics G, int x, int y) {

    }

    /**
     * We override this method to provide custom painting/graphics of the JTextField to provide a similar UI design to
     * Elite:Dangerous.
     *
     * @param g Graphics of the component.
     */
    @Override
    protected void paintComponent( Graphics g ) {
        drawBackground( g );
        drawBorder( g );
        drawTypedText( g );
        drawHintText( g );
    }

    /**
     *
     * @param g
     */
    @Override
    protected void paintBorder( Graphics g ) {

    }

    /**
     *
     */
    private void drawTypedText(Graphics g) {
        drawCenteredString( g, currentTypedText, getVisibleRect(), typedTextFont );
    }

    /**
     *
     * @param g
     */
    private void drawBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        g2d.setColor( new Color(30, 30, 30, 0) );
        g2d.fillRect( 0, 0, getWidth(), getHeight() );
    }


    /**
     * Draws a text "hint" in the background.
     *
     * @param g Graphics of the component.
     */
    private void drawHintText( Graphics g ) {
        if (currentTypedText.length() != 0) {
            drawCenteredString( g, displayedHintText, getVisibleRect(), hintTextFont );
        }
    }

    /**
     * Draws the border of the search box.
     *
     * @param g
     */
    private void drawBorder( Graphics g ) {
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON );

        g2d.setColor( Color.BLACK);//new Color( 135, 136, 138 ) );
        g2d.drawRect( 0, 0, getWidth( ) - 1, getHeight( ) - 1 );
        g2d.drawRect( 1, 1, getWidth( ) - 2, getHeight( ) - 2 );
        g2d.drawRect( 0, 0, getWidth( ) - 2, getHeight( ) - 2 );
    }

    /**
     * TODO: Move to utility class Draws a centered string.
     *
     * @param g    Graphics of the component.
     * @param text Text that should be drawn.
     * @param rect The rectangle used for determining where the string should be drawn.
     * @param font The font to be used for the drawn text.
     */
    private void drawCenteredString( Graphics g, String text, Rectangle rect, Font font ) {
        if (text == null) {
            text = "";
        }

        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics( font );
        // Determine the X coordinate for the text
        int x = rect.x + ( rect.width - metrics.stringWidth( text) ) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ( ( rect.height - metrics.getHeight( ) ) / 2 ) + metrics.getAscent( );
        // Set the font
        g.setFont( font );
        g.setColor( Color.BLACK );
        // Draw the String
        g.drawString( text, x, y );
    }

    public void setEditable(boolean isEditable) {

    }

    /**
     * @param textHint
     */
    public void setTextBoxHintText( final String textHint ) {
        if (currentTypedText.length() == 0) {
            displayedHintText = textHint;
        } else {
            filledHintText = textHint;
        }
        repaint( );
    }

    /**
     * Sets the font used for the hint text.
     *
     * @param newFont The new hint text font.
     */
    public void setTextBoxHintFont( Font newFont ) {
        hintTextFont = newFont;
        repaint( );
    }

    /**
     *
     */
    public String getText() {
        return currentTypedText;
    }

    /**
     *
     * @param options
     */
    public void setOptions( List< String > options ) {

    }
}
