package com.example.proiect.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proiect.Entities.Expense;
import com.example.proiect.Entities.Income;

import java.util.List;

@Dao
public interface IncomeDao {

    @Query("select * from income")
    List<Income> getAllIncomes();

    @Insert
    void insertIncome(Income... income);

    @Delete
    void deleteIncome(Income income);
}
