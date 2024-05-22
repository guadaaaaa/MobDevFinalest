package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {

    ImageButton IBSulitDeals, IBChicken, IBSides, IBDessert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        IBSulitDeals = findViewById(R.id.IBSulitDeals);
        IBChicken = findViewById(R.id.IBChicken);
        IBSides = findViewById(R.id.IBSides);
        IBDessert = findViewById(R.id.IBDesserts);

        IBSulitDeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sulitdeals = new Intent(DashboardActivity.this, FoodView.class);
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
    }
}