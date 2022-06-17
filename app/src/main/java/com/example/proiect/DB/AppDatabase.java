package com.example.proiect.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proiect.DAO.ExpenseDao;
import com.example.proiect.DAO.IncomeDao;
import com.example.proiect.DAO.UserDao;
import com.example.proiect.Entities.Expense;
import com.example.proiect.Entities.Income;
import com.example.proiect.Entities.User;

@Database(entities = {Expense.class, Income.class, User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    private static  AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance == null)
            instance = Room.databaseBuilder(context, AppDatabase.class, "DB_NAME")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        return instance;
    }

    public abstract UserDao getUsersDao();

    public abstract IncomeDao getIncomesDao();

    public abstract ExpenseDao getExpensesDao();


}
