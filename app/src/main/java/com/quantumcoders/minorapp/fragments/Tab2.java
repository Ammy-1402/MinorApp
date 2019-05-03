package com.quantumcoders.minorapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.activities.AgentSignupActivity;
import com.quantumcoders.minorapp.activities.MainActivity;
import com.quantumcoders.minorapp.misc.Constants;
import com.quantumcoders.minorapp.misc.ServerWorker;

import static com.quantumcoders.minorapp.misc.Constants.REQ_SIGNUP;


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
                startActivityForResult(new Intent(getActivity(), AgentSignupActivity.class),Constants.REQ_SIGNUP);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //login code here
                if (!((MainActivity) getActivity()).clickedOnce) {     //if not already clicked on this button
                    ((MainActivity) getActivity()).clickedOnce = true;
                    String email = ((TextInputEditText) getView().findViewById(R.id.email)).getText().toString();
                    String password = ((TextInputEditText) getView().findViewById(R.id.password)).getText().toString();

                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        longToast("Invalid Email");
                    } else if (!password.matches(Constants.PWD_REGEX)) {
                        longToast("Invalid password");
                    } else {
                        ((MainActivity) getActivity()).email = email;
                        ((MainActivity) getActivity()).password = password;
                        ((MainActivity) getActivity()).type = Constants.AGENT;
                        ServerWorker.loginAgent((AppCompatActivity) getActivity(), email, password);
                    }
                } else System.out.println("Already clicked");
            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQ_SIGNUP) {     //whether sign up was successful so as to finish this activity or not
            if (resultCode == Activity.RESULT_OK) {
                System.out.println("Finishing MainActivity from TAB 2");
                getActivity().finish();
            }
        }
    }


    public void longToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

}
