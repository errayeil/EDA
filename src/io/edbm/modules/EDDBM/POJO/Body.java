package io.edbm.modules.EDDBM.POJO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Body {
    
    public int id;
    
    public long id64;
    
    public int bodyID;
    
    public String name;
    
    public String type;
    
    public String subType;
    
    public long distanceToArrival;
    
    public boolean isLandable;
    
    public double gravity;
    
    public double earthMasses;
    
    public double radius;
    
    public int surfaceTemperature;
    
    public double surfacePressure;
    
    public String volcanismType;
    
    public String terraformingState;
    
    public double orbitalPeriod;
    
    public double semiMajorAxis;
    
    public double orbitalEccentricity;
    
    public double orbitalInclination;
    
    public double argOfPeriapsis;
    
    public double rotationalPeriod;
    
    public double rotationalPeriodTidallyLocked;
    
    public double axialTilt;
    
    public String updateTime;
    
    public int systemId;
    
    public long systemId64;
    
    public String systemName;
    
    List<Parent> parents = new ArrayList<>();
    
    Map<String, Double> atmosphereComp = new HashMap<>();
    
    Map<String, Double> solidComp = new HashMap<>();
}
