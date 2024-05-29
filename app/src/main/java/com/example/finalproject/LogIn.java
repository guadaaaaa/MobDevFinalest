package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogIn extends AppCompatActivity {
    protected Session SESSION;
    EditText txtUsername, txtPassword;
    Button btnLogIn, btnLogInRegister;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        SESSION = Session.getInstance();
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LogIn.this, "Fields cannot be blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(() ->{
                    try(Connection c = MySQLConnection.getConnection();
                        PreparedStatement statement = c.prepareStatement("SELECT * from tblusers where username=?")) {
                        statement.setString(1,username);
                        statement.executeQuery();
                        ResultSet rs = statement.getResultSet();
                        if(rs.next()){
                            String pw = rs.getString("password");
                            if(password.equals(pw)){
                                SESSION.put("username",username);
                                SESSION.put("password",password);
                                SESSION.put("firstname",rs.getString("firstname"));
                                SESSION.put("lastname",rs.getString("lastname"));
                                SESSION.put("id",rs.getInt("id"));
                                Intent intent = new Intent(LogIn.this,DashboardActivity.class);
                                runOnUiThread(() ->{
                                    Toast.makeText(LogIn.this,"Log In Success", Toast.LENGTH_SHORT).show();
                                });
                                startActivity(intent);
                                finish();
                            } else {
                                runOnUiThread(() ->{
                                    Toast.makeText(LogIn.this,"Password Incorrect", Toast.LENGTH_SHORT).show();
                                });
                            }
                        } else {
                            runOnUiThread(() ->{
                                Toast.makeText(LogIn.this,"User Not Found", Toast.LENGTH_SHORT).show();
                            });
                        }
                        str = "Log in Successful";
                    } catch (SQLException e) {
                        Log.e("LoginActivity", "Database connection error: " + e.getMessage());
                        runOnUiThread(() -> {
                            Toast.makeText(LogIn.this, "Database connection error", Toast.LENGTH_SHORT).show();
                        });
                    }
                });
            }
        });
        btnLogInRegister = findViewById(R.id.btnLogInRegister);
        btnLogInRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this,Register.class);
                startActivity(intent);
                finish();
            }
        });
    }
}