package com.test.dproject.epidemicdatacnr;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tharushi on 12/18/2016.
 */
public class MedicalOfficerDAOImpl implements MedicalOfficerDAO {
    //list is working as a database
    List<MedicalOfficer> medicalOfficers;

    public MedicalOfficerDAOImpl(){
        medicalOfficers = new ArrayList<MedicalOfficer>();
        MedicalOfficer medicalOfficer1 = new MedicalOfficer("id1", "title1");
        MedicalOfficer medicalOfficer2 = new MedicalOfficer("id2", "title1");
        medicalOfficers.add(medicalOfficer1);
        medicalOfficers.add(medicalOfficer2);
    }

    @Override
    public boolean deleteMedicalOfficer(MedicalOfficer medicalOfficer) {
        medicalOfficers.remove(medicalOfficer.getNational_ID());
        System.out.println("Medical Officer: Officer Id " + medicalOfficer.getNational_ID() + ", deleted from database");
        return true;
    }

    //retrieve list of doctors from the database
    @Override
    public List<MedicalOfficer> getAllMedicalOfficers() {
        return medicalOfficers;
    }

    @Override
    public MedicalOfficer getMedicalOfficer(String natioanal_ID) {
        return medicalOfficers.get(medicalOfficers.indexOf(natioanal_ID));
    }

    @Override
    public boolean updateMedicalOfficer(MedicalOfficer medicalOfficer) {
        medicalOfficers.get(medicalOfficers.indexOf(medicalOfficer)).setName(medicalOfficer.getName());
        System.out.println("Medical Officer: National ID " + medicalOfficer.getNational_ID() + ", updated in the database");
        return true;
    }
}
