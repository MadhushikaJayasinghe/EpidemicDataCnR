package com.test.dproject.epidemicdatacnr;

import java.util.List;
/**
 * Created by Tharushi on 12/18/2016.
 */
public interface DoctorDAO {
    public List<Doctor> getAllDoctors();
    public Doctor getDoctor(String doctor_ID);
    public boolean updateDoctor(Doctor doctor);
    public boolean deleteDoctor(Doctor doctor);
}
