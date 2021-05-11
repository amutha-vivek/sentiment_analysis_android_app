package com.example.splashactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class login extends AppCompatActivity{
     private Button next;
     private EditText email,password;
     private Button signupswitchtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.login_user);
        password = findViewById(R.id.login_pass);
        signupswitchtext=findViewById(R.id.signupswitchtext);
        signupswitchtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivitysignuppage();
            }
        });
        next = findViewById(R.id.login_exit);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                openactivityfrontpage();
//                finish();
                JSONObject postData = new JSONObject();
                try {
                    postData.put("email", email.getText().toString());
                    postData.put("password", password.getText().toString());

                    AsyncTask<String, Void, String> result = new SendDeviceDetails().execute("https://immense-hamlet-81042.herokuapp.com/login", postData.toString());
                    Log.i("Final", result.get());
                    String resultf = result.get();
                    if (resultf.equals("Yes")) {
                        openactivityfrontpage();
                        finish();
                    }
                    else if (resultf.equals("No")){
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void openactivityfrontpage(){
        Intent intent = new Intent(login.this, front_page.class);
        startActivity(intent);
        finish();
    }
    public void openactivitysignuppage(){
        Intent intent = new Intent(login.this, signUp.class);
        startActivity(intent);
        finish();


    }
}

class SendDeviceDetails extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {

        String data = "";

        HttpURLConnection httpURLConnection = null;
        try {

            httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes("PostData=" + params[1]);
            wr.flush();
            wr.close();

            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);

            int inputStreamData = inputStreamReader.read();
            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data += current;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        Log.i("Info", data);
        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
//        if (result.equals("Yes"))
        Log.e("TAG", result); // this is expecting a response code to be sent from your server upon receiving the POST data
    }
}
