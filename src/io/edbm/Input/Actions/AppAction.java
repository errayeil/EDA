package io.edbm.Input.Actions;

/**
 *
 */
public class AppAction implements ActionImpl {
    
    public AppAction ( ) {
    
    }
    
    
    @Override
    public void performAction () {
        System.exit( 0 );
    }
}
