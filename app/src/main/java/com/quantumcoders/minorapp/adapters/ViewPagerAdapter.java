package com.quantumcoders.minorapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import com.quantumcoders.minorapp.fragments.Tab1;
import com.quantumcoders.minorapp.fragments.Tab2;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    int tabCount;
    public ViewPagerAdapter(FragmentManager fm,int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1:
                Tab2 tab2 = new Tab2();
                return tab2;
            default:
                tab1 = new Tab1();
                return tab1;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
