package com.example.chingizmammadli.newspaper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chingizmammadli.newspaper.News;
import com.example.chingizmammadli.newspaper.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.R.attr.data;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;
import static com.example.chingizmammadli.newspaper.R.id.response;

public class NewsAdapter extends ArrayAdapter<News> implements View.OnClickListener{
    private ArrayList<News> newsArray;
    Context mContext;

    private static class NewsHolder{
        TextView headline;
        TextView time;
        TextView author;
        ImageView image;
    }

    public NewsAdapter(ArrayList<News> data, Context context){
        super(context, R.layout.news_item, data);
        this.newsArray = data;
        this.mContext = context;
    }

    @Override
    public boolean isEnabled(int position){
        return true;
    }

    @Override
    public void onClick(View view) {
        Log.v("NEWS","Clicked. listener from newsAdapter");
        int index = (Integer) view.getTag();
        Object newsObject = getItem(index);
        News news = (News) newsObject;

        Snackbar.make(view, "Clicked ", Snackbar.LENGTH_LONG).setAction("No action", null).show();
        Toast.makeText(getContext().getApplicationContext(), news.headline,Toast.LENGTH_LONG).show();
    }

//    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v("NEWS","getView "+position);
        // Get the data item for this position
        final News newsObject = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final NewsHolder newsHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            newsHolder = new NewsHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.news_item, parent, false);
            newsHolder.headline = (TextView) convertView.findViewById(R.id.news_item_headline);
            newsHolder.author = (TextView) convertView.findViewById(R.id.news_item_author);
            newsHolder.time = (TextView) convertView.findViewById(R.id.news_item_time);
            newsHolder.image = (ImageView) convertView.findViewById(R.id.news_item_image);

            result=convertView;

            convertView.setTag(newsHolder);
        } else {
            newsHolder = (NewsHolder) convertView.getTag();
            result=convertView;
        }
//        lastPosition = position;

//        Log.v("NEWS","line 71 "+newsObject.headline);
        Log.v("NEWS","Image url "+newsObject.image);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(newsObject.image+"?w=141&h=102&crop=1");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    final Bitmap newsBitmap = BitmapFactory.decodeStream(input);

                    newsHolder.image.post(new Runnable()
                    {
                        public void run()
                        {
                            if(newsBitmap !=null)
                            {
                                newsHolder.image.setImageBitmap(newsBitmap);
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Exception",e.getMessage());
                }
            }
        }).start();
        newsHolder.headline.setText(newsObject.headline);
        newsHolder.author.setText(newsObject.author);
        newsHolder.time.setText(newsObject.time);
//        newsHolder.txtType.setText(newsObject.getType());
//        newsHolder.txtVersion.setText(newsObject.getVersion_number());
//        viewHolder.info.setOnClickListener(this);
//        newsHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }

    public static Bitmap getBitmapFromUrl(String src){
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
}
