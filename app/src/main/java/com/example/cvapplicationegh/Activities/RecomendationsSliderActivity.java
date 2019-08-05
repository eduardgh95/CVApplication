package com.example.cvapplicationegh.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.cvapplicationegh.Adapters.PagerRecomendationsAdapter;
import com.example.cvapplicationegh.R;

import java.util.Arrays;


public class RecomendationsSliderActivity extends AppCompatActivity {

    //Pagers
    private ViewPager mPager;
    private PagerAdapter mpagerAdapter;
    //Layouts
    private int [] mLayouts = new int[3];

    //Views
    private LinearLayout layoutDots;
    private ImageView[] dots;
    private Button btnSkip;


    private boolean banderaHome = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendations_slider);
        Arrays.fill(mLayouts,R.layout.slider_recomendations);
        setupUI();
        configListenersUI();
    }


    public void setupUI(){
        mPager =  findViewById(R.id.view_pager);
        btnSkip = findViewById(R.id.btn_skip);

        mpagerAdapter = new PagerRecomendationsAdapter(mLayouts,this);
        mPager.setAdapter(mpagerAdapter);
        layoutDots = (LinearLayout)findViewById(R.id.dotslayout);
        createDots(0);
    }

    public void configListenersUI(){
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void createDots(int current_position){

        if (layoutDots != null)
            layoutDots.removeAllViews();

        dots = new ImageView[mLayouts.length];
        for (int i = 0; i<mLayouts.length; i++){
            dots[i] = new ImageView(this);
            if (i==current_position)
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.dost_active));
            else
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.dost_inactive));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 0, 10, 0);
            layoutDots.addView(dots[i], params);
        }
    }

}
