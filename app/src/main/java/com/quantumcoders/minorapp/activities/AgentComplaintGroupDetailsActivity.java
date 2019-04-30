package com.quantumcoders.minorapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.adapters.AgentGroupIdRecyclerAdapter;
import com.quantumcoders.minorapp.adapters.AgentListItemGroupIdComplaint;
import com.quantumcoders.minorapp.misc.Constants;
import com.quantumcoders.minorapp.misc.ServerWorker;

import java.util.ArrayList;
import java.util.List;

public class AgentComplaintGroupDetailsActivity extends AppCompatActivity {

    public TextView textViewGroupId, textViewCategory;
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public List<AgentListItemGroupIdComplaint> agentListItemGroupIdComplaints;

    //intent parameters
    String groupid, category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_complaint_group_details);

        groupid = getIntent().getStringExtra(Constants.PARAM_GROUP_ID);
        category = getIntent().getStringExtra(Constants.PARAM_CATEGORY);
        String status = getIntent().getStringExtra(Constants.PARAM_STATUS);
        String address = getIntent().getStringExtra(Constants.PARAM_ADDRESS);

        TextView locView = ((TextView)findViewById(R.id.tvLoc));
        locView.setText(Html.fromHtml("<u><font color='#0000FF'>"+address+"</font></u>"));
        locView.setClickable(true);
        locView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.co.in/maps?q=" + address));
            startActivity(intent);
        });

        if (status.equals(Constants.STATUS_COMPLETED))
            ((Button) findViewById(R.id.btnAgentResponse)).setVisibility(View.GONE);

        //System.out.println("AgentComplaintGroupDetailsActivity GROUP ID : "+groupid);
        //System.out.println("AgentComplaintGroupDetailsActivity CATEGORY : "+category);
        textViewGroupId = findViewById(R.id.textView18);
        textViewCategory = findViewById(R.id.textView19);
        textViewGroupId.setText(groupid);
        textViewCategory.setText(category);

        recyclerView = findViewById(R.id.id_agentGroupIdRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        agentListItemGroupIdComplaints = new ArrayList<>();

        adapter = new AgentGroupIdRecyclerAdapter(agentListItemGroupIdComplaints, this);
        recyclerView.setAdapter(adapter);

        ServerWorker.loadGroupIdDetails(this, groupid);
    }

    public void longToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void onLoadGroupIdComplaintDetails(String... data) {

        ArrayList<AgentListItemGroupIdComplaint> list = new ArrayList<>();
        System.out.println("data.length = " + data.length);
        for (int i = 1; i < data.length; i += 3) {
            list.add(new AgentListItemGroupIdComplaint(data[i], data[i + 1], data[i + 2]));
        }
        agentListItemGroupIdComplaints = list;
        adapter = new AgentGroupIdRecyclerAdapter(list, this);
        recyclerView.setAdapter(adapter);

        longToast("UPDATED GROUP ID LIST");
    }


    public void startResponseActivity(View view) {
        Intent mc = new Intent(this, SendResponseActivity.class);
        mc.putExtra(Constants.PARAM_CATEGORY, category);
        mc.putExtra(Constants.PARAM_GRP_ID, groupid);
        startActivityForResult(mc, 8080);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 8080) {
            if (resultCode == RESULT_OK) {
                ((Button) findViewById(R.id.btnAgentResponse)).setVisibility(View.GONE);
            }
        }
    }

}
