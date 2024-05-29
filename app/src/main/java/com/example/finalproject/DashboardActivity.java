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
    TextView txtSessionName;
    Button btnSubmit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //instantiate singleton Session instance with Session.getInstance() in each activity
        //in the onCreate method
        SESSION = Session.getInstance();
        txtSessionName = findViewById(R.id.txtSessionName);
        IBSulitDeals = findViewById(R.id.IBSulitDeals);
        IBChicken = findViewById(R.id.IBChicken);
        IBSides = findViewById(R.id.IBSides);
        IBDessert = findViewById(R.id.IBDesserts);
        btnSubmit = findViewById(R.id.btnSubmit);
        String str = "Hello, " + SESSION.get("firstname");
        txtSessionName.setText(str);
        /*
            XXX LOGIC SA MULTI VALUED (PARA NAKO) XXX
            1. inig order, since wa pa tay reference sa unsay gipang order
            simply insert lang a new row sa table tblorder na naay order_id, foreign key id
            sa ga order (use SESSION variable(SESSION.get("id"))), then i-default 0 lang sa
            ang total_price
            2. after og execute sa insert query, unsaon pag kuha sa order_id
            na gi-generate sa database? ResultSet rs = statement.getGeneratedKeys(), and
            ang pag access sa values same ra as you would sa any result set
            3. for each kind of food gi-order, i-insert sa tblorderfood with values
            foreign key order_id (use value from the result set from #2), foreign key food_id from
            tblfood, and quantity (default 1 rani)
            4. how to get total_price sa order? pag select query sa tanan items sa tblorderfood
            i.e (SELECT * from tblorderfood WHERE order_id = rs.getInt("order_id")) example rana
            PAG INNER JOIN SA tblfood PARA MAKUHA ANG PRICE
            5. after makuha ang result set from the select query, initialize int price variable = 0 then,
            simply iterate through the whole result set, increment price by (price of food * quantity)
            6. after ana, simply UPDATE ang price sa atong gi-insert sa tblorder
         */

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

                }
            }
        });
    }
}