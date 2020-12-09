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
    public EDASearchBox() {
    
    }
    
    /**
     *
     */
    private void drawTypedText(Graphics g, String typed) {
    
    }
    
    /**
     *
     * @param g
     * @param hint
     */
    private void drawHintText(Graphics g, String hint) {
    
    }
    
    @Override
    protected void paintComponent ( Graphics g ) {
        super.paintComponent( g );
    }
    
    /**
     *
     * @param textHint
     */
    public void setTextBoxHint(final String textHint) {
    
    }
}
