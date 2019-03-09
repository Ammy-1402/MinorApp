package com.quantumcoders.minorapp.misc;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/* This class is used to deal with communication with the server. It provides an abstraction
 * over URLConnection to make the code more Readable. Okay Bro ?
 * */

public class ServerWorker {

    public static void signUpCitizen(AppCompatActivity activity, String fname, String lname, String phone, String email, String password){
        ServerTask tsk = new ServerTask(activity,new Handler());
        tsk.execute(Constants.CTZ_SIGN_UP_METHOD,fname,lname,phone,email,password);
    }

    public static void signUpAgent(AppCompatActivity activity, String fname, String lname, String phone, String email, String password){
        ServerTask tsk = new ServerTask(activity,new Handler());
        tsk.execute(Constants.AGT_SIGN_UP_METHOD,fname,lname,phone,email,password);
    }


}
