package com.test.dproject.epidemicdatacnr;

/**
 * Created by Tharushi on 12/18/2016.
 */
public class MedicalOfficer {
    private String national_ID;     //char(10)
    private String title;
    private String name;

    //constructor
    public MedicalOfficer(String national_ID, String title){
        this.national_ID = national_ID;
        this.title = title;
    }

    //set methods
    public void setNational_ID(String national_ID) {
        this.national_ID = national_ID;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setName(String name){
        this.name = name;
    }

    //get methods
    public String getNational_ID() {
        return national_ID;
    }
    public String getTitle(){
        return title;
    }
    public String getName(){
        return name;
    }

}
