package io.edbm.UI.Component;

import javax.swing.*;
import java.awt.*;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAListRenderer extends JLabel implements ListCellRenderer {

    /**
     *
     */
    public EDAListRenderer() {
        setForeground( Color.WHITE );
        setBackground( Color.BLACK );
    }

    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
    }

    @Override
    public Component getListCellRendererComponent( JList list, Object value, int index,
                                                   boolean isSelected, boolean cellHasFocus ) {
        return this;
    }
}
