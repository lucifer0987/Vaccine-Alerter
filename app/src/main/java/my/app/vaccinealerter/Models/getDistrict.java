package my.app.vaccinealerter.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getDistrict {

    @SerializedName("districts")
    @Expose
    private List<District> districts = null;
    @SerializedName("ttl")
    @Expose
    private long ttl;

    public getDistrict() {
    }

    public getDistrict(List<District> districts, long ttl) {
        super();
        this.districts = districts;
        this.ttl = ttl;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

}
