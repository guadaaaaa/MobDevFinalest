package com.example.finalproject;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class FoodView extends AppCompatActivity {
    protected Session SESSION;
    ScrollView svParent;
    TextView txtFoodName, txtFoodDesc, txtQuantity, tvPrice;
    Button btnMinus, btnPlus, btnAdd;
    Double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_view);
        Intent intent = getIntent();
        SESSION = Session.getInstance();
        btnAdd = findViewById(R.id.btnAdd);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        String name = intent.getStringExtra("foodName");
        String desc = intent.getStringExtra("foodDesc");
        txtFoodName = findViewById(R.id.txtFoodName);
        txtFoodDesc = findViewById(R.id.txtDescription);
        txtQuantity = (TextView) findViewById(R.id.txtQuantity);
        tvPrice = findViewById(R.id.tvPrice);
        txtFoodName.setText(name);
        txtFoodDesc.setText(desc);
        svParent = findViewById(R.id.svParent);

        if("Burger Steak".equals(name)){
            svParent.setBackgroundResource(R.drawable.steakview);
            price = 105.0;
            tvPrice.setText("Price: "+price);
        } else if("Shawarma Rice".equals(name)){
            svParent.setBackgroundResource(R.drawable.shawarmaview);
            price = 99.0;
            tvPrice.setText("Price: "+price);
        } else if("Steamed Rice".equals(name)){
            svParent.setBackgroundResource(R.drawable.steamedriceview);
            price = 79.0;
            tvPrice.setText("Price: "+price);
        } else if("TapSilog".equals(name)){
            svParent.setBackgroundResource(R.drawable.tapsilogview);
            price = 95.0;
            tvPrice.setText("Price: "+price);
        } else if("Chicken Shots".equals(name)){
            svParent.setBackgroundResource(R.drawable.chickenshotsview);
            price = 99.0;
            tvPrice.setText("Price: "+price);
        } else if("Chicken Katsu".equals(name)){
            svParent.setBackgroundResource(R.drawable.katsuview);
            price = 129.0;
            tvPrice.setText("Price: "+price);
        } else if("Chicken Wings with Rice".equals(name)){
            svParent.setBackgroundResource(R.drawable.chickenwingsriceview);
            price = 139.0;
            tvPrice.setText("Price: "+price);
        } else if("Chicken Wings Platter".equals(name)){
            svParent.setBackgroundResource(R.drawable.chickenwingsview);
            price = 149.0;
            tvPrice.setText("Price: "+price);
        } else if("French Fries".equals(name)){
            svParent.setBackgroundResource(R.drawable.friesview);
            price = 89.0;
            tvPrice.setText("Price: "+price);
        } else if("Extra Rice".equals(name)){
            svParent.setBackgroundResource(R.drawable.riceview);
            price = 15.0;
            tvPrice.setText("Price: "+price);
        } else if("Spaghetti".equals(name)){
            svParent.setBackgroundResource(R.drawable.spaghettiview);
            price = 89.0;
            tvPrice.setText("Price: "+price);
        } else if("Milktea".equals(name)){
            svParent.setBackgroundResource(R.drawable.milkteaview);
            price = 89.0;
            tvPrice.setText("Price: "+price);
        } else if("Cucumber Lemonade".equals(name)){
            svParent.setBackgroundResource(R.drawable.cucumberview);
            price = 15.0;
            tvPrice.setText("Price: "+price);
        } else if("Red Iced Tea".equals(name)){
            svParent.setBackgroundResource(R.drawable.teaview);
            price = 15.0;
            tvPrice.setText("Price: "+price);
        }


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Orders> orders = (ArrayList<Orders>)SESSION.get("orders");
                Integer quantity = Integer.parseInt(txtQuantity.getText().toString());
                orders.add(new Orders(name,price,quantity,FoodMap.map.get(name)));
                SESSION.put("orders",orders);
                Intent i = new Intent(FoodView.this,DashboardActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Currquantity = parseInt((String) txtQuantity.getText());
                int quantity = Currquantity-1;
                txtQuantity.setText(String.valueOf(Math.max(quantity, 0)));
            }
        });

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