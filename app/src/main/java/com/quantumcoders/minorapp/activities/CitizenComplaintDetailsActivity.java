package com.quantumcoders.minorapp.activities;

import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.Constants;
import com.quantumcoders.minorapp.misc.ServerWorker;

import org.w3c.dom.Text;

import java.io.File;

import static com.quantumcoders.minorapp.misc.Constants.PARAM_COMPLT_ID;

public class CitizenComplaintDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_complaint_details);

        ServerWorker.loadComplaintDetails(this,getIntent().getStringExtra(PARAM_COMPLT_ID));
    }

    public void onLoadComplaintDetails(String...response){

        File image=new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/"+ Constants.TEMP_IMAGE_FILE_NAME+".jpg");


        int index=1;
        ((TextView)findViewById(R.id.tv_complaintNo)).setText(response[index++]);
        ((TextView)findViewById(R.id.tv_cgid)).setText(response[index++]);
        ((TextView)findViewById(R.id.tv_category)).setText(response[index++]);
        ((TextView)findViewById(R.id.tv_address)).setText(response[index++]);

        String stat = response[index++];
        if(stat.equals(Constants.STATUS_PENDING))
            ((TextView)findViewById(R.id.tv_status)).setText("Problem is under examination");
        else if(stat.equals(Constants.STATUS_WORK_IN_PROGRESS))
            ((TextView)findViewById(R.id.tv_status)).setText("Work has been assigned to agent. It will be completed ASAP");
        else
            ((TextView)findViewById(R.id.tv_status)).setText("Problem has been resolved");

        ((TextView)findViewById(R.id.tv_registeredOn)).setText(response[index++]);
        ((TextView)findViewById(R.id.tv_description)).setText(response[index++]);
        ((ImageView)findViewById(R.id.complaintPhoto)).setImageURI(FileProvider.getUriForFile(this,getPackageName(),image));
        if(image.exists()) System.out.println("Image exists");
        else System.out.println("Image not exists");
    }
}
