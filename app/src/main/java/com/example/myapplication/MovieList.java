package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.DataBase.DBhandler;

import java.util.ArrayList;
import java.util.List;

public class MovieList extends AppCompatActivity {

    ListView movieList;
    ArrayList movieName;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        movieList = findViewById(R.id.movieList);

        final DBhandler dBhandler = new DBhandler(getApplicationContext());

        movieName = dBhandler.viewMovies();

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,movieName);

        movieList.setAdapter(adapter);

        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String id = movieList.getItemAtPosition(i).toString();

                System.out.println("movie name" + id);
                Intent intent = new Intent(MovieList.this,MovieOverview.class);
                intent.putExtra("nameOfSelectedMovie",id);
                startActivity(intent);
            }
        });



    }
}