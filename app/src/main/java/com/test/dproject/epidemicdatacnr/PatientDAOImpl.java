package com.test.dproject.epidemicdatacnr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import com.test.dproject.epidemicdatacnr.exceptions.InvalidPatientException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tharushi on 12/18/2016.
 */
public class PatientDAOImpl implements PatientDAO {

    List<Patient> patients;
    private transient Context context;
    protected transient SQLiteDatabase database;
    private transient DatabaseHelper dbHelper;

    public void createmethod(Context context) throws SQLException {
        this.context = context;
        dbHelper = DatabaseHelper.getHelper(context);
        if (dbHelper == null)
            dbHelper = DatabaseHelper.getHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    public void addPatient(Patient patient) {
        ContentValues values = new ContentValues();
        try {
            values.put(DatabaseHelper.P_Name_Col, patient.getName());
            values.put(DatabaseHelper.P_NatID_Col, patient.getNational_ID());
            values.put(DatabaseHelper.P_DOB_Col, patient.getDate_Of_Birth());
            values.put(DatabaseHelper.P_Illness_Col, patient.getIllness());
            values.put(DatabaseHelper.P_Condition_Col, patient.getCondition());
            values.put(DatabaseHelper.P_Province_Col, patient.getProvince());
            values.put(DatabaseHelper.P_District_Col, patient.getDistrict());
            values.put(DatabaseHelper.P_Area_Col, patient.getArea());
            values.put(DatabaseHelper.P_Hospital_Col, patient.getHospital());
            database.insertOrThrow(DatabaseHelper.PATIENT_TABLE, null, values);
        } catch (SQLiteConstraintException e) {
        }
    }

    public Patient getPatient(int patientId) throws InvalidPatientException {
        int isthere0 = (int) DatabaseUtils.longForQuery(database,
                "SELECT COUNT(*) FROM " + DatabaseHelper.PATIENT_TABLE + " WHERE " + DatabaseHelper.P_PatientNum_Col + "= '"
                        + String.valueOf(patientId) + "' ;", null);
        if (isthere0 > 0) {
            String getquery = "select * from " + DatabaseHelper.PATIENT_TABLE + " WHERE " + DatabaseHelper.P_PatientNum_Col + " = '"
                    + String.valueOf(patientId) + "' ;";
            Cursor getcursor = database.rawQuery(getquery, null);
            Patient thePatient = new Patient(getcursor.getInt(0),
                    getcursor.getString(1), getcursor.getString(2), getcursor.getString(3),
                    getcursor.getString(4), getcursor.getString(5), getcursor.getString(6),
                    getcursor.getString(7), getcursor.getString(8), getcursor.getString(9));//national id is 10 digits long

            return thePatient;
        }
        String msg = "Account " + String.valueOf(patientId) + " is invalid.";
        throw new InvalidPatientException(msg);
    }


    private static final String WHERE_ID_EQUALS = DatabaseHelper.P_PatientNum_Col + " =?";

    public boolean removePatient(int patientId) throws InvalidPatientException {
        int isthere1 = (int) DatabaseUtils.longForQuery(database,
                "SELECT COUNT(*) FROM " + DatabaseHelper.PATIENT_TABLE + " WHERE " + DatabaseHelper.PATIENT_TABLE + " = '"
                        + patientId + "' ;", null);
        if (isthere1 <= 0) {
            String msg = "Account " + patientId + " is invalid.";
            throw new InvalidPatientException(msg);
        }
        database.delete(DatabaseHelper.PATIENT_TABLE, WHERE_ID_EQUALS,
                new String[]{patientId + ""});
        return true;
        //accounts.remove(accountNo);
    }

    @Override
    public boolean removeAllPatient() {
        database.delete(DatabaseHelper.PATIENT_TABLE, "1", null);
        return false;
    }


    //retrive list of patients from the database
    @Override
    public List<Patient> getAllPatients() {
        List<Patient> fullList = new ArrayList<>();
        String listquery = "select " + DatabaseHelper.P_PatientNum_Col + " from " + DatabaseHelper.PATIENT_TABLE + ";";
        Cursor listcursor = database.rawQuery(listquery, null);
        while (listcursor.moveToNext()) {
            try {
                Patient aPatient = getPatient(listcursor.getInt(0));
                fullList.add(aPatient);
            } catch (InvalidPatientException e) {
                String msg = "previous execution is invalid.";
            }
        }
        return fullList;
    }


}
