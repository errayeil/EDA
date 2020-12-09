package io.edbm.modules.EDDBM.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parent {
    @SerializedName("Planet")
    @Expose
    private long planet;
    @SerializedName ("Star")
    @Expose
    private long star;
    
    public long getPlanet() {
        return planet;
    }
    
    public void setPlanet(long planet) {
        this.planet = planet;
    }
    
    public long getStar() {
        return star;
    }
    
    public void setStar(long star) {
        this.star = star;
    }
}
