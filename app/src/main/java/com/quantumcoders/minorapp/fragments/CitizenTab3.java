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

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.misc.Constants;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CitizenTab3 extends Fragment {

    Uri imageUri = null;
    ImageView profilePic = null ,imageButton = null;


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

        //DEMO DATA FOR name, email, contact FIELDS

        name.setText("First Name Here");
        email.setText("Email Here");
        contact.setText("Contact Here");

        profilePic = view.findViewById(R.id.imageView);
        imageButton = view.findViewById(R.id.id_changeProfilePicture);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(()->{
                    OpenGallery();
                }).start();
            }
        });

        return view;
    }


    public void OpenGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, Constants.PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == Constants.PICK_IMAGE){
            imageUri = data.getData();
            profilePic.setImageURI(imageUri);
        }
    }
}
