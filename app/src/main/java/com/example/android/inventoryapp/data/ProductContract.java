package com.example.android.inventoryapp.data;

import android.net.Uri;
import android.provider.BaseColumns;

//Contract class that defines name of table and constants.

public class ProductContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.inventoryapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PRODUCTS = "products";

    private ProductContract() {}

    //Defines the schema of the table
    public static abstract class ProductEntry implements BaseColumns {

        public static final String TABLE_NAME = "products";                         // Table name
        public static final String _ID = BaseColumns._ID;                           // Product ID
        public static final String COLUMN_PRODUCT_NAME = "name";                    // Product name
        public static final String COLUMN_PRODUCT_PRICE = "price";                  // Product price
        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";            // Product quantity
        public static final String COLUMN_PRODUCT_SUPPLIER_NAME = "supplier";       // Supplier name
        public static final String COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER = "phone";  // Supplier phone number

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);


    }
}
