package com.example.chingizmammadli.newspaper;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class NewsActivity extends AppCompatActivity{
    private String apiKey = "9864f2bd78824c42bac9ae912619e301";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        RetrieveData networkProcess = new RetrieveData();
        networkProcess.execute();

//        LinearLayout news_item = (LinearLayout) findViewById(R.id.news_item);
//        news_item.setOnClickListener(this);
    }


    class RetrieveData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            Log.v("NEWS","RetrieveData doInBackground");

            try{
                URL url = new URL("https://newsapi.org/v1/articles?source=techcrunch&apiKey=9864f2bd78824c42bac9ae912619e301");
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();

                try{
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;

                    while((line = bufferedReader.readLine()) != null){
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();

                    return stringBuilder.toString();
                }finally{
                    urlCon.disconnect();
                }
            }catch(Exception e){
                Log.e("ERROR",e.getMessage(),e);
                return null;
            }
        }

        protected void onPostExecute(String response){
            Log.v("NEWS","RetrieveData onPostExecute");
            if(response == null){
                response = "There was an error";
            }

            Log.i("INFO",response);

            try{
                String emptyString = "";
                ArrayList<News> newsArray = new ArrayList<>();
                JSONObject jObject = new JSONObject(response);
                String source = jObject.getString("source");

                JSONArray articles = jObject.getJSONArray("articles");

                for(int i=0;i<articles.length();i++){
//                    emptyString += articles.getJSONObject(i).getString("author")+"\n";
                    String headline = articles.getJSONObject(i).getString("title");
                    Log.v("NEWS","Adding article to array: "+headline);
                    String author = articles.getJSONObject(i).getString("author");
                    String body = articles.getJSONObject(i).getString("description");
                    String image = articles.getJSONObject(i).getString("urlToImage");
                    String publishedAt = articles.getJSONObject(i).getString("publishedAt");

                    String[] datetimeParts = publishedAt.split("T"); // String array, each element is text between dots

                    String date = datetimeParts[0];
                    String time = datetimeParts[1].substring(0,5);

                    newsArray.add(new News(headline,body,author,image,date,time));
                }

                Log.v("NEWS","newsArray length: "+newsArray.size());

//                newsArray = new ArrayList<>();

                ListView newsList = (ListView) findViewById(R.id.news_list);
                NewsAdapter adapter = new NewsAdapter(newsArray,getApplicationContext());
                newsList.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.v("NEWS","News clicked");

                        News news = (News) parent.getItemAtPosition(position);
                        Toast.makeText(getBaseContext(),"Will open news",Toast.LENGTH_SHORT).show();

                        Intent newsItemIntent = new Intent(NewsActivity.this,NewsItemActivity.class);
                        newsItemIntent.putExtra("headline",news.headline);
                        newsItemIntent.putExtra("author",news.author);
                        newsItemIntent.putExtra("body",news.body);
                        newsItemIntent.putExtra("date",news.date);
                        newsItemIntent.putExtra("time",news.time);
                        startActivity(newsItemIntent);

//                Toast.makeText(getBaseContext(), news.headline,Toast.LENGTH_LONG).show();

//                Snackbar.make(view, news.headline, Snackbar.LENGTH_LONG).setAction("No action", null).show();
                    }
                });
            }catch(Exception e){
                Log.e("ERROR",e.getMessage(),e);
            }
        }
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }
}
