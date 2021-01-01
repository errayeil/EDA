package io.edbm.UI.Windows;

import io.edbm.UI.LAF.EDARectangle;
import io.edbm.UI.LAF.EDAThemeColors;

import java.awt.*;

/**
 * Custom border used for EDAWindows and EDADialogs.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDABorder {

    /**
     * Top border line.
     */
    protected EDARectangle topBorder;

    /**
     * Right border line.
     */
    protected EDARectangle rightBorder;

    /**
     * Bottom border line.
     */
    protected EDARectangle bottomBorder;

    /**
     * Left border line.
     */
    protected EDARectangle leftBorder;

    /**
     * Constructs the border with default values.
     */
    public EDABorder() {
        topBorder = new EDARectangle();
        rightBorder = new EDARectangle();
        bottomBorder = new EDARectangle();
        leftBorder = new EDARectangle();

        topBorder.color = EDAThemeColors.EG_7;
        rightBorder.color = EDAThemeColors.EG_7;
        bottomBorder.color = EDAThemeColors.EG_7;
        leftBorder.color = EDAThemeColors.EG_7;

        topBorder.shouldFill = true;
        rightBorder.shouldFill = true;
        bottomBorder.shouldFill = true;
        leftBorder.shouldFill = true;

        topBorder.height = 3;
        rightBorder.width = 3;
        bottomBorder.height = 3;
        leftBorder.width = 3;

        topBorder.x = 0;
        topBorder.y = 0;

        rightBorder.y = 0;

        bottomBorder.x = 0;

        leftBorder.x = 0;
        leftBorder.y = 0;
    }

    /**
     * Sets the width of the top border line.
     * @param thickness The width, in pixels.
     */
    public void setTopBorderThickness(int thickness) {
        topBorder.height =  thickness;
    }

    /**
     * Set the width of the right border line.
     * @param thickness The width, int pixels.
     */
    public void setRightBorderThickness(int thickness) {
        rightBorder.width = thickness;
    }

    /**
     * Sets the width of the bottom border line.
     * @param thickness The width, in pixels.
     */
    public void setBottomBorderThickness(int thickness) {
        bottomBorder.height = thickness;
    }

    /**
     *  Sets the width of the left border line.
     * @param thickness The width, in pixels.
     */
    public void setLeftBorderThickness(int thickness) {
        leftBorder.width = thickness;
    }

    /**
     * Sets the color of the top border line.
     * @param newColor
     */
    public void setTopBorderColor( Color newColor) {
        topBorder.color = newColor;
    }

    /**
     * Sets the color of the right border line.
     * @param newColor
     */
    public void setRightBorderColor(Color newColor) {
        rightBorder.color = newColor;
    }

    /**
     * Sets the color of the bottom border line.
     * @param newColor
     */
    public void setBottomBorderColor(Color newColor) {
        bottomBorder.color = newColor;
    }

    /**
     * Sets the color of the left border line.
     * @param newColor
     */
    public void setLeftBorderColor(Color newColor) {
        leftBorder.color = newColor;
    }

    /**
     * Sets the borders individual width and height properties.
     * The parent component of the border should call this when
     * the border is added.
     *
     * @param width The width of the top and bottom borders.
     * @param height The height of the left and right borders.
     */
    protected void setWidthHeightFromParent(int width, int height) {
        topBorder.width = width;
        rightBorder.height = height;
        bottomBorder.width = width;
        leftBorder.height = height;

        rightBorder.x = width;
        bottomBorder.y = height - 3;
    }
}
