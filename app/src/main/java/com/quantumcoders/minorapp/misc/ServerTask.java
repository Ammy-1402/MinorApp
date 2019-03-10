package com.quantumcoders.minorapp.misc;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.quantumcoders.minorapp.activities.AgentSignupActivity;
import com.quantumcoders.minorapp.activities.CitizenSignupActivity;
import com.quantumcoders.minorapp.activities.MainActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import static com.quantumcoders.minorapp.misc.Constants.*;

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
        String result="ELSE";   //"ELSE" used for debugging purposes
        try{
            if(method.equals(CTZ_SIGN_UP_METHOD)){

                return signUpCitizen(param);

            } else if(method.equals(AGT_SIGN_UP_METHOD)){

                return signUpAgent(param);

            } else if(method.equals(CTZ_LOGIN_METHOD)){

                return loginCitizen(param);

            } else if(method.equals(AGT_LOGIN_METHOD)){

                return loginAgent(param);

            }
        } catch(IOException ex){
            ex.printStackTrace();
        }
        return result;  //return here used for debugging purposes.

    }

    @Override
    protected void onProgressUpdate(String...values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(final String s) {
        System.out.println("Response - " + s);
        if(s.equals(CTZ_SIGN_UP_SUCCESS)){  //citizen signup success

            hnd.post(() -> ((CitizenSignupActivity)activity).signUpSuccess(s));

        } else if(s.equals(CTZ_SIGN_UP_FAILED)){    //citizen signup failed

            hnd.post(()->{((CitizenSignupActivity)activity).signUpFailed(s);});

        } else if(s.equals(AGT_SIGN_UP_SUCCESS)){  //agent signup success

            hnd.post(() -> ((AgentSignupActivity)activity).signUpSuccess(s));

        } else if(s.equals(AGT_SIGN_UP_FAILED)){  //agent signup failed

            hnd.post(() -> ((AgentSignupActivity)activity).signUpFailed(s));

        } else if(s.equals(CTZ_LOGIN_SUCCESS) || s.equals(AGT_LOGIN_SUCCESS)){

            hnd.post(()-> ((MainActivity)activity).loginSuccess(s));

        } else if(s.equals(CTZ_LOGIN_FAILED) || s.equals(AGT_LOGIN_FAILED)){

            hnd.post(()-> ((MainActivity)activity).loginFailed(s));

        }
    }



    private String signUpCitizen(String...param) throws IOException {

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

    }

    private String signUpAgent(String...param) throws IOException {
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

    }

    private String loginCitizen(String...param) throws IOException{
        System.out.println(param[0]);
        //setup connection
        URL url = new URL(Constants.LOGIN_URL_CITIZEN);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        //get parameters
        String email = param[1];
        String password = param[2];

        //send parameters
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
        String str = "email="+URLEncoder.encode(email,"UTF-8")+
                "&password="+URLEncoder.encode(password,"UTF-8");

        writer.write(str);


        //read result
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response=br.readLine();


        //return result
        return response;

    }

    private String loginAgent(String...param) throws IOException{
        //setup connection
        URL url = new URL(Constants.LOGIN_URL_AGENT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        //get parameters
        String email = param[1];
        String password = param[2];

        //send parameters
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
        String str = "email="+URLEncoder.encode(email,"UTF-8")+
                "&password="+URLEncoder.encode(password,"UTF-8");

        writer.write(str);


        //read result
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response=br.readLine();


        //return result
        return response;
    }


}
