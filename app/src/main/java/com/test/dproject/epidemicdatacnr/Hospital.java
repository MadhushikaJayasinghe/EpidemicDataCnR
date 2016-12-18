package com.test.dproject.epidemicdatacnr;

import java.lang.reflect.Constructor;

/**
 * Created by Tharushi on 12/18/2016.
 */
public class Hospital {
    private String hospital_ID;     //char(8)
    private String name;

    //constructor
    public Hospital(String hospital_ID, String name){
        this.hospital_ID = hospital_ID;
        this.name = name;
    }

    //set methods
    public void setHospital_ID(String hospital_ID) {
        this.hospital_ID = hospital_ID;
    }
    public void setName(String name) {
        this.name = name;
    }
    //get methods
    public String getHospital_ID(){
        return hospital_ID;
    }
    public String getName(){
        return name;
    }
}
