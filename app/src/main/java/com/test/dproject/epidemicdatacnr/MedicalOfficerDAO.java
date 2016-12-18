package com.test.dproject.epidemicdatacnr;

import java.util.List;
/**
 * Created by Tharushi on 12/18/2016.
 */
public interface MedicalOfficerDAO {
    List<MedicalOfficer> getAllMedicalOfficers();
    public MedicalOfficer getMedicalOfficer(String med_Officer_ID);
    public boolean updateMedicalOfficer(MedicalOfficer medicalOfficer);
    public boolean deleteMedicalOfficer(MedicalOfficer medicalOfficer);
}
