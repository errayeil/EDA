package io.edbm.modules.EDDBM;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonStreamParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import io.edbm.modules.EDDBM.POJO.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;

/**
 * Parses JSON files provided by EDDB.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDDBParser {
    
    /**
     *
     */
    private final String NA = "Unavailable";
    
    
    /**
     *
     */
    public EDDBParser() {
    
    }
    
    /**
     * Searches the populated systems json file for the specified system and returns possible results.
     * @param systemName The name of the system to parse for
     */
    public List<PopulatedSystem> parseForSystem(final String systemName) throws IOException {
        return parsePopulatedSystemsJSON( systemName );
    }
    
    /**
     *
     * @param systemID
     * @return
     */
    public List<Station> getStationsForSystem(final int systemID) throws IOException {
        return parseStationsJSON(systemID);
    }
    
    /**
     *
     * @param systemID
     * @return
     * @throws IOException
     */
    public List<Faction> getFactionsForSystem(final int systemID) throws IOException {
        return parseFactionsJSON(systemID);
    }
    
    /**
     * Creates and returns a new InputStreamReader for the JSONParser.
     *
     * @param strURL  The URL we are opening a connection too.
     * @return The InputStreamReader
     * @throws IOException If an exception occurred.
     */
    private InputStreamReader getJSONStreamReader(String strURL) throws IOException {
        URL url = new URL( strURL );
        InputStream urlStream = url.openStream();
        
        return new InputStreamReader( urlStream );
    }
    
    /**
     *
     * @param systemName
     */
    public List<Body> parseBodiesFor(final String systemName) throws IOException {
        GZIPInputStream gStream = new GZIPInputStream( new URL( EDDBModuleManager.bodiesURL ).openStream() );
        InputStreamReader strReader = new InputStreamReader( new URL(EDDBModuleManager.JSON_factionsURL).openStream() );
        JsonReader reader = new JsonReader( strReader );
        
        Gson gson = new GsonBuilder().serializeNulls().create();
        
        List<Body> bodies = new ArrayList<>();
        
        if (reader.peek() != JsonToken.BEGIN_ARRAY) {
            reader.beginArray();
        }
        
        while (reader.hasNext()) {

        }
        
        System.out.println("Loop exited.");
        
        strReader.close();
        return bodies;
    }
    
    /**
     * Testing function.
     *
     * @throws IOException
     */
    private List<Station> parseStationsJSON(final int systemID) throws IOException {
        InputStreamReader strReader = getJSONStreamReader( EDDBModuleManager.JSONstationsURL );
        
        JsonElement mainEle = new JsonParser().parse( strReader );
        JsonArray array = mainEle.getAsJsonArray();
        
        List<Station> stations = new ArrayList<>();
    
        for (JsonElement element : array) {
            Station station = new Station();
            JsonObject obj = element.getAsJsonObject();
            
            if (!obj.get( "system_id" ).isJsonNull()) {
                if (obj.get( "system_id" ).getAsInt() == systemID) {
                    station.id = !obj.get( "id" ).isJsonNull() ? obj.get( "id" ).getAsInt() : -1;
                    station.name = !obj.get( "name" ).isJsonNull() ? obj.get( "name" ).getAsString() : NA;
                    station.system_id = !obj.get( "system_id" ).isJsonNull() ? obj.get( "system_id" ).getAsInt() : -1;
                    station.updated_at = !obj.get( "updated_at" ).isJsonNull() ? obj.get( "updated_at" ).getAsLong() : -1;
                    station.max_landing_pad_size = !obj.get( "max_landing_pad_size" ).isJsonNull() ? obj.get( "max_landing_pad_size" ).getAsString() : NA;
                    station.distance_to_star = !obj.get( "distance_to_star" ).isJsonNull() ? obj.get( "distance_to_star" ).getAsLong() : -1;
                    station.government_id = !obj.get( "government_id" ).isJsonNull() ? obj.get( "government_id" ).getAsInt() : -1;
                    station.government = !obj.get( "government" ).isJsonNull() ? obj.get( "government" ).getAsString() : NA;
                    station.allegiance_id = !obj.get( "allegiance_id" ).isJsonNull() ? obj.get( "allegiance_id" ).getAsInt() : -1;
                    station.allegiance = !obj.get( "allegiance" ).isJsonNull() ? obj.get( "allegiance" ).getAsString() : NA;
                    station.type_id = !obj.get( "type_id" ).isJsonNull() ? obj.get( "type_id" ).getAsInt() : -1;
                    station.type = !obj.get( "type" ).isJsonNull() ? obj.get( "type" ).getAsString() : NA;
                    station.has_blackmarket = !obj.get( "has_blackmarket" ).isJsonNull() ? obj.get( "has_blackmarket" ).getAsBoolean() : false;
                    station.has_market = !obj.get( "has_market" ).isJsonNull() ? obj.get( "has_market" ).getAsBoolean() : false;
                    station.has_refuel = !obj.get( "has_refuel" ).isJsonNull() ? obj.get( "has_refuel" ).getAsBoolean() : false;
                    station.has_repair = !obj.get( "has_repair" ).isJsonNull() ? obj.get( "has_repair" ).getAsBoolean() : false;
                    station.has_rearm = !obj.get( "has_rearm" ).isJsonNull() ? obj.get( "has_rearm" ).getAsBoolean() : false;
                    station.has_outfitting = !obj.get( "has_outfitting" ).isJsonNull() ? obj.get( "has_outfitting" ).getAsBoolean() : false;
                    station.has_shipyard = !obj.get( "has_shipyard" ).isJsonNull() ? obj.get( "has_shipyard" ).getAsBoolean() : false;
                    station.has_docking = !obj.get( "has_docking" ).isJsonNull() ? obj.get( "has_docking" ).getAsBoolean() : false;
                    station.has_commodities = !obj.get( "has_commodities" ).isJsonNull() ? obj.get( "has_commodities" ).getAsBoolean() : false;
                    station.has_material_trader = !obj.get( "has_material_trader" ).isJsonNull() ? obj.get( "has_material_trader" ).getAsBoolean() : false;
                    station.has_technology_broker = !obj.get( "has_technology_broker" ).isJsonNull() ? obj.get( "has_technology_broker" ).getAsBoolean() :
                            false;
                    station.has_carrier_vendor = !obj.get( "has_carrier_vendor" ).isJsonNull() ? obj.get( "has_carrier_vendor" ).getAsBoolean() : false;
                    station.has_carrier_administration = !obj.get( "has_carrier_administration" ).isJsonNull() ? obj.get( "has_carrier_administration" ).getAsBoolean() :
                            false;
                    station.has_interstellar_factors = !obj.get( "has_interstellar_factors" ).isJsonNull() ?
                            obj.get( "has_interstellar_factors" ).getAsBoolean() : false;
                    station.has_universal_cartographics = !obj.get( "has_universal_cartographics" ).isJsonNull() ?
                            obj.get( "has_universal_cartographics" ).getAsBoolean() : false;
                    station.shipyard_updated_at = !obj.get( "shipyard_updated_at" ).isJsonNull() ? obj.get( "shipyard_updated_at" ).getAsLong() : -1;
                    station.outfitting_updated_at = !obj.get( "outfitting_updated_at" ).isJsonNull() ? obj.get( "outfitting_updated_at" ).getAsLong() : -1;
                    station.market_updated_at = !obj.get( "market_updated_at" ).isJsonNull() ? obj.get( "market_updated_at" ).getAsLong() : -1;
                    station.is_planetary = !obj.get( "is_planetary" ).isJsonNull() ? obj.get( "is_planetary" ).getAsBoolean() : false;
                    station.settlement_size_id = !obj.get( "settlement_size_id" ).isJsonNull() ? obj.get( "settlement_size_id" ).getAsInt() : -1;
                    station.settlement_size = !obj.get( "settlement_size" ).isJsonNull() ? obj.get( "settlement_size" ).getAsString() : NA;
                    station.settlement_security_id = !obj.get( "settlement_security_id" ).isJsonNull() ? obj.get( "settlement_security_id" ).getAsInt() : -1;
                    station.settlement_security = !obj.get( "settlement_security" ).isJsonNull() ? obj.get( "settlement_security" ).getAsString() : NA;
                    station.body_id = !obj.get( "body_id" ).isJsonNull() ? obj.get( "body_id" ).getAsInt() : -1;
                    station.controlling_minor_faction_id = !obj.get( "controlling_minor_faction_id" ).isJsonNull() ? obj.get(
                            "controlling_minor_faction_id" ).getAsInt() : -1;
                    station.ed_market_id = !obj.get( "ed_market_id" ).isJsonNull() ? obj.get( "ed_market_id" ).getAsLong() : -1;
    
                    JsonArray statesArray = obj.getAsJsonArray("states");
    
                    for (JsonElement statesElement : statesArray) {
                        States state = new States();
                        JsonObject obj2 = statesElement.getAsJsonObject();
        
                        state.id = !obj2.get( "id" ).isJsonNull() ? obj2.get( "id" ).getAsInt() : -1;
                        state.name = !obj2.get( "name" ).isJsonNull() ? obj2.get( "name" ).getAsString() : NA;
        
                        station.states.add( state );
                    }
    
                    JsonArray importArray = obj.getAsJsonArray("import_commodities");
    
                    for (int a = 0; a < importArray.size(); a++) {
                        String com = importArray.get( a ).getAsString();
                        station.import_commodities.add( com);
                    }
    
                    JsonArray exportArray = obj.getAsJsonArray("export_commodities");
    
                    for (int b = 0; b < exportArray.size(); b++) {
                        String com = exportArray.get( b ).getAsString();
                        station.export_commodities.add( com );
                    }
    
                    JsonArray prohibitedArray = obj.getAsJsonArray("prohibited_commodities");
    
                    for (int c = 0; c < prohibitedArray.size(); c++) {
                        String com = prohibitedArray.get( c ).getAsString();
                        station.prohibited_commodities.add( com );
                    }
    
                    JsonArray economiesArray = obj.getAsJsonArray("economies");
    
                    for (int d = 0; d < economiesArray.size(); d++) {
                        station.economies.add( economiesArray.get( d ).getAsString() );
                    }
    
                    JsonArray sellingShipsArray = obj.getAsJsonArray("selling_ships");
    
                    for (int e = 0; e < sellingShipsArray.size(); e++) {
                        String ship = sellingShipsArray.get( e ).getAsString();
                        station.selling_ships.add( ship );
                    }
    
                    JsonArray sellingModulesArray = obj.getAsJsonArray("selling_modules");
    
                    for (int f = 0; f < sellingModulesArray.size(); f++) {
                        station.selling_modules.add( sellingModulesArray.get( f ).getAsInt() );
                    }
    
                    stations.add( station );
                }
            }
        }
        
        strReader.close();
        
        System.out.println("Stations list size : " +  stations.size());
        
        return stations;
    }
    
    /**
     *
     * @param systemName
     * @throws IOException
     */
    private List<PopulatedSystem> parsePopulatedSystemsJSON(final String systemName) throws IOException {
        InputStreamReader strReader = getJSONStreamReader( EDDBModuleManager.JSONpopulatedSystemsURL );
        
        JsonElement mainEle = new JsonParser().parse( strReader );
        JsonArray array = mainEle.getAsJsonArray();
        
        List<PopulatedSystem> popSystems = new ArrayList<>();
        
        for (JsonElement element : array) {
            PopulatedSystem system = new PopulatedSystem();
            
            JsonObject obj = element.getAsJsonObject();
            
            if (obj.get( "name").getAsString().equals( systemName )) {
                system.id = !obj.get( "id" ).isJsonNull() ? obj.get( "id" ).getAsInt() : -1;
                system.edsm_id = !obj.get( "edsm_id" ).isJsonNull() ? obj.get( "edsm_id" ).getAsInt(): -1;
                system.name = !obj.get( "name" ).isJsonNull() ? obj.get( "name" ).getAsString() : NA;
                system.x = !obj.get( "x" ).isJsonNull() ? obj.get( "x" ).getAsDouble() : -1;
                system.y = !obj.get( "y" ).isJsonNull() ? obj.get( "y" ).getAsDouble() : -1;
                system.z = !obj.get( "z" ).isJsonNull() ? obj.get( "z" ).getAsDouble() : -1;
                system.population = !obj.get( "population" ).isJsonNull() ? obj.get( "population" ).getAsLong() : -1;
                system.is_populated = !obj.get( "is_populated" ).isJsonNull() ? obj.get( "is_populated" ).getAsBoolean() : false;
                system.government_id = !obj.get( "government_id" ).isJsonNull() ? obj.get( "government_id" ).getAsInt() : -1;
                system.government = !obj.get( "government").isJsonNull() ? obj.get( "government" ).getAsString() : NA;
                system.allegiance_id = !obj.get( "allegiance_id" ).isJsonNull() ? obj.get( "allegiance_id" ).getAsInt() : -1;
                system.allegiance = !obj.get( "allegiance" ).isJsonNull() ? obj.get( "allegiance" ).getAsString() : NA;
                system.security_id = !obj.get( "security_id" ).isJsonNull() ? obj.get( "security_id" ).getAsInt() : -1;
                system.security = !obj.get( "security" ).isJsonNull() ? obj.get( "security" ).getAsString() : NA;
                system.primary_economy_id = !obj.get( "primary_economy_id" ).isJsonNull() ? obj.get( "primary_economy_id" ).getAsInt() : -1;
                system.primary_economy = !obj.get( "primary_economy" ).isJsonNull() ? obj.get( "primary_economy" ).getAsString() : NA;
                system.power = !obj.get( "power" ).isJsonNull() ? obj.get( "power" ).getAsString() : NA;
                system.power_state = !obj.get( "power_state" ).isJsonNull() ? obj.get( "power_state" ).getAsString() : NA;
                system.power_state_id = !obj.get( "power_state_id" ).isJsonNull() ? obj.get( "power_state_id" ).getAsString() : NA;
                system.needs_permit = !obj.get( "needs_permit" ).isJsonNull() ? obj.get( "needs_permit" ).getAsBoolean() : false;
                system.updated_at = !obj.get( "updated_at" ).isJsonNull() ? obj.get( "updated_at" ).getAsLong() : -1;
                system.simbad_ref = !obj.get( "simbad_ref" ).isJsonNull() ? obj.get( "simbad_ref" ).getAsString() : NA;
                system.controlling_minor_faction_id = !obj.get( "controlling_minor_faction_id" ).isJsonNull() ?
                        obj.get( "controlling_minor_faction_id" ).getAsInt() : -1;
                system.controlling_minor_faction = !obj.get( "controlling_minor_faction" ).isJsonNull() ?
                        obj.get( "controlling_minor_faction" ).getAsString() : NA;
                system.reserve_type_id = !obj.get( "reserve_type_id" ).isJsonNull() ? obj.get( "reserve_type_id" ).getAsString() : NA;
                system.reserve_type = !obj.get( "reserve_type").isJsonNull() ? obj.get( "reserve_type" ).getAsString() : NA;
                system.ed_system_address = !obj.get( "ed_system_address" ).isJsonNull() ? obj.get( "ed_system_address" ).getAsLong() : -1;
    
                JsonArray statesArray = obj.get( "states" ).getAsJsonArray();
    
                for ( JsonElement jsonElement : statesArray ) {
                    JsonObject obj2 = jsonElement.getAsJsonObject();
                    States sysStates = new States();
                    sysStates.id = ! obj.get( "id" ).isJsonNull() ? obj.get( "id" ).getAsInt() : -1;
                    sysStates.name = ! obj.get( "name" ).isJsonNull() ? obj.get( "name" ).getAsString() : NA;
                    system.states.add( sysStates );
                }
    
                JsonArray minorFactionsArray = obj.get( "minor_faction_presences" ).getAsJsonArray();
    
                for (JsonElement factEle : minorFactionsArray) {
                    JsonObject factObj = factEle.getAsJsonObject();
                    PSMinorFactions minorFactions = new PSMinorFactions();
        
                    minorFactions.happiness_id = !factObj.get( "happiness_id" ).isJsonNull() ? factObj.get( "happiness_id" ).getAsInt() : -1;
                    minorFactions.minor_faction_id = !factObj.get( "minor_faction_id" ).isJsonNull() ? factObj.get( "minor_faction_id" ).getAsInt()
                            : -1;
                    minorFactions.influence = !factObj.get( "influence" ).isJsonNull() ? factObj.get( "influence" ).getAsDouble() : -1;
        
                    JsonArray activeStatesArray = factObj.getAsJsonArray("active_states");
                    JsonArray pendingStatesArray = factObj.getAsJsonArray( "pending_states" );
                    JsonArray recoveringStatesArray = factObj.getAsJsonArray("recovering_states");
        
                    Iterator<JsonElement> activeIt = activeStatesArray.iterator();
                    Iterator<JsonElement> pendingIt = pendingStatesArray.iterator();
                    Iterator<JsonElement> recoveringIt = recoveringStatesArray.iterator();
        
                    while (activeIt.hasNext()) {
                        JsonObject obj2 = activeIt.next().getAsJsonObject();
                        ActiveState state = new ActiveState();
                        state.id = !obj2.get( "id" ).isJsonNull() ? obj.get( "id" ).getAsInt() : -1;
                        state.name = !obj2.get( "name" ).isJsonNull() ? obj.get( "name" ).getAsString() : NA;
            
                        minorFactions.active_states.add( state );
                    }
        
                    while (pendingIt.hasNext()) {
                        JsonObject obj2 = pendingIt.next().getAsJsonObject();
                        PendingState state = new PendingState();
                        state.id = !obj.get( "id" ).isJsonNull() ? obj.get( "id" ).getAsInt() : -1;
                        state.name = !obj2.get( "name" ).isJsonNull() ? obj.get( "name" ).getAsString() : NA;
            
                        minorFactions.pending_states.add( state );
                    }
        
                    while (recoveringIt.hasNext()) {
                        JsonObject obj2 = recoveringIt.next().getAsJsonObject();
                        RecoveringState state =  new RecoveringState();
                        state.id = !obj.get( "id" ).isJsonNull() ? obj.get( "id" ).getAsInt() : -1;
                        state.name = !obj2.get( "name" ).isJsonNull() ? obj.get( "name" ).getAsString() : NA;
            
                        minorFactions.recovering_states.add( state );
                    }
        
                    system.minor_faction_presences.add( minorFactions );
        
                    if ( !popSystems.contains( system )) {
                        popSystems.add( system );
                    }
                }
            }
        }
        
        strReader.close();
        
        return popSystems;
    }
    
    /**
     * Testing function.
     * Parses the JSOn file for factions provided by EDDB at this URL:
     * <p></p>>
     * <a href = "https://eddb.io/archive/v6/factions.json"> Faction JSON at EDDB </a>
     * <p></p>
     * TODO: Investiage possible performance is
     * <br>
     * TODO: Optimize/Cleanup how this works
     * @throws java.io.IOException
     */
    private List<Faction> parseFactionsJSON (final int systemID) throws IOException {
        InputStreamReader strReader = getJSONStreamReader( EDDBModuleManager.JSON_factionsURL );
        
        JsonParser parser = new JsonParser();
        JsonElement mainEle = parser.parse( strReader );
        JsonArray array = mainEle.getAsJsonArray();
        
        List<Faction> factions = new ArrayList<>();
        
        for ( JsonElement element : array ) {
            Faction newFaction = new Faction();
            JsonObject obj = element.getAsJsonObject();
            
            if (!obj.get( "home_system_id" ).isJsonNull()) {
                if (obj.get( "home_system_id" ).getAsInt() == systemID) {
                    newFaction.id = !obj.get( "id" ).isJsonNull() ? obj.get( "id" ).getAsInt() : -1;
                    newFaction.name = !obj.get( "name" ).isJsonNull() ? obj.get( "name" ).getAsString() : NA;
                    newFaction.updated_at = !obj.get( "updated_at" ).isJsonNull() ? obj.get( "updated_at" ).getAsLong() : -1;
                    newFaction.government_id = !obj.get( "government_id" ).isJsonNull() ? obj.get( "government_id" ).getAsInt() : -1;
                    newFaction.government = !obj.get( "government" ).isJsonNull() ? obj.get( "government" ).getAsString() : NA;
                    newFaction.allegiance_id = !obj.get( "allegiance_id" ).isJsonNull() ? obj.get( "allegiance_id" ).getAsInt() : -1;
                    newFaction.allegiance = !obj.get( "allegiance" ).isJsonNull() ? obj.get( "allegiance" ).getAsString() : NA;
                    newFaction.is_player_faction = !obj.get( "is_player_faction" ).isJsonNull() ? obj.get( "is_player_faction" ).getAsBoolean() :
                            false;
                    newFaction.home_system_id = !obj.get( "home_system_id" ).isJsonNull() ? obj.get( "home_system_id" ).getAsInt() : -1;
                    
                    if (!factions.contains( newFaction )) {
                        factions.add( newFaction );
                    }
                }
            }
        }
        
        strReader.close();
        
        System.out.println("Faction list size : " + factions.size());
        
        return factions;
    }
    
    /**
     * Testing function
     * Parses the JSON file for commodities provided by EDDB at this URL:
     * <p></p>
     * <a href = "https://eddb.io/archive/v6/commodities.json"> Commodities JSON at EDDB</a>
     * <p></p>
     * TODO: Investiage possible performance issues
     * <br>
     * TODO: Optimize/Cleanup how this works
     * @throws IOException
     */
    private void parseCommodityJSON() throws IOException {
        InputStreamReader streamReader = getJSONStreamReader( EDDBModuleManager.JSONcommoditiesURL );
        
        JsonParser parser = new JsonParser();
        JsonElement mainEle = parser.parse( streamReader );
        
        JsonArray array = mainEle.getAsJsonArray();
        
        
        List<Commodity> commodities = new ArrayList<>();
        
        for (JsonElement element : array) {
            Commodity com = new Commodity();
            CommodityCategory cat = new CommodityCategory();
            JsonObject obj = element.getAsJsonObject();
            
            com.id = !obj.get("id").isJsonNull() ? obj.get( "id" ).getAsString() : NA;
            com.name = !obj.get( "name" ).isJsonNull() ? obj.get( "name" ).getAsString() : NA;
            com.category_id = !obj.get( "category_id" ).isJsonNull() ? obj.get( "category_id" ).getAsString() : NA;
            com.average_price = !obj.get( "average_price" ).isJsonNull() ? obj.get( "average_price" ).getAsString() : NA;
            com.is_rare = !obj.get( "is_rare" ).isJsonNull() ? obj.get( "is_rare" ).getAsString() : NA;
            com.max_buy_price = !obj.get( "max_buy_price" ).isJsonNull() ? obj.get( "max_buy_price" ).getAsString() : NA;
            com.max_sell_price = !obj.get("max_sell_price").isJsonNull() ? obj.get( "max_sell_price" ).getAsString() : NA;
            com.min_buy_price = !obj.get( "min_buy_price" ).isJsonNull() ? obj.get( "min_buy_price" ).getAsString() : NA;
            com.min_sell_price = !obj.get( "min_sell_price" ).isJsonNull() ? obj.get( "min_sell_price" ).getAsString() : NA;
            com.buy_price_lower_average = !obj.get( "buy_price_lower_average" ).isJsonNull() ? obj.get( "buy_price_lower_average" ).getAsString() :
                    NA;
            com.sell_price_upper_average = !obj.get( "sell_price_upper_average" ).isJsonNull() ? obj.get( "sell_price_upper_average" ).getAsString() :
                    NA;
            com.is_non_marketable = !obj.get( "is_non_marketable" ).isJsonNull() ? obj.get( "is_non_marketable" ).getAsString() : NA;
            com.ed_id = !obj.get( "ed_id" ).isJsonNull() ? obj.get("ed_id").getAsString() : NA;
            
            JsonObject obj2 = obj.getAsJsonObject("category");
            cat.id = !obj2.get( "id" ).isJsonNull() ? obj.get( "id" ).getAsString() : NA;
            cat.name = !obj2.get( "name" ).isJsonNull() ? obj.get( "name" ).getAsString() : NA;
            
            com.category = cat;
            
            commodities.add( com );
        }
        
        streamReader.close();
        System.out.println(commodities.size());
    }
}
