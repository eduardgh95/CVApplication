package com.example.cvapplicationegh.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.cvapplicationegh.R;
import com.example.cvapplicationegh.Utilities.ListItemHome;

import java.util.List;

public class ListHomeAdapter extends ArrayAdapter {



    private TextView txtUser,txtOcupation,txtTime,txtContent;
    private ImageView mProfile;
    private Context mContext;


    public ListHomeAdapter(Context context, List objects) {
        super(context, 0, objects);
        this.mContext = context;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)parent.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.items_list_home, null);
        }

        setupUI(convertView,position);


        return convertView;
    }

    private void setupUI(View view,final int mPosition) {
        txtUser = view.findViewById(R.id.txt_home_name);
        txtOcupation = view.findViewById(R.id.txt_home_workdescrip);
        txtTime = view.findViewById(R.id.txt_home_time);
        txtContent = view.findViewById(R.id.txt_home_content);
        mProfile = view.findViewById(R.id.img_home_profile);

        ListItemHome item = (ListItemHome) getItem(mPosition);
        txtUser.setText(item.getStringUsername());
        txtOcupation.setText(item.getStringOcupation());
        txtTime.setText(item.getStringTime());
        txtContent.setText(item.getStringContent());


        Glide.with(mContext)
                .load(item.getStringUrlImage())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(mProfile);
    }
}

