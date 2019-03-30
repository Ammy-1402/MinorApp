package com.quantumcoders.minorapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

}
