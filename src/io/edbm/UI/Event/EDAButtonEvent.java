package io.edbm.UI.Event;

import io.edbm.UI.Component.EDAButton;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAButtonEvent {

    /**
     * The source of the event.
     */
    private EDAButton source;

    /**
     * The time of the event in nano time..
     */
    private long time;
    
    /**
     *
     * @param source
     */
    public EDAButtonEvent( EDAButton source) {
        this.source = source;

        time = System.nanoTime();
    }
    
    /**
     * Returns the source of the event.
     * @return
     */
    public EDAButton getSource() {
        return source;
    }

    /**
     * Returns the nano time of the event.
     * @return
     */
    public long getNanoTimeOfEvent() {
        return time;
    }
}
