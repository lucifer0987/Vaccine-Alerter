package my.app.vaccinealerter.Models;

import java.util.ArrayList;
import java.util.List;

public class notificationURL {

    private String state, district, URL;
    private List<Integer> age = new ArrayList<>();

    public notificationURL() {
    }

    public notificationURL(String URL, List<Integer> age) {
        this.URL = URL;
        this.age = age;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public List<Integer> getAge() {
        return age;
    }

    public void setAge(List<Integer> age) {
        this.age = age;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
