package edu.csulb.lab9maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private final LatLng LOCATION_UNIV = new LatLng(33.783768, -118.114336);
    private final LatLng LOCATION_ECS = new LatLng(33.782777, -118.111868);
    private static int mZoom;
    private DatabaseHandler mDatabase;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Create Database
        mDatabase = new DatabaseHandler(getApplicationContext());
    }

    public void onClick_ECS(View v){
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_UNIV, 14);
        map.animateCamera(update);
        mZoom = 14;
    }

    public void onClick_LongBeach(View v){
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_ECS, 16);
        map.animateCamera(update);
        mZoom = 16;
    }

    public void onClick_City(View v){
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_UNIV, 9);
        map.animateCamera(update);
        mZoom = 9;
    }

    @Override
    public void onMapReady(GoogleMap gmap) {
        map = gmap;
        map.addMarker( new MarkerOptions().position(LOCATION_ECS).title("Find me here!") );

        // Listener when map is clicked on - short click
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                // Adds a marker when click on map
                map.addMarker( new MarkerOptions().position(point) );
                // Add marker to database
                double lng = point.latitude;
                double lat = point.longitude;
                mDatabase.insertMarker(lng, lat, mZoom);

                Toast.makeText(getApplicationContext(), "Marker inserted in db \nLatitude: " +lat +
                        "\nLongitude: " + lng, Toast.LENGTH_LONG).show();
            }
        });

        // Removes all markers on Long Click
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng point) {
                map.clear();
                mDatabase.removeAllMarkers();

                Toast.makeText(getApplicationContext(), "All Markers Removed.", Toast.LENGTH_SHORT).show();
            }
        });

        // Load all markers saved in the database
        List<Marker> markers = mDatabase.getAllMarkers();
        for(Marker m : markers){
            LatLng latlng = new LatLng(m.getLong(), m.getLat());
            map.addMarker(new MarkerOptions().position(latlng));
        }
    }
}


