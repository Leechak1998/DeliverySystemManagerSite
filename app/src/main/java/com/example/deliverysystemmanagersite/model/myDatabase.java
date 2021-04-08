package com.example.deliverysystemmanagersite.model;

import android.content.Context;

import androidx.room.Room;

public class myDatabase {
    public AppDatabase db;
    private Context context;

    public myDatabase(Context c){
        this.context = c;
        db = Room.databaseBuilder(context, AppDatabase.class, "database-name-test2").build();
        System.out.println("database created successfully!");
    }

    public AppDatabase getDb(){
        return db;
    }

    public String check(){
        if(db != null)
            return "ok";
        else
            return "false";
    }
}
