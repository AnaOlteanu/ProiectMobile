package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.proiect.DB.AppDatabase;
import com.example.proiect.Entities.Income;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class IncomeActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    private ArrayList<Income> incomeList;
    private RecyclerView recyclerView;
    private IncomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.income);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(IncomeActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.profile:
                        Intent intent2 = new Intent(IncomeActivity.this, ProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.expenses:
                        Intent intent4 = new Intent(IncomeActivity.this, ExpensesActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.animation:
                        Intent intent5 = new Intent(IncomeActivity.this, AnimationActivity.class);
                        startActivity(intent5);
                        break;
                }
                return true;
            }
        });


        //recyclerView income
        ;
        recyclerView = findViewById(R.id.recycler_view);
        setIncomeList();
        setAdapter();

    }

    private void setAdapter() {
        adapter = new IncomeAdapter(incomeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setIncomeList() {
        AppDatabase db =  AppDatabase.getInstance(this.getApplicationContext());
        incomeList = new ArrayList<>(db.getIncomesDao().getAllIncomes());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}