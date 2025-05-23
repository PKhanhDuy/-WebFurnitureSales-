package com.supportjobsearch.Location;
import java.util.*;



public  class Location {
    private List<Province> province;
    private List<District> district;

    public List<Province> getProvince() {
        return province;
    }

    public void setProvince(List<Province> province) {
        this.province = province;
    }

    public List<District> getDistrict() {
        return district;
    }

    public void setDistrict(List<District> district) {
        this.district = district;
    }
}
