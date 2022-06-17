package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.proiect.DB.AppDatabase;
import com.example.proiect.Entities.Expense;
import com.example.proiect.Entities.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    ImageView animationImageView;
    Button btAnimation;
    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.home);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        Intent intent2 = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.income:
                        Intent intent3 = new Intent(MainActivity.this, IncomeActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.expenses:
                        Intent intent4 = new Intent(MainActivity.this, ExpensesActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.animation:
                        Intent intent5 = new Intent(MainActivity.this, AnimationActivity.class);
                        startActivity(intent5);
                        break;
                }
                return true;
            }
        });

        //add new Expense
        Button addNewExpenseButton = findViewById(R.id.addExpenseButton);
        addNewExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddExpenseActivity.class));
            }
        });

        //add new Income
        Button addNewIncomeButton = findViewById(R.id.addIncomeButton);
        addNewIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddIncomeActivity.class));
            }
        });

        //add user to db

        AppDatabase db =  AppDatabase.getInstance(this.getApplicationContext());
        if(db.getUsersDao().getAllUsers().isEmpty()) {
            User user = new User();
            user.setLastName("Olteanu");
            user.setFirstName("Ana-Maria");
            db.getUsersDao().insertUser(user);
        }

        //animation object animator
        animationImageView = (ImageView) findViewById(R.id.moneyAnimation);
        btAnimation = (Button) findViewById(R.id.btnmove);
        objectAnimator = objectAnimator.ofFloat(animationImageView,"y", 600);
        btAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objectAnimator.setDuration(3000);
                objectAnimator.start();
            }
        });


    }


}