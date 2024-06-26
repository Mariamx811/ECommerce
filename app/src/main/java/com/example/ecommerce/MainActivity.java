package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    return true;
                case R.id.bottom_search:
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slideright, R.anim.slideleft);
                    finish();
                    return true;
//                case R.id.bottom_settings:
//                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
//                    overridePendingTransition(R.anim.slideright, R.anim.slideleft);
//                    finish();
//                    return true;
//                case R.id.bottom_profile:
//                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
//                    overridePendingTransition(R.anim.slideright, R.anim.slideleft);
//                    finish();
//                    return true;
            }
            return false;
        });

        SqlConnection connection = new SqlConnection(this);
        String res = connection.AddUser("admin", "123", "admin@gmail.com", "12/12/2023");
        TextView t1 = (TextView) findViewById(R.id.productname1);

        t1.setText(res);

    }


}
