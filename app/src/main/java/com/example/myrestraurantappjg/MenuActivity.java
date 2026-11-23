package com.example.myrestraurantappjg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        TextView quantityText = findViewById(R.id.quantityText);

        Button plus = findViewById(R.id.quantityPlus);
        Button minus = findViewById(R.id.quantityMinus);
        Button back = findViewById(R.id.backButton);
        Button checkout = findViewById(R.id.checkoutButton);

        RadioGroup wingType = findViewById(R.id.wingTypeGroup);
        RadioGroup sizeGroup = findViewById(R.id.sizeGroup);

        CheckBox fries = findViewById(R.id.friesCheck);
        CheckBox drinks = findViewById(R.id.drinkCheck);

        plus.setOnClickListener(v -> {
            quantity++;
            quantityText.setText(String.valueOf(quantity));
        });

        minus.setOnClickListener(v -> {
            if (quantity > 0) {
                quantity--;
                quantityText.setText(String.valueOf(quantity));
            }
        });

        back.setOnClickListener(v -> finish());

        checkout.setOnClickListener(v -> {

            if (quantity == 0) {
                Toast.makeText(this,
                        "Please select a quantity",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedWing = wingType.getCheckedRadioButtonId();
            int selectedSize = sizeGroup.getCheckedRadioButtonId();

            if (selectedWing == -1 || selectedSize == -1) {
                Toast.makeText(this,
                        "Please select wing type and size",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            String wing = selectedWing == R.id.flats ? "Flats" :
                    selectedWing == R.id.drums ? "Drums" : "Both";

            String size;
            double wingPrice;

            if (selectedSize == R.id.smallSize) {
                size = "Small";
                wingPrice = 8.99;
            } else if (selectedSize == R.id.mediumSize) {
                size = "Medium";
                wingPrice = 11.99;
            } else {
                size = "Large";
                wingPrice = 14.99;
            }

            double friesPrice = fries.isChecked() ? 2.99 : 0;
            double drinkPrice = drinks.isChecked() ? 1.99 : 0;

            double total = (wingPrice + friesPrice + drinkPrice) * quantity;

            Intent intent = new Intent(MenuActivity.this,
                    CheckoutActivity.class);

            intent.putExtra("quantity", quantity);
            intent.putExtra("wing", wing);
            intent.putExtra("size", size);
            intent.putExtra("fries", fries.isChecked());
            intent.putExtra("drinks", drinks.isChecked());
            intent.putExtra("total", total);
            intent.putExtra("wingPrice", wingPrice);
            intent.putExtra("friesPrice", friesPrice);
            intent.putExtra("drinkPrice", drinkPrice);

            startActivity(intent);
        });
    }
}
