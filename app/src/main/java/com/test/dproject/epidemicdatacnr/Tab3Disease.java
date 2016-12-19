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

public class Tab3Disease extends Fragment implements AdapterView.OnItemSelectedListener {

    String[] diseases;
    ArrayAdapter<String> adapter;
    Spinner spinner;
    AllArrays allArrays;

    String disease;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3_disease, container, false);

        allArrays = new AllArrays();
        setDiseaseSpinner(rootView);
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

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
