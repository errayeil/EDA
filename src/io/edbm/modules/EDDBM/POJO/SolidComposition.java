package io.edbm.modules.EDDBM.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolidComposition {
    @SerializedName ("Ice")
    @Expose
    private double ice;
    @SerializedName("Rock")
    @Expose
    private double rock;
    @SerializedName("Metal")
    @Expose
    private double metal;
    
    public double getIce() {
        return ice;
    }
    
    public void setIce(double ice) {
        this.ice = ice;
    }
    
    public double getRock() {
        return rock;
    }
    
    public void setRock(double rock) {
        this.rock = rock;
    }
    
    public double getMetal() {
        return metal;
    }
    
    public void setMetal(double metal) {
        this.metal = metal;
    }
}
