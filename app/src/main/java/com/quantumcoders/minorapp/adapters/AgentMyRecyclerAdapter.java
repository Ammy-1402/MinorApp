package com.quantumcoders.minorapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.activities.AgentComplaintGroupDetailsActivity;
import com.quantumcoders.minorapp.misc.Constants;

import java.util.List;

import static com.quantumcoders.minorapp.misc.Constants.REQ_RELOAD_LIST;

public class AgentMyRecyclerAdapter extends RecyclerView.Adapter<AgentMyRecyclerAdapter.ViewHolder> {

    private List<AgentListItemComplaint> listItems;
    private Activity ctxt;



    public AgentMyRecyclerAdapter(List<AgentListItemComplaint> listItems, Activity ctxt) {
        this.listItems = listItems;
        this.ctxt = ctxt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.agentitemlist_cardview,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        AgentListItemComplaint agentListItemComplaint = listItems.get(position);

        viewHolder.textViewGroupId.setText(agentListItemComplaint.getGroupId());
        viewHolder.textViewNoOfComplaints.setText(agentListItemComplaint.getNoOfComplaints());
        viewHolder.textViewCategory.setText(agentListItemComplaint.getCategory());
        viewHolder.textViewLocation.setText(agentListItemComplaint.getLocation());

        viewHolder.groupIdComplaint = agentListItemComplaint;
        viewHolder.groupIdCategory = agentListItemComplaint;


        if(agentListItemComplaint.getAgentStatus().equals(Constants.STATUS_PENDING)){

            viewHolder.textViewAgentStatus.setBackgroundResource(R.drawable.status_pending);

        }else if(agentListItemComplaint.getAgentStatus().equals(Constants.STATUS_WORK_IN_PROGRESS)){

            viewHolder.textViewAgentStatus.setBackgroundResource(R.drawable.status_wip);

        }else{

            viewHolder.textViewAgentStatus.setBackgroundResource(R.drawable.status_complete);

        }

        viewHolder.itemView.findViewById(R.id.clickAgentLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctxt, AgentComplaintGroupDetailsActivity.class);
                intent.putExtra(Constants.PARAM_GROUP_ID,viewHolder.groupIdComplaint.getGroupId());
                intent.putExtra(Constants.PARAM_CATEGORY,viewHolder.groupIdCategory.getCategory());
                intent.putExtra(Constants.PARAM_STATUS,viewHolder.groupIdComplaint.getAgentStatus());
                intent.putExtra(Constants.PARAM_ADDRESS,viewHolder.groupIdComplaint.getLocation());
                //System.out.println("intent extra : "+viewHolder.groupIdComplaint.getGroupId());
                ctxt.startActivityForResult(intent,REQ_RELOAD_LIST);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewGroupId , textViewNoOfComplaints , textViewCategory , textViewLocation, textViewAgentStatus;
        public AgentListItemComplaint groupIdComplaint=null,groupIdCategory=null;
        public View itemView = null;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewGroupId = itemView.findViewById(R.id.id_groupId);
            textViewNoOfComplaints = itemView.findViewById(R.id.id_noOfComplaints);
            textViewCategory = itemView.findViewById(R.id.id_agentCategory);
            textViewLocation = itemView.findViewById(R.id.id_agentLocation);
            textViewAgentStatus = itemView.findViewById(R.id.id_agentStatus);

            this.itemView = itemView;
        }
    }
}
