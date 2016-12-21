package com.test.dproject.epidemicdatacnr;

import java.util.Date;

/**
 * Created by Tharushi on 12/18/2016.
 */
public class Patient {
    private int patient_ID;      //char(8)
    private String name;
    private String national_ID;     //char(10)
    private String date_Of_Birth;     //date(10)
    private String illness;
    private String condition;
    private String province;
    private String district;
    private String area;
    private String hospital;

    //Constructor method. Name and National ID are not null fields.
    public Patient(int patient_ID, String name, String national_ID, String date_Of_Birth,
                   String illness, String condition, String province, String district, String area, String hospital) { //national id is 10 digits long
        this.name = name;
        this.national_ID = national_ID;
        this.date_Of_Birth = date_Of_Birth;
        this.illness = illness;
        this.condition = condition;
        this.province = province;
        this.district = district;
        this.area = area;
        this.hospital = hospital;
    }


    //Get Methods
    public int getPatient_ID() {
        return patient_ID;
    }

    public String getName() {
        return name;
    }

    public String getNational_ID() {
        return national_ID;
    }

    public String getDate_Of_Birth() {
        return date_Of_Birth;
    }

    public String getIllness() {
        return illness;
    }

    public String getCondition() {
        return condition;
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

    public String getHospital() {
        return hospital;
    }


}


