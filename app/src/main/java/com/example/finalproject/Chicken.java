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

public class Chicken extends AppCompatActivity {

    ImageButton btnChickenShots, btnChickenKatsu, btnChickenWingsRice, btnChickenWingsOnly;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken);
        btnChickenShots = findViewById(R.id.btnChickenShots);
        btnChickenShots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shots = new Intent(Chicken.this, FoodView.class);
                shots.putExtra("foodName","Chicken Shots");
                shots.putExtra("foodDesc", "Bite-sized pieces of chicken that are seasoned, breaded, and fried to crispy perfection topped with flavorful sauce.");
                startActivity(shots);
            }
        });

        btnChickenKatsu = findViewById(R.id.btnChickenKatsu);
        btnChickenKatsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent katsu = new Intent(Chicken.this, FoodView.class);
                katsu.putExtra("foodName","Chicken Katsu");
                katsu.putExtra("foodDesc", "Seasoned, cooked, and coated in a sauce, offering a delightful combination of savory, spicy, or sweet flavor. 6 pieces of wings");
                startActivity(katsu);
            }
        });
        btnChickenWingsRice = findViewById(R.id.btnChickenWingsRice);
        btnChickenWingsRice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wingsrice = new Intent(Chicken.this, FoodView.class);
                wingsrice.putExtra("foodName","Chicken Wings with Rice");
                wingsrice.putExtra("foodDesc", "Seasoned, cooked, and coated in a sauce, offering a delightful combination of savory, spicy, or sweet flavor.");
                startActivity(wingsrice);
            }
        });
        btnChickenWingsOnly = findViewById(R.id.btnChickenWingsOnly);
        btnChickenWingsOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wings = new Intent(Chicken.this, FoodView.class);
                wings.putExtra("foodName","Chicken Wings Platter");
                wings.putExtra("foodDesc", "Seasoned, cooked, and coated in a sauce, offering a delightful combination of savory, spicy, or sweet flavor.");
                startActivity(wings);
            }
        });
    }
}