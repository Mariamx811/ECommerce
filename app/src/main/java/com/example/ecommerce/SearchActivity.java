package com.example.ecommerce;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_search);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slideright, R.anim.slideleft);
                    finish();
                    return true;
                case R.id.bottom_search:
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
    }
}
