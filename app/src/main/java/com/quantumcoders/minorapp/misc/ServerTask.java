package com.quantumcoders.minorapp.misc;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.quantumcoders.minorapp.activities.AgentComplaintDetailsActivity;
import com.quantumcoders.minorapp.activities.AgentComplaintGroupDetailsActivity;
import com.quantumcoders.minorapp.activities.AgentMainActivity;
import com.quantumcoders.minorapp.activities.AgentSignupActivity;
import com.quantumcoders.minorapp.activities.Base;
import com.quantumcoders.minorapp.activities.CitizenComplaintDetailsActivity;
import com.quantumcoders.minorapp.activities.CitizenMainActivity;
import com.quantumcoders.minorapp.activities.CitizenSignupActivity;
import com.quantumcoders.minorapp.activities.MainActivity;
import com.quantumcoders.minorapp.activities.SendResponseActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.quantumcoders.minorapp.misc.Constants.AGT_COMPLAINT_DETAILS_OBTAINED;
import static com.quantumcoders.minorapp.misc.Constants.AGT_COMPLAINT_LIST_OBTAINED;
import static com.quantumcoders.minorapp.misc.Constants.AGT_GROUP_ID_COMPLAINT_DETAILS_OBTAINED;
import static com.quantumcoders.minorapp.misc.Constants.AGT_LOAD_COMPLAINT_DETAILS;
import static com.quantumcoders.minorapp.misc.Constants.AGT_LOAD_GROUP_ID_COMPLAINT_DETAILS;
import static com.quantumcoders.minorapp.misc.Constants.AGT_LOGIN_FAILED;
import static com.quantumcoders.minorapp.misc.Constants.AGT_LOGIN_METHOD;
import static com.quantumcoders.minorapp.misc.Constants.AGT_LOGIN_SUCCESS;
import static com.quantumcoders.minorapp.misc.Constants.AGT_PROFILE_METHOD;
import static com.quantumcoders.minorapp.misc.Constants.AGT_PROFILE_OBTAINED;
import static com.quantumcoders.minorapp.misc.Constants.AGT_PROFILE_URL;
import static com.quantumcoders.minorapp.misc.Constants.AGT_RELOAD_COMPLAINT_LIST_METHOD;
import static com.quantumcoders.minorapp.misc.Constants.AGT_SIGN_UP_FAILED;
import static com.quantumcoders.minorapp.misc.Constants.AGT_SIGN_UP_METHOD;
import static com.quantumcoders.minorapp.misc.Constants.AGT_SIGN_UP_SUCCESS;
import static com.quantumcoders.minorapp.misc.Constants.CITIZEN;
import static com.quantumcoders.minorapp.misc.Constants.COMPLAINT_IMAGE_OBTAINED;
import static com.quantumcoders.minorapp.misc.Constants.COMPLAINT_REG_FAILED;
import static com.quantumcoders.minorapp.misc.Constants.COMPLAINT_REG_SUCCESS;
import static com.quantumcoders.minorapp.misc.Constants.COMPLT_IMAGE_PREFIX;
import static com.quantumcoders.minorapp.misc.Constants.CTZ_COMPLAINT_DETAILS_OBTAINED;
import static com.quantumcoders.minorapp.misc.Constants.CTZ_COMPLAINT_LIST_OBTAINED;
import static com.quantumcoders.minorapp.misc.Constants.CTZ_LOAD_COMPLAINT_DETAILS;
import static com.quantumcoders.minorapp.misc.Constants.CTZ_LOGIN_FAILED;
import static com.quantumcoders.minorapp.misc.Constants.CTZ_LOGIN_METHOD;
import static com.quantumcoders.minorapp.misc.Constants.CTZ_LOGIN_SUCCESS;
import static com.quantumcoders.minorapp.misc.Constants.CTZ_PROFILE_METHOD;
import static com.quantumcoders.minorapp.misc.Constants.CTZ_PROFILE_OBTAINED;
import static com.quantumcoders.minorapp.misc.Constants.CTZ_PROFILE_URL;
import static com.quantumcoders.minorapp.misc.Constants.CTZ_RELOAD_COMPLAINT_LIST_METHOD;
import static com.quantumcoders.minorapp.misc.Constants.CTZ_SIGN_UP_FAILED;
import static com.quantumcoders.minorapp.misc.Constants.CTZ_SIGN_UP_METHOD;
import static com.quantumcoders.minorapp.misc.Constants.CTZ_SIGN_UP_SUCCESS;
import static com.quantumcoders.minorapp.misc.Constants.FILE_COMPLAINT_METHOD;
import static com.quantumcoders.minorapp.misc.Constants.FILE_COMPLAINT_URL;
import static com.quantumcoders.minorapp.misc.Constants.LOAD_COMPLAINT_DETAILS_AGENT_URL;
import static com.quantumcoders.minorapp.misc.Constants.LOAD_COMPLAINT_DETAILS_CITIZEN_URL;
import static com.quantumcoders.minorapp.misc.Constants.LOAD_COMPLAINT_IMAGE;
import static com.quantumcoders.minorapp.misc.Constants.LOAD_COMPLAINT_IMAGE_URL;
import static com.quantumcoders.minorapp.misc.Constants.LOAD_GROUP_ID_COMPLAINT_DETAILS_AGENT_URL;
import static com.quantumcoders.minorapp.misc.Constants.LOAD_RESPONSE_IMAGE;
import static com.quantumcoders.minorapp.misc.Constants.LOAD_RESPONSE_IMAGE_URL;
import static com.quantumcoders.minorapp.misc.Constants.NO_INTERNET;
import static com.quantumcoders.minorapp.misc.Constants.PARAM_ADDRESS;
import static com.quantumcoders.minorapp.misc.Constants.PARAM_AGT_ID;
import static com.quantumcoders.minorapp.misc.Constants.PARAM_CATEGORY;
import static com.quantumcoders.minorapp.misc.Constants.PARAM_COMPLT_ID;
import static com.quantumcoders.minorapp.misc.Constants.PARAM_CTZ_ID;
import static com.quantumcoders.minorapp.misc.Constants.PARAM_DESC;
import static com.quantumcoders.minorapp.misc.Constants.PARAM_GROUP_ID;
import static com.quantumcoders.minorapp.misc.Constants.PARAM_GRP_ID;
import static com.quantumcoders.minorapp.misc.Constants.RELOAD_COMPLAINTS_AGENT_URL;
import static com.quantumcoders.minorapp.misc.Constants.RELOAD_COMPLAINTS_CITIZEN_URL;
import static com.quantumcoders.minorapp.misc.Constants.REQUEST_TIMEOUT;
import static com.quantumcoders.minorapp.misc.Constants.RESPONSE_IMAGE_OBTAINED;
import static com.quantumcoders.minorapp.misc.Constants.RESPONSE_SENT;
import static com.quantumcoders.minorapp.misc.Constants.RESP_IMAGE_PREFIX;
import static com.quantumcoders.minorapp.misc.Constants.SEND_RESPONSE_METHOD;
import static com.quantumcoders.minorapp.misc.Constants.SEND_RESPONSE_URL;
import static com.quantumcoders.minorapp.misc.Constants.STATUS_COMPLETED;

public class ServerTask extends AsyncTask<String, String[], String[]> {
    Handler hnd = null;
    AppCompatActivity activity = null;
    File toUpload = null;

    public ServerTask(AppCompatActivity activity, Handler hnd, File file) {
        this.hnd = hnd;
        this.activity = activity;
        this.toUpload = file;
    }

    public ServerTask(AppCompatActivity activity, Handler hnd) {
        this(activity, hnd, null);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String[] doInBackground(String... param) {
        String method = param[0];
        System.out.println("doInBackground method value: " + method);
        try {
            ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if (networkInfo == null || !networkInfo.isConnected()) {
                return stringArrayOf(NO_INTERNET);
            } else if (method.equals(CTZ_SIGN_UP_METHOD)) {

                return (signUpCitizen(param));

            } else if (method.equals(AGT_SIGN_UP_METHOD)) {

                return (signUpAgent(param));

            } else if (method.equals(CTZ_LOGIN_METHOD)) {

                return loginCitizen(param);

            } else if (method.equals(AGT_LOGIN_METHOD)) {

                return loginAgent(param);

            } else if (method.equals(FILE_COMPLAINT_METHOD)) {

                return fileComplaint(param);

            } else if (method.equals(CTZ_RELOAD_COMPLAINT_LIST_METHOD)) {

                return reloadComplaintListCitizen(param);

            } else if (method.equals(AGT_RELOAD_COMPLAINT_LIST_METHOD)) {

                return reloadComplaintListAgent(param);
            } else if (method.equals(CTZ_LOAD_COMPLAINT_DETAILS)) {

                return loadComplaintDetailsCitizen(param);
            } else if (method.equals(AGT_LOAD_GROUP_ID_COMPLAINT_DETAILS)) {

                return loadGroupIdComplaintDetails(param);
            } else if (method.equals(LOAD_COMPLAINT_IMAGE)) {

                return loadComplaintImage(param);

            } else if (method.equals(LOAD_RESPONSE_IMAGE)) {

                return loadResponseImage(param);
            } else if (method.equals(AGT_LOAD_COMPLAINT_DETAILS)) {

                return loadComplaintDetailsAgent(param);
            } else if (method.equals(SEND_RESPONSE_METHOD)) {

                return sendResponse(param);
            } else if(method.equals(AGT_PROFILE_METHOD)){

                return loadAgentProfile(param);
            } else if(method.equals(CTZ_PROFILE_METHOD)){

                return loadCitizenProfile(param);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return new String[]{ex.getMessage()};
        }
        return new String[]{"DefaultReturn"};
    }


    @Override
    protected void onProgressUpdate(String[]... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(final String[] response) {
        String s = response[0];
        System.out.println("onPostExecute Response tag - " + response[0]);
//        System.out.println("Response **  -  " + s);
        if (s.equals(CTZ_SIGN_UP_SUCCESS)) {  //citizen signup success

            hnd.post(() -> ((CitizenSignupActivity) activity).signUpSuccess(response));
            //s must also include the USERID/AGENTID generated by the server

        } else if (s.equals(CTZ_SIGN_UP_FAILED)) {    //citizen signup failed

            hnd.post(() -> {
                ((CitizenSignupActivity) activity).signUpFailed(response);
            });

        } else if (s.equals(AGT_SIGN_UP_SUCCESS)) {  //agent signup success

            hnd.post(() -> ((AgentSignupActivity) activity).signUpSuccess(response));
            //s must also include the USERID/AGENTID generated by the server

        } else if (s.equals(AGT_SIGN_UP_FAILED)) {  //agent signup failed

            hnd.post(() -> ((AgentSignupActivity) activity).signUpFailed(response));

        } else if (s.equals(CTZ_LOGIN_SUCCESS) || s.equals(AGT_LOGIN_SUCCESS)) {

            hnd.post(() -> ((MainActivity) activity).loginSuccess(response));
            //s must also include the USERID/AGENTID generated by the server

        } else if (s.equals(CTZ_LOGIN_FAILED) || s.equals(AGT_LOGIN_FAILED)) {

            hnd.post(() -> ((MainActivity) activity).loginFailed(response));

        } else if (s.equals(COMPLAINT_REG_SUCCESS)) {

            hnd.post(() -> {
                ((CitizenMainActivity) activity).complaintRegSuccess();
            });
        } else if (s.equals(COMPLAINT_REG_FAILED)) {

            hnd.post(() -> {
                ((CitizenMainActivity) activity).complaintRegFailed();
            });
        } else if (s.equals(CTZ_COMPLAINT_LIST_OBTAINED)) {
            hnd.post(() -> {
                ((CitizenMainActivity) activity).complaintListObtainedCitizen(response);
            });

        } else if (s.equals(AGT_COMPLAINT_LIST_OBTAINED)) {
            hnd.post(() -> {
                ((AgentMainActivity) activity).complaintListObtainedAgent(response);
            });

        } else if (s.equals(CTZ_COMPLAINT_DETAILS_OBTAINED)) {

            ((CitizenComplaintDetailsActivity) activity).onLoadComplaintDetails(response);

        } else if (s.equals(COMPLAINT_IMAGE_OBTAINED)) {
            if (response[1] == CITIZEN)
                ((CitizenComplaintDetailsActivity) activity).onLoadComplaintImage();
            else ((AgentComplaintDetailsActivity) activity).onLoadComplaintImage();

        } else if (s.equals(AGT_GROUP_ID_COMPLAINT_DETAILS_OBTAINED)) {

            hnd.post(() -> {
                ((AgentComplaintGroupDetailsActivity) activity).onLoadGroupIdComplaintDetails(response);
            });

        } else if (s.equals(RESPONSE_IMAGE_OBTAINED)) {

            hnd.post(() -> {
                ((CitizenComplaintDetailsActivity) activity).onLoadResponseImage();
            });

        } else if (s.equals(AGT_COMPLAINT_DETAILS_OBTAINED)) {

            hnd.post(() -> {
                ((AgentComplaintDetailsActivity) activity).onLoadComplaintDetails(response);
            });
        } else if (s.equals(RESPONSE_SENT)) {

            System.out.println("Response sent!!!!!!!!!!!!!");
            hnd.post(()->{
                ((SendResponseActivity)activity).onResponseSent();
            });
        } else if(s.equals(AGT_PROFILE_OBTAINED)){

            hnd.post(()->{
                ((AgentMainActivity)activity).profileDataObtained(response);
            });

        } else if(s.equals(CTZ_PROFILE_OBTAINED)){

            hnd.post(()->{
                ((CitizenMainActivity)activity).profileDataObtained(response);
            });

        } else if (s.equals(NO_INTERNET)) {
            ((Base) activity).noInternet();
        } else if (s.contains(REQUEST_TIMEOUT)) {
            ((Base) activity).onRequestTimeout();
        } else {
            System.out.println(s);
        }
    }


    private String[] signUpCitizen(String... param) throws IOException {

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
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
        String str = "fname=" + URLEncoder.encode(fname, "UTF-8") +
                "&lname=" + URLEncoder.encode(lname, "UTF-8") +
                "&phone=" + URLEncoder.encode(phone, "UTF-8") +
                "&email=" + URLEncoder.encode(email, "UTF-8") +
                "&password=" + URLEncoder.encode(password, "UTF-8");

        writer.write(str);
        writer.flush();


        //read result
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response[] = new String[2];
        response[0] = br.readLine();
        if (response[0].equals(CTZ_SIGN_UP_SUCCESS)) response[1] = br.readLine();

        //return result
        return response;

    }

    private String[] signUpAgent(String... param) throws IOException {
        System.out.println("Agent trying to sign up");
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
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
        String str = "fname=" + URLEncoder.encode(fname, "UTF-8") +
                "&lname=" + URLEncoder.encode(lname, "UTF-8") +
                "&phone=" + URLEncoder.encode(phone, "UTF-8") +
                "&email=" + URLEncoder.encode(email, "UTF-8") +
                "&password=" + URLEncoder.encode(password, "UTF-8");

        writer.write(str);
        writer.flush();


        //read result
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response[] = new String[2];
        response[0] = br.readLine();

        //read userid
        if (response[0].equals(AGT_SIGN_UP_SUCCESS)) response[1] = br.readLine();


        //return result
        return response;

    }

    private String[] loginCitizen(String... param) throws IOException {
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
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
        String str = "email=" + URLEncoder.encode(email, "UTF-8") +
                "&password=" + URLEncoder.encode(password, "UTF-8");

        writer.write(str);
        writer.flush();


        //read result
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String[] response = new String[3];
        response[0] = br.readLine();
        response[1] = br.readLine();
        if (response[0].equals(CTZ_LOGIN_SUCCESS)) response[2] = br.readLine();


        //return result
        return response;

    }

    private String[] loginAgent(String... param) throws IOException {
        //setup connection
        URL url = new URL(Constants.LOGIN_URL_AGENT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        //get parameters
        String email = param[1];
        String password = param[2];

        //send parameters
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
        String str = "email=" + URLEncoder.encode(email, "UTF-8") +
                "&password=" + URLEncoder.encode(password, "UTF-8");

        writer.write(str);
        writer.flush();

        //read result
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String[] response = new String[3];
        response[0] = br.readLine();
        response[1] = br.readLine();
        if (response[0].equals(AGT_LOGIN_SUCCESS)) response[2] = br.readLine();


        //return result
        return response;
    }

    private String[] fileComplaint(String... param) throws IOException {
        final ProgressDialog[] dialog = {null};
        hnd.post(() -> {
            dialog[0] = ProgressDialog.show(activity, "Registering complaint", "Please wait for the photo to upload", true, false);
        });

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("category", param[1])
                .addFormDataPart("desc", param[2])
                .addFormDataPart("lat", param[3])
                .addFormDataPart("lng", param[4])
                .addFormDataPart("address", param[5])
                .addFormDataPart("userid", param[6])
                .addFormDataPart("image", toUpload.getName(), RequestBody.create(MediaType.parse("image/jpeg"), toUpload))
                .build();

        Request req = new Request.Builder().url(FILE_COMPLAINT_URL).post(requestBody).build();

        Response response = null;

        try {

            response = client.newCall(req).execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

        hnd.post(() -> {
            dialog[0].dismiss();
        });

        if (response != null) return stringArrayOf(response.body().string());
        else return stringArrayOf(REQUEST_TIMEOUT);

    }

    private String[] reloadComplaintListCitizen(String... param) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userid", param[1])
                .build();

        Request request = new Request.Builder().url(RELOAD_COMPLAINTS_CITIZEN_URL).post(requestBody).build();

        Response response = client.newCall(request).execute();
        String body = response.body().string();
        Scanner sc = new Scanner(body);
//        System.out.println("Reload complt list CITIZEN response:-"+body);

        int n = sc.nextInt();
//        System.out.println("citizen num complt = " + n);
        sc.nextLine();
        String[] ret = new String[5 * n + 1];
        ret[0] = Constants.CTZ_COMPLAINT_LIST_OBTAINED;

        for (int i = 1; i < 5 * n + 1; i++) {
            ret[i] = sc.nextLine();
            System.out.println(ret[i]);
        }

        return ret;
    }

    private String[] reloadComplaintListAgent(String... param) throws IOException {
        System.out.println("--- > " + param[1]);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("Agent_d", param[1])
                .build();

        Request request = new Request.Builder().url(RELOAD_COMPLAINTS_AGENT_URL).post(requestBody).build();

        Response response = client.newCall(request).execute();
        String body = response.body().string();
        Scanner sc = new Scanner(body);
        //System.out.println("--- <1> " +sc.nextLine());
        //System.out.println("--- <2> " +sc.nextLine());
        //System.out.println("--- <3> " +sc.nextLine());
        int n = sc.nextInt();
        System.out.println("agent num complt = " + n);
        sc.nextLine();
        String[] ret = new String[5 * n + 1];
        ret[0] = Constants.AGT_COMPLAINT_LIST_OBTAINED;

        for (int i = 1; i < 5 * n + 1; i++) {
            ret[i] = sc.nextLine();
            System.out.println(ret[i]);
        }

        return ret;
    }

    private String[] loadComplaintDetailsCitizen(String... param) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart(PARAM_COMPLT_ID, param[1]).build();
        Request request = new Request.Builder().url(LOAD_COMPLAINT_DETAILS_CITIZEN_URL).post(requestBody).build();
        Response response = client.newCall(request).execute();

        BufferedReader br = new BufferedReader(response.body().charStream());
        String result[] = new String[9];
        result[0] = CTZ_COMPLAINT_DETAILS_OBTAINED;

        //get other parameters
        for (int i = 1; i <= 7; i++) result[i] = br.readLine();

        if (result[5].equals(STATUS_COMPLETED)) result[8] = br.readLine();


        return result;

    }


    private String[] loadGroupIdComplaintDetails(String... param) throws IOException {
        System.out.println("loadGroupIdComplaintDetails param[1] value : " + param[1]);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart(PARAM_GROUP_ID, param[1]).build();
        Request request = new Request.Builder().url(LOAD_GROUP_ID_COMPLAINT_DETAILS_AGENT_URL).post(requestBody).build();
        Response response = client.newCall(request).execute();
        String body = response.body().string();

        System.out.println(body);
        Scanner sc = new Scanner(body);
        int n = sc.nextInt();
        System.out.println("agent groupId complt num = " + n);
        sc.nextLine();

        String[] ret = new String[3 * n + 1];
        ret[0] = AGT_GROUP_ID_COMPLAINT_DETAILS_OBTAINED;

        for (int i = 1; i < 3 * n + 1; i++) {
            ret[i] = sc.nextLine();
            System.out.println("--here -- " + ret[i]);
        }

        return ret;
    }

    public String[] loadComplaintImage(String... param) throws IOException {
        System.out.println("Load complaint image " + param[1]);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart(PARAM_COMPLT_ID, param[1]).build();

        Request request = new Request.Builder().url(LOAD_COMPLAINT_IMAGE_URL).post(requestBody).build();
        Response response = client.newCall(request).execute();

        InputStream is = response.body().byteStream();
        BufferedInputStream bis = new BufferedInputStream(is);

        File storage = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = new File(storage.getAbsolutePath() + "/" + COMPLT_IMAGE_PREFIX + param[1] + ".jpg");
        System.out.println("Writing image to file " + imageFile.getName());

        FileOutputStream fos = new FileOutputStream(imageFile);

        byte bytearr[] = new byte[100];
        long bcount = 0;

        try {
            while (true) {
                int read = bis.read(bytearr);
                if (read <= 0) break;
                bcount += read;
                fos.write(bytearr, 0, read);
            }
        } catch (Exception ex) {
        }

        System.out.println("Written " + bcount + " bytes");

        fos.flush();
        fos.close();
        return stringArrayOf(COMPLAINT_IMAGE_OBTAINED, param[2]);
    }

    public String[] loadResponseImage(String... param) throws IOException {
        System.out.println("Load response image " + param[1]);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart(PARAM_COMPLT_ID, param[1]).build();

        Request request = new Request.Builder().url(LOAD_RESPONSE_IMAGE_URL).post(requestBody).build();
        Response response = client.newCall(request).execute();

        InputStream is = response.body().byteStream();
        BufferedInputStream bis = new BufferedInputStream(is);

        File storage = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = new File(storage.getAbsolutePath() + "/" + RESP_IMAGE_PREFIX + param[1] + ".jpg");
        System.out.println("Writing image to file " + imageFile.getName());

        FileOutputStream fos = new FileOutputStream(imageFile);

        byte bytearr[] = new byte[100];
        long bcount = 0;

        try {
            while (true) {
                int read = bis.read(bytearr);
                if (read <= 0) break;
                bcount += read;
                fos.write(bytearr, 0, read);
            }
        } catch (Exception ex) {
        }

        System.out.println("Written " + bcount + " bytes");

        fos.flush();
        fos.close();
        return stringArrayOf(RESPONSE_IMAGE_OBTAINED);
    }

    public String[] loadComplaintDetailsAgent(String... param) throws IOException {

        System.out.println("Load complaint details agent");
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart(PARAM_COMPLT_ID, param[1]).build();
        Request request = new Request.Builder().url(LOAD_COMPLAINT_DETAILS_AGENT_URL).post(requestBody).build();
        Response response = client.newCall(request).execute();

        BufferedReader br = new BufferedReader(response.body().charStream());
        String result[] = new String[6];
        result[0] = AGT_COMPLAINT_DETAILS_OBTAINED;

        //get other parameters
        for (int i = 1; i <= 5; i++) {
            result[i] = br.readLine();
        }
        return result;
    }

    public String[] sendResponse(String... param) throws IOException {
        final ProgressDialog[] dialog = {null};
        hnd.post(() -> {
            dialog[0] = ProgressDialog.show(activity, "Sending Response", "Please wait for the photo to upload", true, false);
        });


        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart(PARAM_GRP_ID, param[1])
                .addFormDataPart(PARAM_CATEGORY, param[2])
                .addFormDataPart(PARAM_DESC, param[3])
                .addFormDataPart("image", toUpload.getName(), RequestBody.create(MediaType.parse("image/jpeg"), toUpload))
                .addFormDataPart("lat", param[4])
                .addFormDataPart("lng", param[5])
                .addFormDataPart(PARAM_ADDRESS, param[6])
                .addFormDataPart("Agent_id", param[7])
                .build();

//        for(int i=0;i<7;i++) System.out.println("DEBUG RESP - " + param[i]);

        Request request = new Request.Builder().url(SEND_RESPONSE_URL).addHeader("Connection", "Keep-Alive").post(requestBody).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }


        hnd.post(() -> {
            dialog[0].dismiss();
        });

        if (response != null) return stringArrayOf(response.body().string());
        else return stringArrayOf(REQUEST_TIMEOUT);
    }

    public String[] loadAgentProfile(String...param) throws IOException{
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart(PARAM_AGT_ID,param[1]).build();

        Request request = new Request.Builder().url(AGT_PROFILE_URL).post(requestBody).build();
        Response rsp = client.newCall(request).execute();

        String[] retdata = new String[4];
        retdata[0] = AGT_PROFILE_OBTAINED;

        Scanner sc = new Scanner(rsp.body().string());
        retdata[1] = sc.nextLine();
        retdata[2] = sc.nextLine();
        retdata[3] = sc.nextLine();

        return retdata;

    }

    public String[] loadCitizenProfile(String...param) throws IOException{
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart(PARAM_CTZ_ID,param[1]).build();

        Request request = new Request.Builder().url(CTZ_PROFILE_URL).post(requestBody).build();
        Response rsp = client.newCall(request).execute();

        String[] retdata = new String[4];
        retdata[0] = CTZ_PROFILE_OBTAINED;

        String rstr = rsp.body().string();
        Scanner sc = new Scanner(rstr);
        retdata[1] = sc.nextLine();
        retdata[2] = sc.nextLine();
        retdata[3] = sc.nextLine();

        return retdata;

    }


    public String[] stringArrayOf(String... str) {
        return str;
    }


}
