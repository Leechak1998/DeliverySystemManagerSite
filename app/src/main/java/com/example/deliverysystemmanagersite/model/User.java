package com.example.deliverysystemmanagersite.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "user_psw")
    private String password;

    @ColumnInfo(name = "user_email")
    private String email;

    @ColumnInfo(name = "user_phoneNum")
    private String phoneNum;

    @Ignore
    public User(){}

    public User(String userName, String password, String email, String phoneNum){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String firstName) {
        this.userName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone Number='" + phoneNum + '\'' +
                '}';
    }
}
