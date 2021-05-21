package com.example.vaccinealerter.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Session {

    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("available_capacity")
    @Expose
    private long availableCapacity;
    @SerializedName("min_age_limit")
    @Expose
    private long minAgeLimit;
    @SerializedName("vaccine")
    @Expose
    private String vaccine;
    @SerializedName("slots")
    @Expose
    private List<String> slots = null;
    @SerializedName("available_capacity_dose1")
    @Expose
    private long availableCapacityDose1;
    @SerializedName("available_capacity_dose2")
    @Expose
    private long availableCapacityDose2;

    public Session() {
    }

    public Session(String sessionId, String date, long availableCapacity, long minAgeLimit, String vaccine, List<String> slots, long availableCapacityDose1, long availableCapacityDose2) {
        super();
        this.sessionId = sessionId;
        this.date = date;
        this.availableCapacity = availableCapacity;
        this.minAgeLimit = minAgeLimit;
        this.vaccine = vaccine;
        this.slots = slots;
        this.availableCapacityDose1 = availableCapacityDose1;
        this.availableCapacityDose2 = availableCapacityDose2;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(long availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public long getMinAgeLimit() {
        return minAgeLimit;
    }

    public void setMinAgeLimit(long minAgeLimit) {
        this.minAgeLimit = minAgeLimit;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public List<String> getSlots() {
        return slots;
    }

    public void setSlots(List<String> slots) {
        this.slots = slots;
    }

    public long getAvailableCapacityDose1() {
        return availableCapacityDose1;
    }

    public void setAvailableCapacityDose1(long availableCapacityDose1) {
        this.availableCapacityDose1 = availableCapacityDose1;
    }

    public long getAvailableCapacityDose2() {
        return availableCapacityDose2;
    }

    public void setAvailableCapacityDose2(long availableCapacityDose2) {
        this.availableCapacityDose2 = availableCapacityDose2;
    }

}
