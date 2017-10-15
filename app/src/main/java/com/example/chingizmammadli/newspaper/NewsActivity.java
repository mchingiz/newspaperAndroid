package com.example.chingizmammadli.newspaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NewsActivity extends AppCompatActivity {
    private String apiKey = "9864f2bd78824c42bac9ae912619e301";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }

    private void getNews() throws IOException {
//        HttpClient client = new DefaultHttpClient();
//        HttpGet request = new HttpGet();
//        request.setURI(new URI("https://www.googleapis.com/shopping/search/v1/public/products/?key={my_key}&country=&q=t-shirts&alt=json&rankByrelevancy="));
//        response = client.execute(request);
    }
}
