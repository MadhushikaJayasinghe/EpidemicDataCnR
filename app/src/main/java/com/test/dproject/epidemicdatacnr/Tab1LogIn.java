package com.test.dproject.epidemicdatacnr;

/**
 * Created by Subhashinie on 12/3/2016.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;


public class Tab1LogIn extends Fragment{

    private EditText id, password;
    private Button loginButton;
    private RequestQueue requestQueue;
    private static final String url = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_log_in, container, false);

        loginButton = (Button) rootView.findViewById(R.id.loginButton);
        final Button pRegButton = (Button) rootView.findViewById(R.id.pRegButton);


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
                    }
                }
        );
        return rootView;
    }


}
