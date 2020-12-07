package io.edbm.modules.EDDBM;

import io.edbm.modules.EDDBM.POJO.PendingState;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDDBSearch {
    
    private final String none = "none";
    
    /**
     *
     */
    private boolean populated;
    
    /**
     *
     */
    private boolean needsPermit;
    
    /**
     *
     */
    private String allegiance;
    
    /**
     *
     */
    private String government;
    
    /**
     *
     */
    private String primaryEconomy;
    
    /**
     *
     */
    private String minorFaction;
    
    /**
     *
     */
    private String presenceType;
    
    /**
     *
     */
    private String powers;
    
    /**
     *
     */
    private String powerEffects;
    
    /**
     *
     */
    private String referenceSystem;
    
    /**
     *
     */
    private String security;
    
    /**
     *
     */
    private String factionStates;
    
    /**
     *
     */
    public EDDBSearch() {
        populated = true;
        needsPermit = false;
        allegiance = none;
        government = none;
        primaryEconomy = none;
        minorFaction = none;
        presenceType = none;
    }
    
    /**
     *
     */
    public void startSearch() {
    
    }
    
    public void setSearchCriteria(boolean populated, boolean needsPermit, String allegiance, String government, String primaryEconomy,
                                  String minorFaction, String presenceType, String powers, String powerEffects, String referenceSystem,
                                  String security, String factionStates) {
        
        
    }
    
    /**
     *
     * @param populated
     */
    public void setSearchForPopulated(boolean populated) {
        this.populated = populated;
    }
    
    /**
     *
     * @param allegiance
     */
    public void setSearchForAllegiance(String allegiance) {
        this.allegiance = allegiance;
    }
    
    /**
     *
     * @param government
     */
    public void setSearchForGovernment(String government) {
        this.government = government;
    }
    
    /**
     *
     * @param primaryEconomy
     */
    public void setSearchForPrimaryEconomy(String primaryEconomy) {
        this.primaryEconomy = primaryEconomy;
    }
    
    /**
     *
     * @param minorFaction
     */
    public void setSearchForMinorFaction(String minorFaction) {
        this.minorFaction = minorFaction;
    }
    
    /**
     *
     * @param presenceType
     */
    public void setSearchForPresenceType(String presenceType) {
        this.presenceType = presenceType;
    }
    
    /**
     *
     * @param needsPermit
     */
    public void setSearchForNeedsPermit(boolean needsPermit) {
        this.needsPermit = needsPermit;
    }
    
    /**
     *
     * @param powers
     */
    public void setSearchForPowers(String powers) {
       this.powers = powers;
    }
    
    /**
     *
     * @param powerEffects
     */
    public void setSearchForPowerEffects(String powerEffects) {
        this.powerEffects = powerEffects;
    }
    
    /**
     *
     * @param referenceSystem
     */
    public void setSearchForReferenceSystem(String referenceSystem) {
        this.referenceSystem = referenceSystem;
    }
    
    /**
     *
     * @param security
     */
    public void setSearchForSecurity(String security) {
        this.security = security;
    }
    
    /**
     *
     * @param factionStates
     */
    public void setSearchForFactionStates(String factionStates) {
        this.factionStates = factionStates;
    }
    
    /**
     *
     */
    public void setSearchForStations() {
    
    }
}
