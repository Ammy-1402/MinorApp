package com.quantumcoders.minorapp.fragments;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.ListItemCardView;
import com.quantumcoders.minorapp.misc.MyRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CitizenTab2 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItemCardView> listItemCardViews;

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
        listItemCardViews = new ArrayList<>();

        for(int i=1 ; i<9 ; i++){

            System.out.println("i ========= "+ i);

            ListItemCardView listItemCardView = new ListItemCardView(
                    ""+i,  //complaintNo is the complaintId
                    "Timestamp Value "+i,
                    "ABC "+i,
                    "Hey Demo data Description .. "+i);

            listItemCardViews.add(listItemCardView);
        }

        adapter = new MyRecyclerAdapter(listItemCardViews,getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

}
