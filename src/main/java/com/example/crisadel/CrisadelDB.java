package com.example.crisadel;

import static java.sql.Types.BLOB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


class CrisadelDB extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME= "Crisadel.db";
    private static final int DATABASE_VERSION= 1;
    private static final String TABLE_NAME= "stock_list";
    private static final String COLUMN_ID= "stock_id";
    private static final String COLUMN_NAME= "stock_name";
    private static final String COLUMN_DESC= "stock_desc";
    private static final String COLUMN_PRICE= "stock_price";
    private static final String COLUMN_QUANTITY= "stock_qty";
    private static final String COLUMN_DATE_ADDED= "stock_date_added";
    private static final String COLUMN_EXPIRY= "stock_expiry";



    CrisadelDB(@Nullable Context context) {
        super(context, DATABASE_NAME,null,  DATABASE_VERSION);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESC + " TEXT, " +
                COLUMN_PRICE + " FLOAT, " +
                COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_DATE_ADDED + " DATE, " +
                COLUMN_EXPIRY + " DATE);";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addStock(String name, String desc, float price, int quantity, String date_added, String expiry) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESC, desc);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_QUANTITY, quantity);
        cv.put(COLUMN_DATE_ADDED, date_added);
        cv.put(COLUMN_EXPIRY, expiry);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT) .show();
        }else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT) .show();
        }
    }

    Cursor readAllData(){
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateStock(String row_id, String name, String desc, String  price, String  quantity, String date_added, String expiry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESC, desc);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_QUANTITY, quantity);
        cv.put(COLUMN_DATE_ADDED, date_added);
        cv.put(COLUMN_EXPIRY, expiry);

        long result = db.update(TABLE_NAME, cv, "stock_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Update failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Item successfully updated", Toast.LENGTH_SHORT).show();
        }

    }

}
