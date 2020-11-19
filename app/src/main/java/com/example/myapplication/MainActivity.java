package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DataBase.DBhandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.userName);
        password= findViewById(R.id.password);

        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBhandler dBhandler = new DBhandler(getApplicationContext());

                List userType = dBhandler.loginUser(username.getText().toString().trim(),password.getText().toString().trim());



                if(!userType.isEmpty()){
                    if(userType.get(0).equals("admin")){

                        Toast.makeText(getApplicationContext(), "Logged in as an admin", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,AddMovie.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Logged in as a customer", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,MovieList.class);
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBhandler dBhandler = new DBhandler(getApplicationContext());

                if(dBhandler.registerUser(username.getText().toString().trim(),password.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "User not registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}