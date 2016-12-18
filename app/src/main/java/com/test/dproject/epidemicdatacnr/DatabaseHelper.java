package com.test.dproject.epidemicdatacnr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Subhashinie on 12/4/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "EDCRS.db";
    public static final int DATABASE_VERSION = 1;

    public static final String PATIENT_TABLE = "Patient";
    public static final String DOCTOR_TABLE = "Doctor";
    public static final String MED_OFFICER_TABLE = "Medical_officer";
    public static final String DISEASE_TABLE = "Disease";
    public static final String REGION_TABLE = "Region";
    public static final String HOSPITAL_TABLE = "Hospital";

    //Doctor's attributes
    public static final String Doc_DocID_Col = "DoctorId";
    public static final String Doc_NatID_Col = "NationalId";
    public static final String Doc_Name_Col = "Name";

    // password attributes
    public static final String PASSWORD = "password";


    //CREATE PATIENT TABLE
    public static final String CREATE_DOCTOR_TABLE = "CREATE TABLE IF NOT EXISTS "
            + PATIENT_TABLE + "(" + Doc_DocID_Col + " TEXT PRIMARY KEY, "
            + Doc_NatID_Col + " TEXT, " + Doc_Name_Col + " TEXT);";

    //CREATE PASSWORD_TABLE

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DOCTOR_TABLE );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
