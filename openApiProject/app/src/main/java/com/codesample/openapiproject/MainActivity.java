package com.codesample.openapiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.codesample.openapiproject.databinding.ActivityMainBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
    implements OnMapReadyCallback,  LocationListener, Serializable {

    private ActivityMainBinding binding;

    private LocationManager  locationManager;
    static double latitude;
    static double longitude;
    static String name;
    private GoogleMap mMap;
    ArrayList<Data> Dates =new ArrayList<Data>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        StrictMode.enableDefaults();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        binding.locationButton.setOnClickListener(v -> {



            boolean inmapx=false, inmapy=false,inname = false;
            try {


                URL html =  new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey=EDCsurlBXlvFV1P0ORYpA9gAHzHeBRDmxrF6wrZYgxzDlShWnHkvwGLNxLoJBIF2KTL76o4wkRvSSQ9hMVD3Lw%3D%3D&contentTypeId=&mapX="+126.981611+"&mapY="+37.568477+"&radius=2000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1");


                XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
                XmlPullParser parser = parserCreator.newPullParser();

                parser.setInput(html.openStream(), null);

                int parserEvent = parser.getEventType();

                while (parserEvent != XmlPullParser.END_DOCUMENT){
                    Data places =new Data();

                    switch (parserEvent){
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equals("mapx")){
                                inmapx = true;

                            }
                            if(parser.getName().equals("mapy")){
                                inmapy = true;

                            }
                            if(parser.getName().equals("title")){
                                inname = true;

                            }
                            break;
                        case XmlPullParser.TEXT:
                            if (inmapx)
                            { latitude = Double.parseDouble(parser.getText());
                                inmapx =false;

                            }
                            if (inmapy)
                            { longitude = Double.parseDouble(parser.getText());
                                inmapy =false;
                            }if (inname)
                        { name = parser.getText();
                            inname =false;
                        }
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("item")){
                                Log.i("TAG", latitude+","+longitude+name);
                                places.name=name;
                                places.latitude = latitude;
                                places.longitude = longitude;

                                Dates.add(places);
                            }
                            break;
                    }
                    parserEvent = parser.next();
                }
            }catch (Exception e)
            {
                Log.i("TAG","ERROR 났어요");
            }
            Intent Result =new Intent(getApplicationContext(),ResultActivity.class);
            Result.putExtra("list",Dates);
            startActivity(Result);
        });
        binding.buttonRefreshing.setOnClickListener(v -> {


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {return;}

            locationManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE);

            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (lastLocation !=null) {
                longitude = lastLocation.getLongitude();
                latitude =lastLocation.getLatitude();
                Toast.makeText(this, "현재위치 \n위도 " + latitude + "\n경도 " + longitude, Toast.LENGTH_LONG).show();


            }});
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)) {

                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                return;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                return;
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
       }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            // Permission to access the location is missing. Show rationale and request permission

        }
        mMap = googleMap;

        mMap.setMyLocationEnabled(true);

    }

}
