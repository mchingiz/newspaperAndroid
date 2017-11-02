package com.example.chingizmammadli.newspaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

            }
        }
    }
}
