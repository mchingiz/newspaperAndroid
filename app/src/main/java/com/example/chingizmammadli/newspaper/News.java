package com.example.chingizmammadli.newspaper;

import android.util.Log;

public class News {
    public String headline;
    public String body;
    public String image;
    public String author;
    public String date;
    public String time;

    public News(String headline,String body,String author,String image,String date,String time){
        this.headline = headline;
        this.body = body;
        this.author = author;
        this.image = image;
        this.date = date;
        this.time = time;
        Log.v("NEWS","News constructor "+headline);
    }
}
