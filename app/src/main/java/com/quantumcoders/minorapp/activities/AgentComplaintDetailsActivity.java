package com.quantumcoders.minorapp.activities;

import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.Constants;
import com.quantumcoders.minorapp.misc.ServerTask;
import com.quantumcoders.minorapp.misc.ServerWorker;

import java.io.File;

import static com.quantumcoders.minorapp.misc.Constants.AGENT;
import static com.quantumcoders.minorapp.misc.Constants.COMPLT_IMAGE_PREFIX;
import static com.quantumcoders.minorapp.misc.Constants.STATUS_COMPLETED;
import static com.quantumcoders.minorapp.misc.Constants.STATUS_PENDING;

public class AgentComplaintDetailsActivity extends AppCompatActivity {

    TextView textViewcomplntNo,textViewRegisteredOn, textViewRegisteredBy;
    File compltImage=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_complaint_details);

        String complaintNo = getIntent().getStringExtra(Constants.PARAM_COMPLNT_NO);
        String registeredBy = getIntent().getStringExtra(Constants.PARAM_REGISTERED_BY);
        String registeredOn = getIntent().getStringExtra(Constants.PARAM_REGISTERED_ON);
        textViewcomplntNo = findViewById(R.id.id_complntNoAgent);
        textViewRegisteredBy = findViewById(R.id.id_registeredByAgent);
        textViewRegisteredOn = findViewById(R.id.id_registeredOnAgent);

        textViewcomplntNo.setText(complaintNo);
        textViewRegisteredBy.setText(registeredBy);
        textViewRegisteredOn.setText(registeredOn);

        ServerWorker.loadComplaintDetailsAgent(this,complaintNo);
        compltImage = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/"+COMPLT_IMAGE_PREFIX+complaintNo+".jpg");

        if(compltImage==null || !compltImage.exists()){
            ServerWorker.loadComplaintImage(this,complaintNo,AGENT);
        } else {
            ((ImageView) findViewById(R.id.id_imageViewAgent)).setImageURI(FileProvider.getUriForFile(this, getPackageName(), compltImage));
        }

    }


    public void onLoadComplaintDetails(String...response){
        int index=1;
        ((TextView)findViewById(R.id.id_contactNoAgent)).setText(response[index++]);
        ((TextView)findViewById(R.id.id_categoryAgent)).setText(response[index++]);
        ((TextView)findViewById(R.id.id_addressAgent)).setText(response[index++]);

        String status=response[index++];
        if(status.equals(STATUS_COMPLETED)){
            ((TextView)findViewById(R.id.id_statusAgent)).setText("Problem has been resolved");
        } else {    //pending is not posisble
            ((TextView)findViewById(R.id.id_statusAgent)).setText("Work under progress");
        }

        ((TextView)findViewById(R.id.id_descriptionAgent)).setText(response[index++]);
    }


    public void onLoadComplaintImage() {
        System.out.println("onLoadComplaintImage");
        ((ImageView) findViewById(R.id.id_imageViewAgent)).setImageURI(FileProvider.getUriForFile(this, getPackageName(), compltImage));
    }
}
