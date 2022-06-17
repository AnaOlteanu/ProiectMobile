package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proiect.DB.AppDatabase;
import com.example.proiect.Entities.Expense;
import com.example.proiect.Entities.Income;
import com.example.proiect.Entities.User;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;
import java.util.stream.Collectors;

public class ProfileActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    ImageView imageView;
    Button btOpen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.profile);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(ProfileActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.income:
                        Intent intent3 = new Intent(ProfileActivity.this, IncomeActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.expenses:
                        Intent intent4 = new Intent(ProfileActivity.this, ExpensesActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.animation:
                        Intent intent5 = new Intent(ProfileActivity.this, AnimationActivity.class);
                        startActivity(intent5);
                        break;
                }
                return true;
            }
        });

        //For opening camera
        imageView = findViewById(R.id.image_view);
        btOpen = findViewById(R.id.bt_open);

        //Request for camera permission
        if(ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.CAMERA}, 100);
        }

        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });

        //fill the text views
        fillProfileInfo();

        //sendIncomes
        Button sendButton = findViewById(R.id.sendIncomesBtn);
        AppDatabase db =  AppDatabase.getInstance(this.getApplicationContext());
        List<Income> incomeList = db.getIncomesDao().getAllIncomes();
        float totalIncome = 0;
        for(Income i: incomeList){
            if(i.getUserId() == 1){
                totalIncome += i.getAmount();
            }
        }
        String incomeAmount = Float.valueOf(totalIncome).toString();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Your total income is " + incomeAmount);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

            }
        });

    }


    private void fillProfileInfo() {
        AppDatabase db =  AppDatabase.getInstance(this.getApplicationContext());
        User user = db.getUsersDao().getAllUsers().get(0);
        TextView firstname = findViewById(R.id.firstname);
        TextView lastname = findViewById(R.id.lastname);
        TextView income = findViewById(R.id.incomeProfile);
        TextView expense = findViewById(R.id.expenseProfile);

        float totalIncome = 0;
        float totalExpense = 0;

        firstname.setText(user.getFirstName());
        lastname.setText(user.getLastName());

        List<Income> incomeList = db.getIncomesDao().getAllIncomes();
        for(Income i: incomeList){
            if(i.getUserId() == 1){
                totalIncome += i.getAmount();
            }
        }

        List<Expense> expenseList = db.getExpensesDao().getAllExpenses();
        for(Expense e : expenseList){
            if(e.getUserId() == 1){
                totalExpense += e.getAmount();
            }
        }

        income.setText(Float.valueOf(totalIncome).toString());
        expense.setText(Float.valueOf(totalExpense).toString());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(capturedImage);
        }
    }



}