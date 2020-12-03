package io.edbm.Input.Actions;

import io.edbm.UI.BMWindow;
import net.java.games.input.Controller;

public class ScrollDownAction implements ActionImpl {
    
    public String actionName = "scrollTab";
    
    
    public ScrollDownAction ( BMWindow appWindow ) {
    
    }
    
    @Override
    public void performAction () {
     System.out.println("SCROLLING FAKE COMPONENT DOWN");
    }
}
