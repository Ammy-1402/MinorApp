package com.quantumcoders.minorapp.misc;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


/* This class is used to deal with communication with the server. It provides an abstraction
 * over URLConnection to make the code more Readable. Okay Bro ?
 * */

public class ServerWorker {
    /*THESE METHODS MUST BE INVOKED FROM UI THREAD FOR CORRECT HANDLER INITIALIZATION*/


    public static void signUpCitizen(AppCompatActivity activity, String fname, String lname, String phone, String email, String password){
        ServerTask tsk = new ServerTask(activity,new Handler());
        tsk.execute(Constants.CTZ_SIGN_UP_METHOD,fname,lname,phone,email,password);
    }

    public static void signUpAgent(AppCompatActivity activity, String fname, String lname, String phone, String email, String password){
        ServerTask tsk = new ServerTask(activity,new Handler());
        tsk.execute(Constants.AGT_SIGN_UP_METHOD,fname,lname,phone,email,password);
    }

    public static void loginCitizen(AppCompatActivity activity,String email,String password){
        ServerTask tsk = new ServerTask(activity,new Handler());
        tsk.execute(Constants.CTZ_LOGIN_METHOD,email,password);
    }

    public static void loginAgent(AppCompatActivity activity,String email,String password){
        ServerTask tsk = new ServerTask(activity,new Handler());
        tsk.execute(Constants.AGT_LOGIN_METHOD,email,password);
    }


}
