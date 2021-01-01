package io.edbm.Input.Actions;

import io.edbm.UI.Windows.EDADialog;

public class ScrollUpAction implements ActionImpl {
    
    private EDADialog appWindow;
    
    public ScrollUpAction( EDADialog appWindow) {
        this.appWindow = appWindow;
    }
    
    @Override
    public void performAction () {
        System.out.println("SCROLLING FAKE COMPONENT UP");
    }
}
