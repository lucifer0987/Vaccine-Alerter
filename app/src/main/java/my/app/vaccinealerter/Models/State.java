package my.app.vaccinealerter.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class State {

    @SerializedName("state_id")
    @Expose
    private long stateId;
    @SerializedName("state_name")
    @Expose
    private String stateName;

    public State() {
    }

    public State(long stateId, String stateName) {
        super();
        this.stateId = stateId;
        this.stateName = stateName;
    }

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

}
