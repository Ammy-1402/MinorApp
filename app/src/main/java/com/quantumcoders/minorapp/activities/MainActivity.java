package com.quantumcoders.minorapp.activities;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.ViewPagerAdapter;
import com.quantumcoders.minorapp.fragments.Tab1;
import com.quantumcoders.minorapp.fragments.Tab2;

public class MainActivity extends AppCompatActivity implements Tab1.OnFragmentInteractionListener, Tab2.OnFragmentInteractionListener{

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout);

        viewPager = findViewById(R.id.id_viewPager);

        //System.out.println(" --- No of tabs in tab layout --- "+tabLayout.getTabCount());

        adapter = new ViewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        //Setting adapter to view pager
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}

