package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Register extends AppCompatActivity {

    EditText tvUsername, tvFirstName, tvLastName, tvPassword;
    Button btnRegister,btnRegisterLogIn;

    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvUsername = findViewById(R.id.txtUsername);
        tvPassword = findViewById(R.id.txtPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first = String.valueOf(tvFirstName.getText());
                String last = String.valueOf(tvLastName.getText());
                String username = String.valueOf(tvUsername.getText());
                String password = String.valueOf(tvPassword.getText());
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(() ->{
                    try(Connection c = MySQLConnection.getConnection();
                        PreparedStatement statement = c.prepareStatement("INSERT INTO tblusers (username,firstname,lastname,password) values (?,?,?,?)")) {
                        statement.setString(1,username);
                        statement.setString(2,first);
                        statement.setString(3,last);
                        statement.setString(4,password);
                        statement.executeUpdate();
                        str = "Registration Successful";

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    runOnUiThread(() ->{
                        Toast.makeText(Register.this,str,Toast.LENGTH_SHORT).show();
                    });
                });
            }
        });

        btnRegisterLogIn = findViewById(R.id.btnLogInRegister);
        btnRegisterLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,LogIn.class);
                startActivity(intent);
                finish();
            }
        });

    }
//
//    private void connect(){
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(() ->{
//            try(Connection c = MySQLConnection.getConnection()){
//                if(c == null){
//                    str = "Error Connecting to MySQL server";
//                } else {
//                    str = "Connection Successful";
//                }
//            } catch (SQLException e){
//                str = "Error in Connection: "+ e.getMessage();
//            }
//            runOnUiThread(() ->{
//                Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
//            });
//        });
//    }
}