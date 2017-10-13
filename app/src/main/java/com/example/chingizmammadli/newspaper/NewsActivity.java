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
        String url = "https://newsapi.org/v1/articles?apiKey="+apiKey;
        URL newsApiEndpoint = new URL(url);

        HttpsURLConnection myConnection = (HttpsURLConnection) newsApiEndpoint.openConnection();

        myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

        if (myConnection.getResponseCode() == 200) {
            // Success
            // Further processing here
        } else {
            InputStream responseBody = myConnection.getInputStream();
            InputStreamReader responseBodyReader =
                    new InputStreamReader(responseBody, "UTF-8");
            JsonReader jsonReader = new JsonReader(responseBodyReader);
            String server_response = EntityUtils.toString(response.getEntity());

            //Process json

            jsonReader.close();
        }

        myConnection.disconnect();
    }
}
