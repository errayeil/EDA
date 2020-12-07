package io.edbm.modules.EDDBM;

import io.edbm.UI.EDAModulePanel;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDDBModuleManager {
    
    /**
     *
     */
    private final EDAModulePanel mainPanel;
    
    /**
     *
     */
    public static final String systemsURL = "https://eddb.io/archive/v6/systems.csv";
    
    /**
     *
     */
    public static final String bodiesURL = "https://www.edsm.net/dump/bodies7days.json.gz";
    
    /**
     *
     */
    public static final String recentSystemsURL = "https://eddb.io/archive/v6/systems_recently.csv";
    
    /**
     *
     */
    public static final String JSONpopulatedSystemsURL = "https://eddb.io/archive/v6/systems_populated.json";
    
    /**
     *
     */
    public static final String JSON_L_populatedSystemsURL = "https://eddb.io/archive/v6/systems_populated.jsonl";
    
    /**
     *
     */
    public static final String JSONstationsURL = "https://eddb.io/archive/v6/stations.json";
    
    /**
     *
     */
    public static final String JSON_L_stationsURL = "https://eddb.io/archive/v6/stations.jsonl";
    
    /**
     *
     */
    public static final String JSONattractionsURL = "https://eddb.io/archive/v6/attractions.json";
    
    /**
     *
     */
    public static final String JSON_L_attractionsURL = "https://eddb.io/archive/v6/attractions.jsonl";
    
    /**
     *
     */
    public static final String JSON_factionsURL = "https://eddb.io/archive/v6/factions.json";
    
    /**
     *
     */
    public static final String JSON_L_factionsURL = "https://eddb.io/archive/v6/factions.jsonl";
    
    /**
     *
     */
    public static final String CSVfactionsURL = "https://eddb.io/archive/v6/factions.csv";
    
    /**
     *
     */
    public static final String JSONcommoditiesURL = "https://eddb.io/archive/v6/commodities.json";
    
    /**
     *
     */
    public static final String JSONmodulesURL = "https://eddb.io/archive/v6/modules.json";
    
    /**
     *
     */
    public EDDBModuleManager( final EDAModulePanel mainPanel ) {
        this.mainPanel = mainPanel;
    }
}
