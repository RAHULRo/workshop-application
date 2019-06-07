package com.example.workshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class user_data extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "user_db";
    public static final String TABLE_NAME = "usertable";
    public static final String col1 = "ID";
    public static final String col2 = "username";
    public static final String col3 = "userchoice";


    public user_data(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, userchoice TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public long adddata(String name,String userchoice) {
        boolean b = checkchoice(name,userchoice);
        long val=0;
        if(b==false){


        }else{
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", name);
        contentValues.put("userchoice",userchoice);
        val = db.insert(TABLE_NAME, null, contentValues);
        db.close();}
        return val;
    }

    public ArrayList<String> retrieve(String name){

        ArrayList<String> al = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " Where username='" + name + "'", null);
        if (cursor.moveToFirst()){
            do{
                al.add(cursor.getString(2));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
       return al;
    }

    public boolean checkchoice(String name,String choice){
        String col[] = {col1};
        SQLiteDatabase db = getReadableDatabase();
        String seletion = col2 + " = ?" + " and " + col3 + " = ?";
        String[] selargs = {name,choice};
        Cursor cursor = db.query(TABLE_NAME,col,seletion,selargs,null,null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count>0)
            return false;
        return true;
    }
}
