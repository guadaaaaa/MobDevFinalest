package com.example.finalproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        allOrders = new StringBuilder();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details);
        SESSION = Session.getInstance();
        txtAllOrders = findViewById(R.id.txtAllOrders);
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
                         "WHERE of.order_id = ?")) {
                //start of try
                c.setAutoCommit(false);
                createOrder.setInt(1, (int) SESSION.get("id"));
                createOrder.executeUpdate();
                ResultSet rs = createOrder.getGeneratedKeys();
                int order_id = 0;
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
                }
                updateOrderPrice.setDouble(1, total_price);
                updateOrderPrice.setInt(2, order_id);
                ordersStatement.setInt(1, order_id);
                ResultSet details = ordersStatement.executeQuery();
                runOnUiThread(() -> {
                    txtAllOrders.setText("Successfully ordered!");
                });
                c.commit();
            } catch (SQLException e) {
                runOnUiThread(() -> {
                    txtAllOrders.setText(Log.getStackTraceString(e));
                });
            }
        });
    }
}