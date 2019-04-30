package com.quantumcoders.minorapp.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quantumcoders.minorapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgentTab2 extends Fragment {


    public AgentTab2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agent_tab2, container, false);
        return view;
    }


    public void setProfileData(String...data){
        ((TextInputEditText)getView().findViewById(R.id.id_agentName)).setText(data[1]);
        ((TextInputEditText)getView().findViewById(R.id.id_agentEmail)).setText(data[2]);
        ((TextInputEditText)getView().findViewById(R.id.id_contact)).setText(data[3]);
    }

}
