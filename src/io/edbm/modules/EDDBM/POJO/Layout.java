package io.edbm.modules.EDDBM.POJO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class Layout {
    
    public int threat_level_id;
    
    public int race_id;
    
    public int settlement_size_id;
    
    public int settlement_security_id;
    
    public int settlement_type_id;
    
    public int installation_type_id;
    
    public int beacon_type_id;
    
    public int tourist_beacon_index;
    
    public int shipwreck_type_id;
    
    public long updated_at;
    
    public boolean has_cdt;
    
    public boolean requires_srv_jumping;
    
    public boolean is_unique;
    
    public boolean is_unknown;
    
    public String threat_level_name;
    
    public String description;
    
    public String race_name;
    
    public String settlement_size_name;
    
    public String settlement_security_name;
    
    public String settlement_type_name;
    
    public String installation_type_name;
    
    public String beacon_type_name;
    
    public String shipwreck_type_name;
    
    List<String> materials = new ArrayList<>();
    
    List<String> commodities = new ArrayList<>();
}
