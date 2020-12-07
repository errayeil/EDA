package io.edbm.UI;

public class TabEvent {
    
    private EDAButton source;
    
    /**
     *
     * @param source
     */
    public TabEvent(EDAButton source) {
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
