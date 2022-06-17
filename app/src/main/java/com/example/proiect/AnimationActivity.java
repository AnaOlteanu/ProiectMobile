package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.net.URI;

public class AnimationActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.animation);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        Intent intent2 = new Intent(AnimationActivity.this, ProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.income:
                        Intent intent3 = new Intent(AnimationActivity.this, IncomeActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.expenses:
                        Intent intent4 = new Intent(AnimationActivity.this, ExpensesActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.home:
                        Intent intent5 = new Intent(AnimationActivity.this, MainActivity.class);
                        startActivity(intent5);
                        break;

                }
                return true;
            }
        });


        Button videoButton = findViewById(R.id.videoBtn);
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AnimationActivity.this, VideoActivity.class));
            }
        });



    }
}