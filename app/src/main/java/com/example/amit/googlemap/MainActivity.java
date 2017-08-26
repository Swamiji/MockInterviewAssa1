package com.example.amit.googlemap;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setUpMapNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapNeeded();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MarkerOptions options=new MarkerOptions().position(new LatLng(12.9654,77.6380)).title("AcadGild");
        googleMap.addMarker(options);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate center= CameraUpdateFactory.newLatLng(new LatLng(12.9654,77.6380));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                double latitude=latLng.latitude;
                double longitude=latLng.longitude;
                List<Address>addresses;
                Geocoder geocoder;
                geocoder=new Geocoder(MainActivity.this, Locale.getDefault());
                try {
                    addresses=geocoder.getFromLocation(latitude,longitude,1);
                    String Address=addresses.get(0).getAddressLine(0);
                    String City=addresses.get(0).getLocality();
                    String state=addresses.get(0).getAdminArea();
                    String country=addresses.get(0).getCountryName();
                    String postalcode=addresses.get(0).getPostalCode();
                    String knownname=addresses.get(0).getFeatureName();
                    String show=Address+"\n"+City+"\n"+state+"\n"+country+"\n"+postalcode+"\n"+knownname+"\n";
                    Toast.makeText(MainActivity.this,show,Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public void setUpMapNeeded(){
        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.getMapAsync(this);

    }
}






































