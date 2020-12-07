package io.edbm.UI;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

/**
 * The main window of EDA.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAWindow extends JDialog implements EDATabListener {
    
    /**
     *
     */
    private int selectedIndex;
    
    /**
     *
     */
    private EDAButton selected;
    
    /**
     * Button to select the Home module panel.
     */
    private EDAButton HMButton;
    
    /**
     * Button to select the Better Bookmark management module panel.
     */
    private EDAButton BBMMButton;
    
    /**
     * Button to select the Engineering material tracker module panel.
     */
    private EDAButton EMTMButton;
    
    /**
     * Button to select the Nearby star system module panel.
     */
    private EDAButton NSSMButton;
    
    /**
     * Button to select the Galactic market module panel.
     */
    private EDAButton GMMButton;
    
    /**
     * Button to select the Ship yard module panel.
     */
    private EDAButton SYMButton;
    
    /**
     * Button to select the Web page viewer module panel.
     */
    private EDAButton WPVMButton;
    
    /**
     * Button to select the EDDB Query module.
     */
    private EDAButton EDDBMButton;
    
    /**
     * The panel that displays the UI background graphics.
     */
    private EDAWindowPanel windowPanel;
    
    /**
     * Easily retrieve module panels assigned to its EDAButton.
     */
    private Map<EDAButton, EDAWindowPanel> modulePanels;
    
    /**
     *
     */
    public EDAWindow () {
        windowPanel = new EDAWindowPanel();
        HMButton = new EDAButton( "", 40, 40 );
        BBMMButton = new EDAButton( "BMM" );
        EMTMButton = new EDAButton( "EMTM");
        NSSMButton = new EDAButton( "NSSM" );
        GMMButton = new EDAButton( "GMM");
        SYMButton = new EDAButton( "SYM" );
        WPVMButton = new EDAButton( "WPVM" );
        EDDBMButton = new EDAButton( "EDDBM" );
        
        modulePanels = new HashMap<>();
        
        BBMMButton.setButtonSubText( "Bookmark management module" );
        EMTMButton.setButtonSubText( "Engineering material tracker module" );
        NSSMButton.setButtonSubText( "Nearby star system module" );
        GMMButton.setButtonSubText( "Galactic market module" );
        SYMButton.setButtonSubText( "Shipyard module" );
        WPVMButton.setButtonSubText( "Web page viewer module" );
        EDDBMButton.setButtonSubText( "Elite: Dangerous Database" );
        
        HMButton.setIndex( 0 );
        BBMMButton.setIndex( 1 );
        EMTMButton.setIndex( 2 );
        NSSMButton.setIndex( 3 );
        GMMButton.setIndex( 4 );
        SYMButton.setIndex( 5 );
        WPVMButton.setIndex( 6 );
        EDDBMButton.setIndex( 7 );
        
        HMButton.addTabListener( this );
        BBMMButton.addTabListener( this );
        EMTMButton.addTabListener( this );
        NSSMButton.addTabListener( this );
        GMMButton.addTabListener( this );
        SYMButton.addTabListener( this );
        WPVMButton.addTabListener( this );
        EDDBMButton.addTabListener( this );
        
        selected = HMButton;
        
        HMButton.setSelected( true );
        
        setUndecorated( true );
        setBackground( new Color( 0, 0, 0, 200) );
        setContentPane( windowPanel );
        setAlwaysOnTop( true );
        setModal( true );
        
        windowPanel.add( HMButton );
        windowPanel.add( BBMMButton );
        windowPanel.add( EMTMButton );
        windowPanel.add( NSSMButton );
        windowPanel.add( GMMButton );
        windowPanel.add( SYMButton );
        windowPanel.add( WPVMButton );
        windowPanel.add( EDDBMButton );
        
        HMButton.setLocation( 6, 9 );
        BBMMButton.setLocation( 52, 9 );
        EMTMButton.setLocation( 208, 9 );
        NSSMButton.setLocation( 364, 9 );
        GMMButton.setLocation( 520, 9 );
        SYMButton.setLocation(676, 9);
        WPVMButton.setLocation( 832, 9 );
        EDDBMButton.setLocation( 988, 9 );
    }
    
    /**
     *
     */
    public void setVisible() {
        setVisible( ! isVisible() );
    }
    
    /**
     * Sets the selected button based off the provided index.
     * Does nothing if the index does not exist.
     * @param index
     */
    public void setSelectedButton(int index) {
        
        if (index >= 0 && index <= 7) {
            unselectButton();
    
            SwingUtilities.invokeLater( () -> {
                switch (index) {
                    case 0:
                        HMButton.setSelected( true );
                        selectedIndex = 0;
                        break;
                    case 1:
                        BBMMButton.setSelected( true );
                        selectedIndex = 1;
                        break;
                    case 2:
                        EMTMButton.setSelected( true );
                        selectedIndex = 2;
                        break;
                    case 3:
                        NSSMButton.setSelected( true );
                        selectedIndex = 3;
                        break;
                    case 4:
                        GMMButton.setSelected( true );
                        selectedIndex = 4;
                        break;
                    case 5:
                        SYMButton.setSelected( true );
                        selectedIndex = 5;
                        break;
                    case 6:
                        WPVMButton.setSelected( true );
                        selectedIndex = 6;
                        break;
                    case 7:
                        EDDBMButton.setSelected( true );
                        selectedIndex = 7;
                }
            } );
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
    private void switchPanel( EDAWindowPanel panelToDisplay) {
    
    }
    
    /**
     * Unselects any selected buttons.
     */
    private void unselectButton() {
        
        selectedIndex = -1;
        
        if ( HMButton.isSelected()) {
            HMButton.setSelected( false );
        } else if ( BBMMButton.isSelected()) {
            BBMMButton.setSelected( false );
        } else if ( EMTMButton.isSelected()) {
            EMTMButton.setSelected( false );
        } else if ( NSSMButton.isSelected()) {
            NSSMButton.setSelected( false );
        } else if ( GMMButton.isSelected()) {
            GMMButton.setSelected( false );
        } else if (SYMButton.isSelected()) {
            SYMButton.setSelected( false );
        } else if (WPVMButton.isSelected()) {
            WPVMButton.setSelected( false );
        } else if (EDDBMButton.isSelected()) {
            EDDBMButton.setSelected( false );
        }
    }
    
    @Override
    public void tabClicked (TabEvent event) {
        int index = event.getSource().getIndex();
        setSelectedButton( index );
    }
}
