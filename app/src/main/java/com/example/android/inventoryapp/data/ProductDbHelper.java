package com.example.android.inventoryapp.data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventoryapp.data.ProductContract.*;

public class ProductDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "store.db";

    // SQL statement for creating the table inside the database
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProductEntry.TABLE_NAME + " (" +
                    ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ProductEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL," +
                    ProductEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL," +
                    ProductEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL," +
                    ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME + " TEXT NOT NULL," +
                    ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER + " INTEGER NOT NULL)";

    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute the SQL statement to create a new DataBase
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
