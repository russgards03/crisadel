package com.example.crisadel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList stock_id, stock_name, stock_desc, stock_price, stock_qty, stock_date_added, stock_expiry, stock_image;
    Activity activity;
    CustomAdapter(Context context,
                  ArrayList stock_id,
                  ArrayList stock_name,
                  ArrayList stock_desc,
                  ArrayList stock_price,
                  ArrayList stock_qty,
                  ArrayList stock_date_added,
                  ArrayList stock_expiry) {

        this.context = context;
        this.stock_id = stock_id;
        this.stock_name = stock_name;
        this.stock_desc = stock_desc;
        this.stock_price = stock_price;
        this.stock_qty = stock_qty;
        this.stock_date_added = stock_date_added;
        this.stock_expiry = stock_expiry;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.stock_id_txt.setText(String.valueOf(stock_id.get(position)));
        holder.stock_name_txt.setText(String.valueOf(stock_name.get(position)));
        holder.stock_price_txt.setText(String.valueOf(stock_price.get(position)));
        holder.stock_qty_txt.setText(String.valueOf(stock_qty.get(position)));
        holder.mainLayout.setOnClickListener(view -> {

            Intent intent = new Intent(context, StockDetailActivity.class);
            intent.putExtra("id", String.valueOf(stock_id.get(position)));
            intent.putExtra("name", String.valueOf(stock_name.get(position)));
            intent.putExtra("description", String.valueOf(stock_desc.get(position)));
            intent.putExtra("price", String.valueOf(stock_price.get(position)));
            intent.putExtra("quantity", String.valueOf(stock_qty.get(position)));
            intent.putExtra("date_added", String.valueOf(stock_date_added.get(position)));
            intent.putExtra("expiry", String.valueOf(stock_expiry.get(position)));
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return stock_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView stock_id_txt, stock_name_txt, stock_qty_txt, stock_price_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stock_id_txt = itemView.findViewById(R.id.stock_id_txt);
            stock_name_txt = itemView.findViewById(R.id.stock_name_txt);
            stock_qty_txt = itemView.findViewById(R.id.stock_qty_txt);
            stock_price_txt = itemView.findViewById(R.id.stock_price_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
