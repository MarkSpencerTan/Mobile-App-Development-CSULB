package edu.csulb.lab9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mLocationButton;
    GPSTracker mGps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationButton = (Button) findViewById(R.id.location_button);
        mLocationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mGps = new GPSTracker(MainActivity.this);
                if(mGps.canGetLocation()){
                    double latitude = mGps.getLatitude();
                    double longitude = mGps.getLongitude();
                    Toast.makeText(getApplicationContext(), "Your Location is: \n Lat: "
                            + latitude +"\n Long: "+ longitude, Toast.LENGTH_LONG).show();
                }
                else{
                    mGps.showSettingsAlert();
                }
            }
        });
    }
}
