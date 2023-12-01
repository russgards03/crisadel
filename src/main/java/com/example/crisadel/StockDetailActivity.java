package com.example.crisadel;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class StockDetailActivity extends AppCompatActivity {


    EditText name_input, desc_input, price_input, quantity_input, date_added_input, expiry_input;
    TextView stockNameTextView;
    Button btn_update;
    Spinner spinnerItemType;
    String id, name, description, price, quantity, date_added, expiry;
    CustomAdapter customAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);

        stockNameTextView = findViewById(R.id.current_stock);
        String stockName = getIntent().getStringExtra("name");
        stockNameTextView.setText(stockName);

        name_input = findViewById(R.id.et_itemname2);
        desc_input = findViewById(R.id.et_desc2);
        price_input = findViewById(R.id.et_price2);
        quantity_input = findViewById(R.id.et_qty2);
        date_added_input = findViewById(R.id.et_date_added2);
        expiry_input = findViewById(R.id.et_expiry2);
        btn_update = findViewById(R.id.btn_update);
        spinnerItemType = findViewById(R.id.et_itemtype2);

        getAndSetIntentData();

        ActionBar ab =getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        btn_update.setOnClickListener(view -> {
            CrisadelDB myDB = new CrisadelDB(StockDetailActivity.this);

            String selectedSpinnerItem = (String) spinnerItemType.getSelectedItem();
            name = name_input.getText().toString().trim();
            description = desc_input.getText().toString().trim();
            price = price_input.getText().toString().trim();
            quantity = quantity_input.getText().toString().trim();
            date_added = date_added_input.getText().toString().trim();
            expiry = expiry_input.getText().toString().trim();
            myDB.updateStock(id, name, description, price, quantity, date_added, expiry);

            customAdapter.notifyDataSetChanged();

        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("type") && getIntent().hasExtra("description") && getIntent().hasExtra("price") &&
                getIntent().hasExtra("quantity") && getIntent().hasExtra("date_added") && getIntent().hasExtra("expiry")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");
            price = getIntent().getStringExtra("price");
            quantity = getIntent().getStringExtra("quantity");
            date_added = getIntent().getStringExtra("date_added");
            expiry = getIntent().getStringExtra("expiry");

            name_input.setText(name);
            desc_input.setText(description);
            price_input.setText(price);
            quantity_input.setText(quantity);
            date_added_input.setText(date_added);
            expiry_input.setText(expiry);

        } else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();

        }
    }
}
