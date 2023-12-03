package com.example.crisadel;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class StockDetailActivity extends AppCompatActivity {


    EditText name_input, desc_input, price_input, quantity_input, date_added_input, expiry_input;
    TextView stockNameTextView;
    Button btn_update, btn_delete;
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
        btn_delete = findViewById(R.id.btn_delete);

        getAndSetIntentData();

        ActionBar ab =getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        btn_update.setOnClickListener(view -> {
            CrisadelDB myDB = new CrisadelDB(StockDetailActivity.this);

            name = name_input.getText().toString().trim();
            description = desc_input.getText().toString().trim();
            price = price_input.getText().toString().trim();
            quantity = quantity_input.getText().toString().trim();
            date_added = date_added_input.getText().toString().trim();
            expiry = expiry_input.getText().toString().trim();
            myDB.updateStock(id, name, description, price, quantity, date_added, expiry);

            finish();
            customAdapter.notifyDataSetChanged();

        });

        btn_delete.setOnClickListener(view -> {
            confirmDialog();
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("description") && getIntent().hasExtra("price") &&
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

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CrisadelDB myDB = new CrisadelDB(StockDetailActivity.this);
                myDB.deleteRow(id);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
