package com.example.deliverysystemmanagersite.db;

import com.example.deliverysystemmanagersite.model.User;
import com.example.deliverysystemmanagersite.dao.UserDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

}

