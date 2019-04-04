package com.quantumcoders.minorapp.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.adapters.ListItemComplaint;
import com.quantumcoders.minorapp.adapters.MyRecyclerAdapter;
import com.quantumcoders.minorapp.fragments.CitizenTab1;
import com.quantumcoders.minorapp.adapters.CitizenViewPagerAdapter;
import com.quantumcoders.minorapp.fragments.CitizenTab2;
import com.quantumcoders.minorapp.fragments.CitizenTab3;
import com.quantumcoders.minorapp.misc.Constants;
import com.quantumcoders.minorapp.misc.FetchAddressIntentService;
import com.quantumcoders.minorapp.misc.ServerWorker;

import java.util.ArrayList;

public class CitizenMainActivity extends AppCompatActivity implements LocationListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    CitizenViewPagerAdapter adapter;
    LocationManager lm=null;
    boolean locationPermitted=false;

    float latsum=0.0f,lngsum=0.0f;
    public int count=0;
    public float avglat=0.0f,avglng=0.0f;

    int TIME_TO_UPDATE=1000;
    int DIST_TO_UPDATE=0;

    CitizenTab1 tab1 = null;
    CitizenTab2 tab2 = null;
    CitizenTab3 tab3 = null;




    ResultReceiver receiver = new ResultReceiver(new Handler()){
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if(resultCode==1){
                System.out.println("address found = " + resultData.getString("address"));
                tab1.updateLocation(avglat,avglng,resultData.getString("address"));
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_main);
        tabLayout = findViewById(R.id.citizenTabLayout);
        viewPager = findViewById(R.id.id_citizenViewPager);

        adapter = new CitizenViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==1){   //view complaint list fragment/page
                    String userid = getApplicationContext().getSharedPreferences(Constants.SESSION_FILE,MODE_PRIVATE).getString(Constants.USER_ID_KEY,"");
                    ServerWorker.reloadComplaintsListCitizen(CitizenMainActivity.this,userid);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //request for permissions
            String permissions[] = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(this,permissions,1);
        } else {
            if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, TIME_TO_UPDATE, DIST_TO_UPDATE, this);
                locationPermitted=true;
                System.out.println("Location - Using GPS Provider");
            } else if(lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, TIME_TO_UPDATE, DIST_TO_UPDATE, this);
                locationPermitted=true;
                System.out.println("Location - Using Network Provider");
            } else {
                System.out.println("Location - No provider enabled");
            }
        }
    }



    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, TIME_TO_UPDATE, DIST_TO_UPDATE, this);
                    locationPermitted=true;
                    System.out.println("Location - Using GPS Provider");
                } else if(lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, TIME_TO_UPDATE, DIST_TO_UPDATE, this);
                    locationPermitted=true;
                    System.out.println("Location - Using Network Provider");
                } else {
                    System.out.println("Location - No provider enabled");
                }
            }
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if(fragment instanceof CitizenTab1){
            tab1 = (CitizenTab1) fragment;
        } else if(fragment instanceof CitizenTab2){
            tab2= (CitizenTab2) fragment;
        } else {
            tab3 = (CitizenTab3) fragment;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if(count!=0 && count<=10){   //skipping first location, because i think that first location is very inaccurate
            System.out.println("Location changed " + count);
            latsum+=location.getLatitude();
            lngsum+=location.getLongitude();
            avglat=latsum/count;
            avglng=lngsum/count;

            if(count==1 || count==10)FetchAddressIntentService.fetch(this,avglat,avglng,receiver);
        }
        count++;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        System.out.println("status changed " + provider + " | " + status);
    }

    @Override
    public void onProviderEnabled(String provider) {
        System.out.println("enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        System.out.println("disabled");
    }

    public void longToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    public void noInternet(){
        longToast("Please turn on INTERNET");
    }

    public void complaintListObtainedCitizen(String...data){
        ArrayList<ListItemComplaint> list = new ArrayList<>();
        for(int i=1;i<data.length;i+=5){
            list.add(new ListItemComplaint(data[i],data[i+1],data[i+2],data[i+3],data[i+4]));
        }
        tab2.listItemComplaint = list;
        tab2.adapter = new MyRecyclerAdapter(list, this);
        tab2.recyclerView.setAdapter(tab2.adapter);
        longToast("CITIZEN COMPLAINT LIST UPDATED");
    }

}
