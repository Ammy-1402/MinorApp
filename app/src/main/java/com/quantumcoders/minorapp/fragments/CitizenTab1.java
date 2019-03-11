package com.quantumcoders.minorapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.quantumcoders.minorapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CitizenTab1 extends Fragment {

    Spinner dropDown;

    public CitizenTab1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_citizen_tab1, container, false);

        dropDown = view.findViewById(R.id.spinner);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.dropDownCategory));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        dropDown.setAdapter(arrayAdapter);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().findViewById(R.id.id_registerComplaint).requestFocus();
    }
}
