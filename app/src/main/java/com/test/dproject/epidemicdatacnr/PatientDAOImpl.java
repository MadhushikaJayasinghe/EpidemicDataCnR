package com.test.dproject.epidemicdatacnr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tharushi on 12/18/2016.
 */
public class PatientDAOImpl implements PatientDAO {
    //list is working as a database
    List<Patient> patients;

    public PatientDAOImpl(){
        patients = new ArrayList<Patient>();
        Patient patient1 = new Patient("name1","id1");
        Patient patient2 = new Patient("name2", "id2");
        patients.add(patient1);
        patients.add(patient2);
    }

    @Override
    public boolean deletePatient(Patient patient) {
        patients.remove(patient.getPatient_ID());
        System.out.println("Patient: Patient Id " + patient.getPatient_ID() + ", deleted from database");
        return true;
    }

    //retrive list of patients from the database
    @Override
    public List<Patient> getAllPatients() {
        return patients;
    }

    @Override
    public Patient getPatient(String patient_ID) {
        return patients.get(patients.indexOf(patient_ID));            // int --string problem...
    }

    @Override
    public boolean updatePatient(Patient patient) {
        patients.get(patients.indexOf(patient)).setName(patient.getName());
        System.out.println("Patient: Patient ID " + patient.getPatient_ID() + ", updated in the database");
        return true;
    }
}
