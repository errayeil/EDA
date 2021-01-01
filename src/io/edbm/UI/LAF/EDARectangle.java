package io.edbm.UI.LAF;

import java.awt.*;

/**
 * Custom Rectangle containing color and shouldFill data for painting borders
 * and content separators for the custom EDAContentpane used for EDAWindows and
 * EDADialogs.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDARectangle extends Rectangle {

    /**
     * The color the rectangle should be.
     */
    public Color color;

    /**
     * Boolean determining if the this rectangle painted should be
     * filled instead of an outline.
     */
    public boolean shouldFill;


    /**
     * Constructor.
     */
    public EDARectangle() {
        color = EDAThemeColors.EG_1;
    }

}
