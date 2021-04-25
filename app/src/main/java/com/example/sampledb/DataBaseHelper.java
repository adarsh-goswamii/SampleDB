package com.example.sampledb;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME= "database2";
    private static final int DB_VERSION= 1;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e("tag", "Created");
        String sql= "CREATE TABLE library (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, image_url TEXT);";
        sqLiteDatabase.execSQL(sql);

        sql= "INSERT INTO library(name, description, image_url) values('Some Girls do', 'desc', 'https://i.pinimg.com/474x/99/07/60/99076041b7f56f9835cc57d60c75e4c8.jpg');";
        sqLiteDatabase.execSQL(sql);

        sql= "INSERT INTO library(name, description, image_url) values('The upstairs room', 'desc', 'https://i.pinimg.com/474x/e9/1a/18/e91a1860d26a0c67708f8d35c38284ff.jpg');";
        sqLiteDatabase.execSQL(sql);

        sql= "INSERT INTO library(name, description, image_url) values('Harry Potter', 'desc', 'https://i.pinimg.com/474x/bb/a0/1d/bba01d56f4c437def7a642e0ed390688.jpg');";
        sqLiteDatabase.execSQL(sql);

        sql= "INSERT INTO library(name, description, image_url) values('xoxo', 'desc', 'https://i.pinimg.com/474x/27/ee/b0/27eeb066373894d6340fe670c96e3a92.jpg');";
        sqLiteDatabase.execSQL(sql);

        sql= "INSERT INTO library(name, description, image_url) values('The girl in red', 'desc', 'https://i.pinimg.com/474x/8e/b9/63/8eb963b5794dd3f9aeb7cf19a59e659f.jpg');";
        sqLiteDatabase.execSQL(sql);

        sql= "INSERT INTO library(name, description, image_url) values('Like home', 'desc', 'https://i.pinimg.com/474x/9a/e5/52/9ae552dbb20cfbe257acb08a64d90c5c.jpg');";
        sqLiteDatabase.execSQL(sql);

        sql= "INSERT INTO library(name, description, image_url) values('Berta isla', 'desc', 'https://i.pinimg.com/474x/9c/64/1b/9c641b85aae15110581a79ee73895244.jpg');";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
