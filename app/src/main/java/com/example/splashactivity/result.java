package com.example.splashactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class result extends AppCompatActivity {
    private Button cool;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultView = findViewById(R.id.resultView);
        Intent intent = getIntent();
        String result = intent.getStringExtra("key");
        Log.i("Result", result);
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Getting JSON Array node
//        try {
//            JSONArray contacts = jsonObj.getJSONArray("contacts");
//            for (int i = 0; i < contacts.length(); i++) {
//                JSONObject c = contacts.getJSONObject(i);
//                String score = c.getString("scoref");
//                Log.i("SCORE", score);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        try {
            String scoref = jsonObj.getString("scoref");
            String negPercentage = jsonObj.getString("negPercentage");
            Log.i("Info", negPercentage);
            String posPercentage = jsonObj.getString("posPercentage");
            String neuPercentage = jsonObj.getString("neuPercentage");
            resultView.setText(scoref + "\n" + negPercentage + "\n" + posPercentage + "\n" + neuPercentage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cool=findViewById(R.id.result_exit);
        cool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivityfrontpage();
            }
        });
    }
    public void openactivityfrontpage(){
        Intent intent = new Intent(result.this, front_page.class);
        startActivity(intent);
        finish();

    }
}
