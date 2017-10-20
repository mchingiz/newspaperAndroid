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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Butun click listenerleri bu qayda ile elave olunsun ki, hamisini asagidaki
        // onClick funksiyasindan idare eleyek
        Button registerButton = (Button)findViewById(R.id.submit_button);
        registerButton.setOnClickListener(this);

        // Passwordun hinti basqa fontda cixirdi,onu duzeltmisem
        EditText password = (EditText) findViewById(R.id.register_password);
        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());

        EditText passwordConfirmation = (EditText) findViewById(R.id.register_passwordConfirmation);
        passwordConfirmation.setTypeface(Typeface.DEFAULT);
        passwordConfirmation.setTransformationMethod(new PasswordTransformationMethod());
    }

    public void onClick(View v){

        switch(v.getId()){
            case R.id.submit_button: {
//                Toast.makeText(this, "Clicked on submit", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                setResult(1,intent);
                finish();
            }
        }
    }
}
