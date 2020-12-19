package io.edbm.UI;

import java.awt.*;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDASeparator extends Rectangle {

    /**
     * The color the rectangle should be.
     */
    private Color separatorColor;

    /**
     *
     */
    public EDASeparator() {
        separatorColor = EDAThemeColors.EG_1;
    }

    /**
     * Sets the separator color.
     * @param newColor
     */
    public void setSeparatorColor(Color newColor) {
        this.separatorColor = newColor;
    }

    /**
     * Returns the separators color.
     * @return
     */
    public Color getSeparatorColor() {
        return separatorColor;
    }
}
