package com.quantumcoders.minorapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.quantumcoders.minorapp.fragments.CitizenTab1;
import com.quantumcoders.minorapp.fragments.CitizenTab2;
import com.quantumcoders.minorapp.fragments.CitizenTab3;

public class CitizenViewPagerAdapter extends FragmentPagerAdapter {
    int tabCount;

    public CitizenViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CitizenTab1 tab1 = new CitizenTab1();
                return tab1;
            case 1:
                CitizenTab2 tab2 = new CitizenTab2();
                return tab2;
            case 2:
                CitizenTab3 tab3 = new CitizenTab3();
                return tab3;
            default:
                tab1 = new CitizenTab1();
                return tab1;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
