package com.example.crisadel;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class AddStockActivity extends AppCompatActivity {
    EditText name_input, desc_input, price_input, quantity_input, date_added_input, expiry_input;
    Button btn_add;
    CustomAdapter customAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);

        name_input = findViewById(R.id.et_itemname);
        desc_input = findViewById(R.id.et_desc);
        price_input = findViewById(R.id.et_price);
        quantity_input = findViewById(R.id.et_qty);
        date_added_input = findViewById(R.id.et_date_added);
        expiry_input = findViewById(R.id.et_expiry);
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(view -> {
            CrisadelDB crisadelDB = new CrisadelDB(AddStockActivity.this);
            crisadelDB.addStock(name_input.getText().toString().trim(),
                    desc_input.getText().toString().trim(),
                    Integer.valueOf(price_input.getText().toString().trim()),
                    Integer.valueOf(quantity_input.getText().toString().trim()),
                    date_added_input.getText().toString().trim(),
                    expiry_input.getText().toString().trim());

            customAdapter.notifyDataSetChanged();

        });
        
        }

    }

