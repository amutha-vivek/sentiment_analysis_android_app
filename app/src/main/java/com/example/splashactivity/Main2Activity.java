package com.example.splashactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    private Button login1;
    private Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        login1 = findViewById(R.id.login_button);
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivitylogin();
            }
        });
        signup = findViewById(R.id.signup_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivitysignup();
            }
        });

    }public void openactivitylogin(){
        Intent intent=new Intent(Main2Activity.this, login.class);
        startActivity(intent);

    }
    public void openactivitysignup() {
        Intent intent = new Intent(Main2Activity.this, signUp.class);
        startActivity(intent);

    }
}
