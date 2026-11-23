package com.example.myrestraurantappjg;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        TextView orderQuantity = findViewById(R.id.orderQuantity);
        TextView orderWing = findViewById(R.id.orderWing);
        TextView orderSize = findViewById(R.id.orderSize);
        TextView orderFries = findViewById(R.id.orderFries);
        TextView orderDrinks = findViewById(R.id.orderDrinks);
        TextView orderTotal = findViewById(R.id.orderTotal);

        Button confirmOrderButton = findViewById(R.id.confirmOrderButton);
        Button returnMenuButton = findViewById(R.id.returnMenuButton);

        int quantity = getIntent().getIntExtra("quantity", 0);
        String wing = getIntent().getStringExtra("wing");
        String size = getIntent().getStringExtra("size");
        boolean fries = getIntent().getBooleanExtra("fries", false);
        boolean drinks = getIntent().getBooleanExtra("drinks", false);
        double total = getIntent().getDoubleExtra("total", 0.0);

        orderQuantity.setText("Quantity: " + quantity);
        orderWing.setText("Wing Type: " + (wing != null ? wing : "N/A"));
        orderSize.setText("Size: " + (size != null ? size : "N/A"));
        orderFries.setText("French Fries: " + (fries ? "Yes ($2.99)" : "No"));
        orderDrinks.setText("Soft Drinks: " + (drinks ? "Yes ($1.99)" : "No"));
        orderTotal.setText(String.format(Locale.US, "$%.2f", total));

        confirmOrderButton.setOnClickListener(v -> {
            Toast.makeText(this, "Order Confirmed! Thank you for ordering.", Toast.LENGTH_LONG).show();
            finish();
        });

        returnMenuButton.setOnClickListener(v -> finish());
    }
}
