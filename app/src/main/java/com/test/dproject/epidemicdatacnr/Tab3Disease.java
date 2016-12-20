package com.test.dproject.epidemicdatacnr;

/**
 * Created by Subhashinie on 12/3/2016.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
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

public class Tab3Disease extends Fragment implements AdapterView.OnItemSelectedListener {

    String[] diseases;
    ArrayAdapter<String> adapter;
    Spinner spinner;
    AllArrays allArrays;

    private RequestQueue requestQueue;
    private static final String url = "mainUrl";
    private StringRequest request;

    String disease;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3_disease, container, false);

        allArrays = new AllArrays();
        setDiseaseSpinner(rootView);

        requestQueue = Volley.newRequestQueue(getActivity());
        return rootView;
    }

    public void setDiseaseSpinner(View view) {

        diseases = allArrays.getDiseaseArray();
        adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, diseases);
        spinner = (Spinner) view.findViewById(R.id.diseaseSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.diseaseSpinner:
                disease = (String) parent.getItemAtPosition(position).toString();
                if (!disease.equals("None")) {
                    getDiseaseData();
                    String[][] diseaseData = {{"Western Province", "6"}, {"Eastern Province", "7"}};
                    setTable(diseaseData);
                }

        }


    }

    public void setTable(String[][] tableArray) {
        TableLayout table = (TableLayout) getActivity().findViewById(R.id.diseaseTable);
        table.setStretchAllColumns(true);
        table.bringToFront();
        for (int i = 0; i < tableArray.length; i++) {
            TableRow tr = new TableRow(getActivity());
            TextView c1 = new TextView(getActivity());
            c1.setTextColor(0xFF000000);
            c1.setTextSize(17);
            c1.setText(tableArray[i][0]);
            TextView c2 = new TextView(getActivity());
            c2.setTextColor(0xFF000000);
            c1.setTextSize(17);
            c2.setText(String.valueOf(tableArray[i][1]));
            //TextView c3 = new TextView(getActivity());
            //c3.setTextColor(0xFF000000);
            //c3.setText(String.valueOf(drug[i][2]));
            tr.addView(c1);
            tr.addView(c2);
            //tr.addView(c3);
            table.addView(tr);
        }
    }

    public void getDiseaseData() {
        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.names().get(0).equals("success")) {
                        Toast.makeText(getActivity().getApplicationContext(), jsonObject.getString("success"), Toast.LENGTH_SHORT).show();

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
                hashMap.put("flag", "get disease data");
                hashMap.put("disease", disease);
                return hashMap;
            }
        };
        requestQueue.add(request);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
