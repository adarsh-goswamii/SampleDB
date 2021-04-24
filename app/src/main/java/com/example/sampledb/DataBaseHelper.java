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

        sql= "INSERT INTO library(name, description, image_url) values('name', 'desc', 'https://i.pinimg.com/564x/23/00/50/230050f9dc1bd5345cd1d8014bc5e23b.jpg');";
        sqLiteDatabase.execSQL(sql);

        sql= "INSERT INTO library(name, description, image_url) values('name', 'desc', 'https://i.pinimg.com/564x/23/00/50/230050f9dc1bd5345cd1d8014bc5e23b.jpg');";
        sqLiteDatabase.execSQL(sql);

        sql= "INSERT INTO library(name, description, image_url) values('name', 'desc', 'https://i.pinimg.com/564x/23/00/50/230050f9dc1bd5345cd1d8014bc5e23b.jpg');";
        sqLiteDatabase.execSQL(sql);

        sql= "INSERT INTO library(name, description, image_url) values('name', 'desc', 'https://i.pinimg.com/564x/23/00/50/230050f9dc1bd5345cd1d8014bc5e23b.jpg');";
        sqLiteDatabase.execSQL(sql);

        sql= "INSERT INTO library(name, description, image_url) values('name', 'desc', 'https://i.pinimg.com/564x/23/00/50/230050f9dc1bd5345cd1d8014bc5e23b.jpg');";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
