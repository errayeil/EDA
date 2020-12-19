package io.edbm.modules.EDDBM.UI;

import io.edbm.UI.EDAButton;
import io.edbm.UI.EDASearchBox;
import io.edbm.UI.EDATable;
import io.edbm.modules.EDDBM.EDDBSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel providing components to query EDDB system data.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDDBMSystemsPanel extends JPanel   {

    /**
     *
     */
    private float backgroundAlpha;

    /**
     *
     */
    private EDASearchBox referenceSystemBox;

    /**
     *
     */
    private EDASearchBox searchSystemBox;

    /**
     *
     */
    private EDASearchBox allegianceBox;

    /**
     *
     */
    private EDASearchBox governmentBox;

    /**
     *
     */
    private EDASearchBox primaryEconomyBox;

    /**
     *
     */
    private EDASearchBox minorFactionBox;

    /**
     *
     */
    private EDASearchBox presenceTypeBox;

    /**
     *
     */
    private EDASearchBox needPermitBox;

    /**
     *
     */
    private EDASearchBox stationFilterBox;

    /**
     *
     */
    private EDASearchBox powersBox;

    /**
     *
     */
    private EDASearchBox powerEffectsBox;

    /**
     *
     */
    private EDASearchBox securityBox;

    /**
     *
     */
    private EDASearchBox factionStateBox;

    /**
     *
     */
    private EDAButton searchButton;

    /**
     *
     */
    private EDATable searchResultsTable;

    /**
     *
     */
    private EDDBSearch eddbSearcher;

    /*
     *
     */
    public EDDBMSystemsPanel(int width, int height) {
        setSize( new Dimension(width, height ));
        setMinimumSize( new Dimension(width, height) );
        setLayout( null );
        setOpaque( false );

        referenceSystemBox = new EDASearchBox( true);
        searchSystemBox = new EDASearchBox( true);
        allegianceBox = new EDASearchBox( false );
        governmentBox = new EDASearchBox( false );
        primaryEconomyBox = new EDASearchBox( false );
        minorFactionBox = new EDASearchBox( false );
        presenceTypeBox = new EDASearchBox( false);
        needPermitBox = new EDASearchBox( false);
        stationFilterBox = new EDASearchBox( false );
        powersBox = new EDASearchBox( false );
        powerEffectsBox = new EDASearchBox(  false );
        securityBox = new EDASearchBox(  false );
        factionStateBox = new EDASearchBox( false );
        searchButton = new EDAButton( "Search" );
        searchResultsTable = new EDATable(); //TODO
        eddbSearcher = new EDDBSearch(); //TODO

        referenceSystemBox.setTextBoxHintText( "Reference system" );
        searchSystemBox.setTextBoxHintText( "Search for a system" );
        allegianceBox.setTextBoxHintText( "Select allegiance" );
        governmentBox.setTextBoxHintText( "Select government" );
        primaryEconomyBox.setTextBoxHintText( "Select Economy" );
        minorFactionBox.setTextBoxHintText( "Search for a Minor Faction" );
        presenceTypeBox.setTextBoxHintText( "Presence or control?" );
        needPermitBox.setTextBoxHintText( "Requires Permit?" );
        stationFilterBox.setTextBoxHintText( "Has stations?" );
        powersBox.setTextBoxHintText( "Add Powers" );
        powerEffectsBox.setTextBoxHintText( "Add Power effects" );
        securityBox.setTextBoxHintText( "Select Security" );
        factionStateBox.setTextBoxHintText( "Select Faction States" );

          add( referenceSystemBox );
//        add( searchSystemBox );
//        add( allegianceBox );
//        add( governmentBox );

        referenceSystemBox.setLocation( 20, 10 );
        searchSystemBox.setLocation( 20, 20 );
        allegianceBox.setLocation( 20, 40 );
        governmentBox.setLocation( 20, 60 );

        searchButton.addMouseListener( new MouseAdapter( ) {

            @Override
            public void mousePressed( MouseEvent e ) {
                searchButton.setSelected( true );
            }

            @Override
            public void mouseReleased( MouseEvent e ) {
                searchButton.setSelected( false );
            }

        });

        setBackground( new Color(0,0,0,200) );
    }
}
