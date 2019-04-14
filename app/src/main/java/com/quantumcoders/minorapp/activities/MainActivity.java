package com.quantumcoders.minorapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.Constants;
import com.quantumcoders.minorapp.adapters.ViewPagerAdapter;

import static com.quantumcoders.minorapp.misc.Constants.*;

public class MainActivity extends AppCompatActivity implements Base {

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;

    public String email="",password="",type=""; //Will be set by Fragment Tab1 or Tab2
    public boolean clickedOnce=false;   //Will be used by fragments Tab1 and Tab2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("BEGIN");

        //check session existance
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.SESSION_FILE,MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(pref.contains(TYPE_KEY) && pref.contains(EMAIL_ID_KEY) && pref.contains(PWD_KEY)){
            System.out.println("SESSION-"+pref.getString(TYPE_KEY,""));
            System.out.println("EMAIL-"+pref.getString(EMAIL_ID_KEY,""));
            System.out.println("PWD-"+pref.getString(PWD_KEY,""));
            System.out.println("ID-"+pref.getString(USER_ID_KEY,""));

            // go to logged in activity on the bases of the type
            if(pref.getString(TYPE_KEY,"").equals(CITIZEN)){
                startActivityForResult(new Intent(this,CitizenMainActivity.class),1);
            } else {
                startActivityForResult(new Intent(this,AgentMainActivity.class),1);
            }

            //finish this activity
            finish();
        }


        //setup layout
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.id_viewPager);

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
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1){     //whether sign up was successful so as to finish this activity or not
            if(resultCode== Activity.RESULT_OK){
                System.out.println("Finishing MainActivity");
                finish();
            }
        }
    }

    public void loginSuccess(String[] response){
        getApplicationContext().getSharedPreferences(SESSION_FILE,MODE_PRIVATE).edit()
                .putString(EMAIL_ID_KEY,email).putString(PWD_KEY,password)
                .putString(TYPE_KEY,type).putString(USER_ID_KEY,response[1].trim()).commit();

        longToast(response[0]);
        clickedOnce=false;
        System.out.println("------------------->>>>>>>>>><<<<<<<<------------"+response[0]);
        //start logged in activity corresponding to 'response'
        if(response[0].equals(CTZ_LOGIN_SUCCESS)){
            startActivity(new Intent(this,CitizenMainActivity.class));
        } else {
            startActivity(new Intent(this,AgentMainActivity.class));
        }

        //end this activity
        finish();
    }

    public void loginFailed(String[] response){
        if(response[1].equals(NO_SUCH_USER))longToast("No Such User");
        else longToast("Wrong password");
        clickedOnce=false;
    }

    @Override
    public void noInternet(){
        longToast("Please turn on INTERNET");
        clickedOnce=false;
    }

    public void longToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }


}

