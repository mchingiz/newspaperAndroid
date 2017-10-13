package com.example.chingizmammadli.newspaper;

public class News {
    private String headline;
    private String body;
    private String image;
    private String category;
    private String dateTime;

    public News(String headline){
        this.headline = headline;
    }

    public String getHeadline(){
        return this.headline;
    }

    public void setHeadline(String headline){
        this.headline = headline;
    }
}
