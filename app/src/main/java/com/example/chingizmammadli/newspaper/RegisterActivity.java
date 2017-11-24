package com.example.chingizmammadli.newspaper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    public SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Butun click listenerleri bu qayda ile elave olunsun ki, hamisini asagidaki
        // onClick funksiyasindan idare eleyek
        Button registerButton = (Button)findViewById(R.id.submit_button);
        registerButton.setOnClickListener(this);

        db = openOrCreateDatabase("dictionary",MODE_PRIVATE,null);

        // Passwordun hinti basqa fontda cixirdi,onu duzeltmisem
        EditText password = (EditText) findViewById(R.id.register_password);
        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());

        EditText passwordConfirmation = (EditText) findViewById(R.id.register_passwordConfirmation);
        passwordConfirmation.setTypeface(Typeface.DEFAULT);
        passwordConfirmation.setTransformationMethod(new PasswordTransformationMethod());
    }

    public void onClick(View v){

        EditText nameField = (EditText) findViewById(R.id.register_name);
        EditText surnameField = (EditText) findViewById(R.id.register_surname);
        EditText emailField = (EditText) findViewById(R.id.register_email);
        EditText passwordField = (EditText) findViewById(R.id.register_password);
        EditText passwordConfirmationField = (EditText) findViewById(R.id.register_passwordConfirmation);

        String name = nameField.getText().toString().trim();
        String surname = surnameField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String passwordConfirmation = passwordConfirmationField.getText().toString().trim();

        switch(v.getId()){
            case R.id.submit_button: {
                Log.v("NEWS",name+surname+email+password+passwordConfirmation);

                if(name == null || name.equals("")){
                    Toast.makeText(this, "Name field cannot be empty", Toast.LENGTH_SHORT).show();
                }else if(surname == null || surname.equals("")){
                    Toast.makeText(this, "Surname field cannot be empty", Toast.LENGTH_SHORT).show();
                }else if(email == null || email.equals("")){
                    Toast.makeText(this, "Email field cannot be empty", Toast.LENGTH_SHORT).show();
                }else if((password == null || password.equals("")) || (passwordConfirmation == null || passwordConfirmation.equals(""))){
                    Toast.makeText(this, "Password and confirmations fields cannot be empty", Toast.LENGTH_SHORT).show();
                }else if(!passwordConfirmation.equals(password)){
                    Toast.makeText(this, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
                }else if(!this.isEmailValid(email)){
                    Toast.makeText(this, "Email is not valid", Toast.LENGTH_SHORT).show();
                }else if(password.length() < 6){
                    Toast.makeText(this, "Password length at least 6 characters", Toast.LENGTH_SHORT).show();
                }else if(this.doesUserExist(email)){
                    Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
                }else{
                    db.execSQL("INSERT INTO users (name,surname,email,password) VALUES (?,?,?,?)",new String[]{name,surname,email,password});

                    this.printAllUsers();

                    Toast.makeText(this, "User has been created", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    setResult(1,intent);
                    finish();
                }
            }
        }
    }

    private Boolean doesUserExist(String email){
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ?",new String[]{email});

        if(cursor != null & cursor.getCount() > 0){
            return true;
        }

        return false;
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void printAllUsers(){
        Cursor cursor = db.rawQuery("SELECT * FROM users",null);

        if(cursor != null & cursor.getCount() > 0){
            while(cursor.moveToNext()){
//                String username = cursor.getString(cursor.getColumnIndex("name"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String password = cursor.getString(cursor.getColumnIndex("password"));

                Log.v("NEWS","USER: / "+email+" / "+password);
            }
        }else{
            Log.v("NEWS","NO USER");
        }
    }
}
