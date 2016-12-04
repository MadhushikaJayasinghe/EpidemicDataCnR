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

    String[] districts;
    String[] districts2;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    Spinner spinner;
    Spinner spinner2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_regional, container, false);
        setProvinceSpinner(rootView);
        setDistrictSpinner(rootView);
        districts= new String[]{"Province not specified"};
        return rootView;
    }

    public void setProvinceSpinner(View view){
        String[] provinces = {"All island","Central","Eastern","North Central",
                "Northern","North Western","Sabaragamuwa","Southern","Uva","Western"};

        adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,provinces);
        spinner = (Spinner) view.findViewById(R.id.provinceSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(parent.getContext(),
                parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();
        switch (parent.getId()) {
            case R.id.provinceSpinner:

                String prov = (String) parent.getItemAtPosition(position).toString();
                if (prov.equals("Central")) {
                    districts2 = new String[]{"Kandy", "Matale", "Nuwara Eliya"};
                } else if (prov.equals("Eastern")) {
                    districts2 = new String[]{"Ampara", "Batticaloa", "Trincomalee"};
                } else if (prov.equals("North Central")) {
                    districts2 = new String[]{"Anuradhapura", "Polonnaruwa"};
                } else if (prov.equals("Northern")) {
                    districts2 = new String[]{"Jaffna", "Kilinochchi", "Mannar", "Mullaitivu", "Vavuniya"};
                } else if (prov.equals("North Western")) {
                    districts2 = new String[]{"Kurunegala", "Puttalam"};
                } else if (prov.equals("Sabaragamuwa")) {
                    districts2 = new String[]{"Kegalle", "Ratnapura"};
                } else if (prov.equals("Southern")) {
                    districts2 = new String[]{"Galle", "Hambantota", "Matara"};
                } else if (prov.equals("Uva")) {
                    districts2 = new String[]{"Badulla", "Monaragala"};
                } else if (prov.equals("Western")) {
                    districts2 = new String[]{"Colombo", "Gampaha", "Kalutara"};
                } else {
                    districts2 = new String[]{"Province not specified"};
                }

                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, districts2);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter3);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void setDistrictSpinner(View view){
        districts= new String[]{"Province not specified"};
        adapter2 = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,districts);
        spinner2 = (Spinner) view.findViewById(R.id.districtSpinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setNotifyOnChange(true);
        spinner2.setAdapter(adapter2);
    }
}
