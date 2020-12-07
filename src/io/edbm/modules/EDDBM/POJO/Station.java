package io.edbm.modules.EDDBM.POJO;

import java.util.ArrayList;
import java.util.List;



/**
 * POJO for storing station information pulled from EDDB stations json file.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class Station {
    
    public String name;
    
    public String settlement_security;
    
    public String government;
    
    public String allegiance;
    
    public String type;
    
    public String max_landing_pad_size;
    
    public int government_id;
    
    public int allegiance_id;
    
    public int type_id;
    
    public int body_id;
    
    public int controlling_minor_faction_id;
    
    public int id;
    
    public int settlement_size_id;
    
    public int settlement_security_id;
    
    public int system_id;
    
    public long updated_at;
    
    public String settlement_size;
    
    public long distance_to_star;
    
    public long shipyard_updated_at;
    
    public long outfitting_updated_at;
    
    public long market_updated_at;
    
    public long ed_market_id;
    
    public boolean has_blackmarket;
    
    public boolean has_market;
    
    public boolean has_refuel;
    
    public boolean has_repair;
    
    public boolean has_rearm;
    
    public boolean has_outfitting;
    
    public boolean has_shipyard;
    
    public boolean has_docking;
    
    public boolean has_commodities;
    
    public boolean has_material_trader;
    
    public boolean has_technology_broker;
    
    public boolean has_carrier_vendor;
    
    public boolean has_carrier_administration;
    
    public boolean has_interstellar_factors;
    
    public boolean has_universal_cartographics;
    
    public boolean is_planetary;
    
    public List<States> states = new ArrayList<>();
    
    public List<String> import_commodities = new ArrayList<>();
    
    public List<String> export_commodities = new ArrayList<>();
    
    public List<String> prohibited_commodities = new ArrayList<>();
    
    public List<String> economies = new ArrayList<>();
    
    public List<String> selling_ships = new ArrayList<>();
    
    public List<Integer> selling_modules = new ArrayList<>();
    
}
