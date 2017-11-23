package com.example.chingizmammadli.newspaper;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button getData = (Button) findViewById(R.id.getDataButton);
        getData.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.getDataButton: {
                RetrieveData networkProcess = new RetrieveData();
                networkProcess.execute();
            }
        }
    }

}
