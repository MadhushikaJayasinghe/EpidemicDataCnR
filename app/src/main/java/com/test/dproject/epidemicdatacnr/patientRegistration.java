package com.test.dproject.epidemicdatacnr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class patientRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] provinces;
    String[] districts;
    String[] districts2;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    Spinner spinner;
    Spinner spinner2;
    AllArrays allArrays;

    private EditText name, nationalId, dob, disease, state, area, hospital, apassword, cpassword;
    private Button pRegisterButton;
    private RequestQueue requestQueue;
    private static final String url = "http://10.0.2.2/new/startingPoint.php";
    private StringRequest request;
    //to database
    String prov;
    String dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);

        //for the spinners
        allArrays = new AllArrays();
        setProvinceSpinner();
        setDistrictSpinner();
        districts = new String[]{"Province needed"};

        name = (EditText) findViewById(R.id.pRegNameET);
        nationalId = (EditText) findViewById(R.id.pRegNatIdET);
        dob = (EditText) findViewById(R.id.pRegDOBET);
        disease = (EditText) findViewById(R.id.pRegDiseaseET);
        state = (EditText) findViewById(R.id.pRegConditionET);
        area = (EditText) findViewById(R.id.pRegAreaET);
        hospital = (EditText) findViewById(R.id.pRegHospitalET);
        apassword = (EditText) findViewById(R.id.pRegPasswordET);
        cpassword = (EditText) findViewById(R.id.pRegConfirmET);

        pRegisterButton = (Button) findViewById(R.id.pRegPRegButton);

        requestQueue = Volley.newRequestQueue(this);

        pRegisterButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        registerPatient();
                    }
                }
        );
    }

    public void setProvinceSpinner() {

        provinces = allArrays.getInsertProvinceArray();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, provinces);
        spinner = (Spinner) findViewById(R.id.pRegProvinceSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.pRegProvinceSpinner:

                prov = (String) parent.getItemAtPosition(position).toString();
                districts2 = allArrays.getDistrictArray(prov);

                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, districts2);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter3);
                break;

            case R.id.pRegDistrictSpinner:
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
        spinner2 = (Spinner) findViewById(R.id.pRegDistrictSpinner);
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
    public void registerPatient() {
        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.names().get(0).equals("success")) {
                        Toast.makeText(getApplicationContext(), jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                        finish();
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

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("flag", "register patient");
                hashMap.put("name", name.getText().toString());
                hashMap.put("nationalId", nationalId.getText().toString());
                hashMap.put("dob", dob.getText().toString());
                hashMap.put("disease", disease.getText().toString());
                hashMap.put("state", state.getText().toString());
                hashMap.put("province", prov);
                hashMap.put("district", dis);
                hashMap.put("area", area.getText().toString());
                hashMap.put("hospital", hospital.getText().toString());
                hashMap.put("apassword", apassword.getText().toString());
                hashMap.put("cpassword", cpassword.getText().toString());
                return hashMap;
            }
        };
        requestQueue.add(request);

    }
}
