package com.example.accgame;

import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "com.example.accgame.MainActivity";
    private PowerManager.WakeLock mWakeLock;
    private SimulationView mSimulationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PowerManager mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, TAG);

        mSimulationView = (SimulationView) findViewById(R.id.simview);
    }
    @Override
    protected void onResume(){
        super.onResume();
        mWakeLock.acquire();
        mSimulationView.startSimulation();
    }
    @Override
    protected void onPause(){
        super.onPause();
        mWakeLock.release();
        mSimulationView.stopSimulation();
    }
}
