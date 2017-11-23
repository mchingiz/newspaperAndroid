package com.example.chingizmammadli.newspaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class NewsItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item);

        String headline = getIntent().getStringExtra("headline");
        String author = getIntent().getStringExtra("author");
        String body = getIntent().getStringExtra("body");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");

        Log.v("NEWS","Clicked headline is: "+headline);

        TextView headlineText = (TextView) findViewById(R.id.news_headline);
        TextView authorText = (TextView) findViewById(R.id.news_author);
        TextView bodyText = (TextView) findViewById(R.id.news_body);
        TextView datetimeText = (TextView) findViewById(R.id.news_datetime);

        headlineText.setText(headline.toString());
        authorText.setText(author);
        bodyText.setText(body);
        datetimeText.setText(date+" "+time);
    }
}
