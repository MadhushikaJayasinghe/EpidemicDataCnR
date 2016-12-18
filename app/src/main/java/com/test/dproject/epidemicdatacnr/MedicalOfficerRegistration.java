package com.test.dproject.epidemicdatacnr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MedicalOfficerRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] provinces;
    String[] districts;
    String[] districts2;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    Spinner spinner;
    Spinner spinner2;
    AllArrays allArrays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_officer_registration);

        //for the spinners
        allArrays = new AllArrays();
        setProvinceSpinner();
        setDistrictSpinner();
        districts = new String[]{"Province needed"};
    }

    public void setProvinceSpinner() {

        provinces = allArrays.getInsertProvinceArray();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, provinces);
        spinner = (Spinner) findViewById(R.id.mRegProvinceSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.mRegProvinceSpinner:

                String prov = (String) parent.getItemAtPosition(position).toString();
                districts2 = allArrays.getDistrictArray(prov);

                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, districts2);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter3);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setDistrictSpinner() {
        districts = new String[]{"Province needed"};
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, districts);
        spinner2 = (Spinner) findViewById(R.id.mRegDistrictSpinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setNotifyOnChange(true);
        spinner2.setAdapter(adapter2);
    }


    /*
    * Toast.makeText(parent.getContext(),
                parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();
    *
    * */
}
