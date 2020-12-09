package io.edbm.modules.EDDBM.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AtmosphereComposition {
    
    @SerializedName ("Argon")
    @Expose
    private double argon;
    @SerializedName("Nitrogen")
    @Expose
    private double nitrogen;
    
    public double getArgon() {
        return argon;
    }
    
    public void setArgon(double argon) {
        this.argon = argon;
    }
    
    public double getNitrogen() {
        return nitrogen;
    }
    
    public void setNitrogen(double nitrogen) {
        this.nitrogen = nitrogen;
    }
}
