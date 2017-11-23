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

    class RetrieveData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

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
            if(response == null){
                response = "There was an error";
            }

            Log.i("INFO",response);
            
            try{
                String emptyString = "";
                JSONObject jObject = new JSONObject(response);
                String source = jObject.getString("source");

                JSONArray articles = jObject.getJSONArray("articles");

                for(int i=0;i<articles.length();i++){
                    emptyString += articles.getJSONObject(i).getString("author")+"\n";
                }

                TextView textView = (TextView) findViewById(R.id.response);
                textView.setText(emptyString);
            }catch(Exception e){
                Log.e("ERROR",e.getMessage(),e);
            }
        }
    }
}
