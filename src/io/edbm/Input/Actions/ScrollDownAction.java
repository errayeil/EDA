package io.edbm.Input.Actions;

import io.edbm.UI.EDAWindow;

public class ScrollDownAction implements ActionImpl {
    
    public String actionName = "scrollTab";
    
    
    public ScrollDownAction ( EDAWindow appWindow ) {
    
    }
    
    @Override
    public void performAction () {
     System.out.println("SCROLLING FAKE COMPONENT DOWN");
    }
}
