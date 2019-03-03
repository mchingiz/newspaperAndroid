package com.example.chingizmammadli.newspaper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public SQLiteDatabase db;
    private static int REGISTRATION_SUCCESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = openOrCreateDatabase("newspaper_users",MODE_PRIVATE,null);

        Button registerButton = (Button)findViewById(R.id.loginScreen_registerButton);
        registerButton.setOnClickListener(this);

        Button loginButton = (Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);


        // Passwordun hinti basqa fontda cixirdi,onu duzeltmisem
        EditText password = (EditText) findViewById(R.id.login_password);
        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());


        this.createTable();
    }

    private void createTable(){
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name text NOT NULL, surname text NOT NULL, email text NOT NULL, password text NOT NULL)");
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.loginScreen_registerButton: {
                Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivityForResult(registerIntent,REGISTRATION_SUCCESS);
                break;
            }
            case R.id.login_button: {
                this.hideKeyboard(MainActivity.this);

                EditText emailField = (EditText) findViewById(R.id.login_email);
                EditText passwordField = (EditText) findViewById(R.id.login_password);

                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                if(email == null || email.equals("") || password == null || password.equals("")){
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();

                }else{
                    Boolean authenticated = false;

                    Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=?",new String[]{email});

                    if(cursor != null & cursor.getCount() > 0){
                        String authenticatedUserName = null;
                        while(cursor.moveToNext()){
                            String rowEmail = cursor.getString(cursor.getColumnIndex("email"));
                            String rowPassword = cursor.getString(cursor.getColumnIndex("password"));

                            if(rowPassword.equals(password)){
                                authenticatedUserName = cursor.getString(cursor.getColumnIndex("name"));
                                authenticated = true;
                            }
                        }

                        if(authenticated){
                            Toast.makeText(this, "Welcome "+authenticatedUserName+"!", Toast.LENGTH_LONG).show();
                            Intent newsIntent = new Intent(MainActivity.this,CategoryActivity.class);
                            startActivity(newsIntent);
                            finish();
                        }else{
                            Toast.makeText(this, "Password is wrong", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this, "No user", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "You have successfully registered", Toast.LENGTH_LONG).show();
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
