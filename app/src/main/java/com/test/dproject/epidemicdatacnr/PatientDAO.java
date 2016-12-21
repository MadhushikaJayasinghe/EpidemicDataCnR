package com.test.dproject.epidemicdatacnr;

import com.test.dproject.epidemicdatacnr.exceptions.InvalidPatientException;

import java.util.List;

/**
 * Created by Tharushi on 12/18/2016.
 */
public interface PatientDAO {
    public List<Patient> getAllPatients();

    public Patient getPatient(int patient_ID) throws InvalidPatientException;

    public boolean removePatient(int patientId) throws InvalidPatientException;

    boolean removeAllPatient();
}



