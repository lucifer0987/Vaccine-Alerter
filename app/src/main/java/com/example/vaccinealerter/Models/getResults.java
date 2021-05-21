package com.example.vaccinealerter.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getResults {

    @SerializedName("centers")
    @Expose
    private List<Center> centers = null;

    public getResults() {
    }

    public getResults(List<Center> centers) {
        super();
        this.centers = centers;
    }

    public List<Center> getCenters() {
        return centers;
    }

    public void setCenters(List<Center> centers) {
        this.centers = centers;
    }

}
