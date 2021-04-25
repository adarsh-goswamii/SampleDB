package com.example.sampledb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerBook.DeleteBook {

    RecyclerView recyclerView;
    RecyclerBook recyclerBook;

    @Override
    public void onDeleteBookResult(int bookId) {
        new DeleteBookById().execute(bookId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetAllBooks().execute();
        recyclerView= findViewById(R.id.recyclerView);
        recyclerBook= new RecyclerBook(this);

        recyclerView.setAdapter(recyclerBook);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private class GetAllBooks extends AsyncTask<Void, Void, ArrayList<Book>> {

        private DataBaseHelper dataBaseHelper;
        @Override
        protected ArrayList<Book> doInBackground(Void... voids) {
            try {
                SQLiteDatabase sqLiteDatabase= dataBaseHelper.getReadableDatabase();
//                String sql= "SELECT * FROM library;";
                Cursor cursor= sqLiteDatabase.query("library", null, null, null, null, null, null );
                if(cursor!= null) {
                    if(cursor.moveToFirst()) {
                        ArrayList<Book> arrayList= new ArrayList<>();
                        int idIndex= cursor.getColumnIndex("id");
                        int imageIndex= cursor.getColumnIndex("image_url");
                        int nameIndex= cursor.getColumnIndex("name");
                        int descIndex= cursor.getColumnIndex("description");

                        for(int i= 0; i<cursor.getCount();i++) {
                            Book b= new Book();
                            b.setId(cursor.getInt(idIndex));
                            b.setDesc(cursor.getString(descIndex));
                            b.setImage_url(cursor.getString(imageIndex));
                            b.setName(cursor.getString(nameIndex));
                            arrayList.add(b);

                            cursor.moveToNext();
                        }

                        cursor.close();
                        sqLiteDatabase.close();
                        return arrayList;
                    }
                    else {
                        cursor.close();
                        sqLiteDatabase.close();
                    }
                }
                else sqLiteDatabase.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dataBaseHelper= new DataBaseHelper(MainActivity.this);
            Log.e("tag", "preExecute executed");
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            if(books!= null) recyclerBook.setArrayList(books);
            else recyclerBook.setArrayList(new ArrayList<Book>());
        }
    }

    class DeleteBookById extends AsyncTask<Integer, Void, Boolean> {

        DataBaseHelper dataBaseHelper;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dataBaseHelper= new DataBaseHelper(MainActivity.this);
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            try{
                SQLiteDatabase db= dataBaseHelper.getWritableDatabase();
                if(db!= null) {
                    int row= db.delete("library", "id=?", new String[]{integers[0]+""});

                    if(row>0) {
                        db.close();
                        return true;
                    }
                }
                else
                    db.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if(aBoolean) {
                Toast.makeText(MainActivity.this, "Book successfully deleted", Toast.LENGTH_SHORT).show();
                new GetAllBooks().execute();
            }
            else {
                Toast.makeText(MainActivity.this, "Something went wrong try again", Toast.LENGTH_SHORT).show();
            }
        }
    }
}