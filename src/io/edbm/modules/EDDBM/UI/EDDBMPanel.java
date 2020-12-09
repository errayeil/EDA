package io.edbm.modules.EDDBM.UI;

import io.edbm.UI.EDASearchBox;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Panel providing components to interact with EDDB data directly from E.D.A.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDDBMPanel extends JPanel  {

    /**
     *
     */
    private float backgroundAlpha;

    /**
     *
     */
    private EDASearchBox referenceSystemBox;

    /**
     *
     */
    private EDASearchBox searchSystemBox;

    /**
     *
     */
    private EDASearchBox allegianceBox;

    /**
     *
     */
    private EDASearchBox governmentBox;

    /**
     *
     */
    private EDASearchBox primaryEconomyBox;

    /**
     *
     */
    private EDASearchBox minorFactionBox;

    /**
     *
     */
    private EDASearchBox presenceTypeBox;

    /**
     *
     */
    private EDASearchBox needPermitBox;

    /**
     *
     */
    private EDASearchBox stationFilterBox;

    /**
     *
     */
    private EDASearchBox powersBox;

    /**
     *
     */
    private EDASearchBox powerEffectsBox;

    /**
     *
     */
    private EDASearchBox securityBox;

    /**
     *
     */
    private EDASearchBox factionStateBox;

    /*
     *
     */
    public EDDBMPanel() {

    }
    
    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent ( Graphics g ) {
        super.paintComponent( g );
    }
}
