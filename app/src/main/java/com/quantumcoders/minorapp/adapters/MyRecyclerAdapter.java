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


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<ListItemComplaint> listItems;
    private Context ctxt;

    public MyRecyclerAdapter(List<ListItemComplaint> listItems, Context ctxt) {
        this.listItems = listItems;
        this.ctxt = ctxt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist_cardview,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ListItemComplaint listItemComplaint = listItems.get(position);

        viewHolder.textViewComplaintId.setText(listItemComplaint.getComplaintNo());  //getComplaintNo() is the getComplaintId()
        viewHolder.textViewRegisteredOn.setText(listItemComplaint.getRegisteredOn());
        viewHolder.textViewCategory.setText(listItemComplaint.getCategory());
        viewHolder.textViewDescription.setText(listItemComplaint.getDescription());

        if(listItemComplaint.getStatus().equals(Constants.STATUS_PENDING)){

            viewHolder.textViewStatus.setBackgroundResource(R.drawable.status_pending);

        } else if(listItemComplaint.getStatus().equals(Constants.STATUS_WORK_IN_PROGRESS)){

            viewHolder.textViewStatus.setBackgroundResource(R.drawable.status_wip);

        } else {

            viewHolder.textViewStatus.setBackgroundResource(R.drawable.status_complete);

        }


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    //ViewHolder Class

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewComplaintId , textViewRegisteredOn , textViewCategory , textViewDescription, textViewStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewCategory = (TextView)itemView.findViewById(R.id.id_category);
            textViewComplaintId = (TextView)itemView.findViewById(R.id.id_complaintNo);  //complaintNo is the complaintId
            textViewRegisteredOn = (TextView)itemView.findViewById(R.id.id_complaintRegistrationTime);
            textViewDescription = (TextView)itemView.findViewById(R.id.id_descriptionDetails);
            textViewStatus = (TextView) itemView.findViewById(R.id.id_status);
        }
    }
}
