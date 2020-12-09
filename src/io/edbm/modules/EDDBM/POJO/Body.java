package io.edbm.modules.EDDBM.POJO;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Body {
    
    @SerializedName ( "id" )
    @Expose
    private long id;
    @SerializedName ( "id64" )
    @Expose
    private long id64;
    @SerializedName ( "bodyId" )
    @Expose
    private long bodyId;
    @SerializedName ( "name" )
    @Expose
    private String name;
    @SerializedName ( "type" )
    @Expose
    private String type;
    @SerializedName ( "subType" )
    @Expose
    private String subType;
    @SerializedName ( "parents" )
    @Expose
    private List<Parent> parents = new ArrayList<Parent>();
    @SerializedName ( "distanceToArrival" )
    @Expose
    private long distanceToArrival;
    @SerializedName ( "isLandable" )
    @Expose
    private boolean isLandable;
    @SerializedName ( "gravity" )
    @Expose
    private double gravity;
    @SerializedName ( "earthMasses" )
    @Expose
    private double earthMasses;
    @SerializedName ( "radius" )
    @Expose
    private double radius;
    @SerializedName ( "surfaceTemperature" )
    @Expose
    private long surfaceTemperature;
    @SerializedName ( "surfacePressure" )
    @Expose
    private double surfacePressure;
    @SerializedName ( "volcanismType" )
    @Expose
    private String volcanismType;
    @SerializedName ( "atmosphereType" )
    @Expose
    private String atmosphereType;
    @SerializedName ( "atmosphereComposition" )
    @Expose
    private Object atmosphereComposition;
    @SerializedName ( "solidComposition" )
    @Expose
    private SolidComposition solidComposition;
    @SerializedName ( "terraformingState" )
    @Expose
    private String terraformingState;
    @SerializedName ( "orbitalPeriod" )
    @Expose
    private double orbitalPeriod;
    @SerializedName ( "semiMajorAxis" )
    @Expose
    private double semiMajorAxis;
    @SerializedName ( "orbitalEccentricity" )
    @Expose
    private double orbitalEccentricity;
    @SerializedName ( "orbitalInclination" )
    @Expose
    private double orbitalInclination;
    @SerializedName ( "argOfPeriapsis" )
    @Expose
    private double argOfPeriapsis;
    @SerializedName ( "rotationalPeriod" )
    @Expose
    private double rotationalPeriod;
    @SerializedName ( "rotationalPeriodTidallyLocked" )
    @Expose
    private boolean rotationalPeriodTidallyLocked;
    @SerializedName ( "axialTilt" )
    @Expose
    private double axialTilt;
    @SerializedName ( "materials" )
    @Expose
    private Material materials;
    @SerializedName ( "updateTime" )
    @Expose
    private String updateTime;
    @SerializedName ( "systemId" )
    @Expose
    private long systemId;
    @SerializedName ( "systemId64" )
    @Expose
    private long systemId64;
    @SerializedName ( "systemName" )
    @Expose
    private String systemName;
    
    public long getId () {
        return id;
    }
    
    public void setId ( long id ) {
        this.id = id;
    }
    
    public long getId64 () {
        return id64;
    }
    
    public void setId64 ( long id64 ) {
        this.id64 = id64;
    }
    
    public long getBodyId () {
        return bodyId;
    }
    
    public void setBodyId ( long bodyId ) {
        this.bodyId = bodyId;
    }
    
    public String getName () {
        return name;
    }
    
    public void setName ( String name ) {
        this.name = name;
    }
    
    public String getType () {
        return type;
    }
    
    public void setType ( String type ) {
        this.type = type;
    }
    
    public String getSubType () {
        return subType;
    }
    
    public void setSubType ( String subType ) {
        this.subType = subType;
    }
    
    public List<Parent> getParents () {
        return parents;
    }
    
    public void setParents ( List<Parent> parents ) {
        this.parents = parents;
    }
    
    public long getDistanceToArrival () {
        return distanceToArrival;
    }
    
    public void setDistanceToArrival ( long distanceToArrival ) {
        this.distanceToArrival = distanceToArrival;
    }
    
    public boolean isIsLandable () {
        return isLandable;
    }
    
    public void setIsLandable ( boolean isLandable ) {
        this.isLandable = isLandable;
    }
    
    public double getGravity () {
        return gravity;
    }
    
    public void setGravity ( double gravity ) {
        this.gravity = gravity;
    }
    
    public double getEarthMasses () {
        return earthMasses;
    }
    
    public void setEarthMasses ( double earthMasses ) {
        this.earthMasses = earthMasses;
    }
    
    public double getRadius () {
        return radius;
    }
    
    public void setRadius ( double radius ) {
        this.radius = radius;
    }
    
    public long getSurfaceTemperature () {
        return surfaceTemperature;
    }
    
    public void setSurfaceTemperature ( long surfaceTemperature ) {
        this.surfaceTemperature = surfaceTemperature;
    }
    
    public double getSurfacePressure () {
        return surfacePressure;
    }
    
    public void setSurfacePressure ( long surfacePressure ) {
        this.surfacePressure = surfacePressure;
    }
    
    public String getVolcanismType () {
        return volcanismType;
    }
    
    public void setVolcanismType ( String volcanismType ) {
        this.volcanismType = volcanismType;
    }
    
    public String getAtmosphereType () {
        return atmosphereType;
    }
    
    public void setAtmosphereType ( String atmosphereType ) {
        this.atmosphereType = atmosphereType;
    }
    
    public Object getAtmosphereComposition () {
        return atmosphereComposition;
    }
    
    public void setAtmosphereComposition ( Object atmosphereComposition ) {
        this.atmosphereComposition = atmosphereComposition;
    }
    
    public SolidComposition getSolidComposition () {
        return solidComposition;
    }
    
    public void setSolidComposition ( SolidComposition solidComposition ) {
        this.solidComposition = solidComposition;
    }
    
    public String getTerraformingState () {
        return terraformingState;
    }
    
    public void setTerraformingState ( String terraformingState ) {
        this.terraformingState = terraformingState;
    }
    
    public double getOrbitalPeriod () {
        return orbitalPeriod;
    }
    
    public void setOrbitalPeriod ( double orbitalPeriod ) {
        this.orbitalPeriod = orbitalPeriod;
    }
    
    public double getSemiMajorAxis () {
        return semiMajorAxis;
    }
    
    public void setSemiMajorAxis ( double semiMajorAxis ) {
        this.semiMajorAxis = semiMajorAxis;
    }
    
    public double getOrbitalEccentricity () {
        return orbitalEccentricity;
    }
    
    public void setOrbitalEccentricity ( double orbitalEccentricity ) {
        this.orbitalEccentricity = orbitalEccentricity;
    }
    
    public double getOrbitalInclination () {
        return orbitalInclination;
    }
    
    public void setOrbitalInclination ( double orbitalInclination ) {
        this.orbitalInclination = orbitalInclination;
    }
    
    public double getArgOfPeriapsis () {
        return argOfPeriapsis;
    }
    
    public void setArgOfPeriapsis ( double argOfPeriapsis ) {
        this.argOfPeriapsis = argOfPeriapsis;
    }
    
    public double getRotationalPeriod () {
        return rotationalPeriod;
    }
    
    public void setRotationalPeriod ( double rotationalPeriod ) {
        this.rotationalPeriod = rotationalPeriod;
    }
    
    public boolean isRotationalPeriodTidallyLocked () {
        return rotationalPeriodTidallyLocked;
    }
    
    public void setRotationalPeriodTidallyLocked ( boolean rotationalPeriodTidallyLocked ) {
        this.rotationalPeriodTidallyLocked = rotationalPeriodTidallyLocked;
    }
    
    public double getAxialTilt () {
        return axialTilt;
    }
    
    public void setAxialTilt ( double axialTilt ) {
        this.axialTilt = axialTilt;
    }
    
    public Material getMaterials () {
        return materials;
    }
    
    public void setMaterials ( Material materials ) {
        this.materials = materials;
    }
    
    public String getUpdateTime () {
        return updateTime;
    }
    
    public void setUpdateTime ( String updateTime ) {
        this.updateTime = updateTime;
    }
    
    public long getSystemId () {
        return systemId;
    }
    
    public void setSystemId ( long systemId ) {
        this.systemId = systemId;
    }
    
    public long getSystemId64 () {
        return systemId64;
    }
    
    public void setSystemId64 ( long systemId64 ) {
        this.systemId64 = systemId64;
    }
    
    public String getSystemName () {
        return systemName;
    }
    
    public void setSystemName ( String systemName ) {
        this.systemName = systemName;
    }
}