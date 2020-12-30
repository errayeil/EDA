package io.edbm.UI;

import io.edbm.modules.EDDBM.UI.EDDBMSystemsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The main window of EDA.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAWindow extends JDialog implements EDAButtonListener {

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
     *
     */
    private EDAButton NDMButton;

    /**
     * The panel that displays the UI background graphics.
     */
    private EDABackgroundContainer windowPanel;

    /**
     * Easily retrieve module panels assigned to its EDAButton.
     */
    private Map< EDAButton, EDAModulePanel > modulePanels;

    private boolean isReady = false;

    /**
     *
     */
    public EDAWindow( ) {
        windowPanel = new EDABackgroundContainer();
        setUndecorated( true );
        setBackground( new Color( 0, 0, 0, 200 ) );
        setContentPane( windowPanel );
        setAlwaysOnTop( true );
        setModal( true );
        setLayout( null );
    }
    
    /**
     *
     */
    public void initButtons() {
        HMButton = new EDAButton( "", 40, 40 );
        BBMMButton = new EDAButton( "BMM" );
        EMTMButton = new EDAButton( "MTM" );
        NSSMButton = new EDAButton( "NSSM" );
        GMMButton = new EDAButton( "GMM" );
        SYMButton = new EDAButton( "SYM" );
        WPVMButton = new EDAButton( "WPVM" );
        EDDBMButton = new EDAButton( "EDDBM" );
        NDMButton = new EDAButton( "", 40, 40 );

        modulePanels = new HashMap<>( );

        
        HMButton.setImageIcon( new ImageIcon(EDAWindow.class.getResource( "/io/edbm/resources/home24p.png" )) );
        HMButton.setSecondaryText( "" );
        BBMMButton.setSecondaryText( "Bookmark management" );
        EMTMButton.setSecondaryText( "Material tracker" );
        NSSMButton.setSecondaryText( "Nearby star system" );
        GMMButton.setSecondaryText( "Galactic market" );
        SYMButton.setSecondaryText( "Shipyard module" );
        WPVMButton.setSecondaryText( "Web page viewer" );
        EDDBMButton.setSecondaryText( "Elite: Dangerous Database" );
        NDMButton.setSecondaryText( "" );
        NDMButton.setImageIcon( new ImageIcon(EDAWindow.class.getResource( "/io/edbm/resources/nonotif24p.png" )) );

        HMButton.setIndex( 0 );
        BBMMButton.setIndex( 1 );
        EMTMButton.setIndex( 2 );
        NSSMButton.setIndex( 3 );
        GMMButton.setIndex( 4 );
        SYMButton.setIndex( 5 );
        WPVMButton.setIndex( 6 );
        EDDBMButton.setIndex( 7 );
        NDMButton.setIndex( 8 );

//        HMButton.addButtonListener( this );
//        BBMMButton.addButtonListener( this );
//        EMTMButton.addButtonListener( this );
//        NSSMButton.addButtonListener( this );
//        GMMButton.addButtonListener( this );
//        SYMButton.addButtonListener( this );
//        WPVMButton.addButtonListener( this );
//        EDDBMButton.addButtonListener( this );

        selected = HMButton;

        HMButton.setSelected( true );

        windowPanel.add( HMButton );
        windowPanel.add( BBMMButton );
        windowPanel.add( EMTMButton );
        windowPanel.add( NSSMButton );
        windowPanel.add( GMMButton );
        windowPanel.add( SYMButton );
        windowPanel.add( WPVMButton );
        windowPanel.add( EDDBMButton );
        windowPanel.add( NDMButton );

        HMButton.setLocation( 6, 9 );
        BBMMButton.setLocation( 52, 9 );
        EMTMButton.setLocation( 208, 9 );
        NSSMButton.setLocation( 364, 9 );
        GMMButton.setLocation( 520, 9 );
        SYMButton.setLocation( 676, 9 );
        WPVMButton.setLocation( 832, 9 );
        EDDBMButton.setLocation( 988, 9 );
        NDMButton.setLocation( 988 + 156, 9 );

        initModulePanels();
    }

    public boolean isReady() {
        return isReady;
    }

    /**
     *
     */
    private void initModulePanels() {
        EDAModulePanel eddbModulePanel = new EDAModulePanel( 1150, 600 );
        EDDBMSystemsPanel eddbPanel = new EDDBMSystemsPanel(1150, 600);

        modulePanels.put( EDDBMButton, eddbModulePanel );

        windowPanel.add( eddbPanel );

        eddbPanel.setLocation( 0, 75 );

        eddbModulePanel.setLocation( 0, 60 );
    }

    /**
     *
     */
    public void setVisible( ) {
        setVisible( !isVisible( ) );
    }

    /**
     * Sets the selected button based off the provided index. Does nothing if the index does not exist.
     *
     * @param index
     */
    public void setSelectedButton( int index ) {

        if ( index >= 0 && index <= 7 ) {

            SwingUtilities.invokeLater( ( ) -> {
                switch ( index ) {
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
     * @return
     */
    public int getSelectedIndex( ) {
        return selectedIndex;
    }

    /**
     * Switches the currently displayed panel to the new one.
     */
    private void switchPanel( EDABackgroundContainer panelToDisplay ) {

    }

    /**
     * Unselects any selected buttons.
     */
    private void unselectButton( EDAButton selected) {

        selectedIndex = selected.getIndex();

        if ( HMButton.isSelected( )) {
            if (HMButton.getIndex() != selectedIndex) {
                HMButton.setSelected( false );
            }
        } else if ( BBMMButton.isSelected( ) ) {
            if (BBMMButton.getIndex() != selectedIndex) {
                BBMMButton.setSelected( false );
            }
        } else if ( EMTMButton.isSelected( ) ) {
            if (EMTMButton.getIndex() != selectedIndex) {
                EMTMButton.setSelected( false );
            }
        } else if ( NSSMButton.isSelected( ) ) {
            if (NSSMButton.getIndex() != selectedIndex) {
                NSSMButton.setSelected( false );
            }
        } else if ( GMMButton.isSelected( ) ) {
            if (GMMButton.getIndex() != selectedIndex) {
                GMMButton.setSelected( false );
            }
        } else if ( SYMButton.isSelected( ) ) {
            if (SYMButton.getIndex() != selectedIndex) {
                SYMButton.setSelected( false );
            }
        } else if ( WPVMButton.isSelected( ) ) {
            if (WPVMButton.getIndex() != selectedIndex) {
                WPVMButton.setSelected( false );
            }
        } else if ( EDDBMButton.isSelected( ) ) {
            if (EDDBMButton.getIndex() != selectedIndex) {
                EDDBMButton.setSelected( false );
            }
        }
    }

    @Override
    public void buttonClicked( EDAButtonEvent event ) {
        unselectButton( event.getSource() );
        int index = event.getSource().getIndex();

        switch (index) {
            case 7:
                modulePanels.get( event.getSource() ).setVisible( true );
                break;
        }
    }
}
