package com.test.dproject.epidemicdatacnr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InsertData extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] provinces;
    String[] districts;
    String[] districts2;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    Spinner spinner;
    Spinner spinner2;
    AllArrays allArrays;

    private static final String TAG = "volley";

    private EditText name, nationalId, dob, disease, state, area, hospital;
    private Button insertButton;
    private RequestQueue requestQueue;
    private static final String url = "mainUrl";
    private StringRequest request;
    //to database
    String prov;
    String dis;

    String insertingPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        Bundle b = getIntent().getExtras();
        if (b != null)
            insertingPerson = b.getString("insertingPerson");

        //for the spinners
        allArrays = new AllArrays();
        setProvinceSpinner();
        setDistrictSpinner();
        districts = new String[]{"Province needed"};

        name = (EditText) findViewById(R.id.insertNameET);
        nationalId = (EditText) findViewById(R.id.insertNatIdET);
        dob = (EditText) findViewById(R.id.insertDOBET);
        disease = (EditText) findViewById(R.id.insertDiseaseET);
        state = (EditText) findViewById(R.id.insertConditionET);
        area = (EditText) findViewById(R.id.insertAreaET);
        hospital = (EditText) findViewById(R.id.insertHospitalET);
        insertButton = (Button) findViewById(R.id.insertDataButton);

        final PatientDAOImpl patientDAOImpl = new PatientDAOImpl();
        patientDAOImpl.createmethod(this);



        requestQueue = Volley.newRequestQueue(this);

        insertButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        insertData(patientDAOImpl);
                    }
                }
        );
    }

    public void setProvinceSpinner() {

        provinces = allArrays.getInsertProvinceArray();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, provinces);
        spinner = (Spinner) findViewById(R.id.insertProvinceSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.insertProvinceSpinner:

                prov = (String) parent.getItemAtPosition(position).toString();
                districts2 = allArrays.getDistrictArray(prov);

                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, districts2);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter3);
                break;
            case R.id.insertDistrictSpinner:
                dis = (String) parent.getItemAtPosition(position).toString();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setDistrictSpinner() {
        districts = new String[]{"Province needed"};
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, districts);
        spinner2 = (Spinner) findViewById(R.id.insertDistrictSpinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setNotifyOnChange(true);
        spinner2.setAdapter(adapter2);
    }

    public void insertData(final PatientDAOImpl patientDAOImpl) {
        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.names().get(0).equals("success")) {
                        Toast.makeText(getApplicationContext(), jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());
                    } else if (jsonObject.names().get(0).equals("error")) {
                        Toast.makeText(getApplicationContext(), jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "error");
                Patient patient = new Patient(1, name.getText().toString(), nationalId.getText().toString(),
                        dob.getText().toString(), disease.getText().toString(),
                        state.getText().toString(), prov, dis,
                        area.getText().toString(), hospital.getText().toString()
                );
                patientDAOImpl.addPatient(patient);
                Toast.makeText(getApplicationContext(), "No connection. Data added to app database ", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("flag", "insert data");
                hashMap.put("insertingPerson", insertingPerson);
                hashMap.put("name", name.getText().toString());
                hashMap.put("nationalId", nationalId.getText().toString());
                hashMap.put("dob", dob.getText().toString());
                hashMap.put("disease", disease.getText().toString());
                hashMap.put("state", state.getText().toString());
                hashMap.put("province", prov);
                hashMap.put("district", dis);
                hashMap.put("area", area.getText().toString());
                hashMap.put("hospital", hospital.getText().toString());
                return hashMap;
            }
        };
        requestQueue.add(request);

    }
}
