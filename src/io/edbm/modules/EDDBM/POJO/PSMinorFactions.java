package io.edbm.modules.EDDBM.POJO;

import java.util.ArrayList;
import java.util.List;

public class PSMinorFactions {
    
    public int happiness_id;
    
    public int minor_faction_id;
    
    public double influence;
    
    public List<ActiveState> active_states = new ArrayList<>();
    
    public List<PendingState> pending_states = new ArrayList<>();
    
    public List<RecoveringState> recovering_states = new ArrayList<>();
}
