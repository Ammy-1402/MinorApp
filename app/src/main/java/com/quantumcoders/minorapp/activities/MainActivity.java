package com.quantumcoders.minorapp.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.adapters.ViewPagerAdapter;
import com.quantumcoders.minorapp.misc.Constants;

import static com.quantumcoders.minorapp.misc.Constants.CITIZEN;
import static com.quantumcoders.minorapp.misc.Constants.CTZ_LOGIN_SUCCESS;
import static com.quantumcoders.minorapp.misc.Constants.EMAIL_ID_KEY;
import static com.quantumcoders.minorapp.misc.Constants.NO_SUCH_USER;
import static com.quantumcoders.minorapp.misc.Constants.PWD_KEY;
import static com.quantumcoders.minorapp.misc.Constants.REQ_SIGNUP;
import static com.quantumcoders.minorapp.misc.Constants.SESSION_FILE;
import static com.quantumcoders.minorapp.misc.Constants.TYPE_KEY;
import static com.quantumcoders.minorapp.misc.Constants.USER_ID_KEY;

public class MainActivity extends AppCompatActivity implements Base {

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;

    public String email = "", password = "", type = ""; //Will be set by Fragment Tab1 or Tab2
    public boolean clickedOnce = false;   //Will be used by fragments Tab1 and Tab2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("BEGIN");

        //check session existance
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.SESSION_FILE, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (pref.contains(TYPE_KEY) && pref.contains(EMAIL_ID_KEY) && pref.contains(PWD_KEY)) {
            System.out.println("SESSION-" + pref.getString(TYPE_KEY, ""));
            System.out.println("EMAIL-" + pref.getString(EMAIL_ID_KEY, ""));
            System.out.println("PWD-" + pref.getString(PWD_KEY, ""));
            System.out.println("ID-" + pref.getString(USER_ID_KEY, ""));

            // go to logged in activity on the bases of the type
            if (pref.getString(TYPE_KEY, "").equals(CITIZEN)) {
                startActivity(new Intent(this, CitizenMainActivity.class));
            } else {
                startActivity(new Intent(this, AgentMainActivity.class));
            }

            //finish this activity
            finish();
        }


        //setup layout
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.id_viewPager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //request for permissions
            String permissions[] = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, 1);
        } else {
            //if permission is already granted
            checkLocationOnOrNot();
        }

    }


    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                checkLocationOnOrNot();
            } else {
                finish();
            }
        }
    }

    void checkLocationOnOrNot() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER) || lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //both the location providers are enabled
        } else {
            //location is not enabled. notify user to turn on the location
            new AlertDialog.Builder(this).setMessage("Please turn on location service and start the app.").setPositiveButton("OK", (d, w) -> {
                d.dismiss();
                finish();
            }).create().show();
        }
    }


    @Override
    public void onBackPressed() {
        //are you sure to quit
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> {
                    finish();
                    System.exit(0);
                })
                .setNegativeButton("No", (dialog, which) -> dialog.cancel());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




    public void loginSuccess(String[] response) {
        getApplicationContext().getSharedPreferences(SESSION_FILE, MODE_PRIVATE).edit()
                .putString(EMAIL_ID_KEY, email).putString(PWD_KEY, password)
                .putString(TYPE_KEY, type).putString(USER_ID_KEY, response[1].trim()).commit();

        longToast(response[0]);
        longToast("UserId-" + response[1]);
        clickedOnce = false;
        System.out.println("------------------->>>>>>>>>><<<<<<<<------------" + response[0]);
        //start logged in activity corresponding to 'response'
        if (response[0].equals(CTZ_LOGIN_SUCCESS)) {
            startActivity(new Intent(this, CitizenMainActivity.class));
        } else {
            startActivity(new Intent(this, AgentMainActivity.class));
        }

        //end this activity
        finish();
    }

    public void loginFailed(String[] response) {
        if (response[1].equals(NO_SUCH_USER)) longToast("No Such User");
        else longToast("Wrong password");
        clickedOnce = false;
    }

    @Override
    public void noInternet() {
        longToast("Please turn on INTERNET");
        clickedOnce = false;
    }

    public void longToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestTimeout() {
        longToast("Request timed out");
    }
}

