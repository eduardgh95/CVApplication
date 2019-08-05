package com.example.cvapplicationegh.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cvapplicationegh.R;
import com.example.cvapplicationegh.Utilities.ListItemEducation;

import java.util.List;

public class ListExperienceAdapter extends ArrayAdapter {


    public ListExperienceAdapter(Context context, List objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)parent.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.items_list_profile_exp, null);
        }


        ListItemEducation item = (ListItemEducation) getItem(position);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txt_profile_ocup);
        txtTitle.setText(item.getStringTitle());

        TextView txtLocationWork = (TextView) convertView.findViewById(R.id.txt_profile_work);
        txtLocationWork.setText(item.getStringLocation());

        TextView txtDate = (TextView) convertView.findViewById(R.id.txt_profile_time);
        String mDate =  item.getStringDMonthStart() + " " + item.getStringDYearStart() + " - " +
                item.getStringDMonthFinish() + " " + item.getStringDYearFinish();
        txtDate.setText(mDate);

        return convertView;
    }
}

