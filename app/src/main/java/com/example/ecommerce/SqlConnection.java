package com.example.ecommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.regex.Matcher;

public class SqlConnection extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "onecart";
    private static final int DATABASE_VERSION = 1;

    Context context;

    public SqlConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create your tables here
        String cart = "CREATE TABLE cart (\n" +
                "  cartID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  productID INTEGER,\n" +
                "  userID INTEGER,\n" +
                "  FOREIGN KEY (productID) REFERENCES products (productID) ON DELETE SET NULL ON UPDATE SET NULL,\n" +
                "  FOREIGN KEY (userID) REFERENCES users (userID) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                ");";

        String category = "CREATE TABLE category (\n" +
                "  categoryID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  categoryName TEXT NOT NULL\n" +
                ");";

        String user = "CREATE TABLE products (\n" +
                "  productID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  productName TEXT NOT NULL,\n" +
                "  categoryID INTEGER,\n" +
                "  price INTEGER NOT NULL,\n" +
                "  FOREIGN KEY (categoryID) REFERENCES category (categoryID) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                ");";

        String product = "CREATE TABLE users (\n" +
                "  userID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  username TEXT NOT NULL,\n" +
                "  password TEXT NOT NULL,\n" +
                "  email TEXT ,\n" +
                "  birthday TEXT," +
                "  UNIQUE (username)\n" +
                ");\n";

        db.execSQL(cart);
        db.execSQL(category);
        db.execSQL(product);
        db.execSQL(user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If you need to upgrade the database, handle it here
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME + ";");
        onCreate(db);
    }


    public String AddUser(String name, String pass, String email, String birthday) {
        boolean User = MatchedUser(name);

        if (User == true) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues row = new ContentValues();
            row.put("username", name);
            row.put("password", pass);
            row.put("email", email);
            row.put("birthday", birthday);

            long res = db.insert("users", null, row);
            db.close();
            if (res == -1){
                return "not added";
            } else {
                return "Done";
            }
        } else
            return "exist";
    }

    public boolean MatchedUser(String name) {
        SQLiteDatabase db = getReadableDatabase();

        String[] tempinfo = {name};

        Cursor cursor = db.rawQuery("Select * from users where username like ?",
                tempinfo);

        if (cursor.getCount() == 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

}
