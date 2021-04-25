package com.example.sampledb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookDesc extends AppCompatActivity {

    private ImageView img;
    private TextView name, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_desc);

        img= findViewById(R.id.img);
        name= findViewById(R.id.name);
        desc= findViewById(R.id.desc);

        Intent intent= getIntent();
        if(intent!= null) {
            int id= intent.getIntExtra("book_id", -1);
            if(id!= -1) {
                new GetBookById().execute(id);
            }
        }
    }

    private class GetBookById extends AsyncTask<Integer, Void, Book> {

        DataBaseHelper dataBaseHelper;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dataBaseHelper= new DataBaseHelper(BookDesc.this);
        }

        @Override
        protected Book doInBackground(Integer... integers) {
            try{
                SQLiteDatabase sqLiteDatabase= dataBaseHelper.getReadableDatabase();
                if(sqLiteDatabase!= null) {
                    String sql= "SELECT * FROM library WHERE id=?";
                    Cursor cursor= sqLiteDatabase.rawQuery(sql, new String[]{integers[0]+""});
                    if(cursor!= null) {
                        if(cursor.moveToFirst()) {

                            int idIndex= cursor.getColumnIndex("id");
                            int idName= cursor.getColumnIndex("name");
                            int idDesc= cursor.getColumnIndex("description");
                            int idImg= cursor.getColumnIndex("image_url");

                            Book ret= new Book();
                            ret.setId(cursor.getInt(idIndex));
                            ret.setName(cursor.getString(idName));
                            ret.setDesc(cursor.getString(idDesc));
                            ret.setImage_url(cursor.getString(idImg));

                            cursor.close();
                            sqLiteDatabase.close();
                            return ret;
                        }
                        else {
                            cursor.close();
                            sqLiteDatabase.close();
                        }
                    }
                    else sqLiteDatabase.close();
                }
                else sqLiteDatabase.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Book book) {
            super.onPostExecute(book);

            if(book!= null) {
                Glide.with(BookDesc.this).asBitmap().load(book.getImage_url()).into(img);
                name.setText(book.getName());
                desc.setText(book.getDesc());
            }
        }
    }
}