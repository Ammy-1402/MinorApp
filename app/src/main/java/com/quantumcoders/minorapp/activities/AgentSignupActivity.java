package com.quantumcoders.minorapp.activities;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.Constants;
import com.quantumcoders.minorapp.misc.ServerWorker;
import static com.quantumcoders.minorapp.misc.Constants.*;

public class AgentSignupActivity extends AppCompatActivity {

    String email="",password="";

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

    public void btnSignupClicked(View view){
        String fname = ((TextInputEditText)findViewById(R.id.fname)).getText().toString();
        String lname = ((TextInputEditText)findViewById(R.id.lname)).getText().toString();
        String phone = ((TextInputEditText)findViewById(R.id.phone)).getText().toString();
        String email = ((TextInputEditText)findViewById(R.id.email)).getText().toString();
        String password = ((TextInputEditText)findViewById(R.id.password)).getText().toString();
        String cpassword = ((TextInputEditText)findViewById(R.id.cpassword)).getText().toString();

        if(!fname.matches(Constants.NAME_REGEX))longToast("Invalid first name");
        else if(!lname.matches(Constants.NAME_REGEX))longToast("Invalid last name");
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())longToast("Invalid email address");
        else if(!Patterns.PHONE.matcher(phone).matches())longToast("Invalid Phone number");
        else if(!password.matches(Constants.PWD_REGEX)){
            longToast("Invalid/Small Password");
        }
        else if(!password.equals(cpassword))longToast("Passwords do not match");

        else {
            this.email=email;
            this.password=password;
            ServerWorker.signUpAgent(AgentSignupActivity.this,fname,lname,phone,email,password);
        }

    }

    public void longToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    public void signUpSuccess(String response){
        longToast("Welcome Agent");

        //save session code
        SharedPreferences pref = getApplicationContext().getSharedPreferences(SESSION_FILE,MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(TYPE_KEY,CITIZEN).putString(EMAIL_ID_KEY,email).putString(PWD_KEY,password).commit();

        //code to start AgentActivity (auto login)
        //...
    }

    public void signUpFailed(String response){
        longToast("User already exists.");
        // ...
    }

}
