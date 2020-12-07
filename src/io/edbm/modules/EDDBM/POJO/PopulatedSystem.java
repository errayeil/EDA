package io.edbm.modules.EDDBM.POJO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class PopulatedSystem {
    
    public int id;
    
    public int edsm_id;
    
    public int government_id;
    
    public int allegiance_id;
    
    public int security_id;
    
    public int primary_economy_id;
    
    public int controlling_minor_faction_id;
    
    public double x;
    
    public double y;
    
    public double z;
    
    public long population;
    
    public long updated_at;
    
    public long ed_system_address;
    
    public boolean is_populated;
    
    public boolean needs_permit;
    
    public String government;
    
    public String name;
    
    public String allegiance;
    
    public String security;
    
    public String primary_economy;
    
    public String power;
    
    public String power_state;
    
    public String power_state_id;
    
    public String simbad_ref;
    
    public String controlling_minor_faction;
    
    public String reserve_type_id;
    
    public String reserve_type;
    
    public List<States> states = new ArrayList<>();
    
    public List<PSMinorFactions> minor_faction_presences = new ArrayList<>();
    
    
    
}
