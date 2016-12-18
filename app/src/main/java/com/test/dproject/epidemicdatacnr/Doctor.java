package com.test.dproject.epidemicdatacnr;

/**
 * Created by Tharushi on 12/18/2016.
 */
public class Doctor {
    private String doctor_ID;       //char(6)
    private String national_ID;     //char(10)
    private String name;

    //Constructor
    public Doctor( String doctor_ID) {
        this.doctor_ID = doctor_ID;
    }

    //set methods

    public void setDoctor_ID(String doctor_ID){
        this.doctor_ID = doctor_ID;
    }

    public void setNational_ID(String national_ID) { //national id is 10 digits long
        this.national_ID = national_ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    //get methods
    public String getDoctor_ID() {
        return doctor_ID;
    }

    public String getNational_ID() {
        return national_ID;
    }

    public String getName() {
        return name;
    }

}
