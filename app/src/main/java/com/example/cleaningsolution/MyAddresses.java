package com.example.cleaningsolution;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MyAddresses extends AppCompatActivity implements LocationListener {

    public static Context context;
    LocationManager locationManager;
    LocationListener listener;
    public static  String address = null;
    private boolean receivedLocation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);

        context = this;
        //Runtime permissions
        if (ContextCompat.checkSelfPermission(MyAddresses.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MyAddresses.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }


    }

    public void addNewAddressOnClick(View view) {


       // getLocation();
         new_address_dialog new_address_dialog = new new_address_dialog();
        new_address_dialog.show(getSupportFragmentManager(),"New Address Dialog");

    }

    private void getLocation() {
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5, MyAddresses.this);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        if (!receivedLocation){
            Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
            try {
                Geocoder geocoder = new Geocoder(MyAddresses.this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                 address = addresses.get(0).getAddressLine(0);

                Toast.makeText(this,address, Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                e.printStackTrace();
            }
            receivedLocation = true;

        }



    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
