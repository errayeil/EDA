package io.edbm.UI;

import java.awt.*;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDARectangle extends Rectangle {

    /**
     * The color the rectangle should be.
     */
    private Color color;

    /**
     *
     */
    private boolean filledRect;

    /**
     *
     */
    public EDARectangle() {
        color = EDAThemeColors.EG_1;
    }

    /**
     *
     * @param isFilled
     */
    public void setFilledRect(boolean isFilled) {
        filledRect = isFilled;
    }

    /**
     * Sets the separator color.
     * @param newColor
     */
    public void setColor( Color newColor) {
        this.color = newColor;
    }

    /**
     *
     * @return
     */
    public boolean isFilled() {
        return filledRect;
    }

    /**
     * Returns the separators color.
     * @return
     */
    public Color getColor() {
        return color;
    }
}
