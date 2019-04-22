package com.nasir.medicaladviser.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.nasir.medicaladviser.R;
import com.nasir.medicaladviser.app.AppConfig;
import com.nasir.medicaladviser.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

public class HospitalFullActivity extends AppCompatActivity {

    private String id,name;
    private TextView type, email, phone, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            id = extras.getString("h_id");
            name = extras.getString("name");

        }
        setContentView(R.layout.activity_hospital_full);

        //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(name);

        type = findViewById(R.id.type);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fetchDescription(id);
    }

    private void fetchDescription(String id){

        JsonObjectRequest strReq = new JsonObjectRequest(Request.Method.GET, AppConfig.localhost+"/hospital/"+id+"?format=json",null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    type.setText(response.getString("type"));
                    email.setText(response.getString("email"));
                    phone.setText(response.getString("phone"));
                    String adrs=response.getString("address")+" "+response.getString("division")+"-"+response.getString("zip_code");
                    address.setText(adrs);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);

    }

}


