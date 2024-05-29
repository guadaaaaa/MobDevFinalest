package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderDetails extends AppCompatActivity {
    protected Session SESSION;
    TextView txtAllOrders;
    StringBuilder allOrders;
    Button btnPay;
    int order_id;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        allOrders = new StringBuilder("");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details);
        SESSION = Session.getInstance();
        txtAllOrders = findViewById(R.id.txtAllOrders);
        btnPay = findViewById(R.id.btnPay);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try (Connection c = MySQLConnection.getConnection();
                 PreparedStatement createOrder = c.prepareStatement("INSERT INTO tblorder(user_id) VALUES(?)",Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement createOrderFood = c.prepareStatement("INSERT INTO tblorderfood(order_id,food_id) VALUES(?,?)");
                 PreparedStatement updateOrderPrice = c.prepareStatement("UPDATE tblorder SET total_price = ? WHERE order_id = ?");
                 PreparedStatement ordersStatement = c.prepareStatement("SELECT of.orderfood_id, f.food_name " +
                         "FROM tblorderfood of " +
                         "INNER JOIN tblfood f ON of.food_id = f.food_id " +
                         "WHERE of.order_id = ?");
                 PreparedStatement updateTotalPrice = c.prepareStatement("UPDATE tblorder SET total_price = ? WHERE order_id = ?")) {
                //start of try
                c.setAutoCommit(false);
                createOrder.setInt(1, (int) SESSION.get("id"));
                createOrder.executeUpdate();
                ResultSet rs = createOrder.getGeneratedKeys();
                order_id = 0;
                if (rs.next()) {
                    order_id = rs.getInt(1);
                }
                ArrayList<Orders> orders = (ArrayList<Orders>) SESSION.get("orders");
                Double total_price = 0.0;
                for (Orders o : orders) {
                    createOrderFood.setInt(1, order_id);
                    createOrderFood.setInt(2, o.getFood_id());
                    total_price += o.getTotalPrice();
                    createOrderFood.executeUpdate();
                    allOrders.append("Food: ").append(o.getName()).append(" Price: ").append(o.getPrice()).append(" Quantity: ").append(o.getQuantity()).append(" Total Price: ").append(o.getTotalPrice()).append("\n");
                }
                allOrders.append("\nTOTAL PRICE: ").append(total_price);
                runOnUiThread(() -> {
                    txtAllOrders.setText(allOrders.toString());
                });
                updateOrderPrice.setDouble(1, total_price);
                updateOrderPrice.setInt(2, order_id);
                ordersStatement.setInt(1, order_id);
                //
                updateTotalPrice.setDouble(1,total_price);
                updateTotalPrice.setInt(2,order_id);
                updateTotalPrice.executeUpdate();
                c.commit();
            } catch (SQLException e) {
                runOnUiThread(() -> {
                    txtAllOrders.setText(Log.getStackTraceString(e));
                });
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService service = Executors.newSingleThreadExecutor();
                service.execute(() -> {
                    try(Connection c = MySQLConnection.getConnection();
                    PreparedStatement statement = c.prepareStatement("UPDATE tblorder SET isPaid = 1 WHERE order_id = ?")) {
                        //start of try
                        c.setAutoCommit(false);
                        statement.setInt(1,order_id);
                        statement.executeUpdate();
                        runOnUiThread(() -> {
                            Toast.makeText(OrderDetails.this, "Order has been paid and placed successfully",Toast.LENGTH_SHORT).show();
                        });
                        c.commit();
                        Intent i = new Intent(OrderDetails.this,DashboardActivity.class);
                        ArrayList<Orders> orders = (ArrayList<Orders>) SESSION.get("orders");
                        orders.clear();
                        SESSION.put("orders",orders);
                        startActivity(i);
                        finish();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });
    }
}