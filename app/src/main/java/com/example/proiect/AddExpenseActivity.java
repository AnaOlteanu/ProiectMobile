package com.example.proiect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proiect.DB.AppDatabase;
import com.example.proiect.Entities.Expense;

public class AddExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        EditText expenseTitle = findViewById(R.id.expenseTitle);
        EditText expenseDetails = findViewById(R.id.expenseDetails);
        EditText expenseAmount = findViewById(R.id.expenseAmount);
        Button saveButton = findViewById(R.id.addExpenseButton);
        TextView saveMesaage = findViewById(R.id.saveMessage);
        saveMesaage.setVisibility(TextView.INVISIBLE);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveExpense(expenseTitle.getText().toString(), expenseDetails.getText().toString(),
                        Float.valueOf(expenseAmount.getText().toString()));
                saveMesaage.setVisibility(TextView.VISIBLE);

            }
        });
    }

    private void saveExpense(String title, String details, float amount){
        AppDatabase db =  AppDatabase.getInstance(this.getApplicationContext());
        Expense expense = new Expense();
        expense.setExpenseTitle(title);
        expense.setExpenseDetails(details);
        expense.setAmount(amount);
        expense.setUserId(1);
        db.getExpensesDao().insertExpense(expense);
    }
}