package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SplashScreen extends AppCompatActivity {
    private MySQLConnection connection;
    private String name, str;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        connection = new MySQLConnection();
        connect();

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LandingPage.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }

    private void connect(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() ->{
            try(Connection c = MySQLConnection.getConnection()){
                if(c == null){
                    str = "Error Connecting to MySQL server";
                } else {
                    str = "Connection Successful";
                    CreateTable.createDB();
                    CreateTable.createTableUsers();
                    CreateTable.createTableFood();
                }
            } catch (SQLException e){
                str = "Error in Connection: "+ e.getMessage();
            }
            runOnUiThread(() ->{
                Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
            });
        });
    }
}