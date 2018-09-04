package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.inventoryapp.data.ProductContract.*;
import com.example.android.inventoryapp.data.ProductDbHelper;

public class MainActivity extends AppCompatActivity {

    private ProductDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new ProductDbHelper(this);

        insertData();
        queryData();
    }

    private void insertData(){
        // Insert into database.
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, "Masters Of Doom: How Two Guys Created an Empire and Transformed Pop Culture");
        values.put(ProductEntry.COLUMN_PRODUCT_PRICE, 22);
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, 2);
        values.put(ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME, " Little Brown");
        values.put(ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, 983242142);


        long newRowId = db.insert(ProductEntry.TABLE_NAME, null, values);


        // Print if the operation has been successful or failed
        if (newRowId == -1) {
            Log.v("MainActivity", "ERROR");
        }else {
            Log.v("MainActivity", "New row ID " + newRowId);

        }
    }

    private void queryData(){
        /**
         * Query the database.
         * Always close the cursor when you're done reading from it.
         * This releases all its resources and makes it invalid.
         */

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT name, price, quantity, supplier, phone FROM products"
        // to get a Cursor that contains all rows from the products table.

        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER
        };

        Cursor cursor = db.query(
                ProductEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        try {
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(ProductEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER);


            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                int currentSupplierPhoneNumber = cursor.getInt(supplierPhoneNumberColumnIndex);

                Log.v("MainActivity", "Product: " + currentID + " " + currentName + " " + currentPrice + " " + currentQuantity + " " + currentSupplierName + " " + currentSupplierPhoneNumber);

                TextView productTextView = findViewById(R.id.product);

                // Display the values from each column of the current row in the cursor in the TextView
                productTextView.append(currentID
                        + " - " + currentName + "\n"
                        + "Price: " + currentPrice + "\n"
                        + "Quantity: " + currentQuantity + "\n"
                        + "Supplier name: " + currentSupplierName + "\n"
                        + "Supplier phone number:  " + currentSupplierPhoneNumber + "\n");
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
