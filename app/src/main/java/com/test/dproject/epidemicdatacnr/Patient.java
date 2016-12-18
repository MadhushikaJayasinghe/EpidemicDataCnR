package com.test.dproject.epidemicdatacnr;

import java.util.Date;

/**
 * Created by Tharushi on 12/18/2016.
 */
public class Patient {
    private String patient_ID;      //char(8)
    private String name;
    private Date date_Of_Birth;     //date(10)
    private String national_ID;     //char(10)
    private Disease illness;
    private String condition;
    private Region region;

    //Constructor method. Name and National ID are not null fields.
    public Patient(String name, String national_ID) { //national id is 10 digits long
        this.name = name;
        this.national_ID = national_ID;
    }

    // set methods
    public void setPatient_ID(String patient_ID){
        this.patient_ID = patient_ID;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setNational_ID(String national_ID){
        this.national_ID = national_ID;
    }
    public void setDateOfBirth(Date date_Of_Birth) {
        this.date_Of_Birth = date_Of_Birth;
    }
    public void setIllness(Disease illness) {
        this.illness = illness;
    }
    public void setCondition(String condition){
        this.condition = condition;
    }
    public void setRegion(Region region){
        this.region = region;
    }

    //Get Methods
    public String getPatient_ID() {
        return patient_ID;
    }

    public String getName() {
        return name;
    }

    public Date getDate_Of_Birth(){
        return date_Of_Birth;
    }

    public String getNational_ID() {
        return national_ID;
    }
    public String getCondition() {
        return condition;
    }
    public Disease getIllness() {
        return illness;
    }
    public Region getRegion() {
        return region;
    }

    //Reset the condition
    public boolean deleteCondition() {
        this.condition = null;
        return true;
    }
}


