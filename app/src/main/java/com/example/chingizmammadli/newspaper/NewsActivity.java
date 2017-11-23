package com.example.chingizmammadli.newspaper;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener {
    private String apiKey = "9864f2bd78824c42bac9ae912619e301";
    ListView newsList;
    ArrayList<News> newsArray;
    private static NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsList = (ListView)findViewById(R.id.news_list);

        newsArray = new ArrayList<>();

        newsArray.add(new News("headline 3","Chingiz","11:11"));
        newsArray.add(new News("headline 1","Ilyas","11:22"));
        newsArray.add(new News("headline 2","Ayaz","11:33"));

        adapter = new NewsAdapter(newsArray,getApplicationContext());

        newsList.setAdapter(adapter);

        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("NEWS","News clicked");

                News news = (News) parent.getItemAtPosition(position);
                Toast.makeText(getBaseContext(),"Will open news",Toast.LENGTH_SHORT).show();

                Intent newsItemIntent = new Intent(NewsActivity.this,NewsItemActivity.class);
                newsItemIntent.putExtra("headline",news.headline);
                newsItemIntent.putExtra("author",news.author);
                newsItemIntent.putExtra("time",news.time);
                startActivity(newsItemIntent);

//                Toast.makeText(getBaseContext(), news.headline,Toast.LENGTH_LONG).show();

//                Snackbar.make(view, news.headline, Snackbar.LENGTH_LONG).setAction("No action", null).show();
            }
        });

//        LinearLayout news_item = (LinearLayout) findViewById(R.id.news_item);
//        news_item.setOnClickListener(this);
    }

    private void getNews() throws IOException {
//        HttpClient client = new DefaultHttpClient();
//        HttpGet request = new HttpGet();
//        request.setURI(new URI("https://www.googleapis.com/shopping/search/v1/public/products/?key={my_key}&country=&q=t-shirts&alt=json&rankByrelevancy="));
//        response = client.execute(request);
    }

    public void onClick(View v){
//        switch(v.getId()){
//            case R.id.news_item: {
//                Toast.makeText(this,"Will open news",Toast.LENGTH_SHORT).show();
//
//                Intent newsItemIntent = new Intent(NewsActivity.this,NewsItemActivity.class);
//                startActivity(newsItemIntent);
//            }
//        }
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }

    public void openNewsItem(View v){
        Toast.makeText(this,"Will open news",Toast.LENGTH_SHORT).show();

        Intent newsItemIntent = new Intent(NewsActivity.this,NewsItemActivity.class);
        startActivity(newsItemIntent);

    }
}
