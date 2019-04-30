package com.quantumcoders.minorapp.activities;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.Constants;
import com.quantumcoders.minorapp.misc.ServerWorker;

import java.io.File;

import static android.view.View.VISIBLE;
import static com.quantumcoders.minorapp.misc.Constants.CITIZEN;
import static com.quantumcoders.minorapp.misc.Constants.COMPLT_IMAGE_PREFIX;
import static com.quantumcoders.minorapp.misc.Constants.PARAM_COMPLT_ID;
import static com.quantumcoders.minorapp.misc.Constants.RESP_IMAGE_PREFIX;
import static com.quantumcoders.minorapp.misc.Constants.STATUS_COMPLETED;

public class CitizenComplaintDetailsActivity extends AppCompatActivity {
    LinearLayout respLayout = null;
    File compltImage = null;
    File respImage = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_complaint_details);

        respLayout = findViewById(R.id.linearLayout);
        respLayout.setVisibility(View.GONE);
        String cno = getIntent().getStringExtra(PARAM_COMPLT_ID);
        ServerWorker.loadComplaintDetails(this, cno);

        //only load image if doesn't exists
        compltImage = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + COMPLT_IMAGE_PREFIX + cno + ".jpg");
        respImage = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + RESP_IMAGE_PREFIX + cno + ".jpg");

        if (compltImage == null || !compltImage.exists())
            ServerWorker.loadComplaintImage(this, cno, CITIZEN);
        else onLoadComplaintImage();

        if (respImage == null || !respImage.exists()) ServerWorker.loadResponseImage(this, cno);
        else onLoadResponseImage();

        //response image will be loaded in onLoadComplaintDetails
    }

    public void onLoadComplaintDetails(String... response) {

        int index = 1;
        ((TextView) findViewById(R.id.tv_complaintNo)).setText(response[index++]);
        ((TextView) findViewById(R.id.tv_cgid)).setText(response[index++]);
        ((TextView) findViewById(R.id.tv_category)).setText(response[index++]);
        ((TextView) findViewById(R.id.tv_address)).setText(response[index++]);

        String stat = response[index++];
        if (stat.equals(Constants.STATUS_PENDING))
            ((TextView) findViewById(R.id.tv_status)).setText("Problem is under examination");
        else if (stat.equals(Constants.STATUS_WORK_IN_PROGRESS))
            ((TextView) findViewById(R.id.tv_status)).setText("Work has been assigned to agent. It will be completed ASAP");
        else {
            ((TextView) findViewById(R.id.tv_status)).setText("Problem has been resolved");
            respLayout.setVisibility(VISIBLE);
        }

        ((TextView) findViewById(R.id.tv_registeredOn)).setText(response[index++]);
        ((TextView) findViewById(R.id.tv_description)).setText(response[index++]);

        if (stat.equals(STATUS_COMPLETED)) {
            System.out.println("onLoadComplaintDetails - response found");
            ((TextView) respLayout.findViewById(R.id.resp_desc)).setText("#" + response[index++]);
        }
    }

    public void onLoadComplaintImage() {
        System.out.println("onLoadComplaintImage");
        ((ImageView) findViewById(R.id.complaintImage)).setImageURI(FileProvider.getUriForFile(this, getPackageName(), compltImage));
    }


    public void onLoadResponseImage() {
        System.out.println("onLoadResponseImage");
        ((ImageView) respLayout.findViewById(R.id.resp_image)).setImageURI(FileProvider.getUriForFile(this, getPackageName(), respImage));
        if (respImage.length() < 1024) respImage.delete();
    }
}
