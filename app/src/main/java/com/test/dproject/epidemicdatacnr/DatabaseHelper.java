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

    //Doctor's attributes
    public static final String P_PatientNum_Col = "PatientNum";
    public static final String P_Name_Col = "Name";
    public static final String P_NatID_Col = "NationalId";
    public static final String P_DOB_Col = "DOB";
    public static final String P_Illness_Col = "Disease";
    public static final String P_Condition_Col = "Condition";
    public static final String P_Province_Col = "Province";
    public static final String P_District_Col = "District";
    public static final String P_Area_Col = "Area";
    public static final String P_Hospital_Col = "Hospital";


    // password attributes
    public static final String PASSWORD = "password";


    //CREATE PATIENT TABLE
    public static final String CREATE_PATIENT_TABLE = "CREATE TABLE IF NOT EXISTS "
            + PATIENT_TABLE + "(" + P_PatientNum_Col + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + P_Name_Col + " TEXT, " + P_NatID_Col + " TEXT, " + P_DOB_Col + " TEXT,"
            + P_Illness_Col + " TEXT," + P_Condition_Col + " TEXT,"
            + P_Province_Col + " TEXT, " + P_District_Col + " TEXT,"
            + P_Area_Col + " TEXT," + P_Hospital_Col + " TEXT);";

    //CREATE PASSWORD_TABLE
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PATIENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }
}
