package com.quantumcoders.minorapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.adapters.ListItemComplaint;
import com.quantumcoders.minorapp.adapters.MyRecyclerAdapter;
import com.quantumcoders.minorapp.misc.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CitizenTab2 extends Fragment {

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;

    public List<ListItemComplaint> listItemComplaint;

    public CitizenTab2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_citizen_tab2, container, false);

        recyclerView = view.findViewById(R.id.id_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItemComplaint = new ArrayList<>();

        String statuses[] = new String[]{Constants.STATUS_COMPLETED,Constants.STATUS_WORK_IN_PROGRESS,Constants.STATUS_PENDING};

        for(int i=1 ; i<9 ; i++){

            System.out.println("i ========= "+ i);

            ListItemComplaint listItemComplaint = new ListItemComplaint(
                    ""+i,  //complaintNo is the complaintId
                    "Timestamp Value "+i,
                    "ABC "+i,
                    "Hey Demo data Description .. "+i,
                     statuses[i%3]);

            this.listItemComplaint.add(listItemComplaint);
        }

        adapter = new MyRecyclerAdapter(listItemComplaint,getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

}
