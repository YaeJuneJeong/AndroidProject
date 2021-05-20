package com.codesample.openapiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.codesample.openapiproject.databinding.ActivityResultBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ActivityResultBinding binding;
    static ArrayList<Data> data;
    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        data = (ArrayList<Data>) intent.getSerializableExtra("list");

        SupportMapFragment mapFragment =(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap =googleMap;
        for(int i=0;i<data.size();i++)
        {
            MarkerOptions markerOptions =new MarkerOptions();

            markerOptions.position(new LatLng(data.get(i).longitude,data.get(i).latitude))
                    .title(data.get(i).name);

            mMap.addMarker(markerOptions);
        }
        mMap.setMyLocationEnabled(true);
    }
}
