package io.edbm.UI.Component;

import javax.swing.*;
import java.awt.*;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAList extends JList {

    /**
     *
     */
    private EDAListRenderer renderer;

    /**
     *
     */
    public void EDAList() {
        this.renderer = new EDAListRenderer();

        setCellRenderer( renderer );
    }

    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
    }
}
