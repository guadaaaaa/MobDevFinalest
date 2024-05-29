package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EditProfile extends AppCompatActivity {
    protected Session SESSION;

    EditText txtEditUsername, txtEditPassword;
    Button btnChangeUsername, btnChangePassword, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        SESSION = Session.getInstance();
        txtEditUsername = findViewById(R.id.txtEditUsername);
        txtEditPassword = findViewById(R.id.txtEditPassword);
        btnChangePassword = findViewById(R.id.btnEditPassword);
        btnChangeUsername = findViewById(R.id.btnEditUsername);

        txtEditUsername.setText((String)SESSION.get("username"));
        txtEditPassword.setText((String)SESSION.get("password"));

        btnChangeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int)SESSION.get("id");
                String newName = txtEditUsername.getText().toString();
                if(newName.equals(SESSION.get("username"))){
                    Toast.makeText(EditProfile.this, "Username cannot be the same as your current username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(newName.isEmpty()){
                    Toast.makeText(EditProfile.this, "Field is empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(() -> {
                    try(Connection c = MySQLConnection.getConnection();
                        PreparedStatement statement = c.prepareStatement("UPDATE tblusers SET username = ? WHERE id = ?")) {
                        //start of try
                        c.setAutoCommit(false);
                        statement.setString(1,newName);
                        statement.setInt(2,id);
                        statement.executeUpdate();
                        c.commit();
                        SESSION.put("username",newName);
                        runOnUiThread(() -> Toast.makeText(EditProfile.this, "Username changed successfully", Toast.LENGTH_SHORT).show());

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int)SESSION.get("id");
                String password = txtEditPassword.getText().toString();
                if(password.equals(SESSION.get("password"))){
                    Toast.makeText(EditProfile.this, "NO", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.isEmpty()){
                    Toast.makeText(EditProfile.this, "Field is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(() -> {
                    try(Connection c = MySQLConnection.getConnection();
                        PreparedStatement statement = c.prepareStatement("UPDATE tblusers SET password = ? WHERE id = ?")) {
                        //start of try
                        c.setAutoCommit(false);
                        statement.setString(1,password);
                        statement.setInt(2,id);
                        statement.executeUpdate();
                        c.commit();
                        SESSION.put("password",password);
                        runOnUiThread(() -> Toast.makeText(EditProfile.this, "Password changed successfully", Toast.LENGTH_SHORT).show());

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        });

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int)SESSION.get("id");
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(() -> {
                    try(Connection c = MySQLConnection.getConnection();
                        PreparedStatement statement = c.prepareStatement("DELETE from tblusers WHERE id=?")) {
                        //start of try
                        c.setAutoCommit(false);
                        statement.setInt(1,id);
                        statement.executeUpdate();
                        c.commit();
                        runOnUiThread(() -> Toast.makeText(EditProfile.this, "Account Deleted Successfully", Toast.LENGTH_SHORT).show());
                        Intent exit = new Intent(EditProfile.this, SplashScreen.class);
                        SESSION.clear();
                        startActivity(exit);
                        finish();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        });

    }
}