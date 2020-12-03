package io.edbm.UI;

import java.awt.Color;
import javax.swing.JDialog;

/**
 * @author Steven Frizell
 * @version 1
 * @since 1
 */
public class BMWindow extends JDialog {
    
    /**
     *
     */
    private int selectedIndex;
    
    /**
     *
     */
    private BMButton selected;
    
    /**
     *
     */
    private BMButton home;
    
    /**
     *
     */
    private BMButton bookMark;
    
    /**
     *
     */
    private BMButton visited;
    
    /**
     *
     */
    private BMButton nearby;
    
    /**
     *
     */
    private BMButton list;
    
    /**
     *
     */
    private BMPanel windowPanel;
    
    /**
     *
     */
    public BMWindow () {
        windowPanel = new BMPanel();
        home = new BMButton( "", 40, 40 );
        bookMark = new BMButton( "Bookmarks" );
        visited = new BMButton("Visited");
        nearby = new BMButton( "Nearby" );
        list = new BMButton( "Shopping List");
        
        selected = home;
        
        home.setSelected( true );
        
        setUndecorated( true );
        setBackground( new Color( 0, 0, 0, 200) );
        setContentPane( windowPanel );
        setAlwaysOnTop( true );
        setModal( true );
        
        windowPanel.add( home );
        windowPanel.add( bookMark );
        windowPanel.add( visited );
        windowPanel.add( nearby );
        windowPanel.add( list );
        
        home.setLocation( 6, 9 );
        bookMark.setLocation( 52, 9 );
        visited.setLocation( 208, 9 );
        nearby.setLocation( 364, 9 );
        list.setLocation( 520, 9 );
    }
    
    public void setVisible() {
        if (isVisible())
            setVisible(false);
        else
            setVisible(true);
    }
    
    /**
     * Sets the selected button based off the provided index.
     * Does nothing if the index does not exist.
     * @param index
     */
    public void setSelectedButton(int index) {
        
        if (index >= 0 && index <= 4) {
            unselectButton();
    
            switch (index) {
                case 0:
                    home.setSelected( true );
                    selectedIndex = 0;
                    break;
                case 1:
                    bookMark.setSelected( true );
                    selectedIndex = 1;
                    break;
                case 2:
                    visited.setSelected( true );
                    selectedIndex = 2;
                    break;
                case 3:
                    nearby.setSelected( true );
                    selectedIndex = 3;
                    break;
                case 4:
                    list.setSelected( true );
                    selectedIndex = 4;
                    break;
            }
        }
    }
    
    /**
     *
     * @return
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }
    
    /**
     * Switches the currently displayed panel to the new one.
     */
    private void switchPanel(BMPanel panelToDisplay) {
    
    }
    
    /**
     * Unselects any selected buttons.
     */
    private void unselectButton() {
        
        selectedIndex = -1;
        
        if (home.isSelected()) {
            home.setSelected( false );
        } else if (bookMark.isSelected()) {
            bookMark.setSelected( false );
        } else if (visited.isSelected()) {
            visited.setSelected( false );
        } else if (nearby.isSelected()) {
            nearby.setSelected( false );
        } else if (list.isSelected()) {
            list.setSelected( false );
        }
    }
}
