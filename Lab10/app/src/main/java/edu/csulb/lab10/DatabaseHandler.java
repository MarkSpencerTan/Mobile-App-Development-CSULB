package edu.csulb.lab10;

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
    private static final String DATABASE_NAME = "spinnerExample";
    // Labels table name
    private static final String TABLE_LABELS = "labels";
    //Labels Table Column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // Category table create query
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_LABELS + "(" +
                KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + " TEXT)";
        db.execSQL(CREATE_CATEGORIES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LABELS);
        // Create again
        onCreate(db);
    }

    // Inserting new label into labels table
    public void insertLabel(String label){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, label);

        // Inserting Row
        db.insert(TABLE_LABELS, null, values);
        db.close();
    }

    // Getting all labels
    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_LABELS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looper through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                labels.add(cursor.getString(1));
            }while(cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning labels
        return labels;
    }







}
