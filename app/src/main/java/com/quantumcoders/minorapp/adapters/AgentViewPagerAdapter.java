package com.quantumcoders.minorapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.quantumcoders.minorapp.fragments.AgentTab1;
import com.quantumcoders.minorapp.fragments.AgentTab2;
import com.quantumcoders.minorapp.fragments.AgentTab3;

public class AgentViewPagerAdapter extends FragmentPagerAdapter {

    int tabCount ;
    public AgentViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                AgentTab1 tab1 = new AgentTab1();
                return tab1;
            case 1:
                AgentTab2 tab2 = new AgentTab2();
                return tab2;
            case 2:
                AgentTab3 tab3 = new AgentTab3();
                return tab3;
            default:
                tab1 = new AgentTab1();
                return tab1;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
