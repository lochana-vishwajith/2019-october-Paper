package com.example.myapplication.DataBase;

import android.provider.BaseColumns;

public final class DataBaseMaster {
    private DataBaseMaster() {}

    public static final class Users implements BaseColumns{

        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_USERTYPE = "usertype";


    }

    public static final class Movie implements BaseColumns{

        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_NAME_MOVIENAME = "Movie_Name";
        public static final String COLUMN_NAME_MOVIEYEAR = "Movie_Year";

    }

    public static final class Comments implements BaseColumns{

        public static final String TABLE_NAME = "comments";
        public static final String COLUMN_NAME_MOVIENAME = "Movie_Name";
        public static final String COLUMN_NAME_MOVIERATEING = "Movie_rating";
        public static final String COLUMN_NAME_MOVIECOMMENTS = "Movie_comments";

    }
}
