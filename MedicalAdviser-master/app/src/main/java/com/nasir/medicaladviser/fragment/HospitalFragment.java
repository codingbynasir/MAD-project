package com.nasir.medicaladviser.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.nasir.medicaladviser.R;
import com.nasir.medicaladviser.app.AppConfig;
import com.nasir.medicaladviser.model.TipsItem;
import com.nasir.medicaladviser.activities.HospitalFullActivity;
import com.nasir.medicaladviser.adapter.HospitalAdapter;
import com.nasir.medicaladviser.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HospitalFragment extends Fragment implements HospitalAdapter.TipsItemClickCallback {

    private RecyclerView recView;
    private HospitalAdapter adapter;
    private ArrayList<TipsItem> tipsItems;
    private ArrayList<TipsItem> forSynchronisePosition;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hospital, container, false);


        forSynchronisePosition = new ArrayList<>();
        tipsItems = new ArrayList<>();
        recView = rootView.findViewById(R.id.rec_list_f_tips);
        recView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HospitalAdapter(tipsItems,getContext());
        recView.setAdapter(adapter);
        adapter.setTipsItemClickCallback(this);

        swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout_tips);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchHospital();
            }
        });

        fetchHospital();


        return rootView;
    }

    private void fetchHospital(){
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, AppConfig.localhost+"/hospitals/?format=json",null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                tipsItems.clear();

                for (int i = 0; i < response.length(); i++) {
                    try{
                        JSONObject object = response.getJSONObject(i);
                        TipsItem tipsItem = new TipsItem();
                        tipsItem.setTipsId(object.getString("id"));
                        tipsItem.setHeadline(object.getString("full_name"));
                        tipsItem.setDescription(object.getString("type"));

                        tipsItems.add(tipsItem);
                    }
                    catch (JSONException e){
                        //Log.e(TAG, "Json parsing error: " + e.getMessage());
                    }
                }
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
            }

        });
        AppController.getInstance().addToRequestQueue(req);
    }

    @Override
    public void onItemClick(int p, View view) {
        final TipsItem item = tipsItems.get(p);
        Intent intent = new Intent(getActivity(), HospitalFullActivity.class);
        intent.putExtra("h_id",item.getTipsId());
        intent.putExtra("name",item.getHeadline());
        startActivity(intent);
    }
}
