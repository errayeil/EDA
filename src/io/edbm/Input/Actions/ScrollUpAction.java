package io.edbm.Input.Actions;

import io.edbm.UI.EDAWindow;

public class ScrollUpAction implements ActionImpl {
    
    private EDAWindow appWindow;
    
    public ScrollUpAction( EDAWindow appWindow) {
        this.appWindow = appWindow;
    }
    
    @Override
    public void performAction () {
        System.out.println("SCROLLING FAKE COMPONENT UP");
    }
}
