package com.example.myapplication.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.AddMovie;
import com.example.myapplication.MainActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DBhandler extends SQLiteOpenHelper {

    List movienames;
    List movieyears;
    List moviecomments;

    public List getMovienames() {
        return movienames;
    }

    public List getMovieyears() {
        return movieyears;
    }

    public List getMoviecomments() {
        return moviecomments;
    }

    public static final String DATABASE_NAME = "movie.db";

    public DBhandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_USER_CREATE_ENTRIES =
                "CREATE TABLE " + DataBaseMaster.Users.TABLE_NAME + " (" +
                        DataBaseMaster.Users._ID + " INTEGER PRIMARY KEY," +
                        DataBaseMaster.Users.COLUMN_NAME_USERNAME + " TEXT," +
                        DataBaseMaster.Users.COLUMN_NAME_PASSWORD + " TEXT," +
                        DataBaseMaster.Users.COLUMN_NAME_USERTYPE + " TEXT)";

        String SQL_MOVIE_CREATE_ENTRIES =
                "CREATE TABLE " + DataBaseMaster.Movie.TABLE_NAME + " (" +
                        DataBaseMaster.Movie._ID + " INTEGER PRIMARY KEY," +
                        DataBaseMaster.Movie.COLUMN_NAME_MOVIENAME + " TEXT," +
                        DataBaseMaster.Movie.COLUMN_NAME_MOVIEYEAR + " TEXT)";

        String SQL_COMMENTS_CREATE_ENTRIES =
                "CREATE TABLE " + DataBaseMaster.Comments.TABLE_NAME + " (" +
                        DataBaseMaster.Comments._ID + " INTEGER PRIMARY KEY," +
                        DataBaseMaster.Comments.COLUMN_NAME_MOVIENAME + " TEXT," +
                        DataBaseMaster.Comments.COLUMN_NAME_MOVIERATEING + " TEXT," +
                        DataBaseMaster.Comments.COLUMN_NAME_MOVIECOMMENTS + " TEXT)";

        sqLiteDatabase.execSQL(SQL_USER_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(SQL_MOVIE_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(SQL_COMMENTS_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean registerUser(String un,String pw){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DataBaseMaster.Users.COLUMN_NAME_USERNAME, un);
        values.put(DataBaseMaster.Users.COLUMN_NAME_PASSWORD,pw);
        values.put(DataBaseMaster.Users.COLUMN_NAME_USERTYPE,un);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DataBaseMaster.Users.TABLE_NAME, null, values);

        if(newRowId >= 1)
            return true;
        else
            return false;
    }

    public List loginUser(String un, String pw){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DataBaseMaster.Users.COLUMN_NAME_USERTYPE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = DataBaseMaster.Users.COLUMN_NAME_USERNAME + " = ? AND " + DataBaseMaster.Users.COLUMN_NAME_PASSWORD + " = ? ";
        String[] selectionArgs = { un, pw };


        Cursor cursor = db.query(
                DataBaseMaster.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        List userType = new ArrayList<>();
        while(cursor.moveToNext()) {
            String type = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseMaster.Users.COLUMN_NAME_USERTYPE));
            System.out.println(type);
            userType.add(type);
        }

        cursor.close();
        return userType;
    }

    public boolean addMovie(String name,String date){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DataBaseMaster.Movie.COLUMN_NAME_MOVIENAME, name);
        values.put(DataBaseMaster.Movie.COLUMN_NAME_MOVIEYEAR, date);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DataBaseMaster.Movie.TABLE_NAME, null, values);

        if(newRowId >= 1){
            return  true;
        }
        else
            return false;
    }

    public ArrayList viewMovies(){
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                DataBaseMaster.Movie.COLUMN_NAME_MOVIENAME
        };


// How you want the results sorted in the resulting Cursor
        String sortOrder =
                DataBaseMaster.Movie.COLUMN_NAME_MOVIENAME + " DESC";

        Cursor cursor = db.query(
                DataBaseMaster.Movie.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        ArrayList movies = new ArrayList<>();
        while(cursor.moveToNext()) {
            String movieNames = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseMaster.Movie.COLUMN_NAME_MOVIENAME));
            movies.add(movieNames);
        }
        cursor.close();
        return movies;
    }

    public boolean insertComments(String mName,String mRate,String comments){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DataBaseMaster.Comments.COLUMN_NAME_MOVIENAME,mName);
        values.put(DataBaseMaster.Comments.COLUMN_NAME_MOVIERATEING,mRate);
        values.put(DataBaseMaster.Comments.COLUMN_NAME_MOVIECOMMENTS,comments);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DataBaseMaster.Comments.TABLE_NAME, null, values);

        if(newRowId >= 0)
            return true;
        else
            return false;
    }

    public ArrayList viewComments(){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DataBaseMaster.Comments._ID,
                DataBaseMaster.Comments.COLUMN_NAME_MOVIECOMMENTS
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DataBaseMaster.Comments._ID + " DESC";

        Cursor cursor = db.query(
                DataBaseMaster.Comments.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList movieC = new ArrayList<>();
        while(cursor.moveToNext()) {
            String comments = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseMaster.Comments.COLUMN_NAME_MOVIECOMMENTS));
            movieC.add(comments);
        }

        cursor.close();
        return movieC;
    }


}
