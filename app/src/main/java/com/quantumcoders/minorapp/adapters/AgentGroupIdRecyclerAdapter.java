package com.quantumcoders.minorapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.activities.AgentComplaintDetailsActivity;
import com.quantumcoders.minorapp.misc.Constants;

import java.util.List;

public class AgentGroupIdRecyclerAdapter extends RecyclerView.Adapter<AgentGroupIdRecyclerAdapter.ViewHolder> {

    private List<AgentListItemGroupIdComplaint> listItems;
    private Context ctxt;

    public AgentGroupIdRecyclerAdapter(List<AgentListItemGroupIdComplaint> listItems, Context ctxt) {
        this.listItems = listItems;
        this.ctxt = ctxt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.agentgroupiditemlist_cardview,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        AgentListItemGroupIdComplaint agentListItemGroupIdComplaint = listItems.get(position);

        viewHolder.textViewComplaintNo.setText(agentListItemGroupIdComplaint.getGroupIdComplaintNo());
        viewHolder.textViewRegisteredBy.setText(agentListItemGroupIdComplaint.getRegisteredBy());
        viewHolder.textViewRegisteredOn.setText(agentListItemGroupIdComplaint.getRegisteredOn());
        viewHolder.complaintNo = agentListItemGroupIdComplaint;
        viewHolder.itemView.findViewById(R.id.agentGroupIdItemListClickLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctxt, AgentComplaintDetailsActivity.class);
                intent.putExtra(Constants.PARAM_COMPLNT_NO,viewHolder.complaintNo.getGroupIdComplaintNo());
                intent.putExtra(Constants.PARAM_REGISTERED_BY,viewHolder.complaintNo.getRegisteredBy());
                intent.putExtra(Constants.PARAM_REGISTERED_ON,viewHolder.complaintNo.getRegisteredOn());
                //System.out.println("AgentGroupIdRecyclerAdapter intent extra: "+viewHolder.complaintNo.groupIdComplaintNo);
                ctxt.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewComplaintNo , textViewRegisteredBy , textViewRegisteredOn ;
        public AgentListItemGroupIdComplaint complaintNo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewComplaintNo = itemView.findViewById(R.id.id_complaintNoInGroupId);
            textViewRegisteredBy = itemView.findViewById(R.id.id_registeredByInGroupId);
            textViewRegisteredOn =  itemView.findViewById(R.id.id_registeredOnInGroupId);

        }
    }
}
