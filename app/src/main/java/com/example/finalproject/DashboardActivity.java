package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    //declare Session variable SESSION in each activity
    protected Session SESSION;

    ImageButton IBSulitDeals, IBChicken, IBSides, IBDessert;
    TextView txtSessionName,txtCurrOrders;
    Button btnSubmit, btnEdit, btnLogOut;
    StringBuilder currOrders;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //instantiate singleton Session instance with Session.getInstance() in each activity
        //in the onCreate method
        SESSION = Session.getInstance();
        currOrders = new StringBuilder("");
        txtSessionName = findViewById(R.id.txtSessionName);
        txtCurrOrders = findViewById(R.id.txtCurrOrders);
        IBSulitDeals = findViewById(R.id.IBSulitDeals);
        IBChicken = findViewById(R.id.IBChicken);
        IBSides = findViewById(R.id.IBSides);
        IBDessert = findViewById(R.id.IBDesserts);
        btnSubmit = findViewById(R.id.btnSubmit);
        String str = "Hello, " + SESSION.get("firstname");
        txtSessionName.setText(str);
        ArrayList<Orders> orders = (ArrayList<Orders>) SESSION.get("orders");
        try{
            for(Orders o : orders){
                if(o == null) continue;
                currOrders.append("Food: ").append(o.getName()).append(" Price: ").append(o.getPrice()).append(" Quantity: ").append(o.getQuantity()).append(" Total Price: ").append(o.getTotalPrice()).append("\n");
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }

        runOnUiThread(() -> {
            txtCurrOrders.setText(currOrders.toString());
        });
        IBSulitDeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sulitdeals = new Intent(DashboardActivity.this, SulitDeals.class);
                startActivity(sulitdeals);
            }
        });

        IBChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chicken = new Intent(DashboardActivity.this, Chicken.class);
                startActivity(chicken);
            }
        });

        IBSides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sides = new Intent(DashboardActivity.this, Sides.class);
                startActivity(sides);
            }
        });

        IBDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dessert = new Intent(DashboardActivity.this, Desserts.class);
                startActivity(dessert);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Orders> orders = (ArrayList<Orders>) SESSION.get("orders");
                if(orders.isEmpty()){
                    Toast.makeText(DashboardActivity.this,"You haven't ordered anything!", Toast.LENGTH_SHORT).show();
                } else{
                    Intent i = new Intent(DashboardActivity.this, OrderDetails.class);
                    startActivity(i);
                }
            }
        });

        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(DashboardActivity.this, EditProfile.class);
                startActivity(edit);
            }
        });

        btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SESSION.clear();
                Intent out = new Intent(DashboardActivity.this, SplashScreen.class);
                startActivity(out);
                finish();
            }
        });
    }
}