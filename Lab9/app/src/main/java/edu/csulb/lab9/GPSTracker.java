package edu.csulb.lab9;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by Mark on 11/19/2016.
 */

public class GPSTracker extends Service implements LocationListener{
    private final Context mContext;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;

    Location mLocation;
    double mLatitude;
    double mLongitude;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    protected LocationManager mLocManager;
    public GPSTracker(Context context){
        this.mContext = context;
        getLocation();
    }
    public Location getLocation(){
        try{
            mLocManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = mLocManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if(!isGPSEnabled && !isNetworkEnabled){
                Toast.makeText(getApplicationContext(), "GPS Not enabled", Toast.LENGTH_LONG).show();
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                }
            }
            else{
                this.canGetLocation = true;
                if(isNetworkEnabled){
                    mLocManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if(mLocManager != null){
                        mLocation = mLocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if(mLocation != null){
                            mLatitude = mLocation.getLatitude();
                            mLongitude = mLocation.getLongitude();
                        }
                    }
                }
                if(isGPSEnabled){
                    if(mLocation == null){
                        mLocManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        if(mLocManager != null){
                            mLocation = mLocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if(mLocation != null){
                                mLatitude = mLocation.getLatitude();
                                mLongitude = mLocation.getLongitude();
                            }
                        }
                    }

                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return mLocation;
    }

    public double getLatitude(){
        if(mLocation != null){
            mLatitude = mLocation.getLatitude();
        }
        return mLatitude;
    }

    public double getLongitude(){
        if(mLocation != null){
            mLongitude = mLocation.getLongitude();
        }
        return mLongitude;
    }

    public boolean canGetLocation(){
        return this.canGetLocation;
    }



    public void stopUsingGPS(){
        if (mLocManager!= null) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocManager.removeUpdates(GPSTracker.this);
            }
        }
    }

    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("GPS Settings");
        alertDialog.setMessage("GPS is not enabled. Do you want to go to Settings?");
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });

        alertDialog.show();
    }


    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }
    public void onLocationChanged(Location location) {

    }
    public IBinder onBind(Intent intent)
    {
        return null;
    }

}
