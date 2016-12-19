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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Tab1LogIn extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab1_log_in, container, false);

        final Button loginButton = (Button) rootView.findViewById(R.id.loginButton);
        final Button pRegButton = (Button) rootView.findViewById(R.id.pRegButton);
        final Button dRegButton = (Button) rootView.findViewById(R.id.dRegButton);
        final Button mRegButton = (Button) rootView.findViewById(R.id.mRegButton);


        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent insertData = new Intent(getActivity(), insertData.class);
                        getActivity().startActivity(insertData);
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


}
