package com.quantumcoders.minorapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.activities.AgentSignupActivity;
import com.quantumcoders.minorapp.activities.CitizenSignupActivity;


public class Tab2 extends Fragment {


    public Tab2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        Button btnSignup = view.findViewById(R.id.btnSignup);
        Button btnLogin = view.findViewById(R.id.btnLogin);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AgentSignupActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //login code here
            }
        });


        return view;
    }


}
