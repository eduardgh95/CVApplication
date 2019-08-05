package com.example.cvapplicationegh.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.cvapplicationegh.Adapters.ListEducationAdapter;
import com.example.cvapplicationegh.Adapters.ListExperienceAdapter;
import com.example.cvapplicationegh.R;
import com.example.cvapplicationegh.Utilities.AlertLoading;
import com.example.cvapplicationegh.Utilities.ListItemEducation;
import com.example.cvapplicationegh.Utilities.UIUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    private static String mUrlProfile = "https://gist.githubusercontent.com/eduardgh95/6734bfa5abb26bac7a4255cc7f1c4e6e/raw/f79e552ec021a1b86c0d8a74527ba1da27a3ea0c/profile.json";
    private ImageView mImgProfile;
    private TextView mTxtUserName,mTxtOcupation,mTxtWorkLocation;
    private ListView mListExp,mListEduc;
    private ArrayList<ListItemEducation> itemExperienceArrayList = new ArrayList<>();
    private ArrayList<ListItemEducation> itemEducationArrayList = new ArrayList<>();

    private int drawableResourcePerfil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        setupUI(view);
        requestProfileGET(view);

        return view;
    }

    public void setupUI(View view){
        mImgProfile = view.findViewById(R.id.profile_img);
        mTxtUserName = view.findViewById(R.id.txt_user);
        mTxtOcupation = view.findViewById(R.id.txt_ocupation);
        mTxtWorkLocation = view.findViewById(R.id.txt_work_education);
        mListExp = view.findViewById(R.id.list_experience);
        mListEduc = view.findViewById(R.id.list_education);

        drawableResourcePerfil = getActivity().getResources().getIdentifier("img_avatar_perfil",
                "drawable", getActivity().getPackageName());
        Glide.with(this)
                .load(drawableResourcePerfil)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(mImgProfile);
    }

    public void addImageProfile(String urlImage){
        Glide.with(this)
                .load(urlImage)
                .thumbnail(Glide.with(this)
                        .load(R.drawable.img_avatar_perfil))
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(mImgProfile);
    }

    public void requestProfileGET(final View view){
        final AlertLoading mAlertLoader = new AlertLoading();
        mAlertLoader.makeDialog(getContext(),getActivity());
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsObjectRequest = new JsonObjectRequest(Request.Method.GET, mUrlProfile, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mAlertLoader.dismissDialog();
                try {
                    Log.d("ProfileFragment",response.toString());
                    JSONObject mUser = response.getJSONObject("user");
                    JSONArray mExperience = response.getJSONArray("experience");
                    JSONArray mEducation = response.getJSONArray("education_history");


                    addImageProfile(mUser.getString("imgprofile"));
                    mTxtUserName.setText(mUser.getString("name"));
                    mTxtOcupation.setText(mUser.getString("ocupation"));

                    String location,education;
                    location = mUser.getString("locationwork") + " - ";
                    education = location + mUser.getString("education");
                    mTxtWorkLocation.setText(education);


                    String mTitle,mWorkLocation,mDMS,mDYS,mDMF,mDYF;


                    //Setup Experience
                    for (int i=0;i < mExperience.length();i++){
                        try {
                            mTitle = mExperience.getJSONObject(i).getString("title");
                            mWorkLocation = mExperience.getJSONObject(i).getString("work_location");
                            mDMS = mExperience.getJSONObject(i).getString("date_month_start");
                            mDYS = mExperience.getJSONObject(i).getString("date_year_start");
                            mDMF = mExperience.getJSONObject(i).getString("date_month_end");
                            mDYF = mExperience.getJSONObject(i).getString("date_year_end");
                            itemExperienceArrayList.add(new ListItemEducation(mTitle,mWorkLocation,
                                    mDMS,mDYS,mDMF,mDYF));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mListExp.setAdapter(new ListExperienceAdapter(getContext(), itemExperienceArrayList));
                    UIUtils.setListViewHeightBasedOnItems(mListExp);


                    // Setup Education
                    for (int i=0;i < mEducation.length();i++){
                        try {
                            mTitle = mEducation.getJSONObject(i).getString("title");
                            mWorkLocation = mEducation.getJSONObject(i).getString("education_location");
                            mDMS = mEducation.getJSONObject(i).getString("date_month_start");
                            mDYS = mEducation.getJSONObject(i).getString("date_year_start");
                            mDMF = mEducation.getJSONObject(i).getString("date_month_end");
                            mDYF = mEducation.getJSONObject(i).getString("date_year_end");
                            itemEducationArrayList.add(new ListItemEducation(mTitle,mWorkLocation,
                                    mDMS,mDYS,mDMF,mDYF));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mListEduc.setAdapter(new ListEducationAdapter(getContext(), itemEducationArrayList));
                    UIUtils.setListViewHeightBasedOnItems(mListEduc);


                    view.setVerticalScrollbarPosition(0);




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

