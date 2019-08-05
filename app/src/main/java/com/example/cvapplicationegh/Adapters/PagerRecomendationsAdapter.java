package com.example.cvapplicationegh.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.example.cvapplicationegh.R;


public class PagerRecomendationsAdapter extends PagerAdapter {

    private int[] layouts;
    private LayoutInflater layoutInflater;
    private Context context;
    private TextView txtIndicator,txtRecomendation;




    public PagerRecomendationsAdapter(int[] layouts, Context context){

        this.layouts = layouts;
        this.context = context;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return layouts.length;
    }



    @Override
    public boolean isViewFromObject(@NonNull View view,@NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = layoutInflater.inflate(layouts[position],container, false);
        container.addView(view);
        setupUI(view);
        setupText(position,view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position,@NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    private void setupUI(View view){
        txtIndicator = view.findViewById(R.id.txt_indicator);
        txtRecomendation = view.findViewById(R.id.txt_recomendation);
    }
    private void setupText(int position,View view){
        switch (position){
            case 0:
                txtRecomendation.setText(R.string.recomendation_first);
                txtRecomendation.setTextColor(context.getResources().getColor(R.color.colorWhite));
                view.setBackgroundColor(context.getResources().getColor(R.color.colorFirstSlider));
                break;
            case 1:
                txtRecomendation.setText(R.string.recomendation_second);
                txtRecomendation.setTextColor(context.getResources().getColor(R.color.colorBlack));
                view.setBackgroundColor(context.getResources().getColor(R.color.colorSecondSlider));
                break;
            case 2:
                txtRecomendation.setText(R.string.recomendation_third);
                txtRecomendation.setTextColor(context.getResources().getColor(R.color.colorWhite));
                view.setBackgroundColor(context.getResources().getColor(R.color.colorThirdSlider));

                break;
            default:
                txtRecomendation.setText(R.string.recomendation_default);
                txtRecomendation.setTextColor(context.getResources().getColor(R.color.colorWhite));
                view.setBackgroundColor(context.getResources().getColor(R.color.colorFirstSlider));
                break;
        }
        txtIndicator.setText(String.valueOf(position+1));
        txtIndicator.setTextColor(txtRecomendation.getCurrentTextColor());

    }
}
