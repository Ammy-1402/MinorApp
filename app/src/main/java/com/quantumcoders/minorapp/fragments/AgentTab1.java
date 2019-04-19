package com.quantumcoders.minorapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.adapters.AgentListItemComplaint;
import com.quantumcoders.minorapp.adapters.AgentMyRecyclerAdapter;
import com.quantumcoders.minorapp.adapters.ListItemComplaint;
import com.quantumcoders.minorapp.adapters.MyRecyclerAdapter;
import com.quantumcoders.minorapp.misc.Constants;
import com.quantumcoders.minorapp.misc.ServerWorker;

import java.util.ArrayList;
import java.util.List;

import static com.quantumcoders.minorapp.misc.Constants.REQ_RELOAD_LIST;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgentTab1 extends Fragment {

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public List<AgentListItemComplaint> agentListItemComplaints;

    public AgentTab1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agent_tab1, container, false);

        recyclerView = view.findViewById(R.id.id_agentRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        agentListItemComplaints = new ArrayList<>();

        String statuses[] = new String[]{Constants.STATUS_COMPLETED, Constants.STATUS_WORK_IN_PROGRESS, Constants.STATUS_PENDING};

//        for (int i = 1; i < 9; i++) {
//
//            AgentListItemComplaint agentListItemComplaint = new AgentListItemComplaint(
//                    "" + i,
//                    "" + 5 * i,
//                    "ABC.." + i,
//                    "Location " + i,
//                    statuses[i % 3]
//            );
//
//            this.agentListItemComplaints.add(agentListItemComplaint);
//        }

        adapter = new AgentMyRecyclerAdapter(agentListItemComplaints, getActivity());
        recyclerView.setAdapter(adapter);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        getView().findViewById(R.id.loadingLayout).setVisibility(View.VISIBLE);
    }

}
