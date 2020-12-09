package io.edbm.modules.EDDBM.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Material {
    @SerializedName("Sulphur")
    @Expose
    private double sulphur;
    @SerializedName("Carbon")
    @Expose
    private double carbon;
    @SerializedName("Phosphorus")
    @Expose
    private double phosphorus;
    @SerializedName("Iron")
    @Expose
    private double iron;
    @SerializedName ("Nickel")
    @Expose
    private double nickel;
    @SerializedName("Chromium")
    @Expose
    private double chromium;
    @SerializedName("Germanium")
    @Expose
    private double germanium;
    @SerializedName("Zirconium")
    @Expose
    private double zirconium;
    @SerializedName("Cadmium")
    @Expose
    private double cadmium;
    @SerializedName("Ruthenium")
    @Expose
    private double ruthenium;
    @SerializedName("Tin")
    @Expose
    private double tin;
    
    public double getSulphur() {
        return sulphur;
    }
    
    public void setSulphur(double sulphur) {
        this.sulphur = sulphur;
    }
    
    public double getCarbon() {
        return carbon;
    }
    
    public void setCarbon(double carbon) {
        this.carbon = carbon;
    }
    
    public double getPhosphorus() {
        return phosphorus;
    }
    
    public void setPhosphorus(double phosphorus) {
        this.phosphorus = phosphorus;
    }
    
    public double getIron() {
        return iron;
    }
    
    public void setIron(double iron) {
        this.iron = iron;
    }
    
    public double getNickel() {
        return nickel;
    }
    
    public void setNickel(double nickel) {
        this.nickel = nickel;
    }
    
    public double getChromium() {
        return chromium;
    }
    
    public void setChromium(double chromium) {
        this.chromium = chromium;
    }
    
    public double getGermanium() {
        return germanium;
    }
    
    public void setGermanium(double germanium) {
        this.germanium = germanium;
    }
    
    public double getZirconium() {
        return zirconium;
    }
    
    public void setZirconium(double zirconium) {
        this.zirconium = zirconium;
    }
    
    public double getCadmium() {
        return cadmium;
    }
    
    public void setCadmium(double cadmium) {
        this.cadmium = cadmium;
    }
    
    public double getRuthenium() {
        return ruthenium;
    }
    
    public void setRuthenium(double ruthenium) {
        this.ruthenium = ruthenium;
    }
    
    public double getTin() {
        return tin;
    }
    
    public void setTin(double tin) {
        this.tin = tin;
    }
    
}
