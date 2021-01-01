package io.edbm.UI.Event;

/**
 * Custom interface used to communicate when an EDAButton is clicked.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public interface EDAButtonListener {
    
    void buttonClicked( EDAButtonEvent event);
}
