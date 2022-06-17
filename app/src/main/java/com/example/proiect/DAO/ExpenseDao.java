package com.example.proiect.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proiect.Entities.Expense;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Query("select * from expense")
    List<Expense> getAllExpenses();

    @Insert
    void insertExpense(Expense... expense);

    @Delete
    void deleteExpense(Expense Expense);
}
