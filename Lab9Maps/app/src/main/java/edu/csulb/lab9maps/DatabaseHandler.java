package edu.csulb.lab9maps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 11/25/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // Db Version
    private static final int DATABASE_VERSION = 1;
    // Db Name
    private static final String DATABASE_NAME = "markersDatabase";
    // Labels table name
    private static final String TABLE_NAME = "Markers";
    //Labels Table Column names
    private static final String KEY_ID = "id";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_ZOOM = "zoom";


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // Category table create query
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + "INTEGER PRIMARY KEY," + KEY_LONGITUDE + " FLOAT, " +
                KEY_LATITUDE + " FLOAT, " + KEY_ZOOM + " INTEGER)";
        db.execSQL(CREATE_CATEGORIES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create again
        onCreate(db);
    }

    // Inserting new label into labels table
    public void insertMarker(double lng,double lat, int zoom ){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LONGITUDE, lng);
        values.put(KEY_LATITUDE, lat);
        values.put(KEY_ZOOM, zoom);

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Getting all labels
    public List<Marker> getAllMarkers(){
        List<Marker> markers = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looper through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                markers.add(new Marker(cursor.getFloat(1), cursor.getFloat(2), cursor.getInt(3)));
            }while(cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning labels
        return markers;
    }

    public void removeAllMarkers(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME);
    }







}
