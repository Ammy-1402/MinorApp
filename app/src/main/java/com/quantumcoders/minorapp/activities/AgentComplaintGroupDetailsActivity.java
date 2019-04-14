package com.quantumcoders.minorapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.quantumcoders.minorapp.adapters.AgentGroupIdRecyclerAdapter;
import com.quantumcoders.minorapp.adapters.AgentListItemGroupIdComplaint;
import com.quantumcoders.minorapp.misc.Constants;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.ServerWorker;

import java.util.ArrayList;
import java.util.List;

public class AgentComplaintGroupDetailsActivity extends AppCompatActivity {

    public TextView textViewGroupId,textViewCategory;
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public List<AgentListItemGroupIdComplaint> agentListItemGroupIdComplaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_complaint_group_details);

        String groupid = getIntent().getStringExtra(Constants.PARAM_GROUP_ID);
        String category = getIntent().getStringExtra(Constants.PARAM_CATEGORY);

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

        adapter = new AgentGroupIdRecyclerAdapter(agentListItemGroupIdComplaints,this);
        recyclerView.setAdapter(adapter);

        ServerWorker.loadGroupIdDetails(this, groupid);
    }

    public void longToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    public void onLoadGroupIdComplaintDetails(String...data){

        ArrayList<AgentListItemGroupIdComplaint> list = new ArrayList<>();
        System.out.println("data.length = "+data.length);
        for(int i=1;i<data.length;i+=3){
            list.add(new AgentListItemGroupIdComplaint(data[i],data[i+1],data[i+2]));
        }
        agentListItemGroupIdComplaints=list;
        adapter = new AgentGroupIdRecyclerAdapter(list,this);
        recyclerView.setAdapter(adapter);

        longToast("UPDATED GROUP ID LIST");
    }
}
