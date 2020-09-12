package com.example.movies_api.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movies_api.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FavoritosActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        bottomNavigator();
    }

    private void bottomNavigator() {
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.item_favoritos);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.item_cartaz:
                        startActivity(new Intent(getApplicationContext(), CartazActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.item_populares:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.item_series:
                        startActivity(new Intent(getApplicationContext(), SeriesActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    default:
                        return true;
                }
            }
        });

    }
}