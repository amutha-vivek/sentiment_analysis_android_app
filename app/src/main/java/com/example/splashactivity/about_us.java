package com.example.splashactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class about_us extends AppCompatActivity {
    private ImageButton home,history,about_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        home= findViewById(R.id.imageButton14);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivityhomepage();
            }
        });
        history = findViewById(R.id.imageButton15);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivityhistorypage();
            }
        });
    }
    public void openactivityhomepage(){
        Intent intent = new Intent(this, front_page.class);
        startActivity(intent);
        finish();
    }
    public void openactivityhistorypage(){
        Intent intent = new Intent(this,history.class);
        startActivity(intent);
        finish();
    }
    }
