package com.quantumcoders.minorapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.Constants;
import com.quantumcoders.minorapp.misc.ServerWorker;

import static com.quantumcoders.minorapp.misc.Constants.CITIZEN;
import static com.quantumcoders.minorapp.misc.Constants.EMAIL_ID_KEY;
import static com.quantumcoders.minorapp.misc.Constants.PWD_KEY;
import static com.quantumcoders.minorapp.misc.Constants.SESSION_FILE;
import static com.quantumcoders.minorapp.misc.Constants.TYPE_KEY;
import static com.quantumcoders.minorapp.misc.Constants.USER_ID_KEY;
import static com.quantumcoders.minorapp.misc.Constants.VAL_NO;
import static com.quantumcoders.minorapp.misc.Constants.VERIFIED_KEY;

public class CitizenSignupActivity extends AppCompatActivity implements Base {

    String email = "", password = "";
    boolean clickedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_signup);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void btnSignupClicked(View view) {
        if (clickedOnce) return;


        String fname = ((TextInputEditText) findViewById(R.id.fname)).getText().toString();
        String lname = ((TextInputEditText) findViewById(R.id.lname)).getText().toString();
        String phone = ((TextInputEditText) findViewById(R.id.phone)).getText().toString();
        String email = ((TextInputEditText) findViewById(R.id.email)).getText().toString();
        String password = ((TextInputEditText) findViewById(R.id.password)).getText().toString();
        String cpassword = ((TextInputEditText) findViewById(R.id.cpassword)).getText().toString();

        if (!fname.matches(Constants.NAME_REGEX)) longToast("Invalid first name");
        else if (!lname.matches(Constants.NAME_REGEX)) longToast("Invalid last name");
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            longToast("Invalid email address");
        else if (!Patterns.PHONE.matcher(phone).matches()) longToast("Invalid Phone number");
        else if (!password.matches(Constants.PWD_REGEX)) {
            longToast("Invalid/Small Password");
        } else if (!password.equals(cpassword)) longToast("Passwords do not match");
        else {
            //else if not already clicked
            clickedOnce = true;
            this.email = email;
            this.password = password;
            ServerWorker.signUpCitizen(CitizenSignupActivity.this, fname, lname, phone, email, password);
        }

    }

    public void longToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void signUpSuccess(String[] response) {
        System.out.println("signUpSuccess - userid = " + response[1]);

        //save session code
        SharedPreferences pref = getApplicationContext().getSharedPreferences(SESSION_FILE, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(TYPE_KEY, CITIZEN).putString(EMAIL_ID_KEY, email).putString(PWD_KEY, password)
                .putString(USER_ID_KEY, response[1].trim()).putString(VERIFIED_KEY,VAL_NO).commit();


        //send signal to stop the MainActivity
        setResult(Activity.RESULT_OK);

        //show a message
        new AlertDialog.Builder(this).setTitle("Verify Email ID")
                .setMessage("Please check your inbox for a verification mail to verify your email id.")
                .setPositiveButton("Ok",(d,w)->{
                    d.dismiss();
                    //code to start CitizenActivity (auto login)
                    CitizenSignupActivity.this.finish();
                    startActivity(new Intent(this, CitizenMainActivity.class));
                }).create().show();

        clickedOnce = false;
    }

    public void signUpFailed(String[] response) {
        longToast("Sign up failed. User exists.");
        clickedOnce = false;
    }

    @Override
    public void noInternet() {
        longToast("Please turn on INTERNET");
    }

    @Override
    public void onRequestTimeout() {
        longToast("Request timed out");
    }
}
