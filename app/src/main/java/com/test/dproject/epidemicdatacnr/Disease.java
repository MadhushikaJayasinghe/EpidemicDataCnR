package com.test.dproject.epidemicdatacnr;

/**
 * Created by Tharushi on 12/18/2016.
 */
public class Disease {
    private String disease_ID;
    private String general_Name;
    private String symptoms;
    private String firstAid;
    private String agent;
    private String factor;

    //constructor
    public Disease(String disease_ID){
        this.disease_ID = disease_ID;
    }

    //set methods
    public void setDisease_ID(String disease_ID){
        this.disease_ID = disease_ID;
    }
    public void setGeneral_Name(String general_name) {
        this.general_Name = general_name;
    }
    public void setSymptoms(String symptoms){
        this.symptoms = symptoms;
    }
    public void setFirstAid(String firstAid){
        this.firstAid = firstAid;
    }
    public void setAgent(String agent){
        this.agent = agent;
    }
    public void setFactor(String factor) {
        this.factor = factor;
    }
    //get methods
    public String getDisease_ID() {
        return disease_ID;
    }
    public String getGeneral_Name() {
        return general_Name;
    }
    public String getSymptoms(){
        return symptoms;
    }
    public String getFirstAid() {
        return firstAid;
    }
    public String getAgent() {
        return agent;
    }
    public String getFactor() {
        return factor;
    }

}


