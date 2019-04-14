package com.quantumcoders.minorapp.activities;

import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.Constants;
import com.quantumcoders.minorapp.misc.ServerWorker;

import org.w3c.dom.Text;

import java.io.File;

import static android.view.View.VISIBLE;
import static com.quantumcoders.minorapp.misc.Constants.PARAM_COMPLT_ID;
import static com.quantumcoders.minorapp.misc.Constants.STATUS_COMPLETED;

public class CitizenComplaintDetailsActivity extends AppCompatActivity {
    LinearLayout respLayout=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_complaint_details);
        respLayout=findViewById(R.id.linearLayout);
        respLayout.setVisibility(View.GONE);
        ServerWorker.loadComplaintDetails(this,getIntent().getStringExtra(PARAM_COMPLT_ID));
        ServerWorker.loadComplaintImage(this,getIntent().getStringExtra(PARAM_COMPLT_ID));
    }

    public void onLoadComplaintDetails(String...response){

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
        else{
            ((TextView)findViewById(R.id.tv_status)).setText("Problem has been resolved");
            respLayout.setVisibility(VISIBLE);
        }

        ((TextView)findViewById(R.id.tv_registeredOn)).setText(response[index++]);
        ((TextView)findViewById(R.id.tv_description)).setText(response[index++]);

        if(stat.equals(STATUS_COMPLETED)){
            System.out.println("onLoadComplaintDetails - response found");
            ((TextView)respLayout.findViewById(R.id.resp_desc)).setText("#"+response[index++]);
        }
    }

    public void onLoadComplaintImage(){
        System.out.println("onLoadComplaintImage");
        File image=new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/"+ Constants.TEMP_IMAGE_FILE_NAME+".jpg");
        ((ImageView)findViewById(R.id.complaintImage)).setImageURI(FileProvider.getUriForFile(this,getPackageName(),image));
    }
}
