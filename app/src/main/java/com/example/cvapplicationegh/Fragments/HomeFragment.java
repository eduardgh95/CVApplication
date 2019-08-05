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
import com.example.cvapplicationegh.Adapters.ListHomeAdapter;
import com.example.cvapplicationegh.R;
import com.example.cvapplicationegh.Utilities.AlertLoading;
import com.example.cvapplicationegh.Utilities.ListItemHome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static String mUrlHome = "https://gist.githubusercontent.com/eduardgh95/b4cfe251ea7e5e1cba8ac9a09375f688/raw/eac15ed35eb20fc004ebbd6f24d90856ae9ccef6/home.json";
    private ArrayList<ListItemHome> itemHomeArrayList = new ArrayList<>();
    private ListView mListHome;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        setupUI(view);
        requestHomeGET();
        return view;
    }




    private void setupUI(View view){
        mListHome = view.findViewById(R.id.list_home);
    }


    public void requestHomeGET(){
        final AlertLoading mAlertLoader = new AlertLoading();
        mAlertLoader.makeDialog(getContext(),getActivity());
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsObjectRequest = new JsonObjectRequest(Request.Method.GET, mUrlHome, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mAlertLoader.dismissDialog();
                try {
                    Log.d("HomeFragment",response.toString());
                    JSONArray mHome = response.getJSONArray("home");
                    String mUsr,mImgProf,mOcup,mTime,mContent;
                    for (int i=0;i < mHome.length();i++){
                        try {
                            mUsr = mHome.getJSONObject(i).getString("username");
                            mImgProf = mHome.getJSONObject(i).getString("imgprofile");
                            mOcup = mHome.getJSONObject(i).getString("ocupation");
                            mTime = mHome.getJSONObject(i).getString("time_post");
                            mContent = mHome.getJSONObject(i).getString("post");
                            itemHomeArrayList.add(new ListItemHome(mUsr,mImgProf,
                                    mOcup,mTime,mContent));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mListHome.setAdapter(new ListHomeAdapter(getContext(), itemHomeArrayList));

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
