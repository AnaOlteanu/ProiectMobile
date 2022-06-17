package com.example.proiect.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proiect.Entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("select * from user")
    List<User> getAllUsers();

    @Insert
    void insertUser(User... user);

    @Delete
    void deleteUser(User user);


}
