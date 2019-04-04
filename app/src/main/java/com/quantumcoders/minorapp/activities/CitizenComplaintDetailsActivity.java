package com.quantumcoders.minorapp.activities;

import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.quantumcoders.minorapp.R;

import org.w3c.dom.Text;

import java.io.File;

public class CitizenComplaintDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_complaint_details);
    }

    public void onLoadComplaintDetails(File image,String...response){
        int index=0;
        ((TextView)findViewById(R.id.tv_complaintNo)).setText(response[index++]);
        ((TextView)findViewById(R.id.tv_cgid)).setText(response[index++]);
        ((TextView)findViewById(R.id.tv_category)).setText(response[index++]);
        ((TextView)findViewById(R.id.tv_address)).setText(response[index++]);
        ((TextView)findViewById(R.id.tv_status)).setText(response[index++]);
        ((TextView)findViewById(R.id.tv_registeredOn)).setText(response[index++]);
        ((TextView)findViewById(R.id.tv_description)).setText(response[index++]);
        ((ImageView)findViewById(R.id.complaintPhoto)).setImageURI(FileProvider.getUriForFile(this,getPackageName(),image));
    }
}
