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

public class SulitDeals extends AppCompatActivity {

    ImageButton btnBurgerSteak, btnShawarmaRice, btnTapSilog, btnSteamedRice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sulit_deals);

        btnBurgerSteak = findViewById(R.id.btnBurgerSteak);
        btnBurgerSteak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent burgersteak = new Intent(SulitDeals.this, FoodView.class);
                burgersteak.putExtra("foodName","Burger Steak");
                burgersteak.putExtra("foodDesc", "Rice topped with Burger Steak that is super moist and flavorful, with tender beef patties smothered in a rich mushroom gravy");
                startActivity(burgersteak);
            }
        });
        btnShawarmaRice = findViewById(R.id.btnShawarmaRice);
        btnShawarmaRice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shawarma = new Intent(SulitDeals.this, FoodView.class);
                shawarma.putExtra("foodName","Shawarma Rice");
                shawarma.putExtra("foodDesc", "This Pinoy-style shawarma rice comes with buttery yellow rice, a veggie side salad, and garlic yogurt sauce.");
                startActivity(shawarma);
            }
        });
        btnSteamedRice = findViewById(R.id.btnSteamedRice);
        btnSteamedRice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent steamedrice = new Intent(SulitDeals.this, FoodView.class);
                steamedrice.putExtra("foodName","Steamed Rice");
                steamedrice.putExtra("foodDesc", "Try this homemade dim sum recipe for Cebu City steamed rice, a Filipino favorite that tops fried rice with tasty pork belly and shrimp stew.");
                startActivity(steamedrice);
            }
        });
        btnTapSilog = findViewById(R.id.btnTapSilog);
        btnTapSilog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tapsilog = new Intent(SulitDeals.this, FoodView.class);
                tapsilog.putExtra("foodName","TapSilog");
                tapsilog.putExtra("foodDesc", "Sweet-salty peppery beef, crunchy garlic rice, and a runny fried egg make this Filipino breakfast perfect for any meal of the day.");
                startActivity(tapsilog);
            }
        });
    }
}