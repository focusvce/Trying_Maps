package com.thenewboston.trying_maps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monster on 3/7/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Coordinator";

    // Contacts table name
    private static final String TABLE_COORDINATES = "mapCoordinates";

    // Contacts Table Columns names
    private static final String KEY_LONGI = "longi";
    private static final String KEY_LATI = "lati";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_COORDINATES + "("
                + KEY_LONGI + " TEXT,"
                + KEY_LATI + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COORDINATES);

        // Create tables again
        onCreate(db);
    }

    void addCoordinate(SqlCoordinates coordinates) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LONGI, coordinates.getLongi());
        values.put(KEY_LATI, coordinates.getLati());
        db.insert(TABLE_COORDINATES, null, values);
        db.close();
    }

    public List<SqlCoordinates> getAllCoordinates() {
        List<SqlCoordinates> coordinatesList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_COORDINATES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SqlCoordinates coordinates = new SqlCoordinates();
                coordinates.setLongi(cursor.getString(0));
                coordinates.setLati(cursor.getString(1));
                // Adding contact to list
                coordinatesList.add(coordinates);
            } while (cursor.moveToNext());

        }
        return coordinatesList;

    }

}