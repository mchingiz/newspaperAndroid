package com.example.chingizmammadli.newspaper;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Passwordun hinti basqa fontda cixirdi,onu duzeltmisem
            EditText password = (EditText) findViewById(R.id.login_password);
            password.setTypeface(Typeface.DEFAULT);
            password.setTransformationMethod(new PasswordTransformationMethod());
    }
}
