package io.edbm.Input.Actions;

import io.edbm.UI.Windows.EDADialog;

public class ScrollDownAction implements ActionImpl {
    
    public String actionName = "scrollTab";
    
    
    public ScrollDownAction ( EDADialog appWindow ) {
    
    }
    
    @Override
    public void performAction () {
     System.out.println("SCROLLING FAKE COMPONENT DOWN");
    }
}
