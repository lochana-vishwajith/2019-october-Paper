package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DataBase.DBhandler;

public class MovieOverview extends AppCompatActivity {

    ListView commentsList;
    EditText comment;
    Button addComment;
    SeekBar seekBar;
    TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_overview);

        Intent intent = getIntent();

        final String movieName = intent.getStringExtra("nameOfSelectedMovie");

        commentsList = findViewById(R.id.commList);
        comment = findViewById(R.id.comments);
        addComment = findViewById(R.id.commSubmit);
        seekBar = findViewById(R.id.seekBar);
        name = findViewById(R.id.movieName);

        System.out.println("apu nama"+movieName);
        name.setText(movieName);
        final int[] rate = new int[1];

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rate[0] = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        System.out.println("rate"+rate[0]);

        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBhandler dBhandler = new DBhandler(getApplicationContext());
                
                if(dBhandler.insertComments(movieName,String.valueOf(rate[0]),comment.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(), "Comment added", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "comment not added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}