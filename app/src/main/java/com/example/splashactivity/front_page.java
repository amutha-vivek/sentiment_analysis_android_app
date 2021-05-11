package com.example.splashactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

//import com.example.splashactivity.SendDeviceDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class front_page extends AppCompatActivity {
     private Button result;
     private ImageButton home,history,about_us;
     private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
        textView = findViewById(R.id.tweet_line);
        result=findViewById(R.id.button2);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject postData = new JSONObject();
                try {
                    postData.put("keyword", textView.getText().toString().toLowerCase().trim());
//                    postData.put("password", password.getText().toString());

                    AsyncTask<String, Void, String> result = new SendKeyword().execute("https://immense-hamlet-81042.herokuapp.com/keyword", postData.toString());
                    Log.i("Final", result.get());
                    String resultf = result.get();

                    if (resultf.equals("No")){
                        finish();
                    }
                    else {
//                        String value="Hello world";
//                        Intent i = new Intent(front_page.this, result.class);

//                        startActivity(i);
                        openactivityresultpage(resultf);
//                        finish();
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
        history=findViewById(R.id.imageButton15);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivityhistorypage();
            }
        });
        about_us=findViewById(R.id.imageButton16);
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivityaboutpage();
            }
        });

    }
    public void openactivityresultpage(String resultf){
        Intent intent = new Intent(front_page.this, result.class);
        intent.putExtra("key",resultf);
        startActivity(intent);
    }
    public void openactivityhistorypage(){
        Intent intent = new Intent(front_page.this, history.class);
        startActivity(intent);

    }
    public void openactivityaboutpage(){
        Intent intent = new Intent(front_page.this, about_us.class);
        startActivity(intent);

    }
}

class SendKeyword extends AsyncTask<String, Void, String> {

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