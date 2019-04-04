package com.quantumcoders.minorapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.Constants;

import java.util.List;

public class AgentMyRecyclerAdapter extends RecyclerView.Adapter<AgentMyRecyclerAdapter.ViewHolder> {

    private List<AgentListItemComplaint> listItems;
    private Context ctxt;

    public AgentMyRecyclerAdapter(List<AgentListItemComplaint> listItems, Context ctxt) {
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

        if(agentListItemComplaint.getAgentStatus().equals(Constants.STATUS_PENDING)){

            viewHolder.textViewAgentStatus.setBackgroundResource(R.drawable.status_pending);

        }else if(agentListItemComplaint.getAgentStatus().equals(Constants.STATUS_WORK_IN_PROGRESS)){

            viewHolder.textViewAgentStatus.setBackgroundResource(R.drawable.status_wip);

        }else{

            viewHolder.textViewAgentStatus.setBackgroundResource(R.drawable.status_complete);

        }

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewGroupId , textViewNoOfComplaints , textViewCategory , textViewLocation, textViewAgentStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewGroupId = itemView.findViewById(R.id.id_groupId);
            textViewNoOfComplaints = itemView.findViewById(R.id.id_noOfComplaints);
            textViewCategory = itemView.findViewById(R.id.id_agentCategory);
            textViewLocation = itemView.findViewById(R.id.id_agentLocation);
            textViewAgentStatus = itemView.findViewById(R.id.id_agentStatus);
        }
    }
}
