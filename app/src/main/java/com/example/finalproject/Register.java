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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Register extends AppCompatActivity {

    protected Session SESSION;

    EditText tvUsername, tvFirstName, tvLastName, tvPassword;
    Button btnRegister,btnRegisterLogIn;

    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SESSION = Session.getInstance();
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
                if(first.isEmpty() || last.isEmpty() || username.isEmpty() || password.isEmpty()){
                    Toast.makeText(Register.this, "Fields cannot be blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(() ->{
                    try(Connection c = MySQLConnection.getConnection();
                        PreparedStatement statement = c.prepareStatement("INSERT INTO tblusers (username,firstname,lastname,password) values (?,?,?,?)",Statement.RETURN_GENERATED_KEYS)) {
                        statement.setString(1,username);
                        statement.setString(2,first);
                        statement.setString(3,last);
                        statement.setString(4,password);
                        statement.executeUpdate();
                        str = "Registration Successful";
                        SESSION.put("username",username);
                        SESSION.put("password",password);
                        SESSION.put("firstname",first);
                        SESSION.put("lastname",last);
                        ResultSet rs = statement.getGeneratedKeys();
                        rs.next();
                        SESSION.put("id",rs.getInt(1));
                        Intent intent = new Intent(Register.this,DashboardActivity.class);
                        startActivity(intent);
                        finish();
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