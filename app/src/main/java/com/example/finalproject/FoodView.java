package com.example.finalproject;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FoodView extends AppCompatActivity {

    ScrollView svParent;
    TextView txtFoodName, txtFoodDesc, txtQuantity;
    Button btnMinus, btnPlus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_view);
        Intent intent = getIntent();
        String name = intent.getStringExtra("foodName");
        String desc = intent.getStringExtra("foodDesc");
        txtFoodName = findViewById(R.id.txtFoodName);
        txtFoodDesc = findViewById(R.id.txtDescription);

        txtFoodName.setText(name);
        txtFoodDesc.setText(desc);

        svParent = findViewById(R.id.svParent);
        if("Burger Steak".equals(name)){
            svParent.setBackgroundResource(R.drawable.steakview);
        } else if("Shawarma Rice".equals(name)){
            svParent.setBackgroundResource(R.drawable.shawarmaview);
        } else if("Steamed Rice".equals(name)){
            svParent.setBackgroundResource(R.drawable.steamedriceview);
        } else if("TapSilog".equals(name)){
            svParent.setBackgroundResource(R.drawable.tapsilogview);
        } else if("Chicken Shots".equals(name)){
            svParent.setBackgroundResource(R.drawable.chickenshotsview);
        } else if("Chicken Katsu".equals(name)){
            svParent.setBackgroundResource(R.drawable.katsuview);
        } else if("Chicken Wings with Rice".equals(name)){
            svParent.setBackgroundResource(R.drawable.chickenwingsriceview);
        } else if("Chicken Wings Platter".equals(name)){
            svParent.setBackgroundResource(R.drawable.chickenwingsview);
        } else if("French Fries".equals(name)){
            svParent.setBackgroundResource(R.drawable.friesview);
        } else if("Extra Rice".equals(name)){
            svParent.setBackgroundResource(R.drawable.riceview);
        } else if("Spaghetti".equals(name)){
            svParent.setBackgroundResource(R.drawable.spaghettiview);
        } else if("Milktea".equals(name)){
            svParent.setBackgroundResource(R.drawable.milkteaview);
        } else if("Cucumber Lemonade".equals(name)){
            svParent.setBackgroundResource(R.drawable.cucumberview);
        } else if("Red Iced Tea".equals(name)){
            svParent.setBackgroundResource(R.drawable.teaview);
        }

        txtQuantity = (TextView) findViewById(R.id.txtQuantity);


        btnMinus = findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Currquantity = parseInt((String) txtQuantity.getText());
                int quantity = Currquantity-1;
                txtQuantity.setText(String.valueOf(Math.max(quantity, 0)));
            }
        });

        btnPlus = findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Currquantity = parseInt((String) txtQuantity.getText());
                int quantity = Currquantity+1;
                txtQuantity.setText(String.valueOf(quantity));
            }
        });
    }
}