package com.quantumcoders.minorapp.misc;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.ResultReceiver;

import java.io.IOException;
import java.util.List;


public class FetchAddressIntentService extends IntentService {

    static ResultReceiver receiver = null;

    public FetchAddressIntentService() {
        super("FetchAddressIntentService");
    }


    public static void fetch(Context context, double lat, double lng, ResultReceiver resultReceiver) {
        Intent intent = new Intent(context, FetchAddressIntentService.class);
        intent.putExtra("LATITUDE", lat);
        intent.putExtra("LONGITUDE", lng);
        receiver = resultReceiver;
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            double lat = intent.getDoubleExtra("LATITUDE", 0.0f);
            double lng = intent.getDoubleExtra("LONGITUDE", 0.0f);

            if (lat != 0.0f && lng != 0.0f) {


                Geocoder coder = new Geocoder(this);
                try {
                    List<Address> addrList = coder.getFromLocation(lat, lng, 1);
                    StringBuilder address = new StringBuilder();

                    Address a = addrList.get(0);
                    for (int i = 0; i <= a.getMaxAddressLineIndex(); i++) {
                        address.append(a.getAddressLine(i));
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("address", address.toString());
                    if(receiver!=null)receiver.send(1, bundle);
                    else System.out.println("FetchAddressIntentService receiver is null");
                    receiver = null;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
