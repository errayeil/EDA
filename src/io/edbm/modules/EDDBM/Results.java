package io.edbm.modules.EDDBM;

import io.edbm.modules.EDDBM.POJO.Faction;
import io.edbm.modules.EDDBM.POJO.PopulatedSystem;
import io.edbm.modules.EDDBM.POJO.Station;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class Results {
    
    /**
     * A list of populated systems matching the provided search string.
     */
    private final List<PopulatedSystem> popSystemResults;
    
    /**
     * A list of stations matching the provided search string.
     */
    private final List<Station> stationResults;
    
    /**
     * A list of factions matching the provided faction string.
     */
    private final List<Faction> factionResults;
    
    /**
     *
     */
    public Results() {
        popSystemResults = new ArrayList<>();
        stationResults = new ArrayList<>();
        factionResults = new ArrayList<>();
    }
    
    /**
     *
     * @param system
     */
    public void addSystemResult(final PopulatedSystem system) {
        popSystemResults.add( system );
    }
    
    /**
     *
     * @param station
     */
    public void addStationResult(final Station station) {
        stationResults.add( station );
    }
    
    /**
     *
     * @param faction
     */
    public void addFactionResult(final Faction faction) {
        factionResults.add( faction );
    }
    
}
