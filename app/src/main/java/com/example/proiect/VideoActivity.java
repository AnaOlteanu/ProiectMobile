package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class VideoActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.animation);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(VideoActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.income:
                        Intent intent3 = new Intent(VideoActivity.this, IncomeActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.expenses:
                        Intent intent4 = new Intent(VideoActivity.this, ExpensesActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.animation:
                        Intent intent5 = new Intent(VideoActivity.this, AnimationActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.profile:
                        Intent intent6 = new Intent(VideoActivity.this, ProfileActivity.class);
                        startActivity(intent6);
                        break;
                }
                return true;
            }
        });


        //video playback

        VideoView videoView = findViewById(R.id.videoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }
}