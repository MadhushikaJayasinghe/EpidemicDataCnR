package com.test.dproject.epidemicdatacnr;

import java.util.PriorityQueue;

/**
 * Created by Tharushi on 12/18/2016.
 */
public class Region {
    private String region_ID;   //char(6)
    private String province;
    private String district;
    private String area;

    //constructor
    public Region(String region_ID){
        this.region_ID = region_ID;
    }

    //set methods
    public void setRegion_ID(String region_ID){
        this.region_ID= region_ID;
    }
    public void setProvince(String province){
        this.province = province;
    }
    public void setDistrict(String district){
        this.district = district;
    }
    public void setArea(String area){
        this.area = area;
    }

    //get methods
    public String getRegion_ID(){
        return region_ID;
    }
    public String getProvince() {
        return province;
    }
    public String getDistrict() {
        return district;
    }
    public String getArea() {
        return area;
    }


}
