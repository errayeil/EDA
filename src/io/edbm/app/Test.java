package io.edbm.app;

import io.edbm.UI.EDAProgressBar;
import io.edbm.Utilities.Utils;

import javax.swing.*;
import java.awt.*;

public class Test {
    
    /**
     *
     */
    public static void main ( String args[] ) {
        
    JFrame frame = new JFrame(  );
    EDAProgressBar bar = new EDAProgressBar(  );
    
    bar.setMinimumSize( new Dimension(200, 50) );
    bar.setSize( new Dimension(200, 50) );
    bar.setMaximumSize( new Dimension(200, 50) );
    bar.setIndeterminate( true );

    frame.setMinimumSize( new Dimension(600, 600) );
    frame.setSize( new Dimension(600, 600) );

    Container pane = frame.getContentPane();

    pane.setLayout( new BorderLayout(  ) );
    pane.add( bar, BorderLayout.CENTER);

    Utils.invokeSetVisible( frame, true);
    
    }
}
