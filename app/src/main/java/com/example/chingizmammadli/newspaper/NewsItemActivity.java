package com.example.chingizmammadli.newspaper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
        final String image = getIntent().getStringExtra("image");

        TextView headlineText = (TextView) findViewById(R.id.news_headline);
        TextView authorText = (TextView) findViewById(R.id.news_author);
        TextView bodyText = (TextView) findViewById(R.id.news_body);
        TextView datetimeText = (TextView) findViewById(R.id.news_datetime);
        final ImageView imageView = (ImageView) findViewById(R.id.news_image);

        Log.v("NEWS","Clicked headline is: "+headline);

        headlineText.setText(headline.toString());
        authorText.setText(author);
        bodyText.setText(body);
        datetimeText.setText(date+" "+time);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Log.v("NEWS","Starts to load image: "+image);
                    URL url = new URL(image);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    final Bitmap newsBitmap = BitmapFactory.decodeStream(input);

                    imageView.post(new Runnable()
                    {
                        public void run()
                        {
                            if(newsBitmap !=null)
                            {
                                imageView.setImageBitmap(newsBitmap);
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Exception",e.getMessage());
                }
            }
        }).start();
    }
}
