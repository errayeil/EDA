package io.edbm.UI.Windows;

import io.edbm.UI.Component.EDAList;
import io.edbm.UI.Component.EDAScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAOptionsDialog extends JDialog {

    /**
     * Used to determine positioning of the dialog.
     */
    private Component parent;

    /**
     * The content pane of the results dialog.
     */
    private EDAPane dialogPanel;

    /**
     * The list of results to display in the dialog.
     */
    private List<String> defaultOptions;

    /**
     *
     */
    private EDAList optionsList;

    /**
     *
     */
    private EDAScrollPane optionsListScrollPane;

    /**
     * Boolean determining if this results dialog results
     * can be changed. If false, this dialog should be
     * used for static information such as the Primary
     * economy box seen on EDDB.
     */
    private boolean ableToPopulate;

    /**
     *
     */
    public EDAOptionsDialog( Component parent) {
        this.parent = parent;
        this.dialogPanel = new EDAPane(  );
        ableToPopulate = true;
    }

    /**
     *
     * @param defaultOptions
     */
    public EDAOptionsDialog( Component parent, List<String> defaultOptions) {
        this.parent = parent;
        this.dialogPanel = new EDAPane(  );
        this.defaultOptions = defaultOptions;
        this.ableToPopulate = false;

        setModal( true );
        setUndecorated( true );
        setSize( new Dimension(parent.getWidth(), 150) );
    }


}
