package com.example.deliverysystemmanagersite.model;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE user_name LIKE :name")
    User findByName(String name);

    @Query("SELECT * FROM user WHERE user_name LIKE :username AND user_psw LIKE :password")
    User checkUser(String username, String password);

    @Query("SELECT * FROM user WHERE uid LIKE :uid")
    User checkUid(int uid);

    @Query("SELECT * FROM user WHERE user_name LIKE :username")
    User checkUsername(String username);

    @Query("SELECT * FROM user WHERE user_email LIKE :email")
    User checkEmail(String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //void insertAll(List<User> users);
    void insertAll(User... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User users);

    @Delete
    void delete(User user);
}
