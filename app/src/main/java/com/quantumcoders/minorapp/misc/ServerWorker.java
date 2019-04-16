package com.quantumcoders.minorapp.misc;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.webkit.HttpAuthHandler;

import com.quantumcoders.minorapp.activities.AgentComplaintGroupDetailsActivity;
import com.quantumcoders.minorapp.activities.CitizenComplaintDetailsActivity;

import java.io.File;


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

    public static void fileComplaint(AppCompatActivity activity, String category, String desc, File image,double lat,double lng, String address,String userid){
        ServerTask tsk = new ServerTask(activity,new Handler(),image);
        tsk.execute(Constants.FILE_COMPLAINT_METHOD,category,desc,String.valueOf(lat),String.valueOf(lng),address,userid);
    }


    public static void reloadComplaintsListCitizen(AppCompatActivity activity,String userid) {
        ServerTask tsk = new ServerTask(activity,new Handler());
        tsk.execute(Constants.CTZ_RELOAD_COMPLAINT_LIST_METHOD,userid);
    }

    public static void reloadComplaintsListAgent(AppCompatActivity activity,String agentid){
        ServerTask tsk = new ServerTask(activity,new Handler());
        tsk.execute(Constants.AGT_RELOAD_COMPLAINT_LIST_METHOD,agentid);
    }


    public static void loadComplaintDetails(AppCompatActivity activity, String complaint_id){
        ServerTask tsk = new ServerTask(activity,new Handler());
        tsk.execute(Constants.CTZ_LOAD_COMPLAINT_DETAILS,complaint_id);
    }

    //this is for every Group details (group complaint data)
    public static void loadGroupIdDetails(AppCompatActivity activity, String groupid) {
        ServerTask tsk = new ServerTask(activity, new Handler());
        tsk.execute(Constants.AGT_LOAD_GROUP_ID_COMPLAINT_DETAILS,groupid);
    }

    public static void loadComplaintImage(AppCompatActivity activity, String complaint_id,String requester) {
        ServerTask tsk = new ServerTask(activity,new Handler());
        tsk.execute(Constants.LOAD_COMPLAINT_IMAGE,complaint_id,requester);
    }

    public static void loadResponseImage(AppCompatActivity activity, String complaint_id) {
        ServerTask tsk = new ServerTask(activity,new Handler());
        tsk.execute(Constants.LOAD_RESPONSE_IMAGE,complaint_id);
    }

    public static void loadComplaintDetailsAgent(AppCompatActivity activity, String complaint_id){
        ServerTask tsk = new ServerTask(activity,new Handler());
        tsk.execute(Constants.AGT_LOAD_COMPLAINT_DETAILS,complaint_id);
    }

    public static void sendResponse(AppCompatActivity activity,String grpid,String category,String desc,File image,double lat,double lng,String address,String agentid){
        ServerTask tsk = new ServerTask(activity,new Handler(),image);
        tsk.execute(Constants.SEND_RESPONSE_METHOD,grpid,category,desc,String.valueOf(lat),String.valueOf(lng),address,agentid);
    }
}
