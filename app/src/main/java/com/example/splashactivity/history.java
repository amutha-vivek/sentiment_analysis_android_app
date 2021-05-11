package com.example.splashactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class history extends AppCompatActivity {
    private  ImageButton home,history,about_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        home = findViewById(R.id.imageButton14);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivityhomepage();
            }
        });
        about_us = findViewById(R.id.imageButton16);
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivityaboutpage();
            }
        });

    }
    public void openactivityhomepage(){
        Intent intent = new Intent(this, front_page.class);
        startActivity(intent);
        finish();
    }

    public void openactivityaboutpage(){
        Intent intent = new Intent(this, about_us.class);
        startActivity(intent);
        finish();
    }
    }

