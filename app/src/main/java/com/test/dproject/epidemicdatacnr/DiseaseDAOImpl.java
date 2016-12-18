package com.test.dproject.epidemicdatacnr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tharushi on 12/18/2016.
 */
public class DiseaseDAOImpl implements DiseaseDAO {

    //list is working as a database
    List<Disease> diseases;

    public DiseaseDAOImpl(){
        diseases = new ArrayList<Disease>();
        Disease disease1 = new Disease("id1");
        Disease disease2 = new Disease("id2");
       diseases.add(disease1);
        diseases.add(disease2);
    }

    @Override
    public boolean deleteDisease(Disease disease) {
        diseases.remove(disease.getDisease_ID());
        System.out.println("Doctor: Doctor Id " + disease.getDisease_ID() + ", deleted from database");
        return true;
    }

    //retrive list of doctors from the database
    @Override
    public List<Disease> getAllDoctors() {
        return diseases;
    }

    @Override
    public Disease getDisease(String disease_ID) {
        return diseases.get(diseases.indexOf(disease_ID));
    }

    @Override
    public boolean updateDisease(Disease disease) {
        diseases.get(diseases.indexOf(disease)).setGeneral_Name(disease.getGeneral_Name());
        System.out.println("Doctor: doctor ID " + disease.getDisease_ID() + ", updated in the database");
        return true;
    }
}
