package com.example.workshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db";
    public static final String TABLE_NAME = "workshop";
    public static final String col1 = "ID";
    public static final String col2 = "username";
    public static final String col3 = "password";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , username TEXT, password TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long adduser(String name, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", name);
        contentValues.put("password", pass);
        long val = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return val;
    }

    public boolean checkuser(String name, String pass){
        String col[] = {col1};
        SQLiteDatabase db = getReadableDatabase();
        String seletion = col2 + " = ?" + " and " + col3 + " = ?";
        String[] selargs = {name,pass};
        Cursor cursor = db.query(TABLE_NAME,col,seletion,selargs,null,null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count>0)
            return true;
        return false;
    }

    public boolean checkuser(String name){
        String col[] = {col1};
        SQLiteDatabase db = getReadableDatabase();
        String seletion = col2 + " = ?";
        String[] selargs = {name};
        Cursor cursor = db.query(TABLE_NAME,col,seletion,selargs,null,null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count>0)
            return true;
        return false;
    }

}
