package com.example.workshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Databaseevents extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "eventdb";
    public static final String TABLE_NAME = "eventtable";
    public static final String col1 = "ID";
    public static final String col2 = "event_name";
    public Databaseevents(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT , event_name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    //hard coring the events in the database

    public long addEvents(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put(col2,name);
        long val = db.insert(TABLE_NAME,null, contentValues);
        db.close();
        return val;
    }

    public ArrayList<String> retrieve() {
        ArrayList<String> al = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                al.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return al;
    }

    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
        db.close();
    }
}
