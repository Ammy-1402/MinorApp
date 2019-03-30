package com.quantumcoders.minorapp.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.fragments.AgentTab1;
import com.quantumcoders.minorapp.fragments.AgentTab2;
import com.quantumcoders.minorapp.fragments.AgentTab3;
import com.quantumcoders.minorapp.misc.AgentViewPagerAdapter;

public class AgentMainActivity extends AppCompatActivity {

    TabLayout agentTabLayout;
    ViewPager agentViewPager;
    AgentViewPagerAdapter adapter;

    AgentTab1 tab1 = null;
    AgentTab2 tab2 = null;
    AgentTab3 tab3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_main);

        agentTabLayout = findViewById(R.id.agentTabLayout);
        agentViewPager = findViewById(R.id.id_agentViewPager);

        adapter = new AgentViewPagerAdapter(getSupportFragmentManager(), agentTabLayout.getTabCount());
        agentViewPager.setAdapter(adapter);
        agentViewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(agentTabLayout));
        agentViewPager.setOffscreenPageLimit(3);
        agentTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                agentViewPager.setCurrentItem(tab.getPosition());
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
    public void onAttachFragment(Fragment fragment) {
        if(fragment instanceof AgentTab1){
            tab1 = (AgentTab1) fragment;
        }else if(fragment instanceof AgentTab2){
            tab2 = (AgentTab2) fragment;
        }else if(fragment instanceof AgentTab3){
            tab3 = (AgentTab3) fragment;
        }
    }

}
