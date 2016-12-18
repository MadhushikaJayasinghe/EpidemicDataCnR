package com.test.dproject.epidemicdatacnr;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Tharushi on 12/18/2016.
 */
public class DoctorDAOImpl implements DoctorDAO {
    //list is working as a database
    List<Doctor> doctors;

    public DoctorDAOImpl(){
        doctors = new ArrayList<Doctor>();
        Doctor doctor1 = new Doctor("id1");
        Doctor doctor2 = new Doctor("id2");
        doctors.add(doctor1);
        doctors.add(doctor2);
    }

    @Override
    public boolean deleteDoctor(Doctor doctor) {
        doctors.remove(doctor.getDoctor_ID());
        System.out.println("Doctor: Doctor Id " + doctor.getDoctor_ID() + ", deleted from database");
        return true;
    }

    //retrieve list of doctors from the database
    @Override
    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    @Override
    public Doctor getDoctor(String doctor_ID) {
        return doctors.get(doctors.indexOf(doctor_ID));
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        doctors.get(doctors.indexOf(doctor)).setName(doctor.getName());
        System.out.println("Doctor: doctor ID " + doctor.getDoctor_ID() + ", updated in the database");
        return true;
    }
}
