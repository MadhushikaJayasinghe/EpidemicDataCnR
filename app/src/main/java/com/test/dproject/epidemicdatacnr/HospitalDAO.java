package com.test.dproject.epidemicdatacnr;

import java.util.List;
/**
 * Created by Tharushi on 12/18/2016.
 */
public interface HospitalDAO {
    List<Hospital> getAllHospitals();
    public Hospital getHospital(String hospital_ID);
    public boolean updateHospital(Hospital hospital);
    public boolean deleteHospital(Hospital hospital);
}
