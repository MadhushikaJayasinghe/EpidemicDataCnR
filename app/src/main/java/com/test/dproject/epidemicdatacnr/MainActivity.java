package com.test.dproject.epidemicdatacnr;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.test.dproject.epidemicdatacnr.exceptions.InvalidPatientException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private RequestQueue requestQueue;
    private static final String url = "http://10.0.2.2/new/startingPoint.php";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        PatientDAOImpl patientDAOImpl1 = new PatientDAOImpl();
        patientDAOImpl1.createmethod(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            requestQueue = Volley.newRequestQueue(this);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Trying to upload collected data to main server", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    PatientDAOImpl patientDAOImpl = new PatientDAOImpl();
                    List<Patient> patientList = patientDAOImpl.getAllPatients();
                    if (!patientList.isEmpty()) {
                        for (Patient patient : patientList) {
                            uploadData(patient, patientDAOImpl);
                        }
                    }
                    Snackbar.make(view, "Upload complete", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

    }

    public void uploadData(final Patient patient, final PatientDAOImpl patientDAOImpl) {
        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.names().get(0).equals("success")) {
                        Toast.makeText(getApplicationContext(), jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                        patientDAOImpl.removePatient(patient.getPatient_ID());
                    } else if (jsonObject.names().get(0).equals("error")) {
                        Toast.makeText(getApplicationContext(), jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InvalidPatientException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.i(TAG, "error");

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("flag", "insert data");
                hashMap.put("name", patient.getName());
                hashMap.put("nationalId", patient.getNational_ID());
                hashMap.put("dob", patient.getDate_Of_Birth());
                hashMap.put("disease", patient.getIllness());
                hashMap.put("state", patient.getIllness());
                hashMap.put("province", patient.getProvince());
                hashMap.put("district", patient.getDistrict());
                hashMap.put("area", patient.getArea());
                hashMap.put("hospital", patient.getHospital());
                return hashMap;
            }
        };
        requestQueue.add(request);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    Tab1LogIn tab1 = new Tab1LogIn();
                    return tab1;
                case 1:
                    Tab2Regional tab2 = new Tab2Regional();
                    return tab2;
                case 2:
                    Tab3Disease tab3 = new Tab3Disease();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Log In";
                case 1:
                    return "Regional stats";
                case 2:
                    return "Disease stats";
            }
            return null;
        }
    }


}
