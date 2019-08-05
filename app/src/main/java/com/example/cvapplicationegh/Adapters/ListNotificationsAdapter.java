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
import com.example.cvapplicationegh.Utilities.ListItemNotifications;

import java.util.List;

public class ListNotificationsAdapter extends ArrayAdapter {

    private TextView txtContent,txtTime;
    private ImageView imgProfile;
    private Context mContext;


    public ListNotificationsAdapter(Context context, List objects) {
        super(context, 0, objects);
        this.mContext = context;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)parent.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.items_list_notifications, null);
        }

        setupUI(convertView,position);
        return convertView;
    }

    private void setupUI(View view,final int mPosition) {
        txtContent = view.findViewById(R.id.txt_notific_content);
        txtTime = view.findViewById(R.id.txt_notific_time);
        imgProfile = view.findViewById(R.id.img_notif_profile);

        ListItemNotifications item = (ListItemNotifications) getItem(mPosition);
        txtContent.setText(item.getStringTitleContent());
        txtTime.setText(item.getStringTime());


        Glide.with(mContext)
                .load(item.getStringUrlImage())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imgProfile);
    }
}

