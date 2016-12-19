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

public class Tab2Regional extends Fragment implements AdapterView.OnItemSelectedListener{
    /*
    Central         - Kandy, Matale, Nuwara Eliya
    Eastern         - Ampara, Batticaloa, Trincomalee
    North Central   - Anuradhapura, Polonnaruwa
    Northern        - Jaffna, Kilinochchi, Mannar, Mullaitivu, Vavuniya
    North Western   - Kurunegala, Puttalam
    Sabaragamuwa    - Kegalle, Ratnapura
    Southern        - Galle, Hambantota, Matara
    Uva             - Badulla, Monaragala
    Western         - Colombo, Gampaha, Kalutara
     */
    String[] provinces;
    String[] districts;
    String[] districts2;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    Spinner spinner;
    Spinner spinner2;
    AllArrays allArrays;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_regional, container, false);

        //for the spinners
        allArrays = new AllArrays();
        setProvinceSpinner(rootView);
        setDistrictSpinner(rootView);
        districts= new String[]{"Province not specified"};

        return rootView;
    }

    public void setProvinceSpinner(View view){

        provinces = allArrays.getProvinceArray();
        adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,provinces);
        spinner = (Spinner) view.findViewById(R.id.provinceSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void setDistrictSpinner(View view) {
        districts = new String[]{"Province not specified"};
        adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, districts);
        spinner2 = (Spinner) view.findViewById(R.id.districtSpinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setNotifyOnChange(true);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.provinceSpinner:

                String prov = (String) parent.getItemAtPosition(position).toString();
                districts2 = allArrays.getDistrictArray(prov);

                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, districts2);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter3);
                break;

            case R.id.districtSpinner:
                String district = (String) parent.getItemAtPosition(position).toString();
                if (!district.equals("Province not specified")) {
                    String[][] regionData = {{"Measles", "6"}, {"Encephalitis", "7"}};
                    setTable(regionData);
                }
                break;
        }

    }

    public void setTable(String[][] tableArray) {
        TableLayout table = (TableLayout) getActivity().findViewById(R.id.regionTable);
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
