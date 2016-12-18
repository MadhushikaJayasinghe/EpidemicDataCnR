package com.test.dproject.epidemicdatacnr;

import java.util.List;

/**
 * Created by Tharushi on 12/18/2016.
 */
public interface PatientDAO {
    public List<Patient> getAllPatients();
    public Patient getPatient(String patient_ID);
    public boolean updatePatient(Patient patient);
    public boolean deletePatient(Patient patient);
}



