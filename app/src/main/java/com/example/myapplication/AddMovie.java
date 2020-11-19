package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DataBase.DBhandler;

public class AddMovie extends AppCompatActivity {

    EditText mName,mYear;
    Button madd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        
        mName = findViewById(R.id.movieName);
        mYear = findViewById(R.id.movieYear);
        
        madd = findViewById(R.id.movieAdd);
        
        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBhandler dBhandler = new DBhandler(getApplicationContext());
                
                if(dBhandler.addMovie(mName.getText().toString().trim(),mYear.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Movie Successfully added", Toast.LENGTH_SHORT).show();
                    mName.setText("");
                    mYear.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Movie not added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}