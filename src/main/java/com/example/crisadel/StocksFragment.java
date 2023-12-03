package com.example.crisadel;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.crisadel.CrisadelDB;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StocksFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton addStock;

    CrisadelDB myDB;
    ArrayList<String> stock_id, stock_name, stock_desc, stock_price, stock_qty, stock_date_added, stock_expiry;
    CustomAdapter customAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stocks, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        addStock = view.findViewById(R.id.AddStock);

        addStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddStockActivity.class);
                startActivity(intent);
            }
        });

        myDB = new CrisadelDB(getActivity());
        stock_id = new ArrayList<>();
        stock_name = new ArrayList<>();
        stock_desc = new ArrayList<>();
        stock_price = new ArrayList<>();
        stock_qty = new ArrayList<>();
        stock_date_added = new ArrayList<>();
        stock_expiry = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(getActivity(), stock_id, stock_name, stock_desc, stock_price, stock_qty, stock_date_added, stock_expiry);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();

        // Check if the cursor is null
        if (cursor == null) {
            // Handle the case where there was an issue with the database query
            Toast.makeText(getContext(), "Error retrieving data from the database", Toast.LENGTH_SHORT).show();
            return;
        }

        // Continue with processing the cursor if it is not null
        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                stock_id.add(cursor.getString(0));
                stock_name.add(cursor.getString(1));
                stock_desc.add(cursor.getString(2));
                stock_price.add(cursor.getString(3));
                stock_qty.add(cursor.getString(4));
                stock_date_added.add(cursor.getString(5));
                stock_expiry.add(cursor.getString(6));
            }

        }
        int newPosition = stock_id.size() - 1;
        if (newPosition >= 0) {
            recyclerView.scrollToPosition(newPosition);
        }


        // Close the cursor after use
        cursor.close();
    }
}
