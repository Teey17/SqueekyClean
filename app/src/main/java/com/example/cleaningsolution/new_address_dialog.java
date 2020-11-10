package com.example.cleaningsolution;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import static android.content.Context.LOCATION_SERVICE;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

public class new_address_dialog extends DialogFragment{

    private Context mContext;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    double latitude, longitude;
    private EditText etNewAddress;
    private View btnPinNewLocation;
    MyAddresses myAddresses;
    String newAddress = null;
    String loactionPin = null;
    boolean gotLocatoin = false;
    Criteria criteria;
    public String bestProvider;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        myAddresses = new MyAddresses();
        mContext = myAddresses.getApplication();
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.new_address_dialog, null);
        builder.setView(view)
                .setTitle("Add New Address")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (etNewAddress.getText().toString().isEmpty() && loactionPin != null) {
                            newAddress = loactionPin;
                        } else if (!etNewAddress.getText().toString().isEmpty() && loactionPin == null) {
                            newAddress = etNewAddress.getText().toString();
                        }

                    }
                });

        etNewAddress = view.findViewById(R.id.etNewAddres);
        btnPinNewLocation = view.findViewById(R.id.btnPinNewLocation);

        btnPinNewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressButton progressButton = new ProgressButton(MyAddresses.context,v);

                if (MainActivity.address != null)
                    etNewAddress.setText(MainActivity.address);

                else {

                    progressButton.buttonActivated();
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            while (!gotLocatoin){


                                if (MainActivity.address != null){
                                    gotLocatoin = true;

                                }
                            }
                            return;
                        }
                    });
                    etNewAddress.setText(MainActivity.address);
                    progressButton.buttonFinished();

                }
            }

        });
        return builder.create();
    }

    public void newLocationPin() {



    }

}


