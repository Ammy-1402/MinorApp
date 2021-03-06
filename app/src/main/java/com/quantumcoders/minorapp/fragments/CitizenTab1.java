package com.quantumcoders.minorapp.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.activities.CitizenMainActivity;
import com.quantumcoders.minorapp.misc.ServerWorker;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static com.quantumcoders.minorapp.misc.Constants.SESSION_FILE;
import static com.quantumcoders.minorapp.misc.Constants.USER_ID_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class CitizenTab1 extends Fragment {

    CitizenMainActivity mainActivity = null;

    Spinner dropDown;
    TextView locDesc = null;
    MapView map = null;
    GoogleMap gmap = null;
    final int IMAGE_CAPTURE_REQ = 1;
    Uri imageUri = null;
    File imageFile = null;
    boolean imageCaptured = false;
    Handler testHandler = new Handler();

    public CitizenTab1() {
        // Required empty public constructor
    }

    @SuppressLint("MissingPermission")
    public void updateLocation(double lat, double lng, String desc) {
        locDesc.setText(desc);
        if (map != null) {
            gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 18));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_citizen_tab1, container, false);

        //set dropdown list
        dropDown = view.findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.dropDownCategory));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        dropDown.setAdapter(arrayAdapter);

        locDesc = view.findViewById(R.id.locDesc);

        mainActivity = (CitizenMainActivity) getActivity();

        //set button click listener for capture image button
        ((Button) view.findViewById(R.id.id_captureImage)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    imageFile = createImageFile();
                    System.out.println("Image file created - " + imageFile.getAbsolutePath());
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    imageUri = FileProvider.getUriForFile(mainActivity, getActivity().getApplicationContext().getPackageName(), imageFile);

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, IMAGE_CAPTURE_REQ);
                } catch (IOException e) {
                    mainActivity.longToast(e.toString());
                }
            }
        });

        //set button click listener for register complaint button
        ((Button) view.findViewById(R.id.id_registerComplaint)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = ((Spinner) view.findViewById(R.id.spinner)).getSelectedItemPosition();
                String desc = ((TextInputEditText) view.findViewById(R.id.id_description)).getText().toString().trim();

                System.out.println("Register complaint button clicked " + pos + "|" + desc);

                if (pos == 0) mainActivity.longToast("Choose a category");
                else if (desc.equals("")) {
                    mainActivity.longToast("Describe your problem");
                } else if (!imageCaptured) {
                    mainActivity.longToast("Image not captured");
                } else if (mainActivity.count < 10 || ((TextView) view.findViewById(R.id.locDesc)).getText().toString().isEmpty()) {
                    mainActivity.longToast("Location not loaded");
                } else {

                    System.out.println("Filing complaint now");
                    desc = desc.replace("\n", "<br/>");

                    String category = ((Spinner) view.findViewById(R.id.spinner)).getSelectedItem().toString();
                    String address = ((TextView) view.findViewById(R.id.locDesc)).getText().toString();
                    String userid = mainActivity.getApplicationContext().getSharedPreferences(SESSION_FILE, Context.MODE_PRIVATE).getString(USER_ID_KEY, "");

                    ServerWorker.fileComplaint(mainActivity, category, desc, imageFile, mainActivity.avglat, mainActivity.avglng, address, userid);
                    ((Spinner) view.findViewById(R.id.spinner)).setSelection(0);
                    ((TextInputEditText) view.findViewById(R.id.id_description)).setText("");
                    ((ImageView) mainActivity.findViewById(R.id.imageView3)).setImageResource(R.mipmap.ic_launcher_round);
                }

            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_CAPTURE_REQ) {
            if (resultCode == RESULT_OK) {
                ((ImageView) getView().findViewById(R.id.imageView3)).setImageURI(imageUri);
                imageCaptured = true;
            }
        }
    }


    public File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        //get directory to store images
        File storageDirectory = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile("capture_" + timeStamp, ".jpg", storageDirectory);
        return imageFile;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().findViewById(R.id.id_registerComplaint).requestFocus();
        initMap();
//        map.setDuplicateParentStateEnabled(false);
    }

    @SuppressLint("MissingPermission")
    public void initMap() {
        map = ((MapView) getView().findViewById(R.id.mapView));
        map.onCreate(new Bundle());
        map.getMapAsync(googleMap -> {
            gmap = googleMap;
            googleMap.setMyLocationEnabled(true);

        });
        map.setClickable(true);
        map.setFocusable(true);
    }


    @Override
    public void onStart() {
        super.onStart();
        map.onStart();
        getView().findViewById(R.id.id_description).clearFocus();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        map.onStop();
    }
}
