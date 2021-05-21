package com.example.vaccinealerter.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getState {

    @SerializedName("states")
    @Expose
    private List<State> states = null;
    @SerializedName("ttl")
    @Expose
    private long ttl;

    public getState() {
    }

    public getState(List<State> states, long ttl) {
        super();
        this.states = states;
        this.ttl = ttl;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

}
