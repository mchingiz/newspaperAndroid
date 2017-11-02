package com.example.chingizmammadli.newspaper;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Chingiz Mammadli on 11/2/2017.
 */

public class RetrieveData extends AsyncTask<Void, Void, String> {

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

//        TextView textView = (TextView) ;

    }
}
