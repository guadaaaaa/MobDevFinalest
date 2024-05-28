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

public class Desserts extends AppCompatActivity {

    ImageButton btnMilktea, btnCucumberLemon, btnRedTea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desserts);
        btnMilktea = findViewById(R.id.btnMilktea);
        btnMilktea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent milktea = new Intent(Desserts.this, FoodView.class);
                milktea.putExtra("foodName","Milktea");
                milktea.putExtra("foodDesc", "Brewed tea with milk and sweeteners, with tapioca pearls, offering a creamy and customizable treat enjoyed globally.");
                startActivity(milktea);
            }
        });

        btnCucumberLemon = findViewById(R.id.btnCucumberLemon);
        btnCucumberLemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lemon = new Intent(Desserts.this, FoodView.class);
                lemon.putExtra("foodName","Cucumber Lemonade");
                lemon.putExtra("foodDesc", "A refreshing beverage made by blending freshly squeezed lemon juice with cucumber slices, water, and sweetener");
                startActivity(lemon);
            }
        });
        btnRedTea = findViewById(R.id.btnRedTea);
        btnRedTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tea = new Intent(Desserts.this, FoodView.class);
                tea.putExtra("foodName","Red Iced Tea");
                tea.putExtra("foodDesc", "a vibrant and refreshing beverage made by steeping black tea leaves and infusing them with fruity flavors");
                startActivity(tea);
            }
        });
    }
}