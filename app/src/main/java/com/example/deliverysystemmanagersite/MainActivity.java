package com.example.deliverysystemmanagersite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.example.deliverysystemmanagersite.Test.AppDatabase;
import com.example.deliverysystemmanagersite.Test.User;
import com.example.deliverysystemmanagersite.Test.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    TextView textView;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        textView = (TextView)findViewById(R.id.tv);
        textView.setText("test!!");

        //增删改查都需要在子线程操作
        new Thread(() -> {
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "database-name").build();

            //增加数据测试
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setUid(i);
                user.setFirstName("Shell" + i);
                db.userDao().insertAll(user);
            }

            for (User user : db.userDao().getAll()) {
                System.out.println(user);
            }

        }).start();

    }


}