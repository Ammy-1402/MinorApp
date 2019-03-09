package com.quantumcoders.minorapp.misc;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.quantumcoders.minorapp.activities.CitizenSignupActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ServerTask extends AsyncTask<String,String,String> {
    Handler hnd=null;
    AppCompatActivity activity=null;

    public ServerTask(AppCompatActivity activity,Handler hnd){
        this.hnd = hnd;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... param) {
        String method=param[0];
        String result="else";
        if(method.equals(Constants.CTZ_SIGN_UP_METHOD)){
            try {

                //setup connection
                URL url = new URL(Constants.SIGNUP_URL_CITIZEN);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                //get parameters
                String fname = param[1];
                String lname = param[2];
                String phone = param[3];
                String email = param[4];
                String password = param[5];

                //send parameters
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
                String str = "fname="+URLEncoder.encode(fname,"UTF-8")+
                        "&lname="+URLEncoder.encode(lname,"UTF-8")+
                        "&phone"+URLEncoder.encode(phone,"UTF-8")+
                        "&email"+URLEncoder.encode(email,"UTF-8")+
                        "&password"+URLEncoder.encode(password,"UTF-8");

                writer.write(str);


                //read result
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String response=br.readLine();

                //return result
                return response;


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(method.equals(Constants.AGT_SIGN_UP_METHOD)){
            try {

                //setup connection
                URL url = new URL(Constants.SIGNUP_URL_AGENT);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                //get parameters
                String fname = param[1];
                String lname = param[2];
                String phone = param[3];
                String email = param[4];
                String password = param[5];

                //send parameters
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
                String str = "fname="+URLEncoder.encode(fname,"UTF-8")+
                        "&lname="+URLEncoder.encode(lname,"UTF-8")+
                        "&phone"+URLEncoder.encode(phone,"UTF-8")+
                        "&email"+URLEncoder.encode(email,"UTF-8")+
                        "&password"+URLEncoder.encode(password,"UTF-8");

                writer.write(str);


                //read result
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String response=br.readLine();


                //return result
                return response;


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        if(s.equalsIgnoreCase("success")){  //for testing
            hnd.post(new Runnable() {
                @Override
                public void run() {
                    if(activity instanceof CitizenSignupActivity){
                        System.out.println("WORKED FINE CITIZEN SIGN UP");
                        ((CitizenSignupActivity)activity).longToast("WORKED FINE");
                    }
                }
            });
        } else {
            System.out.println("else " + s);
        }
    }
}
