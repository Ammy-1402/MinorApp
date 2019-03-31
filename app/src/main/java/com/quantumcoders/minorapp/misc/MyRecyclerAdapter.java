package com.quantumcoders.minorapp.misc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quantumcoders.minorapp.R;

import java.util.List;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<ListItemCardView> listItems;
    private Context ctxt;

    public MyRecyclerAdapter(List<ListItemCardView> listItems, Context ctxt) {
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
        ListItemCardView listItemCardView = listItems.get(position);

        viewHolder.textViewComplaintId.setText(listItemCardView.getComplaintNo());  //getComplaintNo() is the getComplaintId()
        viewHolder.textViewRegisteredOn.setText(listItemCardView.getRegisteredOn());
        viewHolder.textViewCategory.setText(listItemCardView.getCategory());
        viewHolder.textViewDescription.setText(listItemCardView.getDescription());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    //ViewHolder Class

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewComplaintId , textViewRegisteredOn , textViewCategory , textViewDescription ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewCategory = (TextView)itemView.findViewById(R.id.id_category);
            textViewComplaintId = (TextView)itemView.findViewById(R.id.id_complaintNo);  //complaintNo is the complaintId
            textViewRegisteredOn = (TextView)itemView.findViewById(R.id.id_complaintRegistrationTime);
            textViewDescription = (TextView)itemView.findViewById(R.id.id_descriptionDetails);

        }
    }
}
