package my.app.vaccinealerter.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class District {

    @SerializedName("district_id")
    @Expose
    private long districtId;
    @SerializedName("district_name")
    @Expose
    private String districtName;

    public District() {
    }

    public District(long districtId, String districtName) {
        super();
        this.districtId = districtId;
        this.districtName = districtName;
    }

    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

}
