package com.example.deliverysystemmanagersite.controller;

import android.os.Bundle;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.AppDatabase;
import com.example.deliverysystemmanagersite.model.User;
import com.example.deliverysystemmanagersite.model.myDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class DataActivity extends AppCompatActivity {
    AppDatabase db;
    public static myDatabase mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        init();
    }

    public void init(){
        //增删改查都需要在子线程操作


        new Thread(() -> {
//            db = Room.databaseBuilder(getApplicationContext(),
//                    AppDatabase.class, "database-name2").build();
            mdb = new myDatabase(DataActivity.this.getApplicationContext());
            db = mdb.getDb();
            //增加数据测试
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setUid(i);
                user.setUserName("Shell" + i);
                user.setPassword("psw" + i);
                user.setEmail("email" + i);
                user.setPhoneNum("1111");
                db.userDao().insertAll(user);
            }

            for (User user : db.userDao().getAll()) {
                System.out.println(user);
            }

        }).start();

    }
}
