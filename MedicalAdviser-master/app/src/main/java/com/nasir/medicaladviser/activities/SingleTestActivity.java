package com.nasir.medicaladviser.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
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

import java.util.Objects;

public class SingleTestActivity extends AppCompatActivity {
    String id, name;
    TextView details, home_deliverable, price, delivery_in, hospital_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extra=getIntent().getExtras();
        if (extra !=null){
            id=extra.getString("test_id");
            name=extra.getString("name");
        }

        setContentView(R.layout.activity_single_test);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        details=findViewById(R.id.details);
        home_deliverable=findViewById(R.id.home_deliverable);
        price=findViewById(R.id.test);
        delivery_in=findViewById(R.id.delivery_in);
        hospital_name=findViewById(R.id.hospital_name);
        Objects.requireNonNull(getSupportActionBar()).setTitle(name);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fetchTest(id);
    }

    private void fetchTest(String id) {
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, AppConfig.localhost + "/test/" + id + "?format=json", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    details.setText(Html.fromHtml(response.getString("test_details"), Html.FROM_HTML_MODE_COMPACT));
                    if (response.getBoolean("is_home_deliverable")){
                        home_deliverable.setText("YES");
                    }else{
                        home_deliverable.setText("NO");
                    }
                    StringBuilder str=new StringBuilder();
                    str.append(response.getInt("price"));
                    str.append(" Taka");
                    price.setText(str);
                    str=new StringBuilder();
                    str.append(response.getString("delivary_in"));
                    if (response.getString("delivary_in").equals("1")){
                        str.append(" day");
                    }else{
                        str.append(" days");
                    }
                    delivery_in.setText(str);
                    hospital_name.setText(response.getString("hospital"));

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
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
