package com.example.chingizmammadli.newspaper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int REGISTRATION_SUCCESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Butun click listenerleri bu qayda ile elave olunsun ki, hamisini asagidaki
        // onClick funksiyasindan idare eleyek
        Button registerButton = (Button)findViewById(R.id.loginScreen_registerButton);
        registerButton.setOnClickListener(this);
        Button loginButton = (Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);


        // Passwordun hinti basqa fontda cixirdi,onu duzeltmisem
            EditText password = (EditText) findViewById(R.id.login_password);
            password.setTypeface(Typeface.DEFAULT);
            password.setTransformationMethod(new PasswordTransformationMethod());
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.loginScreen_registerButton: {
//                Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();

                Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivityForResult(registerIntent,REGISTRATION_SUCCESS);
                break;
            }
            case R.id.login_button: {
                Toast.makeText(this, "Clicked on Login", Toast.LENGTH_LONG).show();

                Intent registerIntent = new Intent(MainActivity.this,NewsActivity.class);
                startActivity(registerIntent);
                finish();
                break;
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "You have successfully registered", Toast.LENGTH_LONG).show();
    }
}
