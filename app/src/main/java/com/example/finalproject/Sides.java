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

public class Sides extends AppCompatActivity {
    ImageButton btnFrenchFries, btnExtraRice, btnSpaghetti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sides);

        btnFrenchFries = findViewById(R.id.btnFrenchFries);
        btnFrenchFries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fries = new Intent(Sides.this, FoodView.class);
                fries.putExtra("foodName","French Fries");
                fries.putExtra("foodDesc", "These golden delights are seasoned with salt and often served hot, straight from the fryer, making them irresistibly delicious.");
                startActivity(fries);
            }
        });

        btnExtraRice = findViewById(R.id.btnExtraRice);
        btnExtraRice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rice = new Intent(Sides.this, FoodView.class);
                rice.putExtra("foodName","Extra Rice");
                rice.putExtra("foodDesc", "Cooked rice is soft, fluffy, and hot, made by boiling rice grains in water until they're tender, perfect for pairing with any meal.");
                startActivity(rice);
            }
        });

        btnSpaghetti = findViewById(R.id.btnSpaghetti);
        btnSpaghetti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent spag = new Intent(Sides.this, FoodView.class);
                spag.putExtra("foodName","Spaghetti");
                spag.putExtra("foodDesc", "Served with sweet and savory tomato sauce mixed with ground meat (usually pork or beef), hot dogs or sausages on top");
                startActivity(spag);
            }
        });
    }
}