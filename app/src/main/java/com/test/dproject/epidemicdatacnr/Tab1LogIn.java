package com.test.dproject.epidemicdatacnr;

/**
 * Created by Subhashinie on 12/3/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class Tab1LogIn extends Fragment{

    private EditText id, password;
    private Button loginButton;
    private RequestQueue requestQueue;
    private static final String url = "http://10.0.2.2/phpFiles/user_control.php";
    private StringRequest request;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab1_log_in, container, false);

        id = (EditText) rootView.findViewById(R.id.loginIdET);
        password = (EditText) rootView.findViewById(R.id.loginPwET);
        loginButton = (Button) rootView.findViewById(R.id.loginButton);

        final Button pRegButton = (Button) rootView.findViewById(R.id.pRegButton);
        final Button dRegButton = (Button) rootView.findViewById(R.id.dRegButton);
        final Button mRegButton = (Button) rootView.findViewById(R.id.mRegButton);

        requestQueue = Volley.newRequestQueue(this.getActivity());

        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        login();
                    }
                }
        );

        pRegButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent pReg = new Intent(getActivity(), patientRegistration.class);
                        getActivity().startActivity(pReg);
                        Toast.makeText(rootView.getContext(),"Register as a patient",Toast.LENGTH_SHORT).show();
                    }
                }
        );

        dRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dReg = new Intent(getActivity(),DoctorRegistration.class);
                getActivity().startActivity(dReg);
                Toast.makeText(rootView.getContext(), "Register as a doctor", Toast.LENGTH_SHORT).show();
            }
        });
        mRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mReg = new Intent(getActivity(),MedicalOfficerRegistration.class);
                getActivity().startActivity(mReg);
                Toast.makeText(rootView.getContext(),"Register as a medical officer",Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    public void login() {
        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.names().get(0).equals("success")) {
                        Toast.makeText(getActivity().getApplicationContext(), jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                        Intent insertData = new Intent(getActivity(), InsertData.class);
                        Bundle b = new Bundle();
                        b.putString("insertingPerson", jsonObject.getString("natinalId")); //Your id
                        insertData.putExtras(b);
                        getActivity().startActivity(insertData);
                    } else if (jsonObject.names().get(0).equals("error")) {
                        Toast.makeText(getActivity().getApplicationContext(), jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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
                hashMap.put("flag", "login");
                hashMap.put("nationalId", id.getText().toString());
                hashMap.put("apassword", password.getText().toString());
                return hashMap;
            }
        };
        requestQueue.add(request);

    }


}
