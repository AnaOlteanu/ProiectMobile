package com.example.proiect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proiect.DB.AppDatabase;
import com.example.proiect.Entities.Expense;
import com.example.proiect.Entities.Income;

public class AddIncomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        EditText incomeTitle = findViewById(R.id.incomeTitle);
        EditText incomeDetails = findViewById(R.id.incomeDetails);
        EditText incomeAmount = findViewById(R.id.incomeAmount);
        Button saveButton = findViewById(R.id.addIncomeButton);
        TextView saveMesaage = findViewById(R.id.saveMessage);
        saveMesaage.setVisibility(TextView.INVISIBLE);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveIncome(incomeTitle.getText().toString(), incomeDetails.getText().toString(),
                        Float.valueOf(incomeAmount.getText().toString()));
                saveMesaage.setVisibility(TextView.VISIBLE);

            }
        });
    }

    private void saveIncome(String title, String details, float amount){
        AppDatabase db =  AppDatabase.getInstance(this.getApplicationContext());
        Income income = new Income();
        income.setIncomeTitle(title);
        income.setIncomeDetails(details);
        income.setAmount(amount);
        income.setUserId(1);
        db.getIncomesDao().insertIncome(income);
    }
}