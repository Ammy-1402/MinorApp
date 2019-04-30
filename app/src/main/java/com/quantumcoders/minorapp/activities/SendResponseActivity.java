package com.quantumcoders.minorapp.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.Constants;
import com.quantumcoders.minorapp.misc.FetchAddressIntentService;
import com.quantumcoders.minorapp.misc.ServerWorker;

import java.io.File;

import static com.quantumcoders.minorapp.misc.Constants.PARAM_CATEGORY;
import static com.quantumcoders.minorapp.misc.Constants.PARAM_GRP_ID;
import static com.quantumcoders.minorapp.misc.Constants.PICK_IMAGE;
import static com.quantumcoders.minorapp.misc.Constants.SESSION_FILE;
import static com.quantumcoders.minorapp.misc.Constants.USER_ID_KEY;

public class SendResponseActivity extends AppCompatActivity implements LocationListener, Base {
    File imageFile = null;
    Uri imageUri;
    boolean imageCaptured = false;
    boolean clicked = false;
    boolean locationPermitted = false;
    boolean locationFetched = false;
    LocationManager lm = null;
    int TIME_TO_UPDATE = 0;
    int DIST_TO_UPDATE = 0;
    double lat, lng;

    String grpid, category, address;

    ResultReceiver receiver = new ResultReceiver(new Handler()) {

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            address = resultData.getString("address");
            ((TextView) findViewById(R.id.tvAddress)).setText(address);
            locationFetched = true;
        }
    };


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_response);


        grpid = getIntent().getStringExtra(PARAM_GRP_ID);
        category = getIntent().getStringExtra(PARAM_CATEGORY);


        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, TIME_TO_UPDATE, DIST_TO_UPDATE, this);
            locationPermitted = true;
            System.out.println("Location - Using GPS Provider");
        } else if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, TIME_TO_UPDATE, DIST_TO_UPDATE, this);
            locationPermitted = true;
            System.out.println("Location - Using Network Provider");
        } else {
            System.out.println("Location - No provider enabled");
            new android.app.AlertDialog.Builder(this).setMessage("Please turn on location service").setPositiveButton("OK", (d, w) -> {
                d.dismiss();
                finish();
            }).create().show();
        }


    }


    public void clickImageCapture(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/" + Constants.TEMP_IMAGE_FILE_NAME + ".jpg");
        imageUri = FileProvider.getUriForFile(this, getPackageName(), imageFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                ((ImageView) findViewById(R.id.responseImage)).setImageURI(imageUri);
                imageCaptured = true;
            }
        }
    }

    public void clickSendResponse(View view) {
        if (!clicked) {
            String desc = ((TextInputEditText) findViewById(R.id.responseDescription)).getText().toString();
            if (desc.isEmpty()) {
                longToast("Description is empty");
            } else if (!imageCaptured) {
                longToast("Please capture image for proof");
            } else if (!locationFetched) {
                longToast("Location is loading. Please wait.");
            } else {
                longToast("Send response");
                String agentid = getSharedPreferences(SESSION_FILE, MODE_PRIVATE).getString(USER_ID_KEY, "");
                ServerWorker.sendResponse(this, grpid, category, desc, imageFile, lat, lng, address, agentid);
                clicked = true;
            }
        }
    }

    public void longToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    /*LOCATION LISTENER METHODS*/
    int skip = 0;

    @Override
    public void onLocationChanged(Location location) {
        System.out.println("SendResponse - LocationListener - onLocationChanged");
        if (skip == 0)
            skip = 1;      //skipping first location due to superstition that first location is not accurate :p
        else if (skip == 1) {
            skip = 2;
            lat = location.getLatitude();
            lng = location.getLongitude();
            FetchAddressIntentService.fetch(this, lat, lng, receiver);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        System.out.println("SendResponse - LocationListener - onStatusChanged");
    }

    @Override
    public void onProviderEnabled(String provider) {
        System.out.println("SendResponse - LocationListener - onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        System.out.println("SendResponse - LocationListener - onProviderDisabled");
    }


    public void onResponseSent(){
        new AlertDialog.Builder(this).setMessage("Response sent succesfully").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                setResult(RESULT_OK);
                finish();
            }
        }).create().show();
    }

    //Base
    public void noInternet() {
        longToast("Please turn on the INTERNET");
    }

    @Override
    public void onRequestTimeout() {
        longToast("Request timed out");
    }

}
