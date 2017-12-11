package com.example.chingizmammadli.newspaper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }

    public void openCategory(View v){
        String categoryId = getResources().getResourceName(v.getId());
        Log.v("TEST", categoryId);
//        Toast.makeText(getBaseContext(),getCategoryId(v),Toast.LENGTH_SHORT).show();

        Intent newsIntent = new Intent(CategoryActivity.this,NewsActivity.class);
        newsIntent.putExtra("category_id",getCategoryId(v));
        startActivity(newsIntent);
    }

    public String getCategoryId(View v){
        String viewId = getResources().getResourceName(v.getId());
        int pos = viewId.indexOf("category_id");
        return viewId.substring(pos+12);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }
}
