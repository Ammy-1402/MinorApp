package com.quantumcoders.minorapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.Constants;
import com.quantumcoders.minorapp.misc.ServerWorker;

public class AgentComplaintDetailsActivity extends AppCompatActivity {

    TextView textViewcomplntNo,textViewRegisteredOn, textViewRegisteredBy;
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


    }
}
