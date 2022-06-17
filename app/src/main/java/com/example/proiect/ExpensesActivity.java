package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.proiect.DB.AppDatabase;
import com.example.proiect.Entities.Expense;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class ExpensesActivity extends AppCompatActivity {

    BottomNavigationView navigationView;


    private ArrayList<Expense> expenseList;
    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.expenses);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(ExpensesActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.profile:
                        Intent intent2 = new Intent(ExpensesActivity.this, ProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.income:
                        Intent intent3 = new Intent(ExpensesActivity.this, IncomeActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.animation:
                        Intent intent5 = new Intent(ExpensesActivity.this, AnimationActivity.class);
                        startActivity(intent5);
                        break;
                }
                return true;
            }
        });

        //recyclerView expense
        recyclerView = findViewById(R.id.recycler_view_expense);

        setExpenseList();
        setAdapter();

    }



    private void setExpenseList() {
        AppDatabase db =  AppDatabase.getInstance(this.getApplicationContext());
        expenseList = new ArrayList<>(db.getExpensesDao().getAllExpenses());
    }

    private void setAdapter() {
        adapter = new ExpenseAdapter(expenseList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
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