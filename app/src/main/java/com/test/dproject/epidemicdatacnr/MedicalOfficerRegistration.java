package com.test.dproject.epidemicdatacnr;

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

public class MedicalOfficerRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] provinces;
    String[] districts;
    String[] districts2;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    Spinner spinner;
    Spinner spinner2;
    AllArrays allArrays;

    private EditText name, nationalId, title, area, apassword, cpassword;
    private Button medicalRgisterButton;
    private RequestQueue requestQueue;
    private static final String url = "mainUrl";
    private StringRequest request;
    //to database
    String prov;
    String dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_officer_registration);

        //for the spinners
        allArrays = new AllArrays();
        setProvinceSpinner();
        setDistrictSpinner();
        districts = new String[]{"Province needed"};

        name = (EditText) findViewById(R.id.moRegNameET);
        nationalId = (EditText) findViewById(R.id.moRegNationalIdET);
        title = (EditText) findViewById(R.id.moRegTitleET);
        area = (EditText) findViewById(R.id.moRegAreaET);
        apassword = (EditText) findViewById(R.id.moRegPasswordET);
        cpassword = (EditText) findViewById(R.id.moRegConfirmET);

        medicalRgisterButton = (Button) findViewById(R.id.moRegButton);

        requestQueue = Volley.newRequestQueue(this);

        medicalRgisterButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        moRegister();
                    }
                }
        );
    }

    public void moRegister() {
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
                hashMap.put("flag", "register mo");
                hashMap.put("name", name.getText().toString());
                hashMap.put("nationalId", nationalId.getText().toString());
                hashMap.put("title,,", title.getText().toString());
                hashMap.put("province", prov);
                hashMap.put("district", dis);
                hashMap.put("area", area.getText().toString());
                hashMap.put("apassword", apassword.getText().toString());
                hashMap.put("cpassword", cpassword.getText().toString());
                return hashMap;
            }
        };
        requestQueue.add(request);

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

                prov = (String) parent.getItemAtPosition(position).toString();
                districts2 = allArrays.getDistrictArray(prov);

                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, districts2);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter3);
                break;

            case R.id.mRegDistrictSpinner:
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
