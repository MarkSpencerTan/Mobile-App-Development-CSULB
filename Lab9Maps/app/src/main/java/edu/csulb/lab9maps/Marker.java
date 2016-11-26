package edu.csulb.lab9maps;

public class Marker {
    private float mLong;
    private float mLat;
    private int mZoom;

    public Marker(float lng, float lat, int z){
        mLong = lng;
        mLat = lat;
        mZoom = z;
    }

    public float getLong(){
        return mLong;
    }

    public float getLat(){
        return mLat;
    }

    public int getZoom(){
        return mZoom;
    }
}
