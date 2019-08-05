package com.example.cvapplicationegh.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cvapplicationegh.Adapters.ListNotificationsAdapter;
import com.example.cvapplicationegh.R;
import com.example.cvapplicationegh.Utilities.AlertLoading;
import com.example.cvapplicationegh.Utilities.ListItemNotifications;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class NotificationsFragment extends Fragment {


    private static String mUrlNotifications = "https://gist.githubusercontent.com/eduardgh95/095c6466399f87ab4b0ad625d8432f36/raw/617f0c56acec564847c558aed69777addd08dfee/notifications.json";
    private ArrayList<ListItemNotifications> itemNotificationsArrayList = new ArrayList<>();
    private ListView mListNotifications;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications,container,false);
        setupUI(view);
        requestNotificationsGET();
        return view;
    }



    private void setupUI(View view){
        mListNotifications = view.findViewById(R.id.list_notifications);
    }


    public void requestNotificationsGET(){
        final AlertLoading mAlertLoader = new AlertLoading();
        mAlertLoader.makeDialog(getContext(),getActivity());
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsObjectRequest = new JsonObjectRequest(Request.Method.GET, mUrlNotifications,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mAlertLoader.dismissDialog();
                try {
                    Log.d("NotificationsFragment",response.toString());
                    JSONArray mNotifications = response.getJSONArray("notifications");
                    String mContent,mUrlImg,mTime;
                    for (int i=0;i < mNotifications.length();i++){
                        try {
                            mContent = mNotifications.getJSONObject(i).getString("title");
                            mUrlImg = mNotifications.getJSONObject(i).getString("img");
                            mTime = mNotifications.getJSONObject(i).getString("time");
                            itemNotificationsArrayList.add(new ListItemNotifications(mContent,mUrlImg,mTime));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mListNotifications.setAdapter(new ListNotificationsAdapter(getContext(),
                            itemNotificationsArrayList));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("TAG", "Error Respuesta en JSON: " + error.getMessage());
                        mAlertLoader.dismissDialog();
                        Toast.makeText(getContext(),"Weak connection",Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsObjectRequest);
    }

}




