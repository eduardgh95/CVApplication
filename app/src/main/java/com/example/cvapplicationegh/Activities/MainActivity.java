package com.example.cvapplicationegh.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.cvapplicationegh.Fragments.HomeFragment;
import com.example.cvapplicationegh.Fragments.NotificationsFragment;
import com.example.cvapplicationegh.Fragments.ProfileFragment;
import com.example.cvapplicationegh.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;



public class MainActivity extends AppCompatActivity {
    public static String urlProjectGithub = "https://github.com/eduardgh95/CVApplication";

    public Toolbar toolbar;
    public DrawerLayout drawer;
    public ActionBarDrawerToggle toggle;

    public BottomNavigationView menuBottom;
    public NavigationView navigationView;
    public Fragment mFragmentLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        setupListenersUI();
    }

    public void setupUI(){
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        menuBottom = findViewById(R.id.menu_navegation);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setSupportActionBar(toolbar);
        mFragmentLoader = new HomeFragment();
        loadFragment(mFragmentLoader);
    }

    public void setupListenersUI(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_my_profile:
                        menuBottom.setSelectedItemId(R.id.nav_profile);
                        break;
                    //case R.id.nav_recomend:
                       // Intent intentRecomend= new Intent (MainActivity.this, RecomendationsSliderActivity.class);
                       // startActivity(intentRecomend);
                       // break;
                    case R.id.nav_about:
                        Intent intentAbout= new Intent (MainActivity.this, AboutActivity.class);
                        startActivity(intentAbout);
                        break;
                    case R.id.nav_share:
                        shareGithubProject();
                    break;

                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        menuBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        mFragmentLoader = new HomeFragment();
                        loadFragment(mFragmentLoader);
                    break;
                    case R.id.nav_profile:
                        mFragmentLoader = new ProfileFragment();
                        loadFragment(mFragmentLoader);
                        break;
                    case R.id.nav_notifications:
                        mFragmentLoader = new NotificationsFragment();
                        loadFragment(mFragmentLoader);
                        break;
                }
                return true;
            }
        });


    }


    public void loadFragment(Fragment mFragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mFragment).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        drawer.openDrawer(GravityCompat.START);
        return true;
    }


    public void shareGithubProject() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, urlProjectGithub);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Github CVApplication"));
    }
}
