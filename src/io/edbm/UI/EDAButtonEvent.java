package io.edbm.UI;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAButtonEvent {

    /**
     *
     */
    private EDAButton source;
    
    /**
     *
     * @param source
     */
    public EDAButtonEvent( EDAButton source) {
        this.source = source;
    }
    
    /**
     *
     * @return
     */
    public EDAButton getSource() {
        return source;
    }
}
