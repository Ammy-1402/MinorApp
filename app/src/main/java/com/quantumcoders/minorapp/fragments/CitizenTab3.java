package com.quantumcoders.minorapp.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.Constants;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CitizenTab3 extends Fragment {



    public CitizenTab3() {
        // Required empty public constructor
    }

    TextInputEditText name, email, contact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_citizen_tab3, container, false);

        name = view.findViewById(R.id.id_name);
        email = view.findViewById(R.id.id_email);
        contact = view.findViewById(R.id.id_contact);


        return view;
    }




    public void setProfileData(String...data){
        ((TextInputEditText)getView().findViewById(R.id.id_name)).setText(data[1]);
        ((TextInputEditText)getView().findViewById(R.id.id_email)).setText(data[2]);
        ((TextInputEditText)getView().findViewById(R.id.id_contact)).setText(data[3]);
    }
}
