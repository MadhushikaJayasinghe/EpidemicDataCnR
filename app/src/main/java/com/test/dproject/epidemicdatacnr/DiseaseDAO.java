package com.test.dproject.epidemicdatacnr;

import java.util.List;
/**
 * Created by Tharushi on 12/18/2016.
 */
public interface DiseaseDAO {
    public List<Disease> getAllDoctors();
    public Disease getDisease(String disease_ID);
    public boolean updateDisease(Disease disease);
    public boolean deleteDisease(Disease disease);
}
