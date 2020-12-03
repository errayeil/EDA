package io.edbm.Input.Actions;

import io.edbm.UI.BMWindow;

public class ScrollUpAction implements ActionImpl {
    
    private BMWindow appWindow;
    
    public ScrollUpAction(BMWindow appWindow) {
        this.appWindow = appWindow;
    }
    
    @Override
    public void performAction () {
        System.out.println("SCROLLING FAKE COMPONENT UP");
    }
}
