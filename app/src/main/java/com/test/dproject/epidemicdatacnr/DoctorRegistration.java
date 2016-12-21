package com.test.dproject.epidemicdatacnr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class DoctorRegistration extends AppCompatActivity {

    private EditText name, nationalId, doctorId, apassword, cpassword;
    private Button doctorRegButton;
    private RequestQueue requestQueue;
    private static final String url = "mainUrl";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);

        name = (EditText) findViewById(R.id.docRegNameET);
        nationalId = (EditText) findViewById(R.id.docRegNationalIdET);
        doctorId = (EditText) findViewById(R.id.docRegDoctorIdET);
        apassword = (EditText) findViewById(R.id.docRegPasswordET);
        cpassword = (EditText) findViewById(R.id.docRegConfirmET);

        doctorRegButton = (Button) findViewById(R.id.docRegRegisterButton);

        requestQueue = Volley.newRequestQueue(this);

        doctorRegButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        registerDoctor();
                    }
                }
        );
    }

    public void registerDoctor() {
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
                hashMap.put("flag", "register doctor");
                hashMap.put("name", name.getText().toString());
                hashMap.put("nationalId", nationalId.getText().toString());
                hashMap.put("doctorId", doctorId.getText().toString());
                hashMap.put("apassword", apassword.getText().toString());
                hashMap.put("cpassword", cpassword.getText().toString());
                return hashMap;
            }
        };
        requestQueue.add(request);

    }
}
