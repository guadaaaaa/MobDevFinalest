package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LandingPage extends AppCompatActivity {

    protected Session SESSION;
    Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SESSION = Session.getInstance();
        SESSION.put("orders",new ArrayList<Orders>()); //array of foods
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        btnLogin = findViewById(R.id.btnLandingLogin);
        btnRegister = findViewById(R.id.btnLandingRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(LandingPage.this, Register.class);
                startActivity(signup);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(LandingPage.this, LogIn.class);
                startActivity(login);
                finish();
            }
        });
    }
}