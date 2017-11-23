package com.example.chingizmammadli.newspaper;

import android.util.Log;

public class News {
    public String headline;
    public String body;
    public String image;
    public String author;
    public String time;

    public News(String headline,String author,String time){
        this.headline = headline;
        this.author = author;
        this.time = time;
        Log.v("NEWS","News constructor "+headline);
    }
}
