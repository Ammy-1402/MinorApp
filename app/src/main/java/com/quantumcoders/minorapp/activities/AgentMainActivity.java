package com.quantumcoders.minorapp.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.adapters.AgentListItemComplaint;
import com.quantumcoders.minorapp.adapters.AgentMyRecyclerAdapter;
import com.quantumcoders.minorapp.fragments.AgentTab1;
import com.quantumcoders.minorapp.fragments.AgentTab2;
import com.quantumcoders.minorapp.adapters.AgentViewPagerAdapter;
import com.quantumcoders.minorapp.misc.Constants;
import com.quantumcoders.minorapp.misc.ServerWorker;

import java.util.ArrayList;

import static com.quantumcoders.minorapp.misc.Constants.REQ_RELOAD_LIST;

public class AgentMainActivity extends AppCompatActivity implements Base{

    TabLayout agentTabLayout;
    ViewPager agentViewPager;
    AgentViewPagerAdapter adapter;

    AgentTab1 tab1 = null;
    AgentTab2 tab2 = null;

    String agentid;

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
                if(tab.getPosition()==0){   //view complaint list fragment/page
                    agentid = getApplicationContext().getSharedPreferences(Constants.SESSION_FILE,MODE_PRIVATE).getString(Constants.USER_ID_KEY,"");
                    ServerWorker.reloadComplaintsListAgent(AgentMainActivity.this,agentid);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Deselecting tab 0 and reselecting it to reload list at startup
        agentViewPager.setCurrentItem(1);
        agentViewPager.setCurrentItem(0);

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if(fragment instanceof AgentTab1){
            tab1 = (AgentTab1) fragment;
        }else if(fragment instanceof AgentTab2){
            tab2 = (AgentTab2) fragment;
        }
    }

    public void longToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    public void complaintListObtainedAgent(String...data){
        ArrayList<AgentListItemComplaint> list = new ArrayList<>();
        for(int i=1;i<data.length;i+=5){
            list.add(new AgentListItemComplaint(data[i],data[i+1],data[i+2],data[i+3],data[i+4]));
        }
        tab1.agentListItemComplaints = list;
        tab1.adapter = new AgentMyRecyclerAdapter(list, this);
        tab1.recyclerView.setAdapter(tab1.adapter);

        longToast("AGENT COMPLAINT LIST UPDATED");


        tab1.getView().findViewById(R.id.loadingLayout).setVisibility(View.INVISIBLE);
    }

    public void noInternet(){
        longToast("Please turn on the INTERNET");
    }

    @Override
    public void onRequestTimeout(){
        longToast("Request timed out");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQ_RELOAD_LIST){
            tab1.agentListItemComplaints=new ArrayList<>();
            tab1.adapter=new AgentMyRecyclerAdapter(tab1.agentListItemComplaints,this);
            tab1.recyclerView.setAdapter(tab1.adapter);
            adapter.notifyDataSetChanged();
            ServerWorker.reloadComplaintsListAgent(this,agentid);
        }
    }
}
